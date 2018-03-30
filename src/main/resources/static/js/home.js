var token='';
$(document).ready(function () {
	
	if (typeof(Storage) !== "undefined") {
		token=sessionStorage.getItem("token");
		//alert('token='+token)
	}
	authenticate();
    $("#Quick_Add").click(function (event) {

   
        event.preventDefault();
      
       var products = {}
       products["qty"] = $("#qty").val();
       products["id"] = $("#itemId").val();
       products["name"]=$("#name").val();
       products["description"]=$("#description").val();
       validate(products);
      

    });
    
    
});
function validate(products){
	if(products.id==''||products.qty==''||products.name==''){
		var ermsg='<h4 style="color:red;">Field/fields should be blank</h4>';
		$("#error").append(ermsg);
		
	}else{
		 var url="/add";
		 fire_ajax_submitRequest(url,products);
	}
}

$(window).on("load",getProductList);
function deleteProduct(param) {
    
    var url="/delete";
    //alert(param);
    var req = {}
    req["id"]=param;
    fire_ajax_submitRequest(url, req);

}
function editProduct(param) {
    
  
    //alert(param);
    
    var req = {}
    req["id"]=param;
    var url="/edit/"+param;
    window.location.href = url;
    
}
function getProductList() {
	
	var url="/fetchProductList";	
	fire_ajax_fetchProductList(url);
}

function fire_ajax_fetchProductList(url) {
	var req = {}
	req["token"]=token;
    
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: url,
        async: true,
        data: JSON.stringify(req),
        dataType: "json",
        success: function(response) {
        //	alert('ok');
        	if(response.status=200){
        		if(response.message=='fail'){
        			window.location.href="/welcome";
        			
        		}else{
        		$("#getResultDiv").empty();
        		$.each(response,function(i, value){
        		var products= '<div class="alignCenter padding15 boldText">'+
        		'<div class="floatLeft selectWidth20 alignLeft blueText">'+
        		'<div class="floatLeft selectWidth70"> <input type="submit" id ="Quick_Edit" value="Quick Edit" class="" onclick="editProduct('+value.id+')"></div>'+
        		'<div class="floatLeft">|</div> <div class=""><button onclick="deleteProduct('+value.id+')" class="trashBlack"></button></div>'+
                '<div class="clear"></div>'+
            '</div>'+                
            '<div class="floatLeft selectWidth15">'+value.id+'</div>'+
            
            '<div class="floatLeft selectWidth10">'+value.qty+'</div>'+
            '<div class="floatLeft selectWidth10 alignLeft">'+value.name+'</div>'+
            '<div class="floatLeft selectWidth10">'+value.description+'</div>'+
            '<div class="clear"></div>'+
        '</div>';
        $('#getResultDiv').append(products)
        		
        		});
        		}
        		//alert('go to home');
        		//window.location.href = "/homepage"
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

function fire_ajax_submitRequest(url, request) {

    
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: url,
        data: JSON.stringify(request),
        dataType: "json",
        success: function(response) {
       // 	alert('ok');
        	if(response.status=200){
        		if(response.message=='success'){
        		//alert('go to next page');
        		window.location.href = "/"+response.nextPage+"/"+response.id;
        		}else{
        			//alert("field/fields should not be blank")
        			window.location.href = "/"+response.nextPage;
        		}
        
            console.log("Geodata sent");
        }
        	},
    error: function (response)
    {
    	
    	console.log("baddata sent");
    	window.location.href = "/loginErrorPage";
    }

        
    });
}
function authenticate(){
	var req = {}
	req["token"]=token;
    var url="/api/authenticate";
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: url,
        async: true,
        data: JSON.stringify(req),
        dataType: "json",
        success: function(response) {
       // 	alert('ok');
        	if(response.status=200){
        		if(response.message=='fail'){
        			window.location.href="/welcome";
        			
        		}

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


