$(document).ready(function () {

    function getData() {
        $.ajax({
            url:"http://localhost:8080/account/check",
            type:"get",
            success: function(loggedIn) {
                if(loggedIn){
                    $('#loggedIn').css("display","flex")
                    $('#loggedOut').css("display","none")
                    $('#admin').css("display","none")
                } else {
                    $('#loggedIn').css("display","none")
                    $('#loggedOut').css("display","flex")
                    $('#admin').css("display","none")
                }
                $.ajax({
                    url:"http://localhost:8080/account/",
                    type:"get",
                    success: function(account) {
                        $.ajax({
                            url:"http://localhost:8080/employee/" + account.mail,
                            type:"get",
                            success: function(employee) {
                                 $('#loggedIn').css("display","none")
                                 $('#loggedOut').css("display","none")
                                 $('#admin').css("display","flex")
                            },
                            error: function(data){
                                 $('#loggedIn').css("display","flex")
                                 $('#loggedOut').css("display","none")
                                 $('#admin').css("display","none")
                            }
                        });
                    }
                });
            }
        });
    }
    getData();
});

