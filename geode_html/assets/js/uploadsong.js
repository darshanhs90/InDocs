var app=angular.module('myApp',[]);
app.controller('myCtrl',function($scope,$http) {



$scope.upload=function(){
	alert('upload');
	var fullPath = document.getElementById('upload').value;
if (fullPath) {
	var startIndex = (fullPath.indexOf('\\') >= 0 ? fullPath.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
	var filename = fullPath.substring(startIndex);
	if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
		filename = filename.substring(1);
		alert(filename);
		$http({
    url: 'http://localhost:5000/uploadFile'+"<>?"+filename, 
    method: "GET"
 }).success(function(data, status, headers, config) {
 	var res=(data);
 	alert(res);
	});
	}
}
};







});