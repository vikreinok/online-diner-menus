window.ErrorView = Backbone.View.extend({
	
    initialize: function () {
    	var response = this.model;
    	
    	if(response.status == 401) {
	    	var obj = new AuthView();
	    	obj.initialize();
    	} else {
    		this.render();
    	}
    },

    render: function () {
        var response = this.model;

        $(this.el).html(this.template());

        $('#error_content', this.el).append("<ul>");
        $('#error_content', this.el).append("<li>"+response.status+"</li>");
        $('#error_content', this.el).append("<li>"+response.statusText+"</li>");
        $('#error_content', this.el).append("</ul>");
        
        
        console.log(parseInt(response.status));
        
        if(response.status == 404)
        	$('#error_solution', this.el).append("Please call 56252830 to start server");
        else if(response.status == 500)
        	$('#error_solution', this.el).append("Internal server error. Possible database outage.");
        else
        	$('#error_solution', this.el).append("Some other error");

    }
});


 
 