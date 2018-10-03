var urlRoom = "http://localhost:8080/room/";
var urlRoomType = "http://localhost:8080/roomtype/";
var urlRoomAvailable = "http://localhost:8080/room/available/";

function getAvailableRooms(){
	var startDate = new Date(document.querySelector("input[name='inputRoomStartDate']").value);
	var endDate = new Date(document.querySelector("input[name='inputRoomEndDate']").value);

	fetch(urlRoomAvailable + startDate.toISOString() + "/" + endDate.toISOString())
	.then(function(response){
		return response.json();
	})
	.then(function(json){
		document.querySelector("#roomDisplayText").innerHTML = "";
		document.querySelector("#roomDisplayText").innerHTML = JSON.stringify(json);
	})
	.catch(error => console.error("Error:", error));
}

function getAllRooms(){
	fetch(urlRoom)
	.then(function(response){
		return response.json();
	})
	.then(function(json){	
		document.querySelector("#roomDisplayText").innerHTML = "";
		document.querySelector("#roomDisplayText").innerHTML = JSON.stringify(json);
	})
	.catch(error => console.error("Error:", error));
}

function updateRoom(){
	var roomToEdit = document.querySelector("input[name='inputRetrieveRoom']").value;
	var roomId = document.querySelector("input[name='inputRoomIdUpdate']").value;
	var roomType = document.querySelector("input[name='inputRoomType']").value;
	var request = {
		"roomId" : roomId,
		"roomType" : roomType
	};

	var requestStringifyd = JSON.stringify(request);

	fetch(urlRoom, {
		method: "PUT",
		body: requestStringifyd,
		headers: {
			"Content-Type": "application/json"
		}
	})
	.catch(error => console.error('Error:', error));
}
