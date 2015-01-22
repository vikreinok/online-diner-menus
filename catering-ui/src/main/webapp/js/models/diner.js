window.Diner = Backbone.Model.extend({
	
	urlRoot: "/catering-core/rest/diner/",
	
	initialize: function () {
		this.validators = {};

        this.validators.name = function (value) {
            return value.length >= 2 && value.length <= 20  ? {isValid: true} : {isValid: false, message: "You must enter an diner name from 2 to 20 characters"};
        };
        
        this.validators.description	= function (value) {
            return value.length >= 10 && value.length <= 50  ? {isValid: true} : {isValid: false, message: "You must enter an diner description from 10 to 50 characters"};
        };
        
    },
    
    validateItem: function (key) {
        return (this.validators[key]) ? this.validators[key](this.get(key)) : {isValid: true};
    },
	
	validateAll: function () {

        var messages = {};

        for (var key in this.validators) {
            if(this.validators.hasOwnProperty(key)) {
                var check = this.validators[key](this.get(key));
                if (check.isValid === false) {
                    messages[key] = check.message;
                }
            }
        }

        return _.size(messages) > 0 ? {isValid: false, messages: messages} : {isValid: true};
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

window.DinerCollection = Backbone.Collection.extend({
	model: Diner,
	url: "/catering-core/rest/diner/",

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