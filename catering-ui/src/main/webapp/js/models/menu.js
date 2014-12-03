window.Menu = Backbone.Model.extend({
	
	urlRoot: "http://localhost:8080/catering-core/rest/menu/",
	
	initialize: function () {
		this.validators = {};

        this.validators.name = function (value) {
            return value.length >= 2 && value.length <= 20  ? {isValid: true} : {isValid: false, message: "You must enter an diner name from 2 to 15 characters"};
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
    
    defaults: {
        id: null,
        name: "",
        modifyDate: "",
        created: ""
    }
    
});

window.MenuCollection = Backbone.Collection.extend({
	model: Menu,
	url: "/catering-core/rest/menu/",

	findByName:function (key) {
		var url = '/catering-core/rest/menu/name/'+ key;

		var self = this;
	    $.ajax({
	        url:url,
	        dataType:"json",
	        success:function (data) {
	            console.log("search success: " + data.length);
	            self.reset(data);
	        }
	    });
	}
});