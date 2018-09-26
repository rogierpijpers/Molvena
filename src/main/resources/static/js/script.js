window.onload=console.log("ready");
//window.onload=toggleItems();
var parentForm = document.querySelector("#mainForm");
parentForm.addEventListener("click", callRelevantFunction, false);

// Calls the button function related to the clicked button.
// The name is retrieved from the 'name' of the input field, so make sure that name and the function name is equal.
function callRelevantFunction(e){
	if(e.target !== e.currentTarget){
		var clickedItem = e.target.name;
		console.log(clickedItem + " has been clicked");
		window[clickedItem]();
	}
	e.stopPropagation();
}

// Button functions
function login(mail, password){
console.log("function login calld");
}

function logout(){

}

function register(firstName, lastName, mail, password, dob, number, address, city, zip, country){

}

function toggleItems(){
	document.querySelector(".getReservation").style.visibility = "hidden"
	document.querySelector(".updateReservation").style.visibility = "hidden"
	document.querySelector(".createReservation").style.visibility = "hidden"
	document.querySelector(".deleteReservation").style.visibility = "hidden"
	document.querySelector(".getReservationBtn").style.visibility = "hidden"
	document.querySelector(".createReservationBtn").style.visibility = "hidden";
	document.querySelector(".updateReservationBtn").style.visibility = "hidden";
	document.querySelector(".deleteReservationBtn").style.visibility = "hidden";	
}

function toggleReservationMenu(){
	 document.querySelector("#reservationBtnForm").style.visibility = "visible";
	// document.querySelector(".getReservationBtn").style.visibility = "visible"
	// document.querySelector(".createReservationBtn").style.visibility = "visible";
	// document.querySelector(".updateReservationBtn").style.visibility = "visible";
	// document.querySelector(".deleteReservationBtn").style.visibility = "visible";
}

function toggleGetReservation(){
	document.querySelector(".getReservation").style.visibility = "visible"
}
function toggleCreateReservation(){
	document.querySelector(".createReservation").style.visibility = "visible"
}
function toggleUpdateReservation(){
	document.querySelector(".updateReservation").style.visibility = "visible"
}
function toggleDeleteReservation(){
	document.querySelector(".deleteReservation").style.visibility = "visible"
}

function toggleAccountMenu(){
	document.querySelector(".getGuest").style.visibility = "visible";
	document.querySelector(".createGuest").style.visibility = "visible";
	document.querySelector(".updateGuest").style.visibility = "visible";
	document.querySelector(".deleteGuest").style.visibility = "visible";
}

function getReservation(){
	//document.querySelector("input[name=reservationInput]").style.visibility = "visible";
	//var reservationInput = document.querySelector("input[name=reservationInput]").value();
	//reservation = getSingleReservation(reservationInput);



	// var reservation = null;
	// if (typeof reservationInput === "number" ){
	// 	reservation = getSingleReservationById(reservationInput);
	// }
	// else if (typeof reservationInput === "string"){
	// 	reservation = getSingleReservationByName(reservationInput);
	// }
	// else{
	// 	// Throw error
	// 	alert("Reservation input not valid (replace with exception!!!");
	// 	// TODO: Exception
	// }
}

// Back-end FUNCTIONS
var url = "http://localhost:8080/";

function getSingleReservation(reservationInput){
	fetch(url + "reservation/" + reservationInput)
	.then(function(response){
		return response.json();
	})
	.then(function(json){
		console.log(JSON.stringify(json));
		// TODO: display in html
	});
}

function getAllReservations(){
fetch(url + "reservation/all")
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

}

function updateReservation(){

}

function deleteReservation(reservationId){

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

