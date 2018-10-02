var urlRoom = "http://localhost:8080/room/";

function createRoom(){
	var roomId = document.querySelector("input[name='inputRoomIdCreateRoom']").value;
	var roomType = document.querySelector("input[name='inputRoomTypeCreateRoom']").value;
	var postRequest = {
		"roomId" : roomId,
		"roomType": {
			"singleBeds": 0,
			"doubleBeds": 2
		}
	};

	var postRequestStringifyd = JSON.stringify(postRequest);

	fetch(urlRoom, {
		method: "POST",
		body: postRequestStringifyd,
		headers: {
			"Content-Type": "application/json"
		}
	})	
	.catch(error => console.error('Error:', error));
}

function deleteRoom(){
	var id = document.querySelector("input[name='inputRoomIdDelete']").value;
	fetch(urlRoom + id, {
		method: "DELETE",
	})
	.catch(error => console.error('Error:', error));

}
