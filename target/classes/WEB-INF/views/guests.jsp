<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring Boot</title>
</head>
<body>
	<p>Va rulando...</p>
			<ul>
			<c:forEach var="listValue" items="${guests}">
				<li>${listValue.firstName} ${listValue.lastName } ${listValue.visitDate }</li>
			</c:forEach>
		</ul>
</body>
</html>