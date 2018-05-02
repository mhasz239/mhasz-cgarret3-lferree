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
    		width: 23%;
    	}
    	#game { 
    		float: left;
    		width: 73%;
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
    
    <style type='text/javascript'>
		function scrollToBottom() {
			document.frames['frame'].contentWindow.scrollTo(0, 9999);
    	}
    </style>
</head>
<!-- onLoad="javascript:document.frames['frame'].scrollBy(0, 110)" -->
<body onload="scrollToBottom()">
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
<div id="game" >
	<c:choose>
	<c:when test="${mode == 'game'}">
		<iframe id="frame" style="width:100%;height:100%"src="${pageContext.servletContext.contextPath}/${mode}#end" ></iframe>
	</c:when>
	<c:otherwise>
		<iframe id="frame" style="width:100%;height:100%"src="${pageContext.servletContext.contextPath}/${mode}" ></iframe>
	</c:otherwise>
	</c:choose>
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