<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>Character</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.10/css/all.css" integrity="sha384-+d0P83n9kaQMCwj8F4RJB66tzIwOKmrdb46+porD/OvrJ+37WqIM7UoBtwHO6Nlg" crossorigin="anonymous">
    <style type="text/css">
        .error {
            color: red;
        }
        td.label {
            text-align: right;
        }
        #wrapper {
    		height: 100%; 
   	 		width:  100%;
   	 	}
   	 	#info {
   	 		float:left;
   	 		height: 150px;
   	 		width: 49%;
   	 	}
   	 	#stats {
   	 		float:left;
   	 		height: 150px;
   	 		width: 49%;
   	 	}
   	 	#left{
   	 		float: left;
   	 		height: 300px;
   	 		width: 400px;
   	 		background-image: url(${pageContext.request.contextPath}/image/players/player1.png);
   	 		background-size: 300px 300px;
   	 		background-repeat: no-repeat;
    		background-position: center;
   	 	}
   	 	#leftmid{
   	 		position:relative;
   	 		left: 200px;
   	 		width:200px;
   	 	}
   	 	
   	 	#head{
   	 		position:relative;
   	 		left: -25px;
   	 		top: 10px;
   	 		width: 50px;
   	 		height: 50px;
   	 		z-index: 20;
   	 		background-color:white;
   	 		border-color: black;
   	 		border-width:2px;
   	 		border-style: solid;
   	 	}
   	 	#chest{
   	 		position:relative;
   	 		left: -25px;
   	 		top: 25px;
   	 		width: 50px;
   	 		height: 50px;
   	 		z-index: 20;
   	 		background-color:white;
   	 		border-color: black;
   	 		border-width:2px;
   	 		border-style: solid;
   	 	}
   	 	#arms{
   	 		position:relative;
   	 		left: -100px;
   	 		top: -40px;
   	 		width: 50px;
   	 		height: 50px;
   	 		z-index: 20;
   	 		background-color:white;
   	 		border-color: black;
   	 		border-width:2px;
   	 		border-style: solid;
   	 	}
   	 	#lhand{
   	 		position:relative;
   	 		left: -100px;
   	 		top: -20px;
   	 		width: 50px;
   	 		height: 50px;
   	 		z-index: 20;
   	 		background-color:white;
   	 		border-color: black;
   	 		border-width:2px;
   	 		border-style: solid;
   	 	}
   	 	#rhand{
   	 		position:relative;
   	 		left: 50px;
   	 		top: -75px;
   	 		width: 50px;
   	 		height: 50px;
   	 		z-index: 20;
   	 		background-color:white;
   	 		border-color: black;
   	 		border-width:2px;
   	 		border-style: solid;
   	 	}
   	 	#legs{
   	 		position:relative;
   	 		left: -25px;
   	 		top: -50px;
   	 		width: 50px;
   	 		height: 50px;
   	 		z-index: 20;
   	 		background-color:white;
   	 		border-color: black;
   	 		border-width:2px;
   	 		border-style: solid;
   	 	}
   	 	#boots{
   	 		position:relative;
   	 		left: -25px;
   	 		top: -50px;
   	 		width: 50px;
   	 		height: 50px;
   	 		z-index: 20;
   	 		background-color:white;
   	 		border-color: black;
   	 		border-width:2px;
   	 		border-style: solid;
   	 	}
   	 	#right{
   	 		float: left;
   	 		height: 300px;
   	 		width: 400px;
   	 	}
   	 	.inline {
        	display: inline;
        	padding-right:12px;
        }
        #stat {
        	padding-right:75px;
        }
        .item {
        	display: inline;
        	float: left;
        	width: 50px;
        	height: 50px;
        	font-size: 12px;
        }
        .hidden {
        	position: relative;
        	visibility: hidden;
        	left: 10 px;
        	top: -10px;
        	opacity: 0;
  			transition: .5s ease;
        	min-width: 300px;
        	background-color: lightyellow;
        	border-width: 1px;
        	border-color: black;
        	border-style: solid;
        }
        .item:hover .hidden{
        	opacity: 1;
        	visibility: visible;
        }
        .btn {
        	height: 15px;
        	width:15px;
        	font-size: 10px;
        	margin:0px;
        	padding:0px;
        }
    </style>
    <script>
		function allowDrop(ev) {
		    ev.preventDefault();
		}

		function drag(ev) {
		    ev.dataTransfer.setData("text", ev.target.id);
		}

		function drop(ev) {
		    ev.preventDefault();
		    var data = ev.dataTransfer.getData("text");
		    if (ev.target.id == "head" || ev.target.parentElement.id == "head"|| ev.target.parentElement.parentElement.id == "head") {
		    	console.log("head");
		    	var x = document.getElementById("headForm");
		    	x.elements["head"].value = data;
		    	x.submit();
		    }
		    else if (ev.target.id == "chest" || ev.target.parentElement.id == "chest"|| ev.target.parentElement.parentElement.id == "chest") {
		    	console.log("chest");
		    	var x = document.getElementById("chestForm");
		    	x.elements["chest"].value = data;
		    	x.submit();
		    }
		    else if (ev.target.id == "arms" || ev.target.parentElement.id == "arms"|| ev.target.parentElement.parentElement.id == "arms") {
		    	console.log("arms");
		    	var x = document.getElementById("armsForm");
		    	x.elements["arms"].value = data;
		    	x.submit();
		    }
		    else if (ev.target.id == "lhand" || ev.target.parentElement.id == "lhand"|| ev.target.parentElement.parentElement.id == "lhand") {
		    	console.log("lhand");
		    	var x = document.getElementById("lhandForm");
		    	x.elements["lhand"].value = data;
		    	x.submit();
		    }
		    else if (ev.target.id == "rhand" || ev.target.parentElement.id == "rhand"|| ev.target.parentElement.parentElement.id == "rhand") {
		    	console.log("rhand");
		    	var x = document.getElementById("rhandForm");
		    	x.elements["rhand"].value = data;
		    	x.submit();
		    }
		    else if (ev.target.id == "legs" || ev.target.parentElement.id == "legs"|| ev.target.parentElement.parentElement.id == "legs") {
		    	console.log("legs");
		    	var x = document.getElementById("legsForm");
		    	x.elements["legs"].value = data;
		    	x.submit();
		    }
		    else if (ev.target.id == "boots" || ev.target.parentElement.id == "boots"|| ev.target.parentElement.parentElement.id == "boots") {
		    	console.log("boots");
		    	var x = document.getElementById("bootsForm");
		    	x.elements["boots"].value = data;
		    	x.submit();
		    }
		    else if (ev.target.id == "right" ||ev.target.parentElement.id == "right" || ev.target.parentElement.parentElement.id == "right"  || ev.target.parentElement.parentElement.parentElement.id == "right"  || ev.target.parentElement.parentElement.parentElement.parentElement.id == "right"  || ev.target.parentElement.parentElement.parentElement.parentElement.parentElement.id == "right"  || ev.target.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.id == "right" ) {
		    	var x = document.getElementById("unequip");
		    	x.elements["remove"].value = data;
		    	x.submit();
		    }
		}
	</script>
