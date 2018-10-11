$(document).ready(function(){
    $("#make-booking").click(function() {
        postData();
    });

    let urlParams = new URLSearchParams(location.search);
    let sPath = window.location.pathname;
    let sPage = sPath.substring(sPath.lastIndexOf('/') + 1);
    if (urlParams.get('startDate') != null && urlParams.get('endDate') != null && urlParams.get('guests') != null){
        let startDate = urlParams.get('startDate');
        let endDate = urlParams.get('endDate');
        let amountOfGuests = urlParams.get('guests');
        $("#startDate").text(startDate.split('T')[0]);
        $("#endDate").text(endDate.split('T')[0]);
        $("#amountOfGuests").text(amountOfGuests);
    }    

    if (sPage == "single_room.html" || sPage == "reservation_confirm.html"){
        viewSingleRoomData(startDate.innerHTML, endDate.innerHTML, amountOfGuests.innerHTML);
    }else if(sPage == "all_rooms.html"){
        viewAllRoomsData(startDate.innerHTML, endDate.innerHTML, amountOfGuests.innerHTML);
    }
});

function postData() {
    let startDate;
    let endDate;
    let amountOfGuests;
    startDate = document.getElementById('arrival').value;
    endDate = document.getElementById('departure').value;
    amountOfGuests = $("#persons").val();
	window.location.href = "http://localhost:8080/template/all_rooms.html?startDate=" + startDate + "&endDate=" + endDate + "&guests=" + amountOfGuests;
}

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
    let newButton = "<a href='' onclick='confirmBooking(startDate, endDate, amountOfGuests, singleBeds, doubleBeds)' class='button_hover theme_btn_two'>CONFIRM BOOKING</a>";
    let confirmButtonWrapper = $("#confirmButtonWrapper");
    confirmButtonWrapper.append(newButton);
}

function confirmBooking(startDate, endDate, amountOfGuests, singleBeds, doubleBeds){
    let loggedInApi = "http://localhost:8080/account/check";
    $.ajax({
        url: loggedInApi,
        type: "get",
        success: function(data){
            if (data == false){
                window.location.href = "http://localhost:8080/login";
            }else{ 
                createBooking(startDate, endDate, amountOfGuests, singleBeds, doubleBeds);
                window.location.href="http://localhost:8080/template/reservation_confirm.html?startDate=" + startDate.innerHTML + "&endDate=" + endDate.innerHTML + "&guests=" + amountOfGuests.innerHTML + "&singleBeds=" + singleBeds.innerHTML + "&doubleBeds=" + doubleBeds.innerHTML;
            }
        },
        error: function(error){           
            console.log("Error: " + error);
        }
    });
}

function createBooking(startDate, endDate, amountOfGuests, singleBeds, doubleBeds){
    let userApi = "http://localhost:8080/account";
    let createReservationApi = "http://localhost:8080/reservation";
 
    $.ajax({
        url: userApi,
        type: "get",
        success: function(data){
            let postRequest = {
                "startDate": startDate.innerHTML+"T12:37:28.499+0000",
                "endDate": endDate.innerHTML+"T12:37:28.499+0000",
                "guest": data,
                "amountOfGuests": parseInt(amountOfGuests.innerHTML),
                "room": {
                    "roomType": {
                        "singleBeds": parseInt(singleBeds.innerHTML),
                        "doubleBeds": parseInt(doubleBeds.innerHTML)
                    }
                }
             };
             
        let postRequestStringifyd = JSON.stringify(postRequest);

        console.log(postRequestStringifyd);

        $.ajax({
            url: createReservationApi,
            type: "post",
            contentType: "application/json",
            body: postRequestStringifyd,
            success: function(){
                //console.log("Successfully created reservation");
                // TODO after merging with Chestons branch (or develop when its there)
            },
            error: function(error){           
                console.log("Error creating reservation: " + error);
            }
        });
        },
        error: function(error){           
            console.log("Error: " + error);
        }
  
    });
}