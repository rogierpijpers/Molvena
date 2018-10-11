$(document).ready(function () {

    function getData() {
        $.ajax({
            url:"http://localhost:8080/account/check",
            type:"get",
            success: function(data) {
                if(data){
                    $('#loggedIn').css("display","flex")
                    $('#loggedOut').css("display","none")
                } else {
                    $('#loggedIn').css("display","none")
                    $('#loggedOut').css("display","flex")
                }
            }
        });
    }

    getData();
});

