//var urlReservation = "http://localhost:8080/reservation/";
var urlReservation = "http://localhost:8080/reservation/";
var urlAccount = "http://localhost:8080/account/";

// Only works for 1 reservation per person now
function getSingleReservation(){
	var id = document.querySelector("#inputResId").value;

	if ($.isNumeric(id)){
		console.log("is numeric");
		fetch(urlReservation + id)
		.then(function(response){
				return response.json();
			})
		.then(function(json){document.getElementById("reservationDisplayText").innerHTML = JSON.stringify(json);
			});
		}

	else if (typeof id === "string"){
	console.log("input is string");
	fetch(urlReservation)
	.then(function(response){
		return response.json();
	})
	.then(function(json){
				// Loop through JSON and find all reservations with name
				for (reservations in json){
					if (id == json[reservations]["guest"]["mail"]){
						// Display Reservation
						document.getElementById("reservationDisplayText").innerHTML = JSON.stringify(json[reservations]);
						return json;
					}
				}			
			});
	}
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
		document.querySelector("#allReservationDisplayText").innerHTML = JSON.stringify(json);
		//prettifyJSON(json, "#");
	});
}

var demoRoom = {
			"roomID": 0,
			"roomType": {
				"singleBeds": 0,
				"doubleBeds": 2
			}
		};

var demoRoomType = {
			"singleBeds": 0,
			"doubleBeds": 2
		};

function createReservation(){
	var firstName = document.querySelector("input[name='inputFirstNameCreate']").value;
	var lastName = document.querySelector("input[name='inputLastNameCreate']").value;
	var mail = document.querySelector("input[name='inputMailCreate']").value;
	//var startDate = document.querySelector("input[name='inputStartDateCreate']").value;
	//var endDate = document.querySelector("input[name='inputEndDateCreate']").value;
	var startDate = "1969-12-31T23:59:58.029+0000"; 
	var endDate = "1979-12-31T23:59:58.029+0000";
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
		"room": demoRoom,
		"roomType": demoRoomType
	};

	var postRequestStringifyd = JSON.stringify(postRequest);
	console.log("Creating reservation with json: " + postRequestStringifyd);
	fetch(urlReservation, {
		method: "POST",
		body: postRequestStringifyd,
		headers:{
			'Content-Type': 'application/json'
		}
	})
	.then(response => console.log('Succes:', JSON.stringify(response)))
	.catch(error => console.error('Error:', error));
}

function updateReservation(){
	var id = document.querySelector("input[name='inputIdUpdate']").value;
	//var firstName = document.querySelector("input[name='inputFirstNameUpdate']").value;
	//var lastName = document.querySelector("input[name='inputLastNameUpdate']").value;
	//var mail = document.querySelector("input[name='inputMailUpdate']").value;
	//var startDate = document.querySelector("input[name='inputStartDateCreate']").value;
	//var endDate = document.querySelector("input[name='inputEndDateCreate']").value;
	startDate = "1969-12-31T23:59:58.029+0000"; 
	endDate = "1979-12-31T23:59:58.029+0000";
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
		"room": demoRoom,
		"roomType": demoRoomType
	};

	var postRequestStringifyd = JSON.stringify(postRequest);
	console.log("Updating reservation with json: " + postRequestStringifyd);
	fetch(urlReservation + id, {
		method: "PUT",
		body: postRequestStringifyd,
		headers:{
			'Content-Type': 'application/json'
		}
	})
	.then(response => console.log('Succes:', JSON.stringify(response)))
	.catch(error => console.error('Error:', error));
}

function deleteReservation(){
	var id = document.querySelector("input[name='inputIdDelete']").value;
	console.log("Deleting reservation with id " + id);
	fetch(urlReservation + id, {
		method: "DELETE",
	})
}