$(document).ready(function () {

    //Check-in validation
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1;
    var yyyy = today.getFullYear();
     if(dd<10){
            dd='0'+dd
        }
        if(mm<10){
            mm='0'+mm
        }
    today = yyyy+'-'+mm+'-'+dd;
    document.getElementById("inputCheckIn").setAttribute("min", today);
    document.getElementById("updateCheckIn").setAttribute("min", today);

    $("#inputCheckOut").click(function() {
        var tomorrowInput = $("#inputCheckIn").val();
        document.getElementById("inputCheckOut").setAttribute("min", tomorrowInput);
    });

    $("#updateCheckOut").click(function() {
        var tomorrowUpdate = $("#updateCheckIn").val();
        document.getElementById("updateCheckOut").setAttribute("min", tomorrowUpdate);
    });

    function postData() {

        var firstName = $("#inputFirstName").val();
        var lastName = $("#inputLastName").val();
        var email = $("#inputEmail").val();
        var birth = $("#inputBirth").val();
        var phone = $("#inputPhone").val();
        var address = $("#inputAddress").val();
        var city = $("#inputCity").val();
        var zipCode = $("#inputZipCode").val();
        var state = $("#inputState").val();
        var country = $("#inputCountry").val();
        var guest = $("#inputGuest").val();
        var checkIn = $("#inputCheckIn").val();
        var checkOut = $("#inputCheckOut").val();
        var roomType = $("#inputRoom").val();

        var newReservation = {
            "startDate": checkIn,
            "endDate": checkOut,
            "amountOfGuests": guest,
            "guest": {
                "firstName": firstName,
                "lastName": lastName,
                "password": "$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2",
                "dateOfBirth": birth,
                "mail": email,
                "phone": phone,
                "address": address,
                "city": city,
                "zipCode": zipCode,
                "country": country,
    	    },
            "room": {
                "roomType": {
                    "singleBeds": 0,
                    "doubleBeds": 2
                }
            }
        };

        var JsonReservation = JSON.stringify(newReservation);

        $.ajax({
            url:"http://localhost:8080/reservation/",
            type:"post",
            data: JsonReservation,
            contentType: "application/json",
            success: function(result) {
                getData();
                $("#inputFirstName").val("");
                $("#inputLastName").val("");
                $("#inputEmail").val("");
                $("#inputBirth").val("");
                $("#inputPhone").val("");
                $("#inputAddress").val("");
                $("#inputCity").val("");
                $("#inputZipCode").val("");
                $("#inputState").val("");
                $("#inputCountry").val("");
                $("#inputGuest").val("");
                $("#inputCheckIn").val("");
                $("#inputCheckOut").val("");
                $("#inputRoom").val("");
            }
        });
    }

    function updateData() {

        var firstName = $("#updateFirstName").val();
        var lastName = $("#updateLastName").val();
        var email = $("#updateEmail").val();
        var birth = $("#updateBirth").val();
        var phone = $("#updatePhone").val();
        var address = $("#updateAddress").val();
        var city = $("#updateCity").val();
        var zipCode = $("#updateZipCode").val();
        var state = $("#updateState").val();
        var country = $("#updateCountry").val();
        var guest = $("#updateGuest").val();
        var checkIn = $("#updateCheckIn").val();
        var checkOut = $("#updateCheckOut").val();
        var roomType = $("#updateRoom").val();

        var newUpdate = {
            "reservationID": data.reservationID,
            "startDate": checkIn,
            "endDate": checkOut,
            "amountOfGuests": guest,
            "guest": {
                "firstName": firstName,
                "lastName": lastName,
                "password": "$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2",
                "dateOfBirth": birth,
                "mail": email,
                "phone": phone,
                "address": address,
                "city": city,
                "zipCode": zipCode,
                "country": country,
            },
            "room": {
                "roomType": {
                    "singleBeds": 0,
                    "doubleBeds": 2
                }
            }
        };

        var JsonUpdate = JSON.stringify(newUpdate);
        console.log(JsonUpdate);

        $.ajax({
            url:"http://localhost:8080/reservation/" + data.reservationID,
            type:"put",
            data: JsonUpdate,
            contentType: "application/json",
            success: function(result) {
                getData();
            }
        });
    }

    function getData() {
        $.ajax({
            url:"http://localhost:8080/reservation/",
            type:"get",
            success: function(data) {
                $('#table_id').DataTable().clear();
                $('#table_id').DataTable().rows.add(data);
                $('#table_id').DataTable().columns.adjust().draw();
            }
        });
    }

    $("#submitReservation").click(function(e){
        postData();
    });

    $("#submitUpdate").click(function(e){
        updateData();
    });

    var table = $('#table_id').DataTable({
        columns: [
            {
                "className": 'details-control',
                "orderable": false,
                "data": null,
                "defaultContent": ''
            },
            {'data': 'reservationID'},
            {'data': 'guest',
                render: function ( data, type, row ) {
                    return data.firstName + " " + data.lastName;
                }
            },
            {'data': 'startDate',
                render: function ( data, type, row ) {
                    return data.substring(0,10);
                }
            },
            {'data': 'endDate',
                render: function ( data, type, row ) {
                    return data.substring(0,10);
                }
            },
            {'data': 'guest.mail'},
            {'data': 'guest.phone'},
            {
                "defaultContent":
                `
                    <button id="updateReservation" type="button"
                    class="btn btn-primary" data-toggle="modal" data-target="#newUpdateModal">O</button>
                    <button id="deleteReservation" type="button"
                    class="btn btn-danger" data-toggle="modal" data-target="#newDeleteModal">X</button>
                `
            },
        ],
        "order": [[0, 'asc']],

    });

    var data = null;

    $('#table_id tbody ').on('click','#updateReservation',function () {
        data = table.row( $(this).parents('tr') ).data();
    });

    $('#table_id tbody').on('click','#deleteReservation',function () {
        data = table.row($(this).parents('tr')).data();
    });

    $('#deleteReservationTrue').click(function(){
        $.ajax({
            url:"http://localhost:8080/reservation/" + data.reservationID,
            type:"delete",
            success: function(data) {
                getData();
            }
        });
    });

    getData();

});

