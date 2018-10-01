var urlAccount = "http://localhost:8080/guest/";

function getSingleAccount(){	
	var id = document.querySelector("#inputAccountId").value;

	if ($.isNumeric(id)){
		fetch(urlAccount + id)
		.then(function(response){
			return response.json();
		})
		.then(function(json){
			document.querySelector("#accountDisplayText").innerHTML = "";
			document.querySelector("#accountDisplayText").innerHTML = JSON.stringify(json);
		})
		.catch(error => console.error("Error: " + error));
	}
	else{
		console.log(urlAccount + id);
		fetch(urlAccount + id)
		.then(function(response){
			return response.json();
		})
		.then(function(json){
			document.querySelector("#accountDisplayText").innerHTML = "";
			document.querySelector("#accountDisplayText").innerHTML = JSON.stringify(json);
		})
		.catch(error => console.error("Error:", error));
	}
}

function getAllAccounts(){
	console.log("get all acc3");
	fetch(urlAccount)
	.then(function(response){
		return response.json();
	})
	.then(function(json){
		console.log(JSON.stringify(json));
		document.querySelector("#allAccountDisplayText").innerHTML = "";
		document.querySelector("#allAccountDisplayText").innerHTML = JSON.stringify(json);
	})
	.catch(error => console.error("Error:", error));

}

function createAccount(){
	var firstName = document.querySelector("input[name='inputFirstNameCreateAccount']").value;
	var lastName = document.querySelector("input[name='inputLastNameCreateAccount']").value;
	var password = document.querySelector("input[name='inputPasswordCreateAccount']").value;
	var mail = document.querySelector("input[name='inputMailCreateAccount']").value;
	var dob = document.querySelector("input[name='inputDobCreateAccount']").value;
	var phone = document.querySelector("input[name='inputPhoneCreateAccount']").value;
	var address = document.querySelector("input[name='inputAddressCreateAccount']").value;
	var city = document.querySelector("input[name='inputCityCreateAccount']").value;
	var zip = document.querySelector("input[name='inputZipCreateAccount']").value;
	var country = document.querySelector("input[name='inputCountryCreateAccount']").value;

	var postRequest = {
		"firstName" : firstName,
		"lastName" : lastName,
		"password" : password,
		"dateOfBirth" : dob,
		"mail" : mail,
		"phone" : phone,
		"address" : address,
		"city" : city,
		"zipCode" : zip,
		"country" : country,
		"role" : "ROLE_GUEST"
	};

	var postRequestStringifyd = JSON.stringify(postRequest);
	console.log("Creating account with json: " + postRequestStringifyd);
	fetch(urlAccount, {
		method: "POST",
		body: postRequestStringifyd,
		headers:{
			"Content-Type": "application/json"
		}
	})
	.then(response => console.log("Succes: ", JSON.stringify(response)))
	.catch(error => console.error('Error:', error));
}

function updateAccount(){
	var originalMail = document.querySelector("input[name='inputMailRetrieveAccount']").value;
	var firstName = document.querySelector("input[name='inputFirstNameUpdateAccount']").value;
	var lastName = document.querySelector("input[name='inputLastNameUpdateAccount']").value;
	var password = document.querySelector("input[name='inputPasswordUpdateAccount']").value;
	var newMail = document.querySelector("input[name='inputMailUpdateAccount']").value;
	var dob = document.querySelector("input[name='inputDobUpdateAccount']").value;
	var phone = document.querySelector("input[name='inputPhoneUpdateAccount']").value;
	var address = document.querySelector("input[name='inputAddressUpdateAccount']").value;
	var city = document.querySelector("input[name='inputCityUpdateAccount']").value;
	var zip = document.querySelector("input[name='inputZipUpdateAccount']").value;
	var country = document.querySelector("input[name='inputCountryUpdateAccount']").value;

	var postRequest = {
		"firstName" : firstName,
		"lastName" : lastName,
		"password" : password,
		"dateOfBirth" : dob,
		"mail" : newMail,
		"phone" : phone,
		"address" : address,
		"city" : city,
		"zipCode" : zip,
		"country" : country,
		"role" : "ROLE_GUEST"
	};

	var postRequestStringifyd = JSON.stringify(postRequest);
	console.log("Updating account with json: " + postRequestStringifyd);
	fetch(urlAccount + originalMail, {
		method: "PUT",
		body: postRequestStringifyd,
		headers:{
			"Content-Type": "application/json"
		}
	})
	.then(response => console.log("Succes: ", JSON.stringify(response)))
	.catch(error => console.error('Error:', error));

}

function deleteAccount(){
	var id = document.querySelector("input[name='inputDeleteAccount']").value;
	console.log("Deleting account" + id);
	fetch(urlAccount + id, {
		method: "DELETE",
	})
}

// Only works for 1 reservation per person now
function getSingleReservation(){
	var id = document.querySelector("#inputResId").value;

	if ($.isNumeric(id)){
		console.log("input is numeric");
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
						document.querySelector("#allReservationDisplayText").innerHTML = "";
						document.querySelector("#allReservationDisplayText").innerHTML = JSON.stringify(json[reservations]);
						return json;
					}
				}			
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
		console.log(JSON.stringify(json));
		document.querySelector("#allReservationDisplayText").innerHTML = "";
		document.querySelector("#allReservationDisplayText").innerHTML = JSON.stringify(json);
	})
	.catch(error => console.error('Error:', error));
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