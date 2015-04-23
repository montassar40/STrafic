var myCenter = new google.maps.LatLng(10, 20)

function initialize() {
	var mapProp = {
		center : myCenter,
		zoom : 7,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};

	var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

	marker = new google.maps.Marker({
		position : myCenter,
		animation : google.maps.Animation.BOUNCE
	});

	marker.setMap(map);
}

google.maps.event.addDomListener(window, 'load', initialize);