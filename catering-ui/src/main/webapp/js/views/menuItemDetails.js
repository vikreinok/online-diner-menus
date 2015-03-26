window.MenuItemDetailsView = AuthView.extend({

    initialize: function () {
		DinerDetailsView.__super__.initialize.apply(this, arguments);  

    	this.render();
    	this.afterRender();
    },

    render: function () {
        $(this.el).html(this.template(this.model.toJSON()));                
        return this;
    },

    afterRender: function() {
		setTimeout(function() {
			if ($.cookie("authenticated") == "true") {
				$('#saveMenuItemButton').show();
				$('#deleteMenuItemButton').show();
			} else {
				$('#saveMenuItemButton').hide();
				$('#deleteMenuItemButton').hide();
			}
		}, 1);
    },
    
    events: {
        "change"               : "change",
        "click .save"          : "beforeSave",
        "click .deleteConfirm" : "deleteMenuConfirmation",
        "click .delete"        : "deleteMenuItem"
    },

    change: function (event) {
    	
        // Remove any existing alert message
        utils.hideAlert();

        // Apply the change to the model
        var target = event.target;
        var change = {};
        
    	change[target.name] = target.value;
    	this.model.set(change);

        // Run validation rule (if any) on changed item
        var check = this.model.validateItem(target.id);
        
        if (check.isValid === false) {
            utils.addValidationError(target.id, check.message);
        } else {
            utils.removeValidationError(target.id, this.model);
        }
        
    },

    beforeSave: function () {
        var self = this;
        
        //Check information
        var check = this.model.validateAll();        
        if (check.isValid === false) {
            utils.displayValidationErrors(check.messages);
            return false;
        }
        
		this.saveMenu();		
    },
    
    saveMenu: function () {
        var self = this;
        this.model.save(null, {
            success: function (model) {
                self.render();
                $('#created').text(convertDate(model.get('created')));
                app.navigate('/menu/' + model.id, false);
                utils.showAlert('Success!', 'Menu item saved successfully', 'alert-success', '#menu');
            },
            error: function () {
                utils.showAlert('Error', 'An error occurred while trying to save this menu item', 'alert-danger');
            }            
        });
    },

    deleteMenuConfirmation: function () {        
    	$('#deleteConfirmation').modal('show');    	
    },
    
    deleteMenuItem: function () {    	   	
    	$('#deleteConfirmation').modal('hide');
    	$('body').removeClass('modal-open');
    	$('.modal-backdrop').remove();
        this.model.destroy({
            success: function () {
                window.history.back();
            }
        });
    },
        	    
});