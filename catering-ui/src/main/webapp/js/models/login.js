window.Login = Backbone.Model.extend({
	
	urlRoot: "http://localhost:8080/catering-core/rest/login/",
	
	initialize: function () {
		this.validators = {};

        this.validators.username = function (value) {
            return value.length > 0 ? {isValid: true} : {isValid: false, message: "You must enter username"};
        };
        
        this.validators.password = function (value) {
            return value.length > 0 ? {isValid: true} : {isValid: false, message: "You must enter password"};
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
    	loggedIn: false,
    	username: "",
    	password: "",
    	rememberMe: true
    }
    
});
