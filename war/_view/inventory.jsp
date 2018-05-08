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
        	width: 100px;
        	height: 118px;
        	font-size: 12px;
        }
        .hidden {
        	position: relative;
        	visibility: hidden;
        	left: 75 px;
        	top: -75px;
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

<!--
	private float itemWeight;
	private boolean isQuestItem;
	private String description_update;
	private int attack_bonus;
	private int defense_bonus;
	private int hp_bonus;
	private int lvl_requirement;
	private ItemType type;
	-->

<div id="test">
	<c:forEach items="${itemTest}" var = "i" >
		<div class="item">
			<p>${i.name}</p>
			<img src="${pageContext.request.contextPath}/image/items/${i.description_update}.png"/>
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
<!--<script>
function hover() {
	$(.item).mouseenter(function(){
    	$(this > .hidden).style.display("block");
	}).mouseleave(function(){
    	$(this > .hidden).style.display("none"); 
	});
}
</script> -->

</body>
</html>