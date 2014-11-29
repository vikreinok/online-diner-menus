window.AuthView = Backbone.View.extend({

    initialize: function () {
    	console.log("initialize");
    	
		$.ajax({
		    url: "/catering-core/rest/login/",
		    method: "GET"
		}).done(function(data) {
			
			var items = [];
			$.each( data, function( key, val ) {
				if(key == 'loggedIn') {
					console.log("loggedIn " + val)
					if(val == true) {
						$('.navbar-nav li.logout').show();
						$('.navbar-nav li.login').hide();
						$('#diner-autofill-search').show();
						
					} else {
						$('.navbar-nav li.logout').hide();
						$('.navbar-nav li.login').show();
						$('#diner-autofill-search').hide();
						
						app.navigate('/login',  true);
					}
				}
			});
			
		}).fail(function(data) {
	    	$('.navbar-nav li.logout').hide();
			$('.navbar-nav li.login').show();
		});
		
    },
 
});