package musicfan


import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

import org.w3c.dom.Document
import org.w3c.dom.Node

import utils.SignedRequestsHelper


class ArtistController {
	def bbPreURL = "http://api.remix.bestbuy.com/v1/products(customerReviewAverage>3"
	def bbPostURL = ")?show=sku,name,image,url,customerReviewAverage,ReleaseDate,Genre&apiKey=ww7zssb9pd7trg82yy2wpx5m"
	
	private static final String AWS_ACCESS_KEY_ID = "AKIAJPBDKFV7TJZ4INIQ";
	
		/*
		 * Your AWS Secret Key corresponding to the above ID, as taken from the AWS
		 * Your Account page.
		 */
	private static final String AWS_SECRET_KEY = "/KcmpHwQMz5GbsNLXcLywjtoBBGyl7yT8Xz1rRhO";
	private static final String ENDPOINT="ecs.amazonaws.com" ;
	
	def index() {
		String artistName = params.artistName
		def searchIndex = params.searchIndex
		String orginalName =artistName
		String[] artistNameList = artistName.split(" ")
		StringBuilder sb = new StringBuilder();
		for(String s : artistNameList) {
			sb.append(s).append("%20")
		}
		artistName = sb.toString()
//		log.info ">>>>>>>>>>>>>>>>>" + artistName
		
		def bbXML =	bbProductSearch(artistName, 4)
		def bbresults = [];
//		log.info ">>>>>>>>>>>>>>>>>" + bbXML
		
		bbXML?.product?.each {
			
			def result = [:]
			result.put("img", it.image)
			result.put("review", it.customerReviewAverage)
			result.put("releaseDate", it.releaseDate)
			result.put("sku", it.sku)
			result.put("name", it.name)
			bbresults.add(result)
		}
		def amazonXML = searchItems(amazonItemSearch(artistName, searchIndex))
		def amazonItems = []
		int index = 0;
		amazonXML?.Items?.Item?.each {
			if(it.ItemLinks && index<4) {
				def amazonItem = [:]
				amazonItem.put("ASIN", it.ASIN);
				amazonItem.put("DetailPageURL", it.DetailPageURL)	
				amazonItem.putAt("Name", it.ItemAttributes.Title.toString().substring(0, Math.min(30, it.ItemAttributes.Title.toString().length()-1)))
				
				it.ItemLinks.ItemLink.each {
					if(it.Description.equals("Add To Wishlist")) {
						amazonItem.put("WishList", it.URL)
					}
					if(it.Description.equals("All Offers")) {
						amazonItem.put("Offers", it.URL)
					}
					
				}
				amazonItem = matchAmazonImage(amazonItem, it.ASIN.toString());
				amazonItem = matchAmazonReviews(amazonItem, it.ASIN.toString());
				amazonItem = matchAmazonOffers(amazonItem, it.ASIN.toString());
				
				
				index++;
				amazonItems.add(amazonItem);
			}
		}
		return [artistName: orginalName, bbresults : bbresults,  amazonItems : amazonItems]
	}
	
	def matchAmazonImage(def amazonItem, def ItemId) {
		
		def amazonXML = searchItems(amazonItemLookup(ItemId, "Images"))
		amazonItem.putAt("Image", amazonXML.Items.Item.MediumImage.URL)
		
		return amazonItem
	}
	
	def matchAmazonReviews(def amazonItem, def ItemId) {
		
		def amazonXML = searchItems(amazonItemLookup(ItemId, "Reviews"))
		amazonItem.putAt("Reviews", amazonXML.Items.Item.CustomerReviews.IFrameURL)
		
		return amazonItem
	}
	
	def matchAmazonOffers(def amazonItem, def ItemId) {
		
		def amazonXML = searchItems(amazonItemLookup(ItemId, "Offers"))
		def keyOffers = [:]
		keyOffers.putAt("LowestNewPrice", amazonXML.Items.Item.OfferSummary.LowestNewPrice.FormattedPrice)
		keyOffers.putAt("LowestUsedPrice", amazonXML.Items.Item.OfferSummary.LowestUsedPrice.FormattedPrice)
		keyOffers.putAt("LowestCollectiblePrice", amazonXML.Items.Item.OfferSummary.LowestCollectiblePrice.FormattedPrice)
		
		amazonItem.putAt("keyOffers", keyOffers)
		
		return amazonItem
	}
	
	def amazonItemLookup(def ItemId, def ResponseGroup) {
		SignedRequestsHelper helper;
		try {
			helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		String requestUrl = null;
		String title = null;

		/* The helper can sign requests in two forms - map form and string form */
		
		/*
		 * Here is an example in map form, where the request parameters are stored in a map.
		 */
		Map<String, String> param = new HashMap<String, String>();
		//new added associate tag
		param.put("AssociateTag","musicp00-20");
		param.put("Service", "AWSECommerceService");
		//params.put("Version", "2009-03-31"); //the default oldest version API
		param.put("Version", "2013-08-01");
		param.put("Operation", "ItemLookup");

		//params.put("Operation", "ItemSearch");
		//params.put("ItemId", ITEM_ID);
		param.put("ItemId", ItemId);
		//params.put("Artist","OneRepublic");
		//params.put("Keywords","OneRepublic");
	   // params.put("SearchIndex", "Blended");
		
		param.put("ResponseGroup", ResponseGroup);

		requestUrl = helper.sign(param);
//		System.out.println("Signed Request is \"" + requestUrl + "\"");

		
		return requestUrl
	}
	
	def amazonItemSearch(def artistName, def searchIndex) {
		/*
		 * Set up the signed requests helper
		 */
		SignedRequestsHelper helper;
		
		try {
			helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		String requestUrl = null;
		String title = null;

		/* The helper can sign requests in two forms - map form and string form */
		
		/*
		 * Here is an example in map form, where the request parameters are stored in a map.
		 */
		System.out.println("Map form example:");
		Map<String, String> param = new HashMap<String, String>();
		//new added associate tag
		param.put("AssociateTag","musicp00-20");
		param.put("Service", "AWSECommerceService");
		param.put("Version", "2013-08-01");

		param.put("Operation", "ItemSearch");
		param.put("Keywords",artistName);
		param.put("SearchIndex", searchIndex); //Note search index can be in the range of Fashion,Music, Books,MusicTracks,DVD,DigitalMusic,Beauty,Apparel
		
		//params.put("ResponseGroup", "Reviews");

		requestUrl = helper.sign(param);
		log.info "Signed Request is \"" + requestUrl + "\"";

		return requestUrl
		
	}
	
	def searchItems(String requestUrl) {

		def connection = new URL(requestUrl).openConnection()
		connection.setRequestMethod("GET")
		connection.connect()
		def returnMessage = ""
		def records
		
		if (connection.responseCode == 200 || connection.responseCode == 201){
			returnMessage = connection.content.text
			 records= new XmlSlurper().parseText(returnMessage)
		} else {
		  println "Error Connecting to " + url
		}
		log.info "<<<<<<<<<<<<<<<<" + returnMessage
		return records
	}
	
	/*
	 * Utility function to fetch the response from the service and extract the
	 * title from the XML.
	 */
	private static String fetchTitle(String requestUrl) {
		String title = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(requestUrl);
			Node titleNode = doc.getElementsByTagName("Title").item(0);
			title = titleNode.getTextContent();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return title;
	}
	def bbProductSearch(def artistName, def pageSize) {
		//Setup a connection to pull data in with REST
		def url = new URL(bbPreURL + "&artistName=" + artistName + bbPostURL + "&pageSize=" + pageSize)
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

