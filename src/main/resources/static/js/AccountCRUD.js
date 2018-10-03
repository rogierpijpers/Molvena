var urlAccount = "http://localhost:8080/guest/";

function getSingleAccount(){	
	var id = document.querySelector("#inputAccountId").value;
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

function getAllAccounts(){
	fetch(urlAccount)
	.then(function(response){
		return response.json();
	})
	.then(function(json){
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
	var dob = new Date(document.querySelector("input[name='inputDobCreateAccount']").value);
	//var dobYYMMDD = dob.getFullYear() + "/" + dob.getMonth() + "/" + dob.getDate();
	var phone = document.querySelector("input[name='inputPhoneCreateAccount']").value;
	var address = document.querySelector("input[name='inputAddressCreateAccount']").value;
	var city = document.querySelector("input[name='inputCityCreateAccount']").value;
	var zip = document.querySelector("input[name='inputZipCreateAccount']").value;
	var country = document.querySelector("input[name='inputCountryCreateAccount']").value;

	var postRequest = {
		"firstName" : firstName,
		"lastName" : lastName,
		"password" : password,
		"dateOfBirth" : dob.toISOString(),
		"mail" : mail,
		"phone" : phone,
		"address" : address,
		"city" : city,
		"zipCode" : zip,
		"country" : country,
		"role" : "ROLE_GUEST"
	};

	var postRequestStringifyd = JSON.stringify(postRequest);
	fetch(urlAccount, {
		method: "POST",
		body: postRequestStringifyd,
		headers:{
			"Content-Type": "application/json"
		}
	})
	.catch(error => console.error('Error:', error));
}

function updateAccount(){
	var originalMail = document.querySelector("input[name='inputMailRetrieveAccount']").value;
	var firstName = document.querySelector("input[name='inputFirstNameUpdateAccount']").value;
	var lastName = document.querySelector("input[name='inputLastNameUpdateAccount']").value;
	var password = document.querySelector("input[name='inputPasswordUpdateAccount']").value;
	var newMail = document.querySelector("input[name='inputMailUpdateAccount']").value;
	var dob = new Date(document.querySelector("input[name='inputDobUpdateAccount']").value);
	//var dobYYMMDD = dob.getFullYear() + "/" + dob.getMonth() + "/" + dob.getDate();
	var phone = document.querySelector("input[name='inputPhoneUpdateAccount']").value;
	var address = document.querySelector("input[name='inputAddressUpdateAccount']").value;
	var city = document.querySelector("input[name='inputCityUpdateAccount']").value;
	var zip = document.querySelector("input[name='inputZipUpdateAccount']").value;
	var country = document.querySelector("input[name='inputCountryUpdateAccount']").value;

	var postRequest = {
		"firstName" : firstName,
		"lastName" : lastName,
		"password" : password,
		"dateOfBirth" : dob.toISOString(),
		"mail" : newMail,
		"phone" : phone,
		"address" : address,
		"city" : city,
		"zipCode" : zip,
		"country" : country,
		"role" : "ROLE_GUEST"
	};

	var postRequestStringifyd = JSON.stringify(postRequest);
	fetch(urlAccount + originalMail, {
		method: "PUT",
		body: postRequestStringifyd,
		headers:{
			"Content-Type": "application/json"
		}
	})
	.catch(error => console.error('Error:', error));
}

function deleteAccount(){
	var id = document.querySelector("input[name='inputDeleteAccount']").value;
// NOTE: no delete account function in backend yet
fetch(urlAccount + id, {
	method: "DELETE",
})
}
