window.DinerLogoView = Backbone.View.extend({

    initialize: function () {
		DinerLogoView.__super__.initialize.apply(this, arguments);
		
        this.render();
        this.afterRender();
    },

    render: function () {
        $(this.el).html(this.template(this.model.toJSON()));                
        return this;
    },
    
    afterRender: function() {
		setTimeout(function() {

		}, 1);
   },
        	    
});