window.DinerDetailsView = AuthView.extend({

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
				$('#saveDinerButton').show();
				$('#deleteDinerButton').show();
			} else {
				$('#saveDinerButton').hide();
				$('#deleteDinerButton').hide();
			}
		}, 1);
   },

    events: {
        "change"        : "change",
        "click .save"   : "beforeSave",
        "click .deleteConfirm" : "deleteDinerConfirmation",
        "click .delete" : "deleteDiner",
        "change .btn-file"     : "uploadImage",                        	
    },

    change: function (event) {
    	
        // Remove any existing alert message
        utils.hideAlert();

        // Apply the change to the model
        var target = event.target;
        var change = {};
        
        // Not save input file (upload) changes in the model
        if(target.name != 'fileInput'){
        	change[target.name] = target.value;
        	this.model.set(change);
        } 

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
    	$('#loadingimage').show();
        var self = this;
        
        //Check information
        var check = this.model.validateAll();        
        if (check.isValid === false) {
            utils.displayValidationErrors(check.messages);
            $('#loadingimage').hide();
            return false;
        }
        
        //Check picture file                            		
		if(this.checkFileAndExtension()) {
			this.model.set("picture", utils.createFileName());
			this.saveFile();
		} 
			
		this.saveDiner();		
        $('#loadingimage').hide();
		      
    },
    
    saveDiner: function () {
        var self = this;        
        this.model.save(null, {        	
            success: function (model) {
                self.render();
                $('#loadingModal').modal('hide');
                $('#modifyDate').text(convertDate(model.get('modifyDate')));
                $('#created').text(convertDate(model.get('created')));
                app.navigate('/diner/' + model.id, false);
                utils.showAlert('Success!', 'Diner saved successfully', 'alert-success', '#diners');
            },
            error: function () {
                utils.showAlert('Error', 'An error occurred while trying to save this diner', 'alert-danger');
            }            
        });
    },

    deleteDinerConfirmation: function () {        
    	$('#deleteConfirmation').modal('show');    	
    },
    
    deleteDiner: function () {    	   	
    	$('#deleteConfirmation').modal('hide');
    	$('body').removeClass('modal-open');
    	$('.modal-backdrop').remove();
        this.model.destroy({
            success: function () {
            	//app.navigate('diners', false);
                window.history.back();
            },
	        error: function(model, response) {
	        	utils.showAlert('Warning!', 'Delete related menus before deleting diner', 'alert-warning', '#diners');
	        }
        });
    },
     
    uploadImage: function (event) {
    	$('#loadingModal').modal('show');    	
    	var fileInput = event.target;
    	
		numFiles = fileInput.files ? fileInput.files.length : 1;
		
		//filename management and display    				
		if( numFiles > 0 ) {
			label = fileInput.value.replace(/\\/g, '/').replace(/.*\//, '');
			
			//$('#filenameInput').val(label);
			$('#filename').text(label);
						
			//check file if selected and extension is valid			 	        
			if(this.checkFileAndExtension()){
				var reader = new FileReader();
		        reader.onloadend = function () {
		            $('#pictureFile').attr('src', reader.result);
		        };
				//Read the image file from the local file system and display it in the img tag    				       
		        reader.readAsDataURL(utils.getFile());
			} else{
				$('#pictureFile').attr('src', './resources/img/diner_logo.png');				
				return false;
			} 				
		}		
		$('#loadingModal').modal('hide');		
    },
    
    saveFile: function() {
    	 $('#loadingModal').modal('show');
    	 
    	var data = new FormData($("#fileuploadForm"));		
    	data.append('file', utils.getFile());
		var token = SessionManager.getToken();
		
		$.ajax({
		    url: baseUrl + 'file/image/' + this.model.get('picture'),
		    headers: {
		    	"X-Token": token
		    },
		    data: data,
		    cache: false,
		    contentType: false,
		    processData: false,
		    type: 'POST',
		    
		    success: function(data){
		       
		        $('#loadingModal').modal('hide');
		        			       
		    },
		    
			error: function(data){
		        alert('no upload');
		        $('#loadingModal').modal('hide');
		    }
		});
    },
    
    checkFileAndExtension: function() {
    	// File selected
    	if(utils.checkFile()){
    		// Ext is valid
    		if(utils.checkFileExt()){
        	    return true;
    		} else {
    			utils.showAlert('Error', 'Only image files are allowed ( .jpg, .jpeg, .png )', 'alert-danger');
    			this.model.set("picture", "-1");
        	    $('#loadingModal').modal('hide');
        	    return false;
    		}
    	}  else {
    		return false;    		
    	}   	
    }
        	    
});