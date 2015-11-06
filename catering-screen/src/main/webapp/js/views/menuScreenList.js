window.MenuScreenListView = Backbone.View.extend({

    initialize: function (options) {
        MenuScreenListView.__super__.initialize.apply(this, arguments);
    	
    	this.options = options;
        this.render();
    },

    render: function () {
        var list = this.model.models;
        var len = list.length;

        $(this.el).html(this.template());

        for (var i = 0; i < len; i++) {
            $('.menuScreenList', this.el).append(new MenuScreenListItemView({model: list[i]}).render().el);
        }

        return this;
    }
});

window.MenuScreenListItemView = Backbone.View.extend({
    
	initialize: function () {
        this.model.bind("change", this.render, this);
        this.model.bind("destroy", this.close, this);
    },

    render: function () {
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    }

});