	var firstname;
	var lastname;
	var password;
	var cpassword;
	var dateofbirth;
	var mail;
	var phone;
	var address;
	var city;
	var zipcode;
	var country;

function postData() {
    console.log("posting data...");

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
		country : country
    };
    console.log(postrequest);

    var validJsonBook = JSON.stringify(postrequest);
    console.log(validJsonBook);

    $.ajax({
        url:"http://localhost:8080/createRegistration",
        type:"post",
        data: validJsonBook,
        contentType: "application/json",
        success: function(result) {
            alert("Het registreren van de gast is gelukt");
            console.log("Het registreren van de gast is gelukt");
            console.log(result);
        }
    });
}
        $(document).ready(function () {

        $("#country").click(function() {
        country = $( "#country option:selected" ).attr("value");
        console.log(country);

        var newcode = $( "#country option:selected" ).attr("code");
        $("#phone").attr("value", newcode);

        console.log("Opzoeken van landcode gelukt");
        });

        $("#register").click(function() {

        firstname = $("#firstname").val();
        lastname = $("#lastname").val();
        password = $("#password").val();
        cpassword = $("#cpassword").val();
        dateofbirth = $("#dateofbirth").val();
        mail = $("#mail").val();
        phone = $("#phone").val();
        address = $("#address").val();
        city = $("#city").val();
        zipcode = $("#zipcode").val();

        if (firstname == '' || $("#lastname").val() == '' || $("#password").val() == ''
        || $("#cpassword").val() == '' || $("#dateofbirth").val() == '' || $("#mail").val() == '' || $("#phone").val() == ''
        || $("#address").val() == '' || $("#city").val() == '' || $("#zipcode").val() == '' || country == ''){
        alert("Please fill all fields");
        console.log("Please fill all fields")
        }
        else if ($("#cpassword").val().length < 8) {
        alert("Password should atleast 8 character in length");
        console.log("Password should atleast 8 character in length")
        }
        else if (password.value != cpassword.value) {
        alert("Your passwords don't match. Try again?");
        console.log("Your passwords don't match. Try again?")
        }
        else {
                console.log("Post request send");
                postData();
           };
        });
    });





