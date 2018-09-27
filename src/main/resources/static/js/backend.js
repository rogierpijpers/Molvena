//var urlReservation = "http://localhost:8080/reservation/";
var urlReservation = "http://localhost:8080/reservation/";
var urlAccount = "http://localhost:8080/account/";

function getSingleReservationByString(id){
	fetch(urlReservation)
	.then(function(response){
		return response.json();
	})
	.then(function(json){
				// Loop through JSON and find all reservations with name
				for (reservations in json){
					if (id == json[reservations]["guest"]["mail"]){
						//id = json[reservations]["reservationID"];
						//document.getElementById("reservationDisplayText").innerHTML = JSON.stringify(json[reservations]);
						prettifyJSON(json, "#reservationDisplayText");
						return json;
					}
				}			
			});
}

function getSingleReservationById(id){
	fetch(urlReservation)
	//fetch(urlReservation + id)
	.then(function(response){
		return response.json();
	})
	.then(function(json){
		for (reservations in json){
			if (id == json[reservations]["reservationID"]){
						//document.getElementById("reservationDisplayText").innerHTML = JSON.stringify(json[reservations]);
						prettifyJSON(json, "#reservationDisplayText");
						return json;
					}
				}	
			});
}

// Only works for 1 reservation per person now
function getSingleReservation(){
	var id = document.querySelector("#inputResId").value;

	if ($.isNumeric(id)){
		console.log("input is int, id stays "+ id);
		getSingleReservationById(id);
	}

	else if (typeof id === "string"){
		console.log("input is string");
		getSingleReservationByString(id);
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

function createReservation(){
	var firstName = document.querySelector("input[name='inputFirstNameCreate']").value;
	var lastName = document.querySelector("input[name='inputLastNameCreate']").value;
	var mail = document.querySelector("input[name='inputMailCreate']").value;
	//var startDate = document.querySelector("input[name='inputStartDateCreate']").value;
	//var endDate = document.querySelector("input[name='inputEndDateCreate']").value;
	startDate = "1969-12-31T23:59:58.029+0000"; 
	endDate = "1979-12-31T23:59:58.029+0000";
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
					"room": {
						"roomID": 0,
						"roomType": {
							"singleBeds": 0,
							"doubleBeds": 2
						}
					},
					"roomType": {
						"singleBeds": 0,
						"doubleBeds": 2
					}
};

	var postRequestStringifyd = JSON.stringify(postRequest);
	console.log("Creating reservation with json: " + postRequestStringifyd);
	fetch(urlReservation, {
		method: "POST",
		body: postRequestStringifyd,
		headers:{
			'Content-Type': 'application/json'
		}
	})//.then(res => res.json())
	.then(response => console.log('Succes:', JSON.stringify(response)))
	.catch(error => console.error('Error:', error));
}

function updateReservation(){
	var id = document.querySelector("input[name='inputIdUpdate']").value;
	íf (typeof id === "string"){
		//	get
	}
	else if ($.isNumeric(id)){

	}

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

