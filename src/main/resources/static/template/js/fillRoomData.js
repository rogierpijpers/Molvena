$(document).ready(function(){
    // Met URL als http://localhost:8080/template/all_rooms.html?id=1234
    let urlParams = new URLSearchParams(location.search);

    let startDate = urlParams.get('startDate');
    let endDate = urlParams.get('endDate');
    let amountOfGuests = urlParams.get('guests')

    let startDateText = $("#startDate").text(urlParams.get('startDate'));
    let endDateText = $("#endDate").text(urlParams.get('endDate'));
    let amountOfGuestsText = $("#amountOfGuests").text(urlParams.get('guests'));

    viewAllRoomsData(startDate, endDate, amountOfGuests);
    viewSingleRoomData(startDate, endDate, amountOfGuests);
});

let roomApi = "http://localhost:8080/room/";
function viewAllRoomsData(startDate, endDate, amountOfGuests){  
    $.ajax({
        url: roomApi,
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
    let singleBeds = $("#singleBeds");
    let doubleBeds = $("#doubleBeds");
    // TODO: dynamic
    let roomId = 1;

    $.ajax({
        url: roomApi + roomId,
        type: "get",
        success: function(data){
            singleBeds.text(data.roomType.singleBeds);
            doubleBeds.text(data.roomType.doubleBeds);
        },
        error: function(error){
            console.log("Error: " + error);
        }
    });
}