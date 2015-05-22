<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Page</title>
</head>
<body>
	<div id="topbar">
		<a href="admin">Admin</a>
	</div>
	<h3>The Search</h3>
	<form:form method="post" action="">
		<form:input path="query" style="width: 522px; " />
		<br>
		<input type="submit" value="Search" />
	</form:form>
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
</body>
</html>