<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>Inventory</title>
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
<h1><u>Inventory</u></h1>
<ol>
<c:forTokens items = "${inventory}" delims = ";" var = "item" >
<li><c:out value = "${item}"/></li>
</c:forTokens>
</ol>
<c:if test="${! empty dialog}">
</br>
<ul style="list-style-type:none">
<c:forTokens items = "${dialog}" delims = ";" var = "num_item" >
<li><c:out value = "${num_item}"/></li>
</c:forTokens>
</ul>
</c:if>
</div>

<div>
<p>Type "item #" to see more details about the specific item</br>
Type "game" to return to the game</p></div>

<form action="${pageContext.servletContext.contextPath}/inventory" method="post">
    <table>
        <tr>
            <td class="label">Text Command:</td>
            <td><input type="text" name="command" size="12" value="" /></td>
        </tr>
    </table>
    <input type="Submit" name="submit" value="Submit">
</form>
</body>
</html>