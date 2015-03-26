window.IntegrationDinerListView = Backbone.View.extend({

    initialize: function (options) {
    	this.options = options;
        this.render();
    },

    render: function () {
        var list = this.model.models;
        var len = list.length;
        var startPos = (this.options.page - 1) * 8;
        var endPos = Math.min(startPos + 8, len);

        $(this.el).html(this.template());

        for (var i = startPos; i < endPos; i++) {
            $('.integrationdinerlist', this.el).append(new IntegrationDinerListItemView({model: list[i]}).render().el);
        }

        $(this.el).append(new Paginator({model: this.model, page: this.options.page}).render().el);

        return this;
    }
});

window.IntegrationDinerListItemView = Backbone.View.extend({
    
	initialize: function () {
        this.model.bind("change", this.render, this);
        this.model.bind("destroy", this.close, this);
    },

    render: function () {
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    }

});
