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
	 	
	 	header("Location: luces.php");
	}	else {
		$sqlBDS = "SELECT * FROM statusDispositivos WHERE nameDisp='LED'";
		$queryBDS = $mysqli->query($sqlBDS);
	}	
?>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>House on</title>
	<link rel="shortcut icon" href="../IMG/HouseOn.ico"/>
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="../CSS/frontend.css">
</head>
<body id="lights">

	<a href="../PHP/leave.php" id="cerrar-sesion">
		<img src="../IMG/salir.png" alt="salir.png" id="closeSession" title="Cerrar Sesión">
	</a>

	<header>
		<h1>... Modificando estado de luces ...</h1>
	</header>

	<section class="container">
		<div class="centerTable">
			<table class="table" id="tableLights">
				<tr>
					<th>LUGAR</th>
					<th>ESTADO</th>
					<th>OPCIONES</th>
				</tr>
				<?php while ($arrayBDS = $queryBDS->fetch_assoc()) {?>
					<tr class="lineTable">
						<form action="luces.php" method="GET">
							<td><?php echo $arrayBDS['placeDisp'];?></td>
							<td>
							<?php 
								$status = $arrayBDS['statusDisp'];
								if ($status == 'a' || $status == 'c' || $status == 'e') {
									echo "<img src='../IMG/focoapagado.png' alt='Foco Prendido.png'>";
								} else {
									echo "<img src='../IMG/focoencendido.png' alt='Foco Apagado.png'>";
								}
							?>
							</td>
							<td>
							<?php
								$id = $arrayBDS['codDisp'];
								$status = $arrayBDS['statusDisp'];
								$cadena = $status." ".$id;

								switch ($status) {
									case 'a':
										echo "<button type='submit' name='action' value='b 1' class='btn btnCerrar'>Apagar</button>";
										break;
									case 'c':
										echo "<button type='submit' name='action' value='d 2' class='btn btnCerrar'>Apagar</button>";
										break;
									case 'e':
										echo "<button type='submit' name='action' value='f 3' class='btn btnCerrar'>Apagar</button>
										";
										break;
									case 'b':
										echo "<button type='submit' name='action' value='a 1' class='btn btnAbrir'>Encender</button>";
										break;
									case 'd':
										echo "<button type='submit' name='action' value='c 2' class='btn btnAbrir'>Encender</button>";
										break;
									case 'f':
										echo "<button type='submit' name='action' value='e 3' class='btn btnAbrir'>Encender</button>";
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
