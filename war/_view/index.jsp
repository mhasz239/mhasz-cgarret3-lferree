<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Index view</title>
	</head>

	<body>
		<c:choose>
			<c:when test="${! empty player}">
				<form action="${pageContext.servletContext.contextPath}/index" method="post">
					<table>
						<tr>
							<td><input type="Submit" name="submit" value="Start Game"></td>
						</tr>
						<tr>
							<td><input type="Submit" name="submit" value="Log out"></td>
						</tr>
					</table>
				</form>
			</c:when>
			
			<c:otherwise>
				<form action="${pageContext.servletContext.contextPath}/index" method="post">
					<table>
						<tr>
							<td class="label">Username: </td>
							<td><input type="text" name="username" size="12" value="${username}" /></td>
						</tr>
						<tr>
							<td class="label">Password: </td>
							<td><input type="password" name="password" size="12" value="${password}" /></td>
						</tr>
						<tr>
							<td><input type="Submit" name="submit" value="Login"></td>
						</tr>
						<tr>
							<c:if test="${! empty errorMessage}">
						   		<div class="error">${errorMessage}</div>
							</c:if>
						</tr>
					</table>
				</form>		
			</c:otherwise>
		
		</c:choose>
	</body>
</html>
