<!DOCTYPE html>

<html>
	<head>
		<title>Index view</title>
	</head>

	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		<form action="${pageContext.servletContext.contextPath}/index" method="post">
			<table>
				<tr>
					<td><input type="Submit" name="submit" value="Add Numbers"></td>
				</tr>
				<tr>
					<td><input type="Submit" name="submit" value="Multiply Numbers"></td>
				</tr>
				<tr>
					<td><input type="Submit" name="submit" value="Guessing Game"></td>
				</tr>
		</form>
	</body>
</html>
