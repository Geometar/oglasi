var app = angular.module('adsApp.controllers', [ 'ui.bootstrap' ]);

app
		.controller(
				'CategoriesController',
				function($scope, $location, $routeParams, CategoryService) {

					$scope.init = function() {
						$scope.page = 0;
						$scope.elemenata = 10;
						$scope.sortBy = 'id';
						$scope.orderBy = 'true';
						$scope.redniBroj = 0;
						$scope.kriterijum = "contains";
					}
					$scope.delete = function(id) {
						CategoryService.delete(id)
						.success(function(data) {
							alert('Ad deleted')
								}).error(function() {
							alert('You dont have permission')
						})
					}
					$scope.getAll = function() {
						if ($routeParams.name) {
							CategoryService
									.getAllAds($routeParams.name, $scope.page,
											$scope.elemenata, $scope.sortBy,
											$scope.orderBy, $scope.dt,
											$scope.search, $scope.kriterijum)
									.success(
											function(data) {
												$scope.ads = data;
												$scope.nazivKategorije = $routeParams.name;
											}).error(function() {
										alert('Error finding ad or auth user')
									})
						} else {
							CategoryService.getAll($scope.page,
									$scope.elemenata, $scope.sortBy,
									$scope.orderBy).success(
									function(data, status, headers) {
										$scope.categories = data;
										$scope.userName = headers('user-auth');
										$scope.userRole = headers('user-role');
									}).error(function() {
								alert('Error in finding data!');
							})
						}
					}

					$scope.today = function() {
						$scope.dt = new Date();
					};
					$scope.today();

					$scope.clear = function() {
						$scope.dt = null;
					};

					$scope.inlineOptions = {
						customClass : getDayClass,
						minDate : new Date(),
						showWeeks : true
					};

					$scope.dateOptions = {
						dateDisabled : disabled,
						formatYear : 'yy',
						maxDate : new Date(2020, 5, 22),
						minDate : new Date(),
						startingDay : 1
					};

					// Disable weekend selection
					function disabled(data) {
						var date = data.date, mode = data.mode;
						return mode === 'day'
								&& (date.getDay() === 8 || date.getDay() === 9);
					}

					$scope.toggleMin = function() {
						$scope.inlineOptions.minDate = $scope.inlineOptions.minDate ? null
								: new Date();
						$scope.dateOptions.minDate = $scope.inlineOptions.minDate;
					};

					$scope.toggleMin();

					$scope.open1 = function() {
						$scope.popup1.opened = true;
					};

					$scope.open2 = function() {
						$scope.popup2.opened = true;
					};

					$scope.setDate = function(year, month, day) {
						$scope.dt = new Date(year, month, day);
					};

					$scope.formats = [ 'dd-MMMM-yyyy', 'yyyy/MM/dd',
							'dd.MM.yyyy', 'shortDate' ];
					$scope.format = $scope.formats[0];
					$scope.altInputFormats = [ 'M!/d!/yyyy' ];

					$scope.popup1 = {
						opened : false
					};

					$scope.popup2 = {
						opened : false
					};

					var tomorrow = new Date();
					tomorrow.setDate(tomorrow.getDate() + 1);
					var afterTomorrow = new Date();
					afterTomorrow.setDate(tomorrow.getDate() + 1);
					$scope.events = [ {
						date : tomorrow,
						status : 'full'
					}, {
						date : afterTomorrow,
						status : 'partially'
					} ];

					function getDayClass(data) {
						var date = data.date, mode = data.mode;
						if (mode === 'day') {
							var dayToCheck = new Date(date)
									.setHours(0, 0, 0, 0);

							for (var i = 0; i < $scope.events.length; i++) {
								var currentDay = new Date($scope.events[i].date)
										.setHours(0, 0, 0, 0);

								if (dayToCheck === currentDay) {
									return $scope.events[i].status;
								}
							}
						}

						return '';
					}

				});


