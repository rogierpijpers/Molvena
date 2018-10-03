var urlReservation = "http://localhost:8080/reservation/";
var urlAccount = "http://localhost:8080/account/";

// Only works for 1 reservation per person now
function getSingleReservation(){
	var id = document.querySelector("#inputResId").value;

	if ($.isNumeric(id)){
		fetch(urlReservation + id)
		.then(function(response){
			return response.json();
		})
		.then(function(json){
			document.querySelector("#reservationDisplayText").innerHTML = "";
			document.querySelector("#reservationDisplayText").innerHTML = JSON.stringify(json);
		})
		.catch(error => console.error('Error:', error));
	}

	else if (typeof id === "string"){
		fetch(urlReservation + "user/" + id)
		.then(function(response){
			return response.json();
		})
		.then(function(json){
			document.querySelector("#reservationDisplayText").innerHTML = "";
			document.querySelector("#reservationDisplayText").innerHTML = JSON.stringify(json);		
		})
		.catch(error => console.error('Error:', error));
	}
}

function getAllReservations(){
	fetch(urlReservation)
	.then(function(response){
		return response.json();
	})
	.then(function(json){
		document.querySelector("#allReservationDisplayText").innerHTML = "";
		document.querySelector("#allReservationDisplayText").innerHTML = JSON.stringify(json);
	})	
	.catch(error => console.error('Error:', error));
}

// TODO: Replace with actual rooms
var demoRoom = {
	"roomID": 0,
	"roomType": {
		"singleBeds": 0,
		"doubleBeds": 2
	}
};

function createReservation(){
	var firstName = document.querySelector("input[name='inputFirstNameCreate']").value;
	var lastName = document.querySelector("input[name='inputLastNameCreate']").value;
	var mail = document.querySelector("input[name='inputMailCreate']").value;
	var startDate = new Date(document.querySelector("input[name='inputStartDateCreate']").value);
	var endDate = new Date(document.querySelector("input[name='inputEndDateCreate']").value);
	var amountOfGuests = document.querySelector("input[name='inputAmountOfGuestsCreate']").value;
	var guest = getOwnUsername();

	// Employee only. If GuestId is entered, var guest will become that Guest object.
	var guestId = document.querySelector("input[name='inputGuestIdCreate']").value;
	if (guestId != null){
		fetch(urlAccount + guestId)
		.then(function(response){
			return response.json();
		})
		.then(function(json){
			guest = json;
		})
		.catch(error => console.error("Error:", error));
	}

	var postRequest = { 
		"startDate" : startDate,
		"endDate" : endDate,
		"amountOfGuests" : amountOfGuests,
		"guest": guest,
		"room": demoRoom
	};

	var postRequestStringifyd = JSON.stringify(postRequest);
	fetch(urlReservation, {
		method: "POST",
		body: postRequestStringifyd,
		headers:{
			'Content-Type': 'application/json'
		}
	})
	.catch(error => console.error('Error:', error));
}

function updateReservation(){
	var id = document.querySelector("input[name='inputIdUpdate']").value;
	var startDate = new Date(document.querySelector("input[name='inputStartDateCreate']").value);
	var endDate = new Date(document.querySelector("input[name='inputEndDateCreate']").value);
	var amountOfGuests = document.querySelector("input[name='inputAmountOfGuestsUpdate']").value;
	var guest = getOwnUsername();

	// Employee only. If GuestId is entered, var guest will become that Guest object.
	var guestId = document.querySelector("input[name='inputGuestIdInput']").value;
	if (guestId != null){
		fetch(urlAccount + guestId)
		.then(function(response){
			return response.json();
		})
		.then(function(json){
			guest = json;
		})
		.catch(error => console.error("Error:", error));
	}
	// TODO: make it possible to choose between using current account for reservation
	// or adding ID.
	

	var postRequest = { 
		"startDate" : startDate,
		"endDate" : endDate,
		"amountOfGuests" : amountOfGuests,
		"guest": guest,
		"room": demoRoom
	};

	var postRequestStringifyd = JSON.stringify(postRequest);
	fetch(urlReservation + id, {
		method: "PUT",
		body: postRequestStringifyd,
		headers:{
			'Content-Type': 'application/json'
		}
	})
	.catch(error => console.error('Error:', error));
}

function deleteReservation(){
	var id = document.querySelector("input[name='inputIdDelete']").value;
	fetch(urlReservation + id, {
		method: "DELETE",
	})
}