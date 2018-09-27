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
	// document.querySelector(".getReservationBtn").style.visibility = "visible"
	// document.querySelector(".createReservationBtn").style.visibility = "visible";
	// document.querySelector(".updateReservationBtn").style.visibility = "visible";
	// document.querySelector(".deleteReservationBtn").style.visibility = "visible";
}

function toggleGetReservation(){
	if(document.querySelector(".getReservationDisplay").style.visibility = "visible"){
		console.log("toggling to hidden");
		document.querySelector(".getReservationDisplay").style.visibility = "hidden";
	}
	else if (document.querySelector(".getReservationDisplay").style.visibility = "hidden")
	{
		console.log("toggling to visible");
		document.querySelector(".getReservationDisplay").style.visibility = "visible";
	}
}

function toggleCreateReservation(){
	document.querySelector(".createReservationDisplay").style.visibility = "visible";
}
function toggleUpdateReservation(){
	document.querySelector(".updateReservationDisplay").style.visibility = "visible";
}
function toggleDeleteReservation(){
	document.querySelector(".deleteReservationDisplay").style.visibility = "visible";
}

function toggleAccountMenu(){
	document.querySelector(".getGuest").style.visibility = "visible";
	document.querySelector(".createGuest").style.visibility = "visible";
	document.querySelector(".updateGuest").style.visibility = "visible";
	document.querySelector(".deleteGuest").style.visibility = "visible";
}
