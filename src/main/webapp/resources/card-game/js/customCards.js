/**
 * Show popover when user clicks share button and send sharing card notification  
 */

$(function(){
  // toggle popover, populate popover with user input form
  $('.share').popover({ 
    html: true,
    content: function() {
    	return $(this).siblings('#popover-content').html();
    }
  }); 

  // put cursor on user text field as soon as share button clicked
  $('.share').on('shown.bs.popover', function () {
	  $(this).siblings().find('input[type="text"]').focus();
	})
  
// only 1 share popover open at any time
// from http://stackoverflow.com/questions/11703093/how-to-dismiss-a-twitter-bootstrap-popover-by-clicking-outside	
$('body').on('click', function (e) {
    $('[data-toggle="popover"]').each(function () {
        //the 'is' for buttons that trigger popups
        //the 'has' for icons within a button that triggers a popup
        if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {
            $(this).popover('hide');
        }
    });
});	
	
  // send share notification via ajax
  $(document).on("click", "input[type='submit']", function() {
	  var $cardId = $(this).siblings('input[type="hidden"]');
	  var $user = $(this).siblings('input[type="text"]');
	  var $form = $(this).parent();
	  var $status = $(this).siblings('#shareStatus');
	  
	  $form.submit(function(e){
		  e.preventDefault();  // prevents form from being submitted normally which would cause full page refresh
		  $status.text("Working...");
		  $.ajax({
			  data: {
				  cardId: $cardId.attr("value"),
				  user: $user.val()
			  },
			  dataType: "text",
			  timeout: 5000,
			  // code to run regardless of success or failure
			  complete: function( xhr, status ) {
				  	if (status === "success"){
					    $status.text(xhr.responseText);
					    $status.delay(2000).fadeOut(500);
				  	}else{
				  		$status.text("Share failed");
				  		$status.delay(2000).fadeOut(500);
				  	}
			  }
		  });
	  });
  });
});