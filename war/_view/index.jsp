<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
	<head>
		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.min.css">
    	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.10/css/all.css" integrity="sha384-+d0P83n9kaQMCwj8F4RJB66tzIwOKmrdb46+porD/OvrJ+37WqIM7UoBtwHO6Nlg" crossorigin="anonymous">
		<title>Index view</title>
		<style>
		.features-boxed{color:#313437;background-color:#eef4f7}
		.features-boxed p{color:#7d8285}
		.features-boxed h2{font-weight:700;margin-bottom:40px;padding-top:40px;color:inherit}
		@media (max-width:767px){
		.features-boxed h2{margin-bottom:25px;padding-top:25px;font-size:24px}}
		.features-boxed .intro{font-size:16px;max-width:500px;margin:0 auto}
		.features-boxed .intro p{margin-bottom:0}
		.features-boxed .features{padding:50px 0}
		.features-boxed .item{text-align:center}
		.features-boxed .item .box{text-align:center;padding:30px;background-color:#fff;margin-bottom:30px}
		.features-boxed .item .icon{font-size:60px;color:#1485ee;margin-top:20px;margin-bottom:35px}
		.features-boxed .item .name{font-weight:700;font-size:18px;margin-bottom:8px;margin-top:0;color:inherit}
		.features-boxed .item .description{font-size:15px;margin-top:15px;margin-bottom:20px}
		.error {color:red}
		</style>
	</head>

	<body>
		<div class="features-boxed">
			        <div class="container">
			            <div class="intro">
			                <h2 class="text-center">Adventures of Middle Earth</h2>
			                <p class="text-center">Weclome ${player}</p>
			                <c:if test="${! empty player}"><form class="text-center" action="${pageContext.servletContext.contextPath}/index" method="post"><input type="Submit" name="submit" value="Log out"></form></c:if>
			            </div>
		<c:choose>
			<c:when test="${! empty player}">
			            <div class="row justify-content-center features">
			                <div class="col-sm-6 col-md-5 col-lg-4 item">
			                    <div class="box"><i class="fas fa-gamepad"></i>
			                        <h3 class="name">Game 1</h3><c:choose> <c:when test="${! empty game1}"><form action="${pageContext.servletContext.contextPath}/index" method="post"><button class="btn btn-primary" type="submit" name="submit" value="Load Game 1">Load Game</button><br/><button class="btn btn-primary" type="submit" name="submit" value="Delete Game 1">Delete Game File</button></form></c:when><c:otherwise><form action="${pageContext.servletContext.contextPath}/index" method="post"><button class="btn btn-primary" type="submit" name="submit" value="Create Game 1">Create Game</button></form></c:otherwise></c:choose>
			                    </div>
			                </div>
			                <div class="col-sm-6 col-md-5 col-lg-4 item">
			                    <div class="box"><i class="fas fa-gamepad"></i>
			                        <h3 class="name">Game 2</h3><c:choose> <c:when test="${! empty game2}"><form action="${pageContext.servletContext.contextPath}/index" method="post"><button class="btn btn-primary" type="submit" name="submit" value="Load Game 2">Load Game</button><br/><button class="btn btn-primary" type="submit" name="submit" value="Delete Game 2">Delete Game File</button></form></c:when><c:otherwise><form action="${pageContext.servletContext.contextPath}/index" method="post"><button class="btn btn-primary" type="submit" name="submit" value="Create Game 2">Create Game</button></form></c:otherwise></c:choose>
			                    </div>
			                </div>
			                <div class="col-sm-6 col-md-5 col-lg-4 item">
			                    <div class="box"><i class="fas fa-gamepad"></i>
			                        <h3 class="name">Game 3</h3><c:choose> <c:when test="${! empty game3}"><form action="${pageContext.servletContext.contextPath}/index" method="post"><button class="btn btn-primary" type="submit" name="submit" value="Load Game 3">Load Game</button><br/><button class="btn btn-primary" type="submit" name="submit" value="Delete Game 3">Delete Game File</button></form></c:when><c:otherwise><form action="${pageContext.servletContext.contextPath}/index" method="post"><button class="btn btn-primary" type="submit" name="submit" value="Create Game 3">Create Game</button></form></c:otherwise></c:choose>
			                    </div>
			                </div>
			                <div class="col-sm-6 col-md-5 col-lg-4 item">
			                    <div class="box"><i class="fas fa-gamepad"></i>
			                        <h3 class="name">Game 4</h3><c:choose> <c:when test="${! empty game4}"><form action="${pageContext.servletContext.contextPath}/index" method="post"><button class="btn btn-primary" type="submit" name="submit" value="Load Game 4">Load Game</button><br/><button class="btn btn-primary" type="submit" name="submit" value="Delete Game 4">Delete Game File</button></form></c:when><c:otherwise><form action="${pageContext.servletContext.contextPath}/index" method="post"><button class="btn btn-primary" type="submit" name="submit" value="Create Game 4">Create Game</button></form></c:otherwise></c:choose>
			                    </div>
			                </div>
			                <div class="col-sm-6 col-md-5 col-lg-4 item">
			                    <div class="box"><i class="fas fa-gamepad"></i>
			                        <h3 class="name">Game 5</h3><c:choose> <c:when test="${! empty game5}"><form action="${pageContext.servletContext.contextPath}/index" method="post"><button class="btn btn-primary" type="submit" name="submit" value="Load Game 5">Load Game</button><br/><button class="btn btn-primary" type="submit" name="submit" value="Delete Game 5">Delete Game File</button></form></c:when><c:otherwise><form action="${pageContext.servletContext.contextPath}/index" method="post"><button class="btn btn-primary" type="submit" name="submit" value="Create Game 5">Create Game</button></form></c:otherwise></c:choose>
			                    </div>
			                </div>
			                <div class="col-sm-6 col-md-5 col-lg-4 item">
			                    <div class="box"><i class="fas fa-gamepad"></i>
			                        <h3 class="name">Game 6</h3><c:choose> <c:when test="${! empty game6}"><form action="${pageContext.servletContext.contextPath}/index" method="post"><button class="btn btn-primary" type="submit" name="submit" value="Load Game 6">Load Game</button><br/><button class="btn btn-primary" type="submit" name="submit" value="Delete Game 6">Delete Game File</button></form></c:when><c:otherwise><form action="${pageContext.servletContext.contextPath}/index" method="post"><button class="btn btn-primary" type="submit" name="submit" value="Create Game 6">Create Game</button></form></c:otherwise></c:choose>
			                    </div>
			                </div>
			            </div>
			        </div>
			    </div>
			    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    			<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
			</c:when>
			
			<c:otherwise>
				<div class="row justify-content-center features">
			                <div class="col-sm-6 col-md-5 col-lg-4 item">
			                    <div class="box"><i class="fas fa-users"></i>
			                <form action="${pageContext.servletContext.contextPath}/index" method="post">
					<table>
						<tr>
							<td class="label">Username: </td>
							<td><input type="text" name="username" size="12" value="${username}" /></td>
						</tr>
						<tr>
							<td class="label">Password: </td>
							<td><input type="password" name="password" size="12" value="${password}" /></td>
						</tr>
							
						<tr>
							<c:if test="${! empty errorMessage}">
						   		<div class="error">${errorMessage}</div>
							</c:if>
						</tr>
					</table>
					<input type="Submit" name="submit" value="Login">
					<p></br>Don't have an account? <button type="Submit" name="submit" value="Create Account">Create an Account</button></p>
				</form>	
				</div>
					
			</c:otherwise>
		
		</c:choose>
	</body>
</html>



