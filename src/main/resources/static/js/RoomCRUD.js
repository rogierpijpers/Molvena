/* RoomCRUD (Employee-only)
	- getRoom.
	- getAllRooms
	- updateRoom
*/

var urlRoom = "http://localhost:8080/room/";
var urlRoomType = "http://localhost:8080/roomtype/";
var urlRoomTypeAvailable = "http://localhost:8080/roomtype/available/";
var urlRoomTypeAvailable = "http://localhost:8080/room/available/";

function getAvailableRoomTypes(){
	// TODO: get input values.
	var startDate = "";
	var endDate = "";

	fetch(urlRoomTypeAvailable + startDate + "/" + endDate)
	.then(function(response){
		console.log("Available roomtypes: " + response.json);
		return response.json();
	});
}

function getAvailableRooms(){
	// TODO: get input values.
	var startDate = "";
	var endDate = "";

	fetch(urlRoomAvailable + startDate + "/" + endDate)
	.then(function(response){
		console.log("Available rooms: " + response.json);
		return response.json();
	});
}

function getAllRooms(){
	fetch(urlRoom)
	.then(function(response){
		console.log("All rooms: " + response.json);
		return response.json();
	});
}

function updateRoom(){

}
