var app=angular.module('myApp',[]);
app.controller('myCtrl',function($scope,$http) {



$scope.path1='';
$scope.path2='';


$scope.single=function(){
		alert('Single Get');
	if($scope.path1!=''){
		//alert($scope.path1);
		if($scope.path1!='vid1.mov'){
			alert('file not present');
		}	
		else{
			alert('file present and retreived ');
		}
		$http({
    url: 'http://localhost:5000/getFile'+"<>?"+$scope.path1, 
    method: "GET"
 }).success(function(data, status, headers, config) {
 	var res=(data);
 	alert(res);
	});
 }
 else{
 	alert('Enter File Name');
 }
}

$scope.multiple=function(){
	alert('Multiple upload');
	if($scope.path2!=''){
		if($scope.path2!='vid1.mov'){
			alert('file not present');
		}	
		else{
			alert('file present and retreived ');
		}
		$http({
    url: 'http://localhost:5000/getDocs'+"<>?"+$scope.path2, 
    method: "GET"
 }).success(function(data, status, headers, config) {
 	var res=(data);
 	alert(res);
	});
 }else
 {
 	alert('Enter File Name');
 }
}




});

