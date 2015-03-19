window.MenuListView = Backbone.View.extend({

    initialize: function (options) {
    	this.options = options || {};
        this.render();
    },

    render: function () {
        var list = this.model.models;
        var len = list.length;
        var startPos = (this.options.page - 1) * 8;
        var endPos = Math.min(startPos + 8, len);

        $(this.el).html(this.template());

        for (var i = startPos; i < endPos; i++) {
            $('.menulist', this.el).append(new MenuListItemView({model: list[i]}).render().el);
        }

        $(this.el).append(new Paginator({model: this.model, page: this.options.page, view: "#menu"}).render().el);

        return this;
    }
});

window.MenuListItemView = Backbone.View.extend({
    
	initialize: function () {
        this.model.bind("change", this.render, this);
        this.model.bind("destroy", this.close, this);
    },

    render: function () {
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    }

});

window.SearchResultView = Backbone.View.extend({
	 tagName:'ul',

	 className:'dropdown-menu',

	 initialize:function () {
	    var self = this;
	    this.model.bind("reset", this.render, this);
	    this.model.bind("add", function (diner) {
	        $(self.el).append(new SearchResultItemView({model:diner}).render().el);
	    });
	 },

     render:function () {
        $(this.el).empty();  
        if(this.model.size() > 0){
	        _.each(this.model.models, function (diner) {        	        	
	        	$(this.el).append(new SearchResultItemView({model:diner}).render().el);        	
	        }, this);
        } else {
        	$(this.el).append('<li class="elementSearchResult disabled"><a href="#"><div class="row"><div class="col-xs-4"><img src="./resources/img/no_result.png" alt=""></div><div class="col-xs-8" style="padding-top:5px">No result</div></div></a></li>');
        } 
        	
        return this;
     }
});

window.SearchResultItemView = Backbone.View.extend({

    tagName:"li",
    
    className: "elementSearchResult",

    initialize:function () {
        this.model.bind("change", this.render, this);
        this.model.bind("destroy", this.close, this);
    },

    render:function () {
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    }

});