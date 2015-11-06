//var baseUrl = 'http://localhost:8080/catering-core/rest/'
var baseUrl = 'http://imbi.ld.ttu.ee:443/catering-core/rest/'
//var baseUrl = 'http://192.168.1.31:8080/catering-core/rest/'

var token = $.cookie("token");

//Inject custom auth token to backbone
var backboneSync = Backbone.sync;
Backbone.sync = function (method, model, options) {
    var token = SessionManager.getToken();
    options.headers = {
        "X-Token": token
    };
    var url = _.result(model, 'url');
    console.log(method.toUpperCase() + "(" + url + "): ", model.toJSON());
    backboneSync(method, model, options);
};
