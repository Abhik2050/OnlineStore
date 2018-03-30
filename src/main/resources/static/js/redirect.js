function redirectToHome(){
	window.location.href = "/homepage";
}

$( "#form" ).submit(function( event ) {
	alert('trying to submit');
  if ( $( "#qty" ).val() === ''|| $( "#select_name" ).val()==='') {
	  var ermsg='<h4 style="color:red;">Field/fields should be blank</h4>';
		$("#error").append(ermsg);
	  event.preventDefault();
  }
 return;
  
});