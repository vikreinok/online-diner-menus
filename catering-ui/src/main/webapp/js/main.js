window.Router = Backbone.Router.extend({
	
    routes: {
    	"": "home",
        "diners": "dinerlist",
        "diners/add": "addDiner",
        "diners/page/:page": "dinerlist",
        "diner/:id": "dinerDetails",
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
        	timeout:500000, 
        	success: function(){
	            $("#content").html(new ListView({model: diners, page: p}).el);
	            //$('#loadingModal').modal('hide');
	            $('#loadingimage').hide();
        	},	
	        error: function(model, response) {
	        	console.log(response.status);
	        	console.log(response.statusText);
	        	$("#content").html(new ErrorView({model: response}).el);
	        	$('#loadingimage').hide();
	        }
        	
        });
        this.headerView.select('diners-menu');        
    },
    
    dinerDetails: function (id) {
        var diner = new Diner({id: id});
        diner.fetch({	success: function(){        	
            $("#content").html(new DetailsView({model: diner}).el);
            $('#modifyDate').text(convertDate(diner.get('modifyDate')));
            $('#created').text(convertDate(diner.get('created')));            
            console.log("created: " + diner.get('created'));
        }});
        
        //this.headerView.selectMenuItem();
    },
    
    addDiner: function() {
        var diner = new Diner();
        $('#content').html(new DetailsView({model: diner}).el);
        //$('#deleteDinerButton').prop('disabled', true);
        $('#deleteDinerButton').hide();  
        this.headerView.select('add-menu');
	},
    
});

templateLoader.load(["HeaderView","HomeView","FooterView","ListView","ListItemView","DetailsView","SearchResultItemView", "ErrorView"],
	function () {
		app = new Router();
		Backbone.history.start();
	});
