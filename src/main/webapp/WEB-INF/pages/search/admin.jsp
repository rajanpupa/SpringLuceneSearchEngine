<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Admin Panel</title>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
</head>
<body>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<div style="float: right;">
			<p>
				Welcome : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</p>
		</div>
	</c:if>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<a href="/">Search Page</a>
	<h2>
		<u>Settings</u>
	</h2>
	
	<form:form method="post" action="/admin">
		<form:input path="query" style="width: 522px; " />
		<br><input type="submit" value="ADD" />
	</form:form>
	<div id="response">
		<h4>${url }</h4>
		<p>${body }</p>
	</div>


</body>
</html>