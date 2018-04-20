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
<div id = "wrapper">
	<div id="top">
		<ul>
			<li>name: ${name}</li>
			<li>gender: ${gender}</li>
			<li>race: ${race}</li>
			<li>level: ${level}</li>
			<li>health: ${hp}</li>
			<li>coins: ${coins}</li>
	</div>
	<div id="left">
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
		<ul>
			<li>attack: ${attack}</li>
			<li>defense: ${defense}</li>
			<li>special attack: ${specialAttack}</li>
			<li>special defense: ${specialDefense}</li>
			<li>magic: ${magic}</li>
		</ul>
	</div>
</div>

</body>
</html>