</head>

<body>
<div id = "wrapper">
	<div id="info">
		<ul>
			<li>name: ${name}</li>
			<li>gender: ${gender}</li>
			<li>race: ${race}</li>
			<li>level: ${level}</li>
			<c:choose>	
    			<c:when test="${sp > 0}">
    				<li>health: <button class="btn btn-danger" onclick="lowerHitpoints()">-</button><span id="hitpoints">${hp}</span><button class="btn btn-success" onclick="raiseHitpoints()">+</button></li>
    			</c:when>
    			<c:otherwise>
					<li>health: <span id="hitpoints">${hp}</span></li>
				</c:otherwise>
			</c:choose>
			<li>coins: ${coins}</li>
	</div>
	<div id="stats">
		<form id="headForm" action="${pageContext.servletContext.contextPath}/character" method="post">
			<input type="hidden" name="head" value="">
    	</form>
    	<c:choose>
    			
    		<c:when test="${sp > 0}">
    			<ul>
    				<li><b>skill points: <span id="skillpoints">${sp}</span></b> <button class="btn btn-success" style="height:24px;width:80px" onclick="saveSP()">Save Skill Points</button></li>
					<li>attack: <button class="btn btn-danger" onclick="lowerAtt()">-</button><span id="att">${attack}</span><button class="btn btn-success" onclick="raiseAtt()">+</button> </li>
					<li>defense: <button class="btn btn-danger" onclick="lowerDef()">-</button><span id="def">${defense}</span><button class="btn btn-success" onclick="raiseDef()">+</button></li>
					<li>special attack: <button class="btn btn-danger" onclick="lowerSpecAtt()">-</button><span id="specatt">${specialAttack}</span><button class="btn btn-success" onclick="raiseSpecAtt()">+</button></li>
					<li>special defense: <button class="btn btn-danger" onclick="lowerSpecDef()">-</button><span id="specdef">${specialDefense}</span><button class="btn btn-success" onclick="raiseSpecDef()">+</button></li>
					<li>magic: <button class="btn btn-danger" onclick="lowerMag()">-</button><span id="mag">${magic}</span><button class="btn btn-success" onclick="raiseMag()">+</button></li>
				</ul>
    		</c:when>
    		<c:otherwise>
				<ul>
					<li>attack: <span id="att">${attack}<span id="skillpoints"></span></li>
					<li>defense: <span id="def">${defense}</span></li>
					<li>special attack: <span id="specatt">${specialAttack}</span></li>
					<li>special defense: <span id="specdef">${specialDefense}</span></li>
					<li>magic: <span id="mag">${magic}</span></li>
				</ul>
			</c:otherwise>
		</c:choose>
	</div>

	<div id="left">
		<div id="leftmid">
			<div id="head" ondrop="drop(event)" ondragover="allowDrop(event)">
				<form id="headForm" action="${pageContext.servletContext.contextPath}/character" method="post">
					<input type="hidden" name="head" value="">
    			</form>
    			<c:if test="${! empty headIMG}">
    				<img id="headIMG" style="width:100%; top:-54px;" src="${pageContext.request.contextPath}/image/items/${headIMG}.png" draggable="true" ondragstart="drag(event)"/>
    			</c:if>
    		</div>
    		<div id="chest" ondrop="drop(event)" ondragover="allowDrop(event)">
    			<form id="chestForm" action="${pageContext.servletContext.contextPath}/character" method="post">
					<input type="hidden" name="chest" value="">
    			</form>
    			<c:if test="${! empty chestIMG}">
    				<img id="chestIMG" style="width:100%; top:-54px;" src="${pageContext.request.contextPath}/image/items/${chestIMG}.png" draggable="true" ondragstart="drag(event)"/>
    			</c:if>
    		</div>
    		<div id="arms" ondrop="drop(event)" ondragover="allowDrop(event)">
    			<form id="armsForm" action="${pageContext.servletContext.contextPath}/character" method="post">
					<input type="hidden" name="arms" value="">
    			</form>
    			<c:if test="${! empty armsIMG}">
    				<img id="armsIMG" style="width:100%; top:-54px;" src="${pageContext.request.contextPath}/image/items/${armsIMG}.png" draggable="true" ondragstart="drag(event)"/>
    			</c:if>
    		</div>
    		<div id="lhand" ondrop="drop(event)" ondragover="allowDrop(event)">
    			<form id="lhandForm" action="${pageContext.servletContext.contextPath}/character" method="post">
					<input type="hidden" name="lhand" value="">
    			</form>
    			<c:if test="${! empty lhandIMG}">
    				<img id="lhandIMG" style="width:100%; top:-54px;" src="${pageContext.request.contextPath}/image/items/${lhandIMG}.png" draggable="true" ondragstart="drag(event)"/>
    			</c:if>
    		</div>
    		<div id="rhand" ondrop="drop(event)" ondragover="allowDrop(event)">
    			<form id="rhandForm" action="${pageContext.servletContext.contextPath}/character" method="post">
					<input type="hidden" name="rhand" value="">
    			</form>
    			<c:if test="${! empty rhandIMG}">
    				<img id="rhandIMG" style="width:100%; top:-54px;" src="${pageContext.request.contextPath}/image/items/${rhandIMG}.png" draggable="true" ondragstart="drag(event)"/>
    			</c:if>
    		</div>
    		<div id="legs" ondrop="drop(event)" ondragover="allowDrop(event)">
    			<form id="legsForm" action="${pageContext.servletContext.contextPath}/character" method="post">
					<input type="hidden" name="legs" value="">
    			</form>
    			<c:if test="${! empty legsIMG}">
    				<img id="legsIMG" style="width:100%; top:-54px;" src="${pageContext.request.contextPath}/image/items/${legsIMG}.png" draggable="true" ondragstart="drag(event)"/>
    			</c:if>
    		</div>
    		<div id="boots" ondrop="drop(event)" ondragover="allowDrop(event)">
    			<form id="bootsForm" action="${pageContext.servletContext.contextPath}/character" method="post">
					<input type="hidden" name="boots" value="">
    			</form>
    			<c:if test="${! empty bootsIMG}">
    				<img id="bootsIMG" style="width:100%; top:-54px;" src="${pageContext.request.contextPath}/image/items/${bootsIMG}.png" draggable="true" ondragstart="drag(event)"/>
    			</c:if>
    		</div>
		</div>
	</div>
	<div id="right" ondrop="drop(event)" ondragover="allowDrop(event)">
		<form id="unequip" action="${pageContext.servletContext.contextPath}/character" method="post">
			<input type="hidden" name="remove" value="">
    	</form>
		<c:forEach items="${itemTest}" var = "i" >
			<div class="item" >
				<img id="${i.name}" style="width:100%" src="${pageContext.request.contextPath}/image/items/${i.description_update}.png" draggable="true" ondragstart="drag(event)"/>
				<div class="hidden">
					<h3><b>${i.name}</b></h3>
					<p>${i.longDescription}</p>
					<hr style="width:75%">
						<div class="inline">Weight: ${i.itemWeight}	</div>
						<div class="inline">Level Requirement: ${i.lvl_requirement}	</div>
						<div class="inline">Type: ${i.type}	</div>
					
					<br>
						<div class="inline" id="stat">ATT: <c:choose>
									<c:when test="${i.attack_bonus > 0}"> <span style="font-color:green"> +${i.attack_bonus}</span></c:when>
									<c:when test="${i.attack_bonus < 0}"> <span style="font-color:red"> -${i.attack_bonus}	</span></c:when>
									<c:otherwise>${i.attack_bonus}	</c:otherwise>
								</c:choose>
						</div>
						<div class="inline" id="stat">DEF: <c:choose>
									<c:when test="${i.defense_bonus > 0}"> <span style="font-color:green"> +${i.defense_bonus}	</span></c:when>
									<c:when test="${i.defense_bonus < 0}"> <span style="font-color:red"> -${i.defense_bonus}	</span></c:when>
									<c:otherwise>${i.defense_bonus}	</c:otherwise>
								</c:choose>
						</div>
						<div class="inline" id="stat">HP: <c:choose>
									<c:when test="${i.hp_bonus > 0}"> <span style="font-color:green"> +${i.hp_bonus}	</span></c:when>
									<c:when test="${i.hp_bonus < 0}"> <span style="font-color:red"> -${i.hp_bonus}	</span></c:when>
									<c:otherwise>${i.hp_bonus}	</c:otherwise>
								</c:choose>
						</div>
				</div>
        	</div>
    	</c:forEach>
    	<form id="skillpointForm" action="${pageContext.servletContext.contextPath}/character" method="post">
			<input type="hidden" name="attack" value="">
			<input type="hidden" name="defense" value="">
			<input type="hidden" name="specialattack" value="">
			<input type="hidden" name="specialdefense" value="">
			<input type="hidden" name="magic" value="">
			<input type="hidden" name="hitpoints" value="">
			<input type="hidden" name="skillpoints" value="">
			<input type="hidden" name="updateskillpoints" value="">
    	</form>
	</div>
