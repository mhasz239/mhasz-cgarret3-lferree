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
    		min-height: 467px;
   	 		min-width: 810px;
    	}
    	#input { width = 100% }
    	#exitWrap{
    		background-color: white;
    		border-style: solid;
    		border-width: 3px;
    		border-color: red;
    		position: absolute;
    		left:50%;
    		top:50%;
    		min-width: 270px;
    	}
    	#exit {
    		left:-50%;
    		top:-50%;
    	}
        .error {
            color: red;
        }
        td.label {
            text-align: right;
        }
        td.label1 {
            text-align: center;
        }
        #yes {
	        float:left;
	        width:20%;
	        position:relative;
	        left:15px;
	        bottom:10px;
	    }
	    #no {
	    	float:right;
	    	width:20%;
	    	position:relative;
	    	right:15px;
	    	bottom:10px;
	    }
    </style>
    
</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h1><u>Adventures of Middle Earth</u></h1>
		</div>
		
		<div id="help">
			<h3>Help</h3>
			<p>
				The basics of the game are as follows:<br/></br>

				Switching display: There are 3 displays you can use to switch between them you simply type their name in the command box.</br>
				The 3 displays are: game, character (displays stats and inventory), map.</br></br>

				Movement: "move (direction)" directions are north, northeast, east, southeast, south, southwest, west, northwest<br/></br>

				When you are in combat use "attack (enemy name)" to initiate an attack</br></br>

				If you see a description of something on the map try finding what command can be used on that object. I.E. if you see a bike, try "ride bike"</br>
				Or if you think it might be an item try "take bike"</br></br>

				The rest is up to you to find out.
			</p>
		</div>
		
		<div id="game">
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
	
	<c:if test="${exit}">
	
	<div id="exitWrap">
		<div id="exit">
			<form action="${pageContext.servletContext.contextPath}/GameView" method="post">
            	<center>
        			<h3 class="label1">Are you sure?</h3>
        			<p class="label1">Any unsaved data will be deleted.</p>
				</center>
    	   		<button id="yes" type="Submit" name="exitAns" value="Yes">Yes</button>
    		   	<button id="no" type="Submit" name="exitAns" value="No">No</button>
    		</form>
		</div>
	</div>
	</c:if>
</body>
</html>