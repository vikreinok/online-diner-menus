window.Menu = Backbone.Model.extend({
	
	urlRoot: baseUrl + '/menu/',
	
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
    
    defaults: function() {
        return {
        	id: null,
            name: "",
            diner: null,
            menuItems: new MenuItemCollection(),
            modifyDate: "",
            created: ""
        };
    },
    
});

// If diner id is not selected print all menus
function getUrl() {
	var url = '';
	var dinerId = $.cookie('diner_id');
	if (typeof dinerId != 'undefined') {
		url = 'diner/menus/' + $.cookie('diner_id');
	} else {
		url = 'menu/';
	}
	return url;
}

window.MenuCollection = Backbone.Collection.extend({
	model: Menu,
	url: baseUrl + getUrl(),
});