</div>

<script>
	var skillpoints = parseInt(document.getElementById("skillpoints").innerHTML);
	var att = parseInt(document.getElementById("att").innerHTML);
	var def = parseInt(document.getElementById("def").innerHTML);
	var specatt = parseInt(document.getElementById("specatt").innerHTML);
	var specdef = parseInt(document.getElementById("specdef").innerHTML);
	var mag = parseInt(document.getElementById("mag").innerHTML);
	var hitpoints = parseInt(document.getElementById("hitpoints").innerHTML);
	var ratt = 0;
	var rdef = 0;
	var rspecatt = 0;
	var rspecdef = 0;
	var rmag = 0;
	var rhitpoints = 0;
	
	
function saveSP() {
	var x = document.getElementById("skillpointForm");
	x.elements["attack"].value = att;
	x.elements["defense"].value = def;
	x.elements["specialattack"].value = specatt;
	x.elements["specialdefense"].value = specdef;
	x.elements["magic"].value = mag;
	x.elements["hitpoints"].value = hitpoints;
	x.elements["skillpoints"].value = skillpoints;
	x.elements["updateskillpoints"].value = "true";
	x.submit();
}



function lowerAtt() {

	if (ratt > 0) {
		ratt = ratt - 1;
		att = att - 1
		skillpoints = skillpoints + 1;
		document.getElementById("skillpoints").innerHTML = skillpoints;
		document.getElementById("att").innerHTML = att;
	}
}
function raiseAtt() {
	if (skillpoints > 0) {
		ratt = ratt + 1;
		att = att + 1
		skillpoints = skillpoints - 1;
		document.getElementById("skillpoints").innerHTML = skillpoints;
		document.getElementById("att").innerHTML = att;
	}
}

