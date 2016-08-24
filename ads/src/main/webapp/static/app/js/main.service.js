var app = angular.module('adsApp.services',[]);

app.service('CategoryService', function($http){
	
	var url="api/categories";
	
	this.getAll = function(page, elemenata, sortBy, orderBy){
		return $http.get(url, {params:{'page':page, 'elemenata':elemenata,'sortBy':sortBy, 'orderBy':orderBy}});
	}
	
	this.getAllAds = function(name, page, elemenata, sortBy, orderBy, istice, search, kriterijum){
		return $http.get('api/categories/'+name+'/ads', {params:{'page':page, 'elemenata':elemenata,'sortBy':sortBy, 'orderBy':orderBy, 'istice':istice, 'search':search, 'kriterijum':kriterijum}});
	}
	
	this.delete = function(id){
		return $http.delete('/api/ad/'+id);
	}
	
	this.add = function(activity){
		if(activity.id){
		return $http.put(url +'/' + activity.id, activity);
		}
		else{
			return $http.post(url, activity);
		}
	}
});

app.service('AdsService', function($http){
	
	var url="api/ad";
	
	this.findOne = function(id){
		return $http.get(url+"/"+id);
	}
	
	this.add = function(ad){
		return $http.post(url,ad);
	}
	
});


app.service('UserAdService', function($http){
	
	var url="api/user/ads";
	
	this.getAll = function(){
		return $http.get(url);
	}
	
	this.delete = function(id){
		return $http.delete('api/ad/'+id);
	}
	
	this.put = function(promenaLozinke){
		return $http.put("api/user",promenaLozinke);
	}
	
});

app.service('AdminService', function($http){
	
	var url="api/admin/users";
	
	this.getAll = function(){
		return $http.get(url);
	}
	
	this.delete = function(id){
		return $http.delete(url+"/"+id);
	}
	
	this.findOne = function(id){
		return $http.get(url+"/"+id);
	}
	
	this.put = function(user, reset){
		if(user.id){
		return $http.put(url+"/"+user.id,user, {params:{'reset':reset}});
		}
	}
	
});

app.service('RegistrationService', function($http){
	
	var url="api/users";
	
	this.getLogin = function(){
		return $http.get("/login");
	}
	
	this.findOne = function(id){
		return $http.get(url+"/"+id);
	}
	this.add = function(user){
		return $http.post(url,user);
	}
	
});