window.MenuItem = Backbone.Model.extend({
	
	urlRoot: baseUrl + '/menu_item/',
	
	initialize: function (ids) {
		this.validators = {};
		this.set({
            menu: {id: Number(ids.menuId)}
        });

    },
    
    fetchCurrent: function (menuId, id, options) {
        options = options || {};
        
        if (options.url === undefined) {
            options.url = this.urlRoot + menuId + "/" + id;
        }

        return Backbone.Model.prototype.fetch.call(this, options);
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
	        price: 0,
	        created: "",
	        menu: new Menu()
	        };
    },
    
});

window.MenuItemCollection = Backbone.Collection.extend({
	
	model: MenuItem,
	url: baseUrl + '/menu_item/',
	
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