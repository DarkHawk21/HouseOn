<?php require('../PHP/validSession.php');?>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>House on</title>
	<link rel="shortcut icon" href="../IMG/HouseOn.ico"/>
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="../CSS/frontend.css">
</head>
<body id="index">

	<a href="../PHP/leave.php" id="cerrar-sesion">
		<img src="../IMG/salir.png" alt="salir.png" id="closeSession" title="Cerrar Sesión">
	</a>

	<header>
		<h1>¡Bienvenido <?php echo $_SESSION['user']['name'];?>!</h1>
	</header>

	<section class="container">
		<h3></h3>
	</section>

	<footer>
		<?php include('src/navBar.php');?>
	</footer>

</body>
</html>
