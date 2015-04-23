class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
		"/artist/$artistName/$searchIndex"(controller: "artist", action: "index", view:"index")
		"/data"(controller: "data", action: "index", view:"index")
		
		"/genre/$genreName/$searchIndex"(controller: "genre", action: "index", view:"index")
		
        "/"(view:"/index")
        "500"(view:'/error')
	}
}
