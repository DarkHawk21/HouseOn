<?php 
	session_start();
	if (isset($_SESSION['user'])) {header('Location:PUBLIC/');}
	//cloudino
	//StreamR
	//IbM  watson
 ?>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>House on</title>
	<link rel="shortcut icon" href="IMG/HouseOn.ico"/>
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="CSS/frontend.css">
</head>
<body id="log">
	<div class="error">
		<span>Los datos ingresados no son válidos, Por favor inténtelo nuevamente.</span>
	</div>

	<div class="container">
		<h1>HOUSE ON</h1>
		<h2>¡Tu casa al alcance de una app!</h2>

		<form action="" id="formLg">
			<div class="usuarioPass">
				<label for="uss">Usuario:</label>
				<input type="text" name="uss" id="uss" tabindex="1" required pattern="[A-Za-z0-9_-]{1,15}">

				<label for="pass">Contraseña:</label>
				<input type="password" name="pass" id="pass" tabindex="2" required pattern="[A-Za-z0-9_-$]{1,9}">
			</div>

			<div class="btnEntrarContainer">
				<input type="submit" id="btn-login" value="Acceder" tabindex="3">
			</div>
		</form>

	</div>

	<footer>
		© • DarkTechCommunity • Todos los derechos reservados • 2018
	</footer>
	
	<script src="JS/jquery-3.2.1.min.js"></script>
	<script src="JS/main.js"></script>

</body>
</html>