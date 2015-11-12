//inject angular file upload directives and services.
var app = angular.module('fileUpload', [ 'ngFileUpload' ]);

app
		.controller(
				'IndexController',
				[
						'$scope',
						'Upload',
						'$timeout',
						function($scope, Upload, $timeout) {
							$scope.upload = function(file) {
								file.upload = Upload
										.upload({
											url : 'http://localhost:8080/fileUpload',
											data : {
												file : file,
											},
										});

								file.upload.then(function(response) {
									$timeout(function() {
										file.result = response.data;
									});
								}, function(response) {
									if (response.status > 0)
										$scope.errorMsg = response.status
												+ ': ' + response.data;
								}, function(evt) {
									// Math.min is to fix IE which reports 200%
									// sometimes
									file.progress = Math.min(100,
											parseInt(100.0 * evt.loaded
													/ evt.total));
								});
							}
						} ]);