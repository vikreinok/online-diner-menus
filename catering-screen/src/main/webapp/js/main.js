window.Router = Backbone.Router.extend({

    routes: {
        "": "menuScreen",
        "diners": "dinerlist",
        "diner/select/:id": "selectDiner",
        "menuscreen": "menuScreen",
    },

    initialize: function () {
        this.footerView = new FooterView();
        $('.footer').html(this.footerView.render().el);

        var dinerId = $.cookie('diner_id');

        // Close the search dropdown on click anywhere in the UI
        $('body').click(function () {
            $('.dropdown').removeClass("open");
        });
    },

    dinerlist: function (page) {

        $('#loadingimage').show();
        var p = page ? parseInt(page, 10) : 1;
        var diners = new DinerCollection();
        diners.fetch({
            success: function () {
                $("#content").html(new DinerListView({model: diners, page: p}).el);
                $('#loadingimage').hide();
            },
            error: function (model, response) {
                $('#loadingimage').hide();
            }

        });
    },


    menuScreen: function (page) {

        var dinerId = $.cookie('diner_id');
        console.log("cookie diner_id " + dinerId);

        if (typeof dinerId == 'undefined') {
            console.log("redirect to diners");
            window.location.replace("./#diners");
            return;
        }

        $("#content").html(new MenuScreenListView({model: new MenuCollection()}).el);

        var diner = new Diner({id: dinerId});
        diner.fetch({
            success: function () {
                $(".dinerPic").html(new DinerLogoView({model: diner}).el);
            },
            error: function (model, response) {

            }
        });

        var menus = new MenuCollection();
        menus.fetch({
            success: function () {
                $(".menuList").html(new MenuScreenListView({model: menus}).el);
            },
            error: function (model, response) {

            }
        });

        const intervalPeriod = 10000;
        setInterval(function () {

            var self = this;
            var promise = SessionManager.isActive();

            promise.then(function (data) {
                console.log("auth initialize " + JSON.parse(data));

                if (data == true) {
                    var menus = new MenuCollection();
                    menus.fetch({
                        success: function () {
                            $(".menuList").html(new MenuScreenListView({model: menus}).el);
                        },
                        error: function (model, response) {

                        }
                    });
                } else {

                }
            }, function (error) {
                //TODO server down show error

            });


        }, intervalPeriod);

    },

    selectDiner: function (id) {
        console.log("Selected a diner with id " + id);
        $.cookie('diner_id', id);
        window.location.replace("./#menuscreen");
        window.location.reload();
    },

});

templateLoader.load(
    [
        "FooterView",
        "DinerLogoView",
        "DinerListView",
        "DinerListItemView",
        "MenuScreenListView",
        "MenuScreenListItemView"
    ],
    function () {
        app = new Router();
        Backbone.history.start();
    }
);
