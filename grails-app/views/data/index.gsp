<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Recommendation</title>
       	<script type="text/javascript" src="${resource(dir:'js',file:'d3.min.js')}"></script>
        
    </head>
    <style>
    
    body  {
    	color: #659CC9;
    	font-family: Curlz MT;
        background-image: url("http://winnipegweddingmusic.com/images/Guitar-HD-Wallpaper-5.jpg");
        background-color: black;
        background-repeat: no-repeat;
    }
    </style>
    <h1 >Hello There, Look what we found for you! </h1>
    
    <body>
       	
    	<a href="/recommend/">Click me</a>
        <script type="text/javascript">
        //function need to implemented: call the back in the background to generate the json file
        
        
       	 //Width and height
			var w = 1500;
			var h = 400;
			
		
		
			//Create SVG element
			var svg = d3.select("body")
						.append("svg")
						.attr("width", w)
						.attr("height", h);
						/*
			var greeting =  d3.select("body")
						.append("h1")
						.attr("width", w)
						.attr("height", h);
						*/
			//set background
			svg.append("defs")
			   .append("pattern")
			   .attr("id", "bg")
			   .append("image")
			   .attr("xlink:href", "http://7-themes.com/data_images/out/55/6957527-photography-guitar-music.jpg");
			
			svg.append("rect")
			   .attr("fill", "url(#bg)");
			   
			   						
		
            d3.text("${grailsApplication.mainContext.getResource("json_item_show.text").file}", function(error,data) { 
            	if (error) {  
            	console.log(error); 
				} 
				else {
				console.log(data.UID); 
				}
            	
            	dataset = data.UID;
            	
            	var force = d3.layout.force()
								 .nodes(dataset)
								 .size([w, h])
								 .charge([-1000])
								 .start();
           				           	
            	var gnodes = svg.selectAll('g.gnode')
								.data(dataset)
								.enter()
								.append('g')
								.classed('gnode', true);
	   							//.on("click", showdata);
   								    
            	var nodes = gnodes.append("svg:a")
            	  .attr("xlink:href", function(d){return d.Url;})				
            	  .call(force.drag);
				
						    	  
		    	var img = nodes.append("image")
		    	.attr("xlink:href", function(d){ return d.Thumbnail;})
		    	.attr("alt","image")
		    	.classed("thumbnails",true)
		    	.attr("x", -30)
		    	.attr("y", -30)
		    	.attr("width", 100)
		    	.attr("height",100)
		    	.on("mouseover", function(d) {
		    				var h1 = d3.select('h1');
		    				     h1.text("Look what we found similar to item "+d.ItemID);
		    				     h1.style('color','#659CC9');		//d3.select("h1")
		    				
		    				var div = d3.select("body")
		    							.selectAll('div')
		    							.data(dataset)
		    							.enter()
		    							.append("div");
		    									    									    					
		    				var recommend = div
		    						 .style({"position": "relative", "top": "400", "left": function (d) {return ((Math.log(d.ItemID)+Math.random()*1000).toString()+"px")}, "width": function (d) {return ((Math.log(d.ItemID)+Math.random()*10000).toString()+"px")}," height": function (d) {return ((Math.log(d.ItemID)+Math.random()).toString()+"px")}});
		    						 //.attr("onclick","window.location= function(d){return (d.Url)	};")	 fix this later.
		    							    						 		 
		    				var img_similar = recommend.append("img")
		    				.attr("src", function(d){ return d.Thumbnail;})
		    				.attr("alt","image")
		    				.classed("thumbnails",true)
		    				.attr("width", 70)
		    				.attr("height", 70);
		    				
		    						 
		    	
		    	})
		    	.on("mouseout", function(d) {
					    	var h1 = d3.select('h1');
					    	     h1.text("Hello There, Look what we found for you! ");
					    	     h1.style('color','#659CC9');		//d3.select("h1")
		    				var div = d3.selectAll('div').remove()
		    							
		    									    	});
		    	  
			    								 			 		 
			    force.on("tick", function() {
					gnodes.attr("transform", function(d) {
							return 'translate(' + [d.x, d.y] + ')';
							});
						
					});
			});
				         	
				
		
			
        </script>
    </body>
</html>