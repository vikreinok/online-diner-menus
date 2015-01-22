window.Router = Backbone.Router.extend({
	
    routes: {
    	"": "home",
        "diners": "dinerlist",
        "integration_diners": "integrationDinerlist",
        "diner/add": "addDiner",
        "diner/page/:page": "dinerlist",
        "diner/:id": "dinerDetails",
        "diner/select/:id": "selectDiner",
        "menus": "menuList",
        "menus/page/:page": "menuList",
        "menu/add": "addMenu",
        "menu/:id": "menuDetails",
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
    
    integrationDinerlist: function(page) {

    	$('#loadingimage').show();
    	var p = page ? parseInt(page, 10) : 1;
        var integrationdiners = new IntegrationDinerCollection();
        integrationdiners.fetch({
        	success: function(){
	            $("#content").html(new IntegrationDinerListView({model: integrationdiners, page: p}).el);
	            $('#loadingimage').hide();
        	},
	        error: function(model, response) {
	        	console.log("Error during integration diner service at main");
        		$("#content").html(new 	ErrorView({model: response}).el);
	        	$('#loadingimage').hide();
	        }
        	
        });
        this.headerView.select('integration-diners-menubar');
    },
    
    dinerlist: function(page) {

    	$('#loadingimage').show();
    	var p = page ? parseInt(page, 10) : 1;
        var diners = new DinerCollection();
        diners.fetch({
        	success: function(){
	            $("#content").html(new DinerListView({model: diners, page: p}).el);
	            $('#loadingimage').hide();
        	},
	        error: function(model, response) {
        		$("#content").html(new 	ErrorView({model: response}).el);
	        	$('#loadingimage').hide();
	        }
        	
        });
        this.headerView.select('diners-menubar');
    },
    
    dinerDetails: function (id) {
    	$('#loadingimage').show();
        var diner = new Diner({id: id});
        diner.fetch({
        	success: function(){
	            $("#content").html(new DinerDetailsView({model: diner}).el);
	            $('#modifyDate').text(convertDate(diner.get('modifyDate')));
	            $('#created').text(convertDate(diner.get('created')));
	            $('#loadingimage').hide();
        	},
        	error: function(model, response) {
         		$("#content").html(new 	ErrorView({model: response}).el);
 	        	$('#loadingimage').hide();
 	        }
        
        });
    },
    
    selectDiner: function (id) {
    	$.cookie('diner_id', id);
    	window.location.replace("./#diners");
    	window.location.reload();
    },
    
    addDiner: function() {
        var diner = new Diner();
        $('#content').html(new DinerDetailsView({model: diner}).el);
        $('#deleteDinerButton').hide();  
        this.headerView.select('add-diner-menubar');
	},

	// List items
	
    menuList: function(page) {
    	console.log("diner_id " + $.cookie('diner_id'));
    	
    	$('#loadingimage').show();
    	var p = page ? parseInt(page, 10) : 1;
        var menus = new MenuCollection();
        menus.fetch({
        	success: function(){
	            $("#content").html(new MenuListView({model: menus, page: p}).el);
	            $('#loadingimage').hide();
        	},	
	        error: function(model, response) {
        		$("#content").html(new 	ErrorView({model: response}).el);
        		$('#loadingimage').hide();
	        }
        	
        });
        this.headerView.select('menus-menubar');        
    },
    
    menuDetails: function (id) {
    	$('#loadingimage').show();
        var menu = new Menu({id: id});
        menu.fetch({
        	success: function(){        	
	            $("#content").html(new MenuDetailsView({model: menu}).el);
	            $('#modifyDate').text(convertDate(menu.get('modifyDate')));
	            $('#created').text(convertDate(menu.get('created')));           
	            $('#loadingimage').hide();
        	},
        	error: function(model, response) {
         		$("#content").html(new 	ErrorView({model: response}).el);
 	        	$('#loadingimage').hide();
 	        }
        });
    },
	
	addMenu: function() {
		var menu = new Menu();
		$('#content').html(new MenuDetailsView({model: menu}).el);
		$('#deleteMenuButton').hide();  
		this.headerView.select('add-menu-menubar');
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
     		$("#content").html(new 	AuthView().el);
     		$.cookie("authenticated", false);
		}).fail(function() {
		   alert( "Error during logout" );
		});
	},
    
});

templateLoader.load(["ErrorView","HeaderView","HomeView","FooterView","IntegrationDinerListView","IntegrationDinerListItemView","DinerListView","DinerListItemView", "MenuListItemView", "MenuListView", "MenuDetailsView", "DinerDetailsView","LoginView","SearchResultItemView"],
	function () {
		app = new Router();
		Backbone.history.start();
	});
