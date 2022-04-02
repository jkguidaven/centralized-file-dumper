
	
	  var geocoder = new google.maps.Geocoder();
	  var arrMarker = new Array();
	  var nMarkerCount = 0;
	  var map;
      function initialize_map() {
		var address = "Philippines";
		geocoder.geocode( { 'address': address}, function(results, status) {
		  if (status == google.maps.GeocoderStatus.OK) {
			var mapOptions = {
			  center: results[0].geometry.location,
			  zoom: 6,
			  mapTypeId: google.maps.MapTypeId.ROADMAP
			};
			map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
			
		  } else {
			alert("Geocode was not successful for the following reason: " + status);
		  }
		});
		
		//addMarker("Cebu, PH","CEB#1", true);
		//addMarker("Manila, PH","MNL#2", false);
      }
	  
	  
      function reinitialize_map() {

		map.setCenter( map.getCenter() , map.getZoom() ,  google.maps.MapTypeId.ROADMAP );
		map.refresh();
	  }
	  
	  function addMarker(address,branchCode,isOnline)
	  {
	 
		geocoder.geocode( { 'address': address}, function(results, status) {
		  if (status == google.maps.GeocoderStatus.OK) {
		    var pinColor = isOnline ? "00FF00" : "FE7569";
		    var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
			new google.maps.Size(21, 34),
			new google.maps.Point(0,0),
			new google.maps.Point(10, 34));
			var pinShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
			new google.maps.Size(40, 37),
			new google.maps.Point(0, 0),
			new google.maps.Point(12, 35));
		
			var marker = new google.maps.Marker({
				map: map,
				position: results[0].geometry.location,
				icon: pinImage,
                shadow: pinShadow,
				BranchCode: branchCode,
				OnlineStatus: isOnline
			});
			
			google.maps.event.addListener(marker, 'click', ShowBranchInfo);
			arrMarker[nMarkerCount++] = marker;
		  } 
		});
	  }
	  
	  function ShowBranchInfo()
	  {
	  }
	  
      google.maps.event.addDomListener(window, 'load', initialize_map);
