function postData() {
    console.log("posting data...");

    // Get values from html.
	var firstname = $("#firstname").val();
	var lastname = $("#lastname").val();
	var password = $("#password").val();
	var cpassword = $("#cpassword").val();
	var dateofbirth = $("#dateofbirth").val();
	var mail = $("#mail").val();
	var phone = $("#phone").val();
	var address = $("#address").val();
	var city = $("#city").val();
	var zipcode = $("#zipcode").val();
	var country = $("#country").val();

    // Create JS object with data.
    var postrequest = {
        firstName : firstname,
		lastName : lastname,
		password : password,
		dateOfBirth : dateofbirth,
		mail : mail,
		phone : phone,
		address : address,
		city : city,
		zipCode : zipcode,
		country : country,
    };
    console.log(postrequest);

    // Convert JS object to JSON
    var validJsonBook = JSON.stringify(postrequest);
    console.log(validJsonBook);

    // Post JSON to endpoint.
    $.ajax({
        url:"http://localhost:8080/createRegistration",
        type:"post",
        data: validJsonBook,
        contentType: "application/json",
        success: function(result) {
            // On successful post, reload data to get the added one as well.
            console.log("success post data!");
        }
    });
}
        $(document).ready(function () {
        $("#register").click(function() {
                console.log("Post request send");
                // Post the data from the modal.
                postData();
            });
        });




		