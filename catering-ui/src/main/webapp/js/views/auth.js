window.AuthView = Backbone.View.extend({

    initialize: function () {
    	
    	// We do no do double requests.
    	if (!document.URL.match("#diner/|#menu/")) {
    		return;
		}
    	
    	var self = this;
    	var promise = SessionManager.isActive();
    	
    	promise.then(function(data) {
        	console.log("auth initialize " + JSON.parse(data));
    		
    	    if(data == true ) {
    	    	self.unauthenticated();
			} else {
				self.authenticated();
    	    }
    	  }, function(error) {
    		  //TODO server down show error
    		  self.unauthenticated();
    	  });
		
    },
    
    authenticated: function() {
    	$('.navbar-nav li.logout').hide();
		$('.navbar-nav li.login').show();
		$.cookie("authenticated", false);
    },
    
    unauthenticated: function() {
    	$('.navbar-nav li.logout').show();
		$('.navbar-nav li.login').hide();
		$.cookie("authenticated", true);
    }, 
 
});