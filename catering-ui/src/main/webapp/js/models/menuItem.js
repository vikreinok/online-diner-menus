window.MenuItem = Backbone.Model.extend({
	
	urlRoot: "http://localhost:8080/catering-core/rest/menu_item/",
	
	initialize: function () {
		this.validators = {};
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
    
    defaults: function() {
        return {
        	id: null,
            name: "",
            price: 0
        };
    },
    
});
	
window.MenuItemCollection = Backbone.Collection.extend({
	model: MenuItem,
	url: "/catering-core/rest/menu_item/",

	findByName:function (key) {
		var url = '/catering-core/rest/menu_item/name/'+ key;

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