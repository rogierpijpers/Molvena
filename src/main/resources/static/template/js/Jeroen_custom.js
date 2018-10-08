var arrival;
var departure;
var persons;

function postData() {
    console.log("sending data...");

    arrival = document.getElementById('arrival').value
    departure = document.getElementById('departure').value 
    persons = $("#persons").val();

    console.log(arrival);
    console.log(departure);
    console.log(persons);

	window.location.href = "http://localhost:8080/all_rooms.html?startDate=" + arrival + "&endDate=" + departure + "&persons=" + persons;
}

$(document).ready(function () {

$("#make-booking").click(function() {
postData();
});
});