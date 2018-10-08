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
	var postRequest = { 
		"startDate" : startDate,
		"endDate" : endDate,
		"amountOfGuests" : amountOfGuests,
		"guest": {
			"firstName": firstName,
			"lastName": lastName,
			"password": "$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2",
			"dateOfBirth": "1969-12-31T23:59:58.029+0000",
			"mail": mail,
			"phone": "123456789",
			"address": "Straat 1",
			"city": "Veldhoven",
			"zipCode": "5555LL",
			"country": "NL",
			"role": "ROLE_GUEST"
		},
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
	var postRequest = { 
		"startDate" : startDate,
		"endDate" : endDate,
		"amountOfGuests" : amountOfGuests,
		"guest": {
			"firstName": "Jan",
			"lastName": "van Dijk",
			"password": "$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2",
			"dateOfBirth": "1969-12-31T23:59:58.029+0000",
			"mail": "Jan@vandijk.nl",
			"phone": "123456789",
			"address": "Straat 1",
			"city": "Veldhoven",
			"zipCode": "5555LL",
			"country": "NL",
			"role": "ROLE_GUEST"
		},
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