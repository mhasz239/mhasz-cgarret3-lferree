<!DOCTYPE html>

<html>
	<head>
		<title>Index view</title>
	</head>

	<body>
	<div>
	<p>${map}</p>
	<p>${characters}</p>
	</div>
		<form action="${pageContext.servletContext.contextPath}/index" method="post">
			<table>
				<tr>
					<td><input type="Submit" name="submit" value="Start Game"></td>
				</tr>
		</form>
	</body>
</html>
