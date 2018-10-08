$(document).ready(function(){

    viewAllRoomsData();
    viewSingleRoomData();
});
let roomApi = "http://localhost:8080/room/";

function viewAllRoomsData(){  
    let api =  "http://localhost:8080/room/"; 
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
                                    <a href='#'  class='btn theme_btn button_hover'>Book Now</a>\
                                </div>\
                            <a href='#'><h4 class='sec_h4'>Standard room, " + value.roomType.singleBeds + ' single beds and '
                            + value.roomType.doubleBeds + " double beds.</h4></a>\
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
    //let roomTypeName = $("#roomTypeName")

    // TODO: dynamic
    let roomId = 1;


    $.ajax({
        url: roomApi + roomId,
        type: "get",
        success: function(data){
            // TODO roomTypeName.text(data.name); or similar
            singleBeds.text(data.roomType.singleBeds);
            doubleBeds.text(data.roomType.doubleBeds);
        },
        error: function(error){
            console.log("Error: " + error);
        }
    });
}