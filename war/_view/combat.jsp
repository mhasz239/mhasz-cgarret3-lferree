<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Adventures of Middle Earth</title>
		<style>
		.red {
            color: red;
        }
        #wrapper {
    		height: 100%; 
    		min-height: 467px;
   	 		width:  100%;
   	 		min-width: 810px;
   	 	}
   	 	#p1{
   	 		font-size:12px;
   	 		height: 100px;
   	 		width: 100px;
   	 		position: absolute;
   	 		bottom: 125px;
   	 		left: 25px;
   	 	}
   	 	#p2{
   	 		font-size:12px;
   	 		height: 100px;
   	 		width: 100px;
   	 		position: absolute;
   	 		bottom: 75px;
   	 		left: 150px;
   	 	}
   	 	#e1 {
   	 		font-size:12px;
   	 		height: 100px;
   	 		width: 100px;
   	 		position: absolute;
   	 		top: 25px;
   	 		right: 275px;
   	 	}
   	 	#e2 {
   	 		font-size:12px;
   	 		height: 100px;
   	 		width: 100px;
   	 		position: absolute;
   	 		top: 75px;
   	 		right: 150px;
   	 	}
   	 	#e3{
   	 		font-size:12px;
   	 		height: 100px;
   	 		width: 100px;
   	 		position: absolute;
   	 		top: 125px;
   	 		right: 25px;
   	 	}
   	 	#boss{
   	 		font-size:18px;
   	 		height: 200px;
   	 		width: 200px;
   	 		position: absolute;
   	 		top: 25px;
   	 		right: 250px;
   	 	}
		#dialog{
			position: absolute;
			top:0px;
			left:0px;
			width: 400px;
			height: 200px;
			font-size:12px;
		}
		</style>
		<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
	</head>

	<body>
		<div id="wrapper" style="height:100%;">
			<div id="dialog">
				<ul>
				<c:forTokens items = "${dialog}" delims = ";" var = "item" >
					<li><c:out value = "${item}"/></li>
				</c:forTokens>
				</ul>
			</div>
			<c:if test="${! empty p1}">
					<div id="p1">
						<p>
							Name: ${p1Name}</br>
							Health: ${p1Health}
						</p>
						<img src="${pageContext.request.contextPath}/image/players/${p1}.png"/>
					</div>
			</c:if>
			<c:if test="${! empty p2}">
				<div id="p2">
					<p>
						Name: ${p2Name}</br>
						Health: ${p2Health}
					</p>
					<img src="${pageContext.request.contextPath}/image/players/${p2}.png"/>
				</div>
			</c:if>
			<c:if test="${! empty e1}">
				<div id="e1">
					<p>
						Name: ${e1Name}</br>
						Health: ${e1Health}
					</p>
					<img src="${pageContext.request.contextPath}/image/enemies/${e1Race}.png"/>
				</div>
			</c:if>
			<c:if test="${! empty e2}">
				<div id="e2">
					<p>
						Name: ${e2Name}</br>
						Health: ${e2Health}
					</p>
					<img src="${pageContext.request.contextPath}/image/enemies/${e2Race}.png"/>
				</div>
			</c:if>
			<c:if test="${! empty e3}">
				<div id="e3">
					<p>Name: ${e3Name}</p>
					<p>Health: ${e3Health}</p>
					<img src="${pageContext.request.contextPath}/image/enemies/${e3Race}.png"/>
				</div>
			</c:if>
			<c:if test="${! empty b1}">
				<div id="boss">
					<p>Name: ${bossName}</p>
					<p>Health: ${bossHealth}</p>
					<img src="${pageContext.request.contextPath}/image/enemies/${bossRace}.png"/>
				</div>
			</c:if>
		</div>
	</body>
</html>
