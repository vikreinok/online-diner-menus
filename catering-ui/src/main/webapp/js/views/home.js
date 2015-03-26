window.HomeView = Backbone.View.extend({

    initialize:function () {
        //this.template = _.template(directory.utils.templateLoader.get('home'));
        //this.template = templates['Home'];
    },

    events:{
//        "click #  ":" ",                	
    },

    render:function () {
        $(this.el).html(this.template());
        return this;
    },

    
});