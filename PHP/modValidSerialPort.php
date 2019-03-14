<?php
	for ($puerto = 1; $puerto < 10; $puerto++) {
		exec("mode COM".$puerto." BAUD=9600 PARITY=N data=8 stop=1 xon=off");

		if (@fopen ("COM".$puerto, "w")) {
			$fp = @fopen ("COM".$puerto, "w");
		}
	}

	if (!isset($fp)) {
		echo "<body style='background: url(../IMG/Back1.jpg); background-position: center;background-attachment: fixed; background-size: cover;'><h1 style='text-align: center;color:#0072FF; text-shadow: 1px 1px 1px #000; padding-top: 20px;'>Tu casa NO está conectada correctamente.</h1><br><br><a href='index.php' style='background: rgba(0,0,255,0.8); width: auto; text-decoration: none; color: #fff; font-family: verdana; font-size: 24px; padding: 10px 25px; cursor: pointer; margin-left: 44%; border-radius: 10px;'>Regresar</a></body>";
		die();
	}
?>