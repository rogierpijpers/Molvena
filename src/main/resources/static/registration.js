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
            console.log("het registreren van de gast is gelukt");
            console.log(validJsonBook);
        }
    });
}
        $(document).ready(function () {
        $("#register").click(function() {

        if (firstname == '' || lastname == '' || password == '' || cpassword == '' || dateofbirth == '' || mail == '' || phone == '' || address == '' || city == '' || zipcode == '' || country == '' ) {
        alert("Please fill all fields");
        console.log("Please fill all fields")
        } else if ((password.length) < 8) {
        alert("Password should atleast 8 character in length");
        console.log("Password should atleast 8 character in length")
        } else if (password.value != cpassword.value) {
        alert("Your passwords don't match. Try again?");
        console.log("Your passwords don't match. Try again?")
        }
        else {
                console.log("Post request send");
                postData();
            };
        });
    });




		