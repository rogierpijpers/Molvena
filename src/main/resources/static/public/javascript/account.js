$(document).ready(function () {

    $("#inputCountry").click(function() {
            country = $( "#inputCountry option:selected" ).attr("value");

            var newcode = $( "#inputCountry option:selected" ).attr("code");
            $("#inputPhone").attr("value", newcode);
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

        var newAccount = {
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
        };

        var JsonAccount = JSON.stringify(newAccount);

        $.ajax({
            url:"http://localhost:8080/guest/",
            type:"post",
            data: JsonAccount,
            contentType: "application/json",
            success: function(result) {
                $("#newAccountModal").hide();
                $('body').removeClass('modal-open');
                $('.modal-backdrop').remove();
                getData();
                $("#newAccountForm")[0].reset();
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

        var newUpdate = {
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
        };

        var JsonUpdate = JSON.stringify(newUpdate);
        console.log(JsonUpdate);

        $.ajax({
            url:"http://localhost:8080/guest/" + data.mail,
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
            url:"http://localhost:8080/guest/",
            type:"get",
            success: function(data) {
                $('#table_id').DataTable().clear();
                $('#table_id').DataTable().rows.add(data);
                $('#table_id').DataTable().columns.adjust().draw();
            }
        });
    }

    $("#newAccountForm").on('submit', function(e) {
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
            {'data': 'firstName'},
            {'data': 'lastName'},
            {'data': 'mail'},
            {'data': 'phone'},
            {'data': 'dateOfBirth',
                render: function ( data, type, row ) {
                    return data.substring(0,10);
                }
            },
            {'data': 'address'},
            {'data': 'city'},
            {'data': 'country'},
            {
                "defaultContent":
                `
                    <button id="updateAccount" type="button"
                    class="btn btn-primary" data-toggle="modal" data-target="#newUpdateModal">O</button>
                    <button id="deleteAccount" type="button"
                    class="btn btn-danger" data-toggle="modal" data-target="#newDeleteModal">X</button>
                `
            },
        ],
        "order": [[0, 'asc']],

    });

    var data = null;

    $('#table_id tbody ').on('click','#updateAccount',function () {
        data = table.row( $(this).parents('tr') ).data();
                $("#updateFirstName").val(data.firstName);
                $("#updateLastName").val(data.lastName);
                $("#updateEmail").val(data.mail);
                $("#updateBirth").val(data.birth);
                $("#updatePhone").val(data.phone);
                $("#updateAddress").val(data.address);
                $("#updateCity").val(data.city);
                $("#updateZipCode").val(data.zipCode);
                $("#updateState").val(data.state);
                $("#updateCountry").val(data.country);
    });

    $('#table_id tbody').on('click','#deleteAccount',function () {
        data = table.row($(this).parents('tr')).data();
    });

    $('#deleteAccountTrue').click(function(){
        $.ajax({
            url:"http://localhost:8080/guest/" + data.mail,
            type:"delete",
            success: function(data) {
                getData();
            }
        });
    });

    getData();

});

