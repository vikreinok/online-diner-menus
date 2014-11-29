window.Router = Backbone.Router.extend({
	
    routes: {
    	"": "home",
        "diners": "dinerlist",
        "diners/add": "addDiner",
        "diners/page/:page": "dinerlist",
        "diner/:id": "dinerDetails",
        "login": "login",
        "logout": "logout",
    },

    initialize: function () {
        this.headerView = new HeaderView();
    	$('.header').html(this.headerView.render().el);
    	this.footerView = new FooterView();		
    	$('.footer').html(this.footerView.render().el);
    	
        // Close the search dropdown on click anywhere in the UI
        $('body').click(function () {        	
        	$('.dropdown').removeClass("open");
        });
    },

    home: function () {
        //Since the home view never changes, we instantiate it and render it only once    	
        if (!this.homeView) {        	        	
            this.homeView = new HomeView();        	
            this.homeView.render();
        } else {
            this.homeView.delegateEvents(); // delegate events when the view is recycled
        }                
        $("#content").html(this.homeView.el);
        this.headerView.select('home-menu');                            
    },
    
    dinerlist: function(page) {
    	//$('#loadingModal').modal('show');

    	$('#loadingimage').show();
    	var p = page ? parseInt(page, 10) : 1;
        var diners = new DinerCollection();
        diners.fetch({
//        	timeout:5000, 
        	success: function(){
	            $("#content").html(new ListView({model: diners, page: p}).el);
	            //$('#loadingModal').modal('hide');
	            $('#loadingimage').hide();
        	},	
	        error: function(model, response) {
        		$("#content").html(new 	ErrorView({model: response}).el);
	        	$('#loadingimage').hide();
	        }
        	
        });
        this.headerView.select('diners-menu');        
    },
    
    dinerDetails: function (id) {
        var diner = new Diner({id: id});
        diner.fetch({	
        	success: function(){        	
	            $("#content").html(new DinerDetailsView({model: diner}).el);
	            $('#modifyDate').text(convertDate(diner.get('modifyDate')));
	            $('#created').text(convertDate(diner.get('created')));            
	            console.log("created: " + diner.get('created'));
        	},
        	error: function(model, response) {
         		$("#content").html(new 	ErrorView({model: response}).el);
 	        	$('#loadingimage').hide();
 	        }
        
        });
        
        //this.headerView.selectMenuItem();
    },
    
    addDiner: function() {
        var diner = new Diner();
        $('#content').html(new DinerDetailsView({model: diner}).el);
        //$('#deleteDinerButton').prop('disabled', true);
        $('#deleteDinerButton').hide();  
        this.headerView.select('add-menu');
	},
	
	login: function() {
        var login = new Login();
        $('#content').html(new LoginView({model: login}).el);
        this.headerView.select('login');
	},
	
	logout: function() {
		$('.navbar-nav li.logout').hide();
		$('.navbar-nav li.login').show();
		
		$.ajax({
		  url: "/catering-core/rest/login/",
		  method: "DELETE"
		}).done(function() {
			alert( "logged out" );
		}).fail(function() {
		   alert( "error" );
		});
	},
    
});

templateLoader.load(["ErrorView","HeaderView","HomeView","FooterView","ListView","ListItemView","DinerDetailsView","LoginView","SearchResultItemView"],
	function () {
		app = new Router();
		Backbone.history.start();
	});
