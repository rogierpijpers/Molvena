window.onload=console.log("ready");
var parentForm = document.querySelector("#mainForm");
parentForm.addEventListener("click", callRelevantFunction, false);

// Calls the button function related to the clicked button.
// The name is retrieved from the 'name' of the input field, so make sure that name and the function name is equal.
function callRelevantFunction(e){
	if(e.target !== e.currentTarget){
		var clickedItem = e.target.name;
		if (e.target.type == "button"){
			window[clickedItem]();
		}
	}
	e.stopPropagation();
}

// If this toggling functionality stays, clean everything up in 1 neat function
// TODO: Alles in 1 nette functie
function toggleGetReservation(){
	document.querySelector(".getReservationDisplay").style.display = "block";
	document.querySelector(".getAllReservationsDisplay").style.display = "none";
	document.querySelector(".createReservationDisplay").style.display = "none";
	document.querySelector(".updateReservationDisplay").style.display = "none";
	document.querySelector(".deleteReservationDisplay").style.display = "none";
}

function toggleAllReservations(){
	document.querySelector(".getReservationDisplay").style.display = "none";
	document.querySelector(".getAllReservationsDisplay").style.display = "block";
	document.querySelector(".createReservationDisplay").style.display = "none";
	document.querySelector(".updateReservationDisplay").style.display = "none";
	document.querySelector(".deleteReservationDisplay").style.display = "none";
	getAllReservations();
}

function toggleCreateReservation(){
	document.querySelector(".getReservationDisplay").style.display = "none";
	document.querySelector(".getAllReservationsDisplay").style.display = "none";
	document.querySelector(".createReservationDisplay").style.display = "block";
	document.querySelector(".updateReservationDisplay").style.display = "none";
	document.querySelector(".deleteReservationDisplay").style.display = "none";
}
function toggleUpdateReservation(){
	document.querySelector(".getReservationDisplay").style.display = "none";
	document.querySelector(".getAllReservationsDisplay").style.display = "none";
	document.querySelector(".createReservationDisplay").style.display = "none";
	document.querySelector(".updateReservationDisplay").style.display = "block";
	document.querySelector(".deleteReservationDisplay").style.display = "none";
}

function toggleDeleteReservation(){
	document.querySelector(".getReservationDisplay").style.display = "none";
	document.querySelector(".getAllReservationsDisplay").style.display = "none";
	document.querySelector(".createReservationDisplay").style.display = "none";
	document.querySelector(".updateReservationDisplay").style.display = "none";
	document.querySelector(".deleteReservationDisplay").style.display = "block";
}

function toggleAllAccount(){
	getAllAccounts();
}