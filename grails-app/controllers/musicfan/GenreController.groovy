package musicfan

class GenreController {
	
	def bbPreURL = "http://api.remix.bestbuy.com/v1/products(customerReviewAverage>3"
	def bbPostURL = ")?show=sku,name,image,url,customerReviewAverage,ReleaseDate,Genre&apiKey=ww7zssb9pd7trg82yy2wpx5m"

	def index() {
		String genre = params.genre
		
		log.info "<<<<<<" + genre
		
		String orginalGenre = genre
		
		log.info "<<<<<<"  + genre
		String[] genreNameList = genre.split(" ")
		StringBuilder sb = new StringBuilder();
		for(String s : genreNameList) {
			sb.append(s).append("%20")
		}
		genre = sb.toString()
//		log.info ">>>>>>>>>>>>>>>>>" + artistName
		
		def bbXML =	bbGenreSearch(genre, 15)
		def bbresults = [];
//		log.info ">>>>>>>>>>>>>>>>>" + bbXML
		
		bbXML?.product?.each {
			
			def result = [:]
			result.put("img", it.image)
			result.put("review", it.customerReviewAverage)
			result.put("releaseDate", it.releaseDate)
			result.put("sku", it.sku)
			result.put("name", it.name.toString().substring(0, Math.min(20, it.name.toString().length()-1)))
			result.put("url", it.url);
			bbresults.add(result)
		}
		
		if(params.type == 'json') {
			render(contentType: "text/json") {
				[genre: orginalGenre, bbresults : bbresults, twitter: "https://twitter.com/search?q=%40"+genre ]
			}
		}
		
		return [genre: orginalGenre, bbresults : bbresults, twitter: "https://twitter.com/search?q=%40"+genre ]
	}
	
	def bbGenreSearch(def genre, def pageSize) {
		//Setup a connection to pull data in with REST
		def url = new URL(bbPreURL + "&genre=" + genre + bbPostURL + "&pageSize=" + pageSize)
		def connection = url.openConnection()
		connection.setRequestMethod("GET")
		connection.connect()
		def returnMessage = ""
		def records
//		log.info "<<<<<<<<<<<<<<<<" + url
		
//		log.info "<<<<<<<<<<<<<<<<" + connection.responseCode
		if (connection.responseCode == 200 || connection.responseCode == 201){
		  returnMessage = connection.content.text
//		  log.info ">>>>>>>>>>>>>>>>>" + returnMessage
		  
		   records= new XmlSlurper().parseText(returnMessage)
		  
		} else {
		  println "Error Connecting to " + url
		}
		return records
	}
}
