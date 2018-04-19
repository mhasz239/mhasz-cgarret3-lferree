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
   	 	#top {
   	 		height: 30%;
   	 		width: 100%;
   	 	}
   	 	#left{
   	 		float: left;
   	 		height: 70%;
   	 		width: 50%;
   	 	}
   	 	#right{
   	 		float: left;
   	 		height: 70%;
   	 		width: 50%;
   	 	}
    </style>
</head>

<body>

<h1>DOES THIS SHOW UP??????</h1>
<p>This is a paragraph that doesnt seem to be doing anything...</p>

<div id = "wrapper">

<h1>DOES THIS SHOW UP??????</h1>
<p>This is a paragraph that doesnt seem to be doing anything...</p>

	<div id="top">
		<ul>
			<li>name: ${player.name}</li>
			<li>gender: ${player.gender}</li>
			<li>race: ${player.race}</li>
			<li>level: ${player.level}</li>
			<li>health: ${player.hit_points}</li>
			<li>coins: ${player.coins}</li>
	</div>
	<div id="left">
		<ul>
			<li>head: ${player.helm}</li>
			<li>breastplate: ${player.chest}</li>
			<li>arms: ${player.braces}</li>
			<li>legs: ${player.legs}</li>
			<li>feet: ${player.boots}</li>
			<li>left hand: ${player.l_hand}</li>
			<li>right hand: ${player.r_hand}</li>
		</ul>
	</div>
	<div id="right">
		<ul>
			<li>attack: ${player.attack}</li>
			<li>defense: ${player.defense}</li>
			<li>special attack: ${player.special_attack}</li>
			<li>special defense: ${player.special_defense}</li>
			<li>magic: ${player.magic}</li>
		</ul>
	</div>
</div>

</body>
</html>