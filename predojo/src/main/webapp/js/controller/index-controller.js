//inject angular file upload directives and services.
var app = angular.module('fileUpload', [ 'ngFileUpload' ]);

app.controller('IndexController', [
		'$scope',
		'Upload',
		'$timeout',
		function($scope, Upload, $timeout) {
			$scope.matches;
			
			$scope.upload = function(file) {
				file.upload = Upload.upload({
					url : 'http://localhost:8080/fileUpload',
					data : {
						file : file,
					},
				}).then(
						function(response) {
							$scope.matches = response.data; 
						},
						function(response) {
							console.error(response); 
						},
						function(evt) {
							// Math.min is to fix IE which reports 200%
							// sometimes
							file.progress = Math.min(100, parseInt(100.0
									* evt.loaded / evt.total));
						});

			}
		} ]);