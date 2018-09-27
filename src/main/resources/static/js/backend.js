// window.onload=console.log("ready");
// //window.onload=toggleItems();
// var parentForm = document.querySelector("#mainForm");
// parentForm.addEventListener("click", callRelevantFunction, false);

// // Calls the button function related to the clicked button.
// // The name is retrieved from the 'name' of the input field, so make sure that name and the function name is equal.
// function callRelevantFunction(e){
// 	if(e.target !== e.currentTarget){
// 		var clickedItem = e.target.name;
// 		console.log(clickedItem + " has been clicked");
// 		window[clickedItem]();
// 	}
// 	e.stopPropagation();
// }

//var urlReservation = "http://localhost:8080/reservation/";
var urlReservation = "http://demo7061046.mockable.io/";
var urlAccount = "http://localhost:8080/account/";

function getSingleReservation(){
	console.log("Getting reservation");
	fetch(urlReservation)
	//fetch(urlReservation + reservationInput)
	.then(function(response){
		return response.json();
	})
	.then(function(json){
		console.log(JSON.stringify(json));
		document.getElementById("reservationDisplayText").innerHTML = json;
		// TODO: display in html
	});
}

function getAllReservations(){
fetch(urlReservation + "/all")
	.then(function(response){
		return response.json();
	})
	.then(function(json){
		console.log(JSON.stringify(json));
		// TODO: display in html
		document.getElementById("reservationDisplay").innerHTML = json;
	});
}

function createReservation(startDate, endDate, guest, amountOfGuests, room, roomType){
	// TODO: add guestid, room, roomtype
	var reservationJSON = "{startDate: " + startDate + ", endDate: " + endDate + ", guest: " +  guest 
	+ ", amountOfGuests: " + amountOfGuests + ", room: " + room + ", roomType: " + roomType + "}";

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

function updateReservation(reservationId){
	fetch(urlReservation + reservationId, {
		method: "PUT",
	})
	.then(response => response.json());
}

function deleteReservation(reservationId){
	console.log("Deleting reservation with id " + reservationId);
	fetch(urlReservation + reservationId, {
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

