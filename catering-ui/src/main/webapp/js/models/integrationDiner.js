window.IntegrationDiner = Backbone.Model.extend({
	
	urlRoot: "/catering-core/rest/diner/integration/",
	
	initialize: function () {
		 
    },
    
    parse: function(response){
        for(var key in this.model)
        {
            var embeddedClass = this.model[key];
            var embeddedData = response[key];
            response[key] = new embeddedClass(embeddedData, {parse:true});
        }
        return response;
    },
    
    defaults: {
        id: null,
        name: "",
        description: "",
        picture: "-1",
        modifyDate: "",
        created: ""
    }
    
});

window.IntegrationDinerCollection = Backbone.Collection.extend({
	model: IntegrationDiner,
	url: "/catering-core/rest/diner/integration/",
	
	findByName:function (key) {
		var url = '/catering-core/rest/diner/name/'+ key;

		var self = this;
	    $.ajax({
	        url:url,
	        dataType:"json",
	        success:function (data) {
	            self.reset(data);
	        }
	    });
	}

});