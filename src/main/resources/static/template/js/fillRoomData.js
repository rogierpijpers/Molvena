$(document).ready(function(){
    let urlParams = new URLSearchParams(location.search);
    let sPath = window.location.pathname;
    let sPage = sPath.substring(sPath.lastIndexOf('/') + 1);

    let startDate = urlParams.get('startDate');
    let endDate = urlParams.get('endDate');
    let amountOfGuests = urlParams.get('guests')

    $("#startDate").text(startDate);
    $("#endDate").text(endDate);
    $("#amountOfGuests").text(amountOfGuests);

    if (sPage == "single_room.html" || sPage == "reservation_confirm.html"){
        viewSingleRoomData(startDate, endDate, amountOfGuests);
    }else if(sPage == "all_rooms.js"){
        viewAllRoomsData(startDate, endDate, amountOfGuests);
    }
});

function viewAllRoomsData(startDate, endDate, amountOfGuests){  
    let roomTypeApi = "http://localhost:8080/roomtype/available/";

    $.ajax({
        url: roomTypeApi + startDate + "/" + endDate,
        type: "get",
        success: function(data){
            // For each room in room list, create a new element. After 3, add new row  
            let count = 0;
            let container = $("#roomContainer");
            let html = "";
            $.each(data, function(index, value){

                if (count % 4 == 0){
                    html += "<div class='row mb_30'>";
                }
                // Adds a new object for each room. Every 4th room a new Bootstrap row begns.
                html += "<div class='col-lg-3 col-sm-6'> \
                            <div class='accomodation_item text-center'>\
                                <div class='hotel_img'>\
                                    <img src='image/room1.jpg' alt=''>\
                                    <a href='single_room.html?startDate=" + startDate + "&endDate=" + endDate + "&guests=" + amountOfGuests + 
                                    "&singleBeds=" + value.roomType.singleBeds + "&doubleBeds=" + value.roomType.doubleBeds +"'  class='btn theme_btn button_hover'>Book Now</a>\
                                </div>\
                            <a href='single_room.html?startDate=" + startDate + "&endDate=" + endDate + "&guests=" + amountOfGuests + 
                            "&singleBeds=" + value.roomType.singleBeds + "&doubleBeds=" + value.roomType.doubleBeds +" '><h4 class='sec_h4'>Standard room <br /> " + 
                            value.roomType.singleBeds + ' single and '+ value.roomType.doubleBeds + " double beds.</h4></a>\
                            <h6>{price}<small>/night</small></h6>\
                            </div>\
                            </div>";

                count++;
                if (count % 4 == 0){
                    html +=("</div>");
                }            
                
            }); 
            container.append(html);       
            
        },
        error: function(error){
            console.log("Error: " + error);
        }
    });
}

function viewSingleRoomData(startDate, endDate, amountOfGuests){
    let urlParams = new URLSearchParams(location.search);
    let singleBedsElement = $("#singleBeds");
    let doubleBedsElement = $("#doubleBeds");

    let singleBeds = urlParams.get('singleBeds');
    let doubleBeds = urlParams.get('doubleBeds');

    singleBedsElement.text(singleBeds);
    doubleBedsElement.text(doubleBeds);

    let confirmButton = $("#confirmButton");
    confirmButton.innerHTML = "<a class='button_hover theme_btn_two' href='single_room.html?startDate=" + startDate + "&endDate=" + endDate + "&guests=" + amountOfGuests + 
    "&singleBeds=" + singleBeds + "&doubleBeds=" + doubleBeds + ">Confirm</a>";
}