<?php
	//Hacemos el objeto conexion
	$mysqli = new mysqli('localhost', 'root', '', 'houseon');
	//Si hay errores pausará el código de la página y mandará un error
	if ($mysqli->connect_errno) {
		echo "Error al conectarse con MySQL debido al error ".$mysqli->connect_error."<br>";
		die();
	}
	//Configuracion para aceptar caracteres especiales
	$mysqli->set_charset("utf8");
 ?>