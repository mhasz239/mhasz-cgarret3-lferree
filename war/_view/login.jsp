<!DOCTYPE html>

<html>
	<head>
		<title>Adventures of Middle Earth</title>
	</head>

	<body>
		<form action="${pageContext.servletContext.contextPath}/login" method="post">
			<table>
				<tr>
					<td class="label">Username: </td>
					<td><input type="text" name="username" size="12" value="${username}" /></td>
				</tr>
				<tr>
					<td class="label">Password:</td>
					<td><input type="password" name="password" size="12" value="${password}" /></td>
				</tr>
				<tr>
					<td><input type="Submit" name="LogIn" value="Login"></td>
				</tr>
				<tr>
					<c:if test="${! empty errorMessage}">
				   		<div class="error">${errorMessage}</div>
					</c:if>
				</tr>
		</form>
	</body>
</html>
