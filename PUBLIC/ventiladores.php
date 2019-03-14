<?php require('../PHP/validSession.php');?>
<?php
	require('../PHP/modValidSerialPort.php');
	require('../PHP/connection.php');

	if (isset($_REQUEST["action"])) {
		$accion = substr($_REQUEST["action"], 0,1);
		$accion = escapeshellcmd($accion);
		fputs ($fp, $accion);
	 	fclose ($fp);	 	

	 	$id = substr($_REQUEST["action"], 1);

	 	$sqlUDT = "UPDATE statusDispositivos SET statusDisp='$accion' WHERE codDisp='$id'";
		$queryUDT = $mysqli->query($sqlUDT);
	 	
	 	header("Location: ventiladores.php");
	}	else {
		$sqlBDS = "SELECT * FROM statusDispositivos WHERE nameDisp='VENTILADOR'";
		$queryBDS = $mysqli->query($sqlBDS);
	}	
?>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>House on:Ventiladores</title>
	<link rel="shortcut icon" href="../IMG/HouseOn.ico"/>
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="../CSS/frontend.css">
</head>
<body id="ventilators">

	<a href="../PHP/leave.php" id="cerrar-sesion">
		<img src="../IMG/salir.png" alt="salir.png" id="closeSession" title="Cerrar Sesión">
	</a>

	<header>
		<h1>... Modificando estado de los ventiladores ...</h1>
	</header>

	<section class="container">
		<div class="centerTable">
			<table class="table">
				<tr>
					<th>LUGAR</th>
					<th>ESTADO</th>
					<th>OPCIONES</th>
				</tr>
				<?php while ($arrayBDS = $queryBDS->fetch_assoc()) {?>
					<tr class="lineTable">
						<form action="ventiladores.php" method="GET">
							<td><?php echo $arrayBDS['placeDisp'];?></td>
							<td>
							<?php 
								$status = $arrayBDS['statusDisp'];
								if ($status == 'g') {
									echo "<img src='../IMG/puertaAbierta.png' alt='Puerta Abierta.png'>";
								} else {
									echo "<img src='../IMG/puertaCerrada.png' alt='Puerta Cerrada.png'>";
								}
							?>
							</td>
							<td>
							<?php
								$id = $arrayBDS['codDisp'];
								$status = $arrayBDS['statusDisp'];
								$cadena = $status." ".$id;

								switch ($status) {
									case 'g':
										echo "<button type='submit' name='action' value='h 4' class='btn btnCerrar'>Apagar</button>";
										break;
									case 'h':
										echo "<button type='submit' name='action' value='g 4' class='btn btnAbrir'>Encender</button>";
										break;
									default:
										echo ".";
										break;
								}
							?>
							</td>
						</form>
					</tr>
				<?php } ?>
			</table>
		</div>
	</section>

	<footer>
		<?php include('src/navBar.php');?>
	</footer>

</body>
</html>
