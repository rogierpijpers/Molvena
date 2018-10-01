/* RoomCRUD (Admin-only)
	- createRoom
	- deleteRoom
*/


function createRoom(){

}

function deleteRoom(){
	var id = document.querySelector("input[name='inputRoomIdDelete']").value;
	fetch(urlReservation + id, {
		method: "DELETE",
	})

}