app.controller('AdsController', function($scope, $location, AdsService,
		$routeParams) {

	$scope.init = function() {
		$scope.ad = {};
		if ($routeParams.id) {
			AdsService.findOne($routeParams.id).success(function(data) {
				$scope.ad = data;
			}).error(function() {
				alert('Error in finding data!')
			})
		}
		if($routeParams.name){
			$scope.ad.kategorija = $routeParams.name;
		}
	}
	
	$scope.save = function() {
		AdsService.add($scope.ad)
		  .success(function(data) {
			$location.url('/')
			alert('Uspesno registrovan oglas')
		}).error(function() {
			alert('Greska u registrovanju oglasa. Proverite da li ste dobro popunili. Jel vreme isteka oglasa posle danasnjeg dana postavljeno?')
		})
	}
	
	$scope.today = function() {
		$scope.dt = new Date();
	};
	$scope.today();

	$scope.clear = function() {
		$scope.dt = null;
	};

	$scope.inlineOptions = {
		customClass : getDayClass,
		minDate : new Date(),
		showWeeks : true
	};

	$scope.dateOptions = {
		dateDisabled : disabled,
		formatYear : 'yy',
		maxDate : new Date(2020, 5, 22),
		minDate : new Date(),
		startingDay : 1
	};

	// Disable weekend selection
	function disabled(data) {
		var date = data.date, mode = data.mode;
		return mode === 'day'
				&& (date.getDay() === 8 || date.getDay() === 9);
	}

	$scope.toggleMin = function() {
		$scope.inlineOptions.minDate = $scope.inlineOptions.minDate ? null
				: new Date();
		$scope.dateOptions.minDate = $scope.inlineOptions.minDate;
	};

	$scope.toggleMin();

	$scope.open1 = function() {
		$scope.popup1.opened = true;
	};

	$scope.open2 = function() {
		$scope.popup2.opened = true;
	};

	$scope.setDate = function(year, month, day) {
		$scope.dt = new Date(year, month, day);
	};

	$scope.formats = [ 'dd-MMMM-yyyy', 'yyyy/MM/dd',
			'dd.MM.yyyy', 'shortDate' ];
	$scope.format = $scope.formats[0];
	$scope.altInputFormats = [ 'M!/d!/yyyy' ];

	$scope.popup1 = {
		opened : false
	};

	$scope.popup2 = {
		opened : false
	};

	var tomorrow = new Date();
	tomorrow.setDate(tomorrow.getDate() + 1);
	var afterTomorrow = new Date();
	afterTomorrow.setDate(tomorrow.getDate() + 1);
	$scope.events = [ {
		date : tomorrow,
		status : 'full'
	}, {
		date : afterTomorrow,
		status : 'partially'
	} ];

	function getDayClass(data) {
		var date = data.date, mode = data.mode;
		if (mode === 'day') {
			var dayToCheck = new Date(date)
					.setHours(0, 0, 0, 0);

			for (var i = 0; i < $scope.events.length; i++) {
				var currentDay = new Date($scope.events[i].date)
						.setHours(0, 0, 0, 0);

				if (dayToCheck === currentDay) {
					return $scope.events[i].status;
				}
			}
		}

		return '';
	}

});

app.controller('RegistrationController', function($scope, $location,
		RegistrationService, $routeParams) {

	$scope.init = function() {
		$scope.user = {};
	}

	$scope.save = function() {
		RegistrationService.add($scope.user)
		  .success(function(data) {
			  RegistrationService.getLogin()
			  .success(function(data) {
				  alert('Uspeno ste se registrovali')
			  }).error(function() {
					alert('Greska Kod Ucitavanja Activitia')
				})
		}).error(function() {
			alert('Greska Kod Ucitavanja Activitia')
		})
	}

});

app.controller('UserController', function($scope, $location,
		UserAdService, $routeParams) {
	
	$scope.init = function() {
		$scope.promenaLozinke = {};
	}
	
	$scope.getAll = function() {
		UserAdService.getAll()
			.success(function(data) {
				$scope.ads = data;
			}).error(function() {
				alert('Greska Kod Ucitavanja Activitia')
			})

		}
	$scope.delete = function(id) {
		UserAdService.delete(id)
			.success(function(data) {
				alert('Oglas je izbrisan iz evidencija.')
			}).error(function() {
				alert('Greska Kod brisanja oglasa.')
			})

		}
	
	$scope.put =function(){
		UserAdService.put($scope.promenaLozinke)
		.success(function(data) {
			alert('Uspesno ste promenili lozinku')
		}).error(function() {
			alert('Niste uspeli da promenite lozinku.')
		})

	}
	
});

app.controller('AdministratorController', function($scope, $location, AdminService,
		$routeParams) {
	
	$scope.init = function() {
		$scope.reset='false';
		$scope.user = {};
		if ($routeParams.id) {
			AdminService.findOne($routeParams.id).success(
					function(data, status, headers) {
						$scope.user = data;
					}).error(function() {
				alert('Greska Kod Ucitavanja Usera')
			})
		}
	}
	$scope.getAll = function() {
		AdminService.getAll()
			.success(function(data) {
				$scope.users = data;
			}).error(function() {
				alert('Greska Kod Ucitavanja Korisnika')
			})

		}
	
	$scope.save = function() {
		
		if($scope.user){
		AdminService.put($scope.user, $scope.reset)
			.success(function(data) {
				alert('Izmene su sacuvane')
			}).error(function() {
				alert('Greska Kod Ucitavanja Korisnika')
			})

		}
	}
	
	$scope.delete = function(id) {
		AdminService.delete(id)
			.success(function(data) {
				alert('Korisnik je izbrisan iz evidencija.')
			}).error(function() {
				alert('Greska Kod brisanja korisnika.')
			})

		}
});
