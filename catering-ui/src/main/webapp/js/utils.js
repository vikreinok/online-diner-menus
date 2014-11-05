// The Template Loader. Used to asynchronously load templates located in separate .html files
window.templateLoader = {
    load: function(views, callback) {
        var deferreds = [];
        $.each(views, function(index, view) {
            if (window[view]) {
                deferreds.push($.get('tpl/' + view + '.html', function(data) {
                    window[view].prototype.template = _.template(data);
                }, 'html'));
            } else {
                alert(view + " not found");
            }
        });

        $.when.apply(null, deferreds).done(callback);
    },
};

window.utils = {
	
	displayValidationErrors: function (messages) {
	    for (var key in messages) {
	        if (messages.hasOwnProperty(key)) {
	            this.addValidationError(key, messages[key]);
	        }
	    }
	    //this.showAlert('Warning!', 'Fix validation errors and try again', 'alert-warning');
	},
	
	addValidationError: function (field, message) {
	    var controlGroup = $('#formGroup' + field);
	    controlGroup.addClass('has-error');
	    $('#msg' + field).text(message);
	    $('#saveDinerButton').prop('disabled', true);
	    $('#deleteDinerButton').prop('disabled', true);
	},
	
	removeValidationError: function (field, model) {
		var controlGroup = $('#formGroup' + field);
		controlGroup.removeClass('has-error');
	    controlGroup.addClass('has-success');
	    $('#msg' + field).text('');
	    
	    var check = model.validateAll();
        
        if (check.isValid === true) {
        	$('#saveDinerButton').prop('disabled', false);
    	    $('#deleteDinerButton').prop('disabled', false);
        }
	    
	},
	
	/*Alert main*/
	showAlert: function(title, text, klass) {
	    $('.alert').removeClass("alert-danger alert-warning alert-success alert-info");
	    $('.alert').addClass(klass);
	    $('.alert').html('<strong>' + title + '</strong> ' + text);
	    
	    if(klass == 'alert-success'){
	    	$('.alert').append(' <a href="#diners" class="alert-link">Back to diners list</a>');
	    }
	    
	    $('.alert').show();
	},
	
	hideAlert: function() {
	    $('.alert').hide();
	},
		
	/*Utility method for image uploading*/
    getFile: function() {
    	var files = $('input[name="fileInput"]')[0].files;
    	return files[0];
    },
    
    createFileName: function() {
    	var filename = 'pictureId' + $.now() + '.' + this.getFileExt();
    	return filename;
    	//return $('#filenameInput').text();
    },
    
    createFileId: function() {
//    	var filename = 'pictureId' + $.now() + '.' + this.getFileExt();    	
    	
		  var hash = 0, i, chr, len;
		  if (this.getFileExt().lenght == 0) return hash;
		  for (i = 0, len = this.getFileExt().lenght; i < len; i++) {
		    chr   = this.getFileExt().charCodeAt(i);
		    hash  = ((hash << 5) - hash) + chr;
		    hash |= 0; // Convert to 32bit integer
		  }
		  console.log("hash of imageFileName: " + hash);
		  return hash;
    },
    
    getFileExt: function() {
    	var files = $('input[name="fileInput"]')[0].files;
    	var ext = null;
    	if(files[0] != null){
    		var filename = files[0].name.replace(/\\/g, '/').replace(/.*\//, '');
    		ext = filename.replace(/^.*\./, '').toLowerCase();
    	}

    	return ext;
    },
    
    checkFile: function() {
    	var files = $('input[name="fileInput"]')[0].files;
    	if(files.length > 0){
    		return true;
    	} else {
    		return false;
    	}    	
    },
    
    checkFileExt: function() {
    	var ext = this.getFileExt();
    	if(ext != null){
	    	if(ext == 'jpg' || ext == 'jpeg' || ext == 'png' || ext == 'gif'){
				return true;
			} else {
				return false;
			}
    	} else {
    		return false;
    	}
    }

};