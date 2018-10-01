
var userDetailsEndPoint = "localhost:8080";
var urlRoomTypeAvailable = "http://localhost:8080/roomtype/available/";

function getOwnUsername(){
	$.get(userDetailsEndPoint, function(data, status){
		console.log(data);
	});
	// fetch(userDetailsEndPoint)
	// 	.then(function(response){
	// 		console.log(response);
	// 		console.log(response.json);
	// 		console.log(JSON.stringify(response));			
	// 		console.log(JSON.stringify(response.json));
	// 		return response.json();
	// 	})
	// 	.catch(error => console.error("Error: " + error));
}

function getAvailableRoomTypes(){
	// TODO: get input values.
	var startDate = document.querySelector("input[name='inputRoomTypeStartDate']").value;
	var endDate = document.querySelector("input[name='inputRoomTypeEndDate']").value;

	fetch(urlRoomTypeAvailable + startDate + "/" + endDate)
	.then(function(response){
		return response.json();
	})
	.then(function(json){
		console.log("Available roomtypes: " + JSON.stringify(json));
		document.querySelector("#roomDisplayText").innerHTML = "";
		document.querySelector("#roomDisplayText").innerHTML = JSON.stringify(json);
	});
}