var app=angular.module('myApp',[]);
app.controller('myCtrl',function($scope,$http) {
	$scope.checker1=false;
	$scope.checker2=false;

$scope.upload=function(){
	alert('upload');
	alert($scope.checker1);
	var fullPath = document.getElementById('upload').value;
if (fullPath) {
	var startIndex = (fullPath.indexOf('\\') >= 0 ? fullPath.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
	var filename = fullPath.substring(startIndex);
	if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
		filename = filename.substring(1);
		alert(filename);
		var check=0;
		if($scope.checker1==true)
			check=1;
		$http({
    url: 'http://localhost:5000/uploadFile'+"<>?"+filename+"<>?"+check, 
    method: "GET"
 }).success(function(data, status, headers, config) {
 	var res=(data);
 	alert(res);
	});
	}
}
};







});