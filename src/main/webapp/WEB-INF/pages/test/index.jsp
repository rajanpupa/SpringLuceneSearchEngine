<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
<title>Insert title here</title>
</head>
<body>
   
    
    <div ng-app="myApp" ng-controller="myCtrl">
     This is a Anjular JS test page being tested by {{person.firstName | uppercase }} {{person.lastName }}.
	    <p>Name : <input type="text" ng-model="name"></p>
	    <h1>Hello {{name}}</h1>
	    
	    <p>Total in dollar: {{ (quantity * cost) | currency }}</p>
	    
	    <p>Iteration Example</p>
	    <ul>
		  <li ng-repeat="x in names">
		    {{ x.name + ', ' + x.country }}
		  </li>
		</ul>
    </div>

<script>
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope) {
    $scope.firstName= "Rajan";
    $scope.lastName= "Upadhyay";
    $scope.person={firstName:'rajan',lastName:'upadhyay'};
    $scope.quantity=5;
    $scope.cost=4;
    $scope.operator='*';
    $scope.names = [
                    {name:'Jani',country:'Norway'},
                    {name:'Hege',country:'Sweden'},
                    {name:'Kai',country:'Denmark'}
                ];
});
</script>
    
</body>
</html>