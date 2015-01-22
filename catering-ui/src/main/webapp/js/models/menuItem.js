window.MenuItem = Backbone.Model.extend({
	
	urlRoot: baseUrl + '/menu_item/',
	
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
	url: baseUrl + '/menu_item/',
//	initialize: function(models, options) {
//		console.log("model options");
//		console.log(models);
//		console.log(options);
//		if (typeof options === "undefined") {
//			console.log("something is undefined");
//			this.url =  baseUrl + '/rest/menu_item/';
//		} else {
//			console.log(options.id + "  is id");
//			this.url =  baseUrl + '/menu_item/by_menu_id/' + options.id + '/';
//		}
//	},

	
	findByMenuId:function (id) {
		var url =  baseUrl + '/menu_item/by_menu_id/' + id ;

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