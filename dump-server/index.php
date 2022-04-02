<!DOCTYPE html>
<html>
  <head>
  
	<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	
	<script type="text/javascript"
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCFlVywfyr1jPDdvOhyP16YMU6fYBXt4Xo&sensor=false">
    </script>
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" type="text/css" href="css/common.css" />
	<link rel="stylesheet" type="text/css" href="css/registration.css" />
	<script src="include/Branch.js" type="text/javascript" ></script>
	<script src="include/googlemap_import.js" type="text/javascript" ></script>
  </head>

  <body>
	<div id="top_nav">
			<ul>
				<li><a href='javascript:view_Main_Form(BRANCHES_CONST)'>BRANCHES</a></li>
				<li><a href='javascript:view_Main_Form(LOGS_CONST)'>LOGS</a></li>
			</ul>
			
			<div id="side_drop">
				<div class="arrow-down" ></div>
			</div>
	</div>
	
	
	<div id="Main_Form_bg"></div>
	<div id="Main_Form">
			<div class='side_button' id='close'></div>
			<h1>Branches   </h1> 
			
			<div class='body'>
				<div class='side_nav'>
					<div class='side_button' id='Branches' 		title='Branches' php='interface/branches.php'></div>
					<div class='side_button' id='logs' 			title='Logs' php='interface/uploads.php'></div>
				</div>
				<div class='content'>
				</div>
			</div>
	</div>
		
    <div id="map-canvas">
	</div>

  </body>
  
</html>