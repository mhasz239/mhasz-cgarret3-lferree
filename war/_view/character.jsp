<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>Character</title>
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
   	 		left: 50%;
   	 		width:1px;
   	 	}
   	 	
   	 	#head{
   	 		left: -50px;
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
   	 	
   	 	}
   	 	#arms{
   	 	
   	 	}
   	 	#lhand{
   	 	
   	 	}
   	 	#rhand{
   	 	
   	 	}
   	 	#legs{
   	 	
   	 	}
   	 	#right{
   	 		float: left;
   	 		height: 70%;
   	 		width: 49%;
   	 	}
   	 	.inline {
        	display: inline;
        	padding-right:18px;
        }
        #stat {
        	padding-right:75px;
        }
        .item {
        	display: inline;
        	float: left;
        	width: 50px;
        	height: 62px;
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
		    console.log(data);
		    console.log(ev.target);
		     console.log(ev.target.id);
		    ev.target.appendChild(document.getElementById(data));
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
			<li>health: ${hp}</li>
			<li>coins: ${coins}</li>
	</div>
	<div id="stats">
		<ul>
			<li>attack: ${attack}</li>
			<li>defense: ${defense}</li>
			<li>special attack: ${specialAttack}</li>
			<li>special defense: ${specialDefense}</li>
			<li>magic: ${magic}</li>
		</ul>
	</div>

	<div id="left">
		<div if="leftmid">
		<div id="head" ondrop="drop(event)" ondragover="allowDrop(event)">
			<form id="head" action="${pageContext.servletContext.contextPath}/GameView" method="post">
				<input type="hidden" name="head" value="">
    		</form>
    	</div>
		</div>
		<ul>
			<li>head: ${helm}</li>
			<li>breastplate: ${chest}</li>
			<li>arms: ${braces}</li>
			<li>legs: ${legs}</li>
			<li>feet: ${boots}</li>
			<li>left hand: ${l_hand}</li>
			<li>right hand: ${r_hand}</li>
		</ul>
	</div>
	<div id="right">
		<c:forEach items="${itemTest}" var = "i" >
			<div class="item" >
				<p>${i.name}</p>
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
	</div>
</div>

</body>
</html>