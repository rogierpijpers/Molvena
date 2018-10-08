$(document).ready(function () {

    guestDropdown('inputEmail');
    guestDropdown('updateEmail');

    roomDropdown('inputRoom');
    roomDropdown('updateRoom');


    var notBefore = new Date();
    var dd = notBefore.getDate();
    var mm = notBefore.getMonth()+1;
    var yyyy = notBefore.getFullYear();

     if(dd<10){
            dd='0'+dd
        }
        if(mm<10){
            mm='0'+mm
        }

    notBefore = yyyy+'-'+mm+'-'+dd;
    document.getElementById("inputCheckIn").setAttribute("min", notBefore);
    document.getElementById("updateCheckIn").setAttribute("min", notBefore);
    document.getElementById("inputCheckOut").setAttribute("min", notBefore);
    document.getElementById("updateCheckOut").setAttribute("min", notBefore);

    $("#inputCheckOut").click(function() {
        var checkInInput = $("#inputCheckIn").val();
        document.getElementById("inputCheckOut").setAttribute("min", checkInInput);
    });

    $("#inputCheckIn").click(function() {
        var checkOutInput = $("#inputCheckOut").val();
        document.getElementById("inputCheckIn").setAttribute("max", checkOutInput);
    });

    $("#updateCheckOut").click(function() {
        var checkInInput = $("#updateCheckIn").val();
        document.getElementById("updateCheckOut").setAttribute("min", checkInInput);
    });

    $("#updateCheckIn").click(function() {
        var checkOutInput = $("#updateCheckOut").val();
        document.getElementById("updateCheckIn").setAttribute("max", checkOutInput);
    });

    function postData() {
        var email = $("#inputEmail").val();
        var guest = $("#inputGuest").val();
        var checkIn = $("#inputCheckIn").val();
        var checkOut = $("#inputCheckOut").val();
        var room = $("#inputRoom").val();

        $.ajax({
            url:"http://localhost:8080/room/" + room,
            type:"get",
            contentType: "application/json",
            success: function(result) {
               room = result;
               $.ajax({
                   url:"http://localhost:8080/guest/" + email,
                   type:"get",
                   contentType: "application/json",
                   success: function(result) {
                      email = result;
                      var newReservation = {
                          "startDate": checkIn,
                          "endDate": checkOut,
                          "amountOfGuests": guest,
                          "guest": email,
                          "room": room
                      };

                      var JsonReservation = JSON.stringify(newReservation);

                      $.ajax({
                          url:"http://localhost:8080/reservation/",
                          type:"post",
                          data: JsonReservation,
                          contentType: "application/json",
                          success: function(result) {
                              $("#newReservationModal").hide();
                              $('body').removeClass('modal-open');
                              $('.modal-backdrop').remove();
                              getData();
                              $("#newReservationForm")[0].reset();
                          }
                       });
                   }
               });
            }
        });
    }

    function updateData() {
        var email = $("#updateEmail").val();
        var guest = $("#updateGuest").val();
        var checkIn = $("#updateCheckIn").val();
        var checkOut = $("#updateCheckOut").val();
        var room = $("#updateRoom").val();


        var updatedReservation = {
            "reservationID" : data.reservationID,
            "startDate": checkIn,
            "endDate": checkOut,
            "amountOfGuests": guest,
            "guest": email,
            "room": room
        };

        var JsonUpdate = JSON.stringify(updatedReservation);

        $.ajax({
            url:"http://localhost:8080/reservation/" + data.reservationID,
            type:"put",
            data: JsonUpdate,
            contentType: "application/json",
            success: function(result) {
                $("#newUpdateModal").hide();
                $('body').removeClass('modal-open');
                $('.modal-backdrop').remove();
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

    $("#newReservationForm").on('submit', function(e) {
        postData();
    });

    $("#newUpdateForm").on('submit', function(e) {
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
                $("#updateFirstName").val(data.guest.firstName);
                $("#updateLastName").val(data.guest.lastName);
                $("#updateEmail").val(data.guest.mail);
                $("#updateBirth").val(data.guest.birth);
                $("#updatePhone").val(data.guest.phone);
                $("#updateAddress").val(data.guest.address);
                $("#updateCity").val(data.guest.city);
                $("#updateZipCode").val(data.guest.zipCode);
                $("#updateState").val(data.guest.state);
                $("#updateCountry").val(data.guest.country);
                $("#updateGuest").val(data.amountOfGuests);
                $("#updateCheckIn").val(data.startDate);
                $("#updateCheckOut").val(data.endDate);
                $("#updateRoom").val(data.roomType);
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

