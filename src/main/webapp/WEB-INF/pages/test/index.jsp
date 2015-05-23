<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">

<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
<title>Angular | Bootstrap | Test page</title>
</head>
<body ng-app="myApp">
	<div class="container">
		<div id="App1" ng-controller="myCtrl">
			<p>This is a Anjular JS test page being tested by
				{{person.firstName | uppercase }} {{person.lastName }}.</p>

			<p>
				Name : <input type="text" ng-model="name" />
			</p>

			<h3>Hello {{name}}</h3>

			<p>Total in dollar: {{ (quantity * cost) | currency }}</p>

			<p>Iteration Example with orderBy filter in the directive of
				angular</p>
			<ul>
				<li ng-repeat="x in names | orderBy:'country'">{{ x.name + ', '
					+ x.country }}</li>
			</ul>
		</div>

		<div id="App2" ng-controller="customersCtrl">
			<h3>{{ description }}</h3>

			<ul>
				<li ng-repeat="x in names">{{x.Name + ',' + x.Country}}</li>
			</ul>
		</div>
	</div>
</body>
<script src="/resources/script/test.js"></script>
</html>