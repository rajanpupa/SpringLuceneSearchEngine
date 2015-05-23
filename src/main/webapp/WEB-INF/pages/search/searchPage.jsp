<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head ng-app="myApp">
	<meta charset="utf-8">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
	<title>Search Page</title>
</head>
<body ng-app="myApp">
<div class="container">
	<div id="topbar" class="page-header">
		<a href="admin/admin">Admin</a>
	</div>
	<div class="jumbotron">
		<h3>The Intranet Search Engine</h3>
		<form:form method="post" action="" >
			<form:input path="query" style="width: 522px; " class="form-control" 
			     ng-controller="inputController" placeholder="Enter Query"/>
			<br>
			<input type="submit" value="Search" />
		</form:form>
	</div>
	<div id="result">
		<c:if test="${webDocumentList.size() > 0}">
			<ul>
				<c:forEach items="${webDocumentList}" var="doc">
					<li><a href="${doc.url}">${doc.url}</a></li>
				</c:forEach>
			</ul>
		</c:if>
		<c:if test="${webDocumentList.size() == 0}">
			<span style="border:red; background-color:yellow;">
			There are now documents in the index, matching the search query
			</span>
		</c:if>
	</div>
</div>
</body>
<script>
var myApp = angular.module('myApp', []);
myApp.controller=('inputController', function($scope) {
    
});

</script>
</html>