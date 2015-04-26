window.HomeView =  AuthView.extend({

    initialize:function () {
    	HomeView.__super__.initialize.apply(this, arguments);
    	
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