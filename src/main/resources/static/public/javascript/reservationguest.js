$(document).ready(function () {

    var account;

    function getData() {
        $.ajax({
            url:"http://localhost:8080/account/",
            type:"get",
            success: function(data) {
                account = data;
                $.ajax({
                    url:"http://localhost:8080/reservation/user/" + account.mail,
                    type:"get",
                    success: function(data) {
                        $('#table_id').DataTable().clear();
                        $('#table_id').DataTable().rows.add(data);
                        $('#table_id').DataTable().columns.adjust().draw();
                    },
                    error: function(data) {
                        $('#reservationDanger').css("display","block")
                    }
                })
            }
        });
    }

    getData();

    var table = $('#table_id').DataTable({
        columns: [
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
            {'data': 'room.roomID'},
            {
                "defaultContent":
                `
                    <button id="deleteReservation" type="button"
                    class="btn btn-danger" data-toggle="modal" data-target="#newDeleteModal">X</button>
                `
            },
        ],
        "order": [[0, 'asc']],

    });

        var selectedValue = null;

        $('#table_id tbody ').on('click','#updateReservation',function () {
            selectedValue = table.row( $(this).parents('tr') ).data();
                    console.log(selectedValue.startDate)

                    var startDate = selectedValue.startDate.substring(0,10);
                    var endDate = selectedValue.endDate.substring(0,10);

                    $("#updateFirstName").val(selectedValue.guest.firstName);
                    $("#updateLastName").val(selectedValue.guest.lastName);
                    $("#updateEmail").val(selectedValue.guest.mail);
                    $("#updateBirth").val(selectedValue.guest.birth);
                    $("#updatePhone").val(selectedValue.guest.phone);
                    $("#updateAddress").val(selectedValue.guest.address);
                    $("#updateCity").val(selectedValue.guest.city);
                    $("#updateZipCode").val(selectedValue.guest.zipCode);
                    $("#updateState").val(selectedValue.guest.state);
                    $("#updateCountry").val(selectedValue.guest.country);
                    $("#updateGuest").val(selectedValue.amountOfGuests);
                    $("#updateCheckIn").val(startDate);
                    $("#updateCheckOut").val(endDate);
                    $("#updateRoom").val(selectedValue.roomType);
        });

        $('#table_id tbody').on('click','#deleteReservation',function () {
            selectedValue = table.row($(this).parents('tr')).data();
        });

        $('#deleteReservationTrue').click(function(){
            $.ajax({
                url:"http://localhost:8080/reservation/" + selectedValue.reservationID,
                type:"delete",
                success: function(data) {
                    getData();
                }
            });
        });

});

