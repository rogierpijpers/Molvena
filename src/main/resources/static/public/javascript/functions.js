function guestDropdown(dropdownid){
// Fill dropdown will all guests
    $.ajax({
        url:"http://localhost:8080/guest/",
        type:"get",
        success: function(data){
            //var count = 0;
            $.each(data, function (key, value) {
                var opt = $('<option></option>');
                //count++;
                opt.text(value.mail);

                $('#' + dropdownid).append($(opt));
            });
        }
    });
}

function roomDropdown(dropdownid){
// Fill dropdown will all guests
    $.ajax({
        url:"http://localhost:8080/room/",
        type:"get",
        success: function(data){
            //var count = 0;
            $.each(data, function (key, value) {
                var opt = $('<option></option>');
                //count++;
                opt.text(value.roomID + " - Singlebeds: " + value.roomType.singleBeds + ", DoubleBeds: " + value.roomType.doubleBeds);

                $('#' + dropdownid).append($(opt));
            });
        }
    });
}