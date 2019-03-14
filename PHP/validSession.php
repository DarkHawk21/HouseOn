<?php
	//Iniciamos el objeto sesion
		session_start();

	//Validamos que la sesión exista para hacer alguna acción
		if (!isset($_SESSION['user'])) {
			header('Location: ../index.php');
		}
?>