$(document).ready(function () {

    function postData() {

        var singleBeds = $("#inputSingle").val();
        var doubleBeds = $("#inputDouble").val();

        var newRoom = {
            "roomType": {
                "singleBeds": singleBeds,
                "doubleBeds": doubleBeds,
            }
        };

        var JsonRoom = JSON.stringify(newRoom);

        $.ajax({
            url:"http://localhost:8080/room/",
            type:"post",
            data: JsonRoom,
            contentType: "application/json",
            success: function(result) {
                alert('success')
                $("#newRoomModal").hide();
                $('body').removeClass('modal-open');
                $('.modal-backdrop').remove();
                getData();
                $("#newRoomForm")[0].reset();
            }
        });
    }

    function updateData() {

        var singleBeds = $("#updateSingle").val();
        var doubleBeds = $("#updateDouble").val();

        var newUpdate = {
            "roomID": data.roomID,
            "roomType": {
                "singleBeds": singleBeds,
                "doubleBeds": doubleBeds,
            }
        };

        var JsonUpdate = JSON.stringify(newUpdate);
        console.log(JsonUpdate);

        $.ajax({
            url:"http://localhost:8080/room/" + data.roomID,
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
            url:"http://localhost:8080/room/",
            type:"get",
            success: function(data) {
                $('#table_id').DataTable().clear();
                $('#table_id').DataTable().rows.add(data);
                $('#table_id').DataTable().columns.adjust().draw();
            }
        });
    }

    $("#newRoomForm").on('submit', function(e) {
        postData();
    });

    $("#newUpdateForm").on('submit', function(e) {
        updateData();
    });

    var table = $('#table_id').DataTable({
        columns: [
            {'data': 'roomID'},
            {'data': 'roomType.singleBeds'},
            {'data': 'roomType.doubleBeds'},
            {
                "defaultContent":
                `
                    <button id="updateRoom" type="button"
                    class="btn btn-primary" data-toggle="modal" data-target="#newUpdateModal">O</button>
                    <button id="deleteRoom" type="button"
                    class="btn btn-danger" data-toggle="modal" data-target="#newDeleteModal">X</button>
                `
            },
        ],
        "order": [[0, 'asc']],
    });

    var data = null;

    $('#table_id tbody ').on('click','#updateRoom',function () {
        data = table.row( $(this).parents('tr') ).data();
                $("#updateSingle").val(data.roomType.singleBeds);
                $("#updateDouble").val(data.roomType.doubleBeds);
    });

    $('#table_id tbody').on('click','#deleteRoom',function () {
        data = table.row($(this).parents('tr')).data();
    });

    $('#deleteRoomTrue').click(function(){
        $.ajax({
            url:"http://localhost:8080/room/" + data.roomID,
            type:"delete",
            success: function(data) {
                getData();
            }
        });
    });

    getData();

});

