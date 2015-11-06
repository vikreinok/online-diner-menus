 window.SessionManager = {
		
    isActive: function () {
        return new Promise(function (fulfill, reject) {
            $.ajax(baseUrl +"status", {
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("X-Token", token);
                },
                complete: function (jqXHR, textStatus) {
                    var status = jqXHR.status;
                    if (status === 200) {
                        fulfill(true);
                    } else if (status === 401) {
                        fulfill(true);
                    } else {
                    	console.log("reject(jqXHR) " + jqXHR);
                        fulfill(false);
//                        reject(jqXHR);
                    }
                }
            });
        });
    },
    login: function (username, password, callback) {
        return new Promise(function (fulfill, reject) {
            $.ajax(baseUrl +"login", {
            	type: 'POST',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader('Authorization', btoa(username + ":" + password));
                },
                complete: function (jqXHR, textStatus) {
                    var status = jqXHR.status;
                    if (status === 200) {
                        token = jqXHR.getResponseHeader("X-Token");
                        $.cookie("token", token);
                        
                        callback();
                        
                        fulfill(true);
                    } else if (status === 403) {
                        fulfill(false);
                    } else {
                    	fulfill(false);
//                        reject(jqXHR);
                    }
                }
            });
        });
    },
    logout: function () {
        return new Promise(function (fulfill, reject) {
            $.ajax(baseUrl +"logout", {
            	type: 'DELETE',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("X-Token", token);
                },
                complete: function (jqXHR, textStatus) {
                    var status = jqXHR.status;
                    if (status === 200) {
                        token = null;
                        $.cookie("token", null);
                        $.cookie("authenticated", false);
                        fulfill(true);
                    } else {
//                        reject(jqXHR);
                        fulfill(false);
                    }
                }
            });
        });
    },
    getToken: function () {
    	return $.cookie("token");
    },

 };