function lowerDef() {
	
	if (rdef > 0) {
		rdef = rdef - 1;
		def = def - 1
		skillpoints = skillpoints + 1;
		document.getElementById("skillpoints").innerHTML = skillpoints;
		document.getElementById("def").innerHTML = def;
	}
}
function raiseDef() {
	if (skillpoints > 0) {
		rdef = rdef + 1;
		def = def + 1
		skillpoints = skillpoints - 1;
		document.getElementById("skillpoints").innerHTML = skillpoints;
		document.getElementById("def").innerHTML = def;
	}
}

function lowerSpecAtt() {
	
	if (rspecatt > 0) {
		rspecatt = rspecatt - 1;
		specatt = specatt - 1
		skillpoints = skillpoints + 1;
		document.getElementById("skillpoints").innerHTML = skillpoints;
		document.getElementById("specatt").innerHTML = specatt;
	}
}
function raiseSpecAtt() {
	if (skillpoints > 0) {
		rspecatt = rspecatt + 1;
		specatt = specatt + 1
		skillpoints = skillpoints - 1;
		document.getElementById("skillpoints").innerHTML = skillpoints;
		document.getElementById("specatt").innerHTML = specatt;
	}
}

function lowerSpecDef() {
	
	if (rspecdef > 0) {
		rspecdef = rspecdef - 1;
		specdef = specdef - 1
		skillpoints = skillpoints + 1;
		document.getElementById("skillpoints").innerHTML = skillpoints;
		document.getElementById("specdef").innerHTML = specdef;
	}
}
function raiseSpecDef() {
	if (skillpoints > 0) {
		rspecdef = rspecdef + 1;
		specdef = specdef + 1
		skillpoints = skillpoints - 1;
		document.getElementById("skillpoints").innerHTML = skillpoints;
		document.getElementById("specdef").innerHTML = specdef;
	}
}

function lowerMag() {
	
	if (rmag > 0) {
		rmag = rmag - 1;
		mag = mag - 1
		skillpoints = skillpoints + 1;
		document.getElementById("skillpoints").innerHTML = skillpoints;
		document.getElementById("mag").innerHTML = mag;
	}
}
function raiseMag() {
	if (skillpoints > 0) {
		rmag = rmag + 1;
		mag = mag + 1
		skillpoints = skillpoints - 1;
		document.getElementById("skillpoints").innerHTML = skillpoints;
		document.getElementById("mag").innerHTML = mag;
	}
}

function lowerHitpoints() {
	
	if (rhitpoints > 0) {
		rhitpoints = rhitpoints - 1;
		hitpoints = hitpoints - 10
		skillpoints = skillpoints + 1;
		document.getElementById("skillpoints").innerHTML = skillpoints;
		document.getElementById("hitpoints").innerHTML = hitpoints;
	}
}
function raiseHitpoints() {
	if (skillpoints > 0) {
		rhitpoints = rhitpoints + 1;
		hitpoints = hitpoints + 10
		skillpoints = skillpoints - 1;
		document.getElementById("skillpoints").innerHTML = skillpoints;
		document.getElementById("hitpoints").innerHTML = hitpoints;
	}
}

</script>


</body>
</html>