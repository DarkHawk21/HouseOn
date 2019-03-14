<?php
	//Iniciamos la sesión
	session_start();
	//Destruimos la sesión que está inicializada en el navegador
	session_destroy();
	//Regresamos al login
	header("Location:../");
 ?>