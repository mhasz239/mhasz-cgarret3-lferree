<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>Game</title>
    <style type="text/css">
        .error {
            color: red;
        }
        td.label {
            text-align: right;
        }
    </style>
</head>

<body>
	<div>
		<ol>
			<c:forTokens items = "${dialog}" delims = ";" var = "item" >
				<li><c:out value = "${item}"/></li>
			</c:forTokens>
		</ol>
	</div>
	<span id="end"></span>
</body>
</html>