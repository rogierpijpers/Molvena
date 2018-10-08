$(document).ready(function(){
    let urlParams = new URLSearchParams(location.search);

    let startDate = urlParams.get('startDate');
    let endDate = urlParams.get('endDate');
    let amountOfGuests = urlParams.get('guests')

    var date = new Date();
    console.log(date.toString());
    let startDateText = $("#startDate").text(urlParams.get('startDate'));
    let endDateText = $("#endDate").text(urlParams.get('endDate'));
    let amountOfGuestsText = $("#amountOfGuests").text(urlParams.get('guests'));

    viewAllRoomsData(startDate, endDate, amountOfGuests);
    viewSingleRoomData(startDate, endDate, amountOfGuests);
});

let roomApi = "http://localhost:8080/room/";
let roomTypeApi = "http://localhost:8080/roomtype/available/";
// TODO: test start and enddate in url, to see if the right format is passed.
function viewAllRoomsData(startDate, endDate, amountOfGuests){  
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

function viewSingleRoomData(){
    let singleBeds = $("#singleBeds");
    let doubleBeds = $("#doubleBeds");

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