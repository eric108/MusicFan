<!DOCTYPE html>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>MusicFan</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="${resource(dir:'css',file:'bootstrap.min.css')}" />
    <link rel="stylesheet" href="${resource(dir:'css',file:'jumbotron.css')}" />
	<script type="text/javascript" src="${resource(dir:'js',file:'bootstrap.min.js')}?color=FA8DFF">
</script>
<script type="text/javascript" src="${resource(dir:'js',file:'musicfan.js')}?color=FA8DFF">
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">MusicFan</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <form class="navbar-form navbar-right">
            <div class="form-group">
              <input type="text" placeholder="Email" class="form-control">
            </div>
            <div class="form-group">
              <input type="password" placeholder="Password" class="form-control">
            </div>
            <button type="submit" class="btn btn-success">Sign in</button>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <p>We aim to build such platform that saves music fans time while busying switching from one webpage to another or one app to another. Our goal is to bridge the gap with integration view of the most related and updated multimedia that presents the user’s query from different channels intelligently. </p>
        <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more &raquo;</a></p>
      </div>
    </div>
    
    <div class="container">
			<div class="row">
				<div class="navbar navbar-inverse" role="navigation">

				    <div class="collapse navbar-collapse navbar-ex1-collapse">
				    	<div class="col-sm-6 col-md-6">
					    	<form class="navbar-form" role="search" method="get" id="search-form" name="search-form" action="/">
					        	<div class="btn-group pull-left" style="margin-right:10px;">
									<select id="selectSearch" class="input-group">
									  <option value="artist">artist</option>
									  <option value="song">song</option>
									</select>	
								</div>
								
						        <div class="input-group">
						            <input type="text" class="form-control" placeholder="Linken Park or my heart will go on" id="inputField" name="query" value="">
							            <div class="input-group-btn">
						            <button type="submit" id="submitButton" class="btn btn-success" onclick="goToResults()">
						            <span class="glyphicon glyphicon-search"></span>
						            </button>
						            </div>
						        </div>
					        </form>
				        </div>
				    </div>
				</div>				
			</div>	
		</div>

 	</body>
</html>