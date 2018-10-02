
var userDetailsEndPoint = "localhost:8080";
var urlRoomTypeAvailable = "http://localhost:8080/roomtype/available/";

function getOwnUsername(){
	$.get(userDetailsEndPoint, function(data, status){
		//console.log(data);
		// TODO
	});
}

function getAvailableRoomTypes(){
	// TODO: get input values.
	var startDate = new Date(document.querySelector("input[name='inputRoomTypeStartDate']").value);
	var endDate = new Date(document.querySelector("input[name='inputRoomTypeEndDate']").value);

	fetch(urlRoomTypeAvailable + startDate.toISOString() + "/" + endDate.toISOString())
	.then(function(response){
		return response.json();
	})
	.then(function(json){
		document.querySelector("#roomDisplayText").innerHTML = "";
		document.querySelector("#roomDisplayText").innerHTML = JSON.stringify(json);
	});
}