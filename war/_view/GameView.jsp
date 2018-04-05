<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>Middle Earth Game</title>
    <style type="text/css">
    	#wrapper {
    		height: 100vh; 
   	 		width:  100vw;
   	 	}
    	#header { text-align: center; }
    	#help { 
    		float: left;
    		width: 25%;
    	}
    	#game { 
    		float: left;
    		width: 75%;
    		height: 75%;
    	}
    	#input { width = 100% }
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

<div id="wrapper">
<div id="header">
<h1><u>Adventures of Middle Earth</u></h1>
</div>
<div id="help">
<p> This is in the help div. </p>
</div>
<div id="game">
<iframe style="width:100%;height:100%"src="${pageContext.servletContext.contextPath}/${mode}"></iframe>
</div>

<div id="input"> 
<form action="${pageContext.servletContext.contextPath}/GameView" method="post">
    <table>
        <tr>
            <td class="label">Text Command:</td>
            <td><input type="text" name="command" size="12" value="" /></td>
        </tr>
    </table>
    <input type="Submit" name="submit" value="Submit">
</form>
</div>
</div>
</body>
</html>