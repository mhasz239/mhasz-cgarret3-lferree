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
<!--
<c:if test="${! empty errorMessage}">
    <div class="error">${errorMessage}</div>
</c:if>
-->



<div>
<!-- <h1><u>Adventures of Middle Earth</u></h1> -->
<ol>
<c:forTokens items = "${dialog}" delims = ";" var = "item" >
<li><c:out value = "${item}"/></li>
</c:forTokens>
</ol>
</div>

<!--
<div>
<p>${map}</p>
</div>
<form action="${pageContext.servletContext.contextPath}/game" method="post">
    <table>
        <tr>
            <td class="label">Text Command:</td>
            <td><input type="text" name="command" size="12" value="" /></td>
        </tr>
    </table>
    <input type="Submit" name="submit" value="Submit">
</form>
-->
</body>
</html>