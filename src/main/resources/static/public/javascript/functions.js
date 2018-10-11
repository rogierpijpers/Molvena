function guestDropdown(dropdownid){
    $.ajax({
        url:"http://localhost:8080/guest/",
        type:"get",
        success: function(data){
            $.each(data, function (key, value) {
                var opt = $('<option></option>');
                opt.text(value.mail);
                $('#' + dropdownid).append($(opt));
            });
        }
    });
}

function roomDropdown(dropdownid){
    $.ajax({
        url:"http://localhost:8080/room/",
        type:"get",
        success: function(data){
            $.each(data, function (key, value) {
                var opt = $('<option></option>');
                opt.text(value.roomID);
                $('#' + dropdownid).append($(opt));
            });
        }
    });
}