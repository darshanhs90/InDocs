var app=angular.module('myApp',[]);
app.controller('myCtrl',function($scope,$http) {



$http({
    url: "http://localhost:5000/init", 
    method: "GET"
 }).success(function(data, status, headers, config) {
 	var res=(data);
 	alert(res);
	});






});