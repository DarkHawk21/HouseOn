<?php 
	//Si existe una petición al servidor
	if(!empty($_SERVER['HTTP_X_REQUESTED_WITH']) && strtolower($_SERVER['HTTP_X_REQUESTED_WITH']) == 'xmlhttprequest'){
		//Nos conectamos al servidor
		require('connection.php');

		//Hacemos un retraso de 2 segundos
		sleep(2);

		//Iniciamos una sesión
		session_start();

		//Declaramos variables para usuario y contraseña
			$user = $mysqli->real_escape_string($_POST['uss']); 
			$pass = $mysqli->real_escape_string($_POST['pass']);

		//Hacemos una consulta para evaluar los datos
		if ($sql = $mysqli->prepare("SELECT name,access FROM login WHERE user=? AND pass=?")) {
			$sql->bind_param('ss', $user, $pass);
			$sql->execute();
			$query = $sql->get_result();
			//Asignamos todos los datos del usuario a una variable de sesión
			if ($query->num_rows == 1) {
				$array = $query->fetch_assoc();
				$_SESSION['user'] = $array;
				echo json_encode(array('error'=>false, 'access'=>$array['access']));
			} else {echo json_encode(array('error'=>true));}

			$sql->close();
		}
	}

	$mysqli->close();
?>