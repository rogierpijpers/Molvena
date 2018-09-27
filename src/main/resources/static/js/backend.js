//var urlReservation = "http://localhost:8080/reservation/";
var urlReservation = "http://www.mocky.io/v2/5bacbc4633000075000eb2f0";
var urlAccount = "http://localhost:8080/account/";

function getSingleReservation(){
	var id = document.querySelector("#inputResId").value;
	console.log("Getting reservation for id " + id);
	fetch(urlReservation + id)
	//fetch(urlReservation + reservationInput)
	.then(function(response){
		return response.json();
	})
	.then(function(json){
		console.log(JSON.stringify(json));
		document.getElementById("reservationDisplayText").innerHTML = JSON.stringify(json);
		// TODO: display in html
	});
}

function getAllReservations(){
	console.log("Getting all reservations");
	fetch(urlReservation)
	.then(function(response){
		return response.json();
	})
	.then(function(json){
		console.log(JSON.stringify(json));
		// TODO: display in html
		document.querySelector("#reservationDisplayText").innerHTML = JSON.stringify(json);
	});
}

function createReservation(){
	var firstName = document.querySelector("#inputFirstNameCreate").value;
	var lastName = document.querySelector("#inputFirstNameCreate").value;
	var mail = document.querySelector("#inputMailCreate").value;
	var startDate = document.querySelector("#inputStartDateCreate").value;
	var endDate = document.querySelector("#inputEndDateCreate").value;
	var amountOfGuests = document.querySelector("#inputAmountOfGuestsCreate").value;
	
	// TODO: guest, room and roomtype objects
	//var guest = document.querySelector("").value;
	//var room = document.querySelector("#inputRoomCreate").value;
	//var roomType = document.querySelector("#inputRoomTypeCreate").value;

	var reservationJSON = "{startDate: " + startDate + ", endDate: " + endDate + ", amountOfGuests: " + amountOfGuests + "}";

	console.log("Creating reservation with json: " + reservationJSON);
	var newReservation = JSON.parse(reservationJSON);

	fetch(urlReservation, {
				method: "POST",
				body: JSON.stringify(reservationJSON),
				headers:{
		    'Content-Type': 'application/json'
		  }
		}).then(res => res.json())
		.then(response => console.log('Success:', JSON.stringify(response)))
		.catch(error => console.error('Error:', error));
}

function updateReservation(){
	var id = document.querySelector("#inputIdUpdate").value;
	var startDate = document.querySelector("#inputStartDateUpdate").value;
	var endDate = document.querySelector("#inputEndDateUpdate").value;
	var amountOfGuests = document.querySelector("#inputAmountOfGuestsUpdate").value;

	// TODO: guest, room and roomtype objects
	var guest = document.querySelector("").value;
	var room = document.querySelector("#inputRoomUpdate").value;
	var roomType = document.querySelector("#inputRoomTypeUpdate").value;

	var reservationJSON = "{ id: " + id + ", startDate: " + startDate + ", endDate: " + endDate + ", amountOfGuests: " + amountOfGuests + "}";

	console.log("Updating reservation with id " + id);
	fetch(urlReservation + id, {
		method: "PUT",
		body: JSON.stringify(reservationJSON),
		headers:{
		    'Content-Type': 'application/json'
		  }
	})
	.then(response => response.json());
}

function deleteReservation(){
	var id = document.querySelector("#inputResId").value;
	console.log("Deleting reservation with id " + id);
	fetch(urlReservation + id, {
	    method: "DELETE",
	  })
	  .then(response => response.json());
}




function getGuestById(reservationId){

}

function getGuestByName(clientName){

}

function getAllGuests(){
}

function createGuest(){

}

function updateGuest(){

}

function deleteGuest(){

}

