/* GUEST CRUD
	- Get own user details
*/

var userDetailsEndPoint = "localhost:8080";

function getOwnUsername(){
	fetch(userDetailsEndPoint)
		.then(function(response){
			console.log(response);
			console.log(response.json);
			console.log(JSON.stringify(response));			
			console.log(JSON.stringify(response.json));
			return response.json();
		})
		.catch(error => console.error("Error: " + error));
}