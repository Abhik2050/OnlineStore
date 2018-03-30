$(document).ready(function () {

    $("#login-form").submit(function (event) {

   
        event.preventDefault();
       var url="/api/login";
        fire_ajax_submit(url);

    });

});

function fire_ajax_submit(url) {

    var login = {}
    login["userid"] = $("#userId").val();
    login["password"] = $("#password").val();
    $("#loginButton").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: url,
        data: JSON.stringify(login),
        async: true,
        dataType: "json",
        success: function(response) {
        //	alert('ok');
        	if(response.status=200 && response.message=='success'){
        		//alert('go to home');
        		if (typeof(Storage) !== "undefined") {
        			sessionStorage.setItem("token", response.token);
        		}
        		window.location.href = "/homepage"
        	}else{
        		window.location.href = "/loginErrorPage";
        	}
            console.log("Geodata sent");
        },
    error: function (response)
    {
    	
    	console.log("baddata sent");
    	window.location.href = "/loginErrorPage";
    }

        
    });

}