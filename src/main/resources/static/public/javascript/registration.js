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
	var state;
	var newsletter;

function postData() {

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
		state : state,
		newsletter : newsletter
    };

    var validJsonBook = JSON.stringify(postrequest);
    $.ajax({
        url:"http://localhost:8080/guest/",
        type:"post",
        data: validJsonBook,
        contentType: "application/json",
        success: function(result) {
            alert("Account aangemaakt!");

            window.location.href="http://localhost:8080/login";
        },
        error: function(error){
            console.log(error);
        }
    });
}
        $(document).ready(function () {

        $("#country").click(function() {
        country = $( "#country option:selected" ).attr("value");

        var newcode = $( "#country option:selected" ).attr("code");
        $("#phone").attr("value", newcode);

        });

        $("#register").click(function() {

        firstname = $("#firstname").val();
        lastname = $("#lastname").val();
        password = $("#password").val();
        cpassword = $("#cpassword").val();
        dateofbirth = $("#dateofbirth").val();
        mail = ($("#mail").val()).toLowerCase();
        phone = $("#phone").val();
        address = $("#address").val();
        city = $("#city").val();
        state = $("#state").val();
        zipcode = $("#zipcode").val();
        newsletter = $('#newsletter').is(':checked');

            if (firstname == '' || $("#lastname").val() == '' || $("#password").val() == ''
            || $("#cpassword").val() == '' || $("#dateofbirth").val() == '' || $("#mail").val() == '' || $("#phone").val() == ''
            || $("#address").val() == '' || $("#city").val() == '' || $("#zipcode").val() == '' || country == '' || state == '')
            {
            alert("Please fill all fields");
            }
            else if (password.length < 8) {
            alert("Password should atleast 8 character in length");
            }
            else if (password.value != cpassword.value) {
            alert("Your passwords don't match. Try again?");
            }
            else if(!(/[a-z]/g).test(password)){
            alert("Use lowercase in your password");
            }
            else if(!(/[A-Z]/g).test(password)){
            alert("Use uppercase in your password");
            }
            else if(!(/[0-9]/g).test(password)){
            alert("Use a number in your password");
            }
        else {
                postData();
           };
        });
    });





