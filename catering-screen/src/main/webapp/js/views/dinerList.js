window.DinerListView = Backbone.View.extend({

    initialize: function (options) {
    	DinerListView.__super__.initialize.apply(this, arguments);

    	this.options = options;
        this.render();
    },

    render: function () {
        var list = this.model.models;
        var len = list.length;
        var startPos = (this.options.page - 1) * 8;
        var endPos = Math.min(startPos + 8, len);

        $(this.el).html(this.template());

        //$('.diner', this.el).append(new DinerListItemView({model: list[i]}).render().el);

        for (var i = startPos; i < endPos; i++) {
            $('.dinerlist', this.el).append(new DinerListItemView({model: list[i]}).render().el);
        }

        $(this.el).append(new Paginator({model: this.model, page: this.options.page, view: '#diner'}).render().el);

        return this;
    }
});

window.DinerListItemView = Backbone.View.extend({
    
	initialize: function () {
        this.model.bind("change", this.render, this);
        this.model.bind("destroy", this.close, this);
    },

    render: function () {
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    }

});
