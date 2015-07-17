var app=angular.module('myApp',[]);
app.controller('myCtrl',function($scope,$http) {



$scope.upload=function(){
	alert('upload');
	var fullPath = document.getElementById('upload');
if (fullPath!=null) {
	fullPath=fullPath.value;
	var startIndex = (fullPath.indexOf('\\') >= 0 ? fullPath.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
	var filename = fullPath.substring(startIndex);
	if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
		filename = filename.substring(1);		
		$http({
    url: 'http://localhost:5000/uploadFile'+"<>?"+filename, 
    method: "GET"
 }).success(function(data, status, headers, config) {
 	var res=(data);
 	alert(res);
	});
	}
}
else{
	filename="vid.mov";
	alert(filename);
	$http({
    url: 'http://localhost:5000/uploadFile'+"<>?"+filename, 
    method: "GET"
 }).success(function(data, status, headers, config) {
 	var res=(data);
 	alert(res);
	});
}
};





});