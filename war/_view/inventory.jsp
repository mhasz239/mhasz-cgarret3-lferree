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
        #item {
        	display: inline;
        	float: left;
        	width: 100px;
        	height: 118px;
        	font-size: 12px;
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
<c:if test="${! empty inventory_dialog}">
</br>
<ul style="list-style-type:none">
<c:forTokens items = "${inventory_dialog}" delims = ";" var = "num_item" >
<li><c:out value = "${num_item}"/></li>
</c:forTokens>
</ul>
</c:if>
</div>

<div id="test">
	<c:forEach items="${itemTest}" var = "i" >
		<div id="item">
			<p>${i.name}</p>
			<img src="${pageContext.request.contextPath}/image/items/${i.description_update}.png"/>
        </div>
    </c:forEach>
<div>

<div>
<p>Type "item #" to see more details about the specific item</br>
Type "game" to return to the game</p></div>
<!--
<form action="${pageContext.servletContext.contextPath}/inventory" method="post">
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