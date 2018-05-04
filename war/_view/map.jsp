<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>Map</title>
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
   	 	#map {
   	 		background-image: url("${pageContext.request.contextPath}/image/map/map.jpeg");
   	 		background-repeat: no-repeat;
   	 		height: 400px;
   	 		width: 400px;
   	 	}
   	 	
    </style>
</head>

<body>
<c:if test="${! empty errorMessage}">
    <div class="error">${errorMessage}</div>
</c:if>
<div id="wrapper">


	<center>
		<div style="width:100%"><h4>Map</h4></div>
		<div id="map"></div>
	</center>
	<!--
	<div class="mapTile" <c:if test="${tile7}">style="background-color:white;"</c:if>><center><p>Tile 7</p></center></div>	<div class="mapTile" <c:if test="${tile8}">style="background-color:white;"</c:if>><center><p>Tile 8</p></center></div>	<div class="mapTile" <c:if test="${tile9}">style="background-color:white;"</c:if>><center><p>Tile 9</p></center></div></br>
	<div class="mapTile" <c:if test="${tile4}">style="background-color:white;"</c:if>><center><p>Tile 4</p></center></div>	<div class="mapTile" <c:if test="${tile5}">style="background-color:white;"</c:if>><center><p>Tile 5</p></center></div>	<div class="mapTile" <c:if test="${tile6}">style="background-color:white;"</c:if>><center><p>Tile 6</p></center></div></br>
	<div class="mapTile" <c:if test="${tile1}">style="background-color:white;"</c:if>><center><p>Tile 1</p></center></div>	<div class="mapTile" <c:if test="${tile2}">style="background-color:white;"</c:if>><center><p>Tile 2</p></center></div>	<div class="mapTile" <c:if test="${tile3}">style="background-color:white;"</c:if>><center><p>Tile 3</p></center></div></br>
	</center>
	-->
</div>

</body>
</html>