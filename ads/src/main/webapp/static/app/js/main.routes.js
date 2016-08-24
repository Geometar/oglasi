var app = angular.module('adsApp.routes', [ 'ngRoute' ]);
app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : '/static/app/html/protected/partial/home.html',
		controller : 'CategoriesController'
	}).when('/promena_lozinke', {
		templateUrl : '/static/app/html/protected/partial/promenaLozinke.html',
		controller : 'UserController'
	})
	.when('/administrator/edit/:id', {
		templateUrl : '/static/app/html/protected/partial/editUser.html',
		controller : 'AdministratorController'
	})
	.when('/administrator', {
		templateUrl : '/static/app/html/protected/partial/administrator.html',
		controller : 'AdministratorController'
	}).when('/vasi_oglasi', {
		templateUrl : '/static/app/html/protected/partial/oglasiKorisnika.html',
		controller : 'UserController'
	}).when('/:name/postavi_oglas', {
		templateUrl : '/static/app/html/protected/partial/postaviOglas.html',
		controller : 'AdsController'
	}).when('/:name', {
		templateUrl : '/static/app/html/protected/partial/ads.html',
		controller : 'CategoriesController'
	}).when('/ad/:id', {
		templateUrl : '/static/app/html/protected/partial/adsDetalji.html',
		controller : 'AdsController'
	}).otherwise({
		redirectTo : '/'
	});
} ]);
