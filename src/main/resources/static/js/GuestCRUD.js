
var userDetailsEndPoint = "http://localhost:8080/account";
var urlRoomTypeAvailable = "http://localhost:8080/roomtype/available/";
var currentUser;

function getOwnUsername(){
	var guest;
	fetch(userDetailsEndPoint)
	.then(function(response){
		return response.json();
	})
	.then(function(json){
		currentUser = json;
	})
	.catch(error => console.log('Error', error));

	return currentUser;
}

function getAvailableRoomTypes(){
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