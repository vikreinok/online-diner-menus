window.MenuDetailsView = AuthView.extend({

    initialize: function () {
    	MenuDetailsView.__super__.initialize.apply(this, arguments);    
        this.render();
    },

    render: function () {
        $(this.el).html(this.template(this.model.toJSON()));                
        return this;
    },

    events: {
        "change"               : "change",
        "click .save"          : "beforeSave",
        "click .deleteConfirm" : "deleteMenuConfirmation",
        "click .delete"        : "deleteMenu"
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
        
        //Check picture file                            		
		//this.checkFileAndExtension();
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
                $('#modifyDate').text(convertDate(model.get('modifyDate')));
                $('#created').text(convertDate(model.get('created')));
                app.navigate('/menu/' + model.id, false);
                utils.showAlert('Success!', 'Menu saved successfully', 'alert-success', '#menu');
            },
            error: function () {
                utils.showAlert('Error', 'An error occurred while trying to save this menu', 'alert-danger');
            }            
        });
    },

    deleteMenuConfirmation: function () {        
    	$('#deleteConfirmation').modal('show');    	
    },
    
    deleteMenu: function () {    	   	
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