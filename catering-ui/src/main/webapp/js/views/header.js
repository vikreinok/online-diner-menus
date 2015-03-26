window.HeaderView = Backbone.View.extend({

    initialize: function () {
    	var obj = new AuthView();
    	obj.initialize();
    	
        this.searchResults = new DinerCollection();
        this.searchresultsView = new SearchResultView({model: this.searchResults, className: 'dropdown-menu'});
    },

    render: function () {    	
        $(this.el).html(this.template());        
        $('.dropdown', this.el).append(this.searchresultsView.render().el);
        
        setTimeout(function() {

        	var diner = new Diner({id: $.cookie('diner_id')});
        	$('#loadingimage').show();
            diner.fetch({
            	success: function(model, response){
            		
            		var picture = diner.get('picture');
            		
            		if(picture == '-1') {
            			var scr = "./resources/img/diner_logo.png";
            		} else {
            			var scr = baseUrl + '/file/image/' + picture;
            		}
            		scr = "'" + scr + "'";
            		var img = "<img style='width:48px; height:48px' src=" + scr + "/>"

            		$("#selected_diner").empty();
            		$("#selected_diner").prepend(img);
            		
     	        	$('#loadingimage').hide();
            	},
            	error: function(model, response) {
            		console.log("Error during header selected diner image loadign. Cookie value: " + $.cookie('diner_id'));
//             		$("#content").html(new 	ErrorView({model: response}).el);
     	        	$('#loadingimage').hide();
     	        }
            });
        	
		}, 2);
        
        return this;
    },
    
    events: {
        "keyup .search-query": "search",
        "keypress .search-query": "onkeypress",
        "click .search-query": "clean"	
    },

    search: function () {    	
    	$('#loadingimage').show();
    	
        var key = $('#searchText').val();
        
        this.searchResults.findByName(key);
        
        var size = this.searchResults.length;
        
        setTimeout(function () {        	
        	$('.dropdown').addClass('open');
        	$('#loadingimage').hide();        	
        	//$('.dropdown-menu').fadeIn(1000);
        }, 1500);
        
    },

    onkeypress: function (event) {
        if (event.keyCode == 13) {
            event.preventDefault();
        }
    },

    select: function(menuItem) {
        $('.nav li').removeClass('active');
        $('.' + menuItem).addClass('active');
    },
    
    clean: function () {      	
    	$('#searchText').val("");
    }

});