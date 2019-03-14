$('#formLg').submit(function(event){
	event.preventDefault();

	$.ajax({
		url: 'PHP/log.php',
		type: 'POST',
		dataType: 'json',
		data: $(this).serialize(),
		beforeSend: function(){$('#btn-login').val("Validando...");}
	})

	.done(function(respuesta){
		console.log(respuesta);
		if(!respuesta.error) {
			if(respuesta.access == '1') {
				location.href = 'PUBLIC/index.php';	
			} 	
		} else {
			$('.error').slideDown('slow');
			setTimeout(function(){$('.error').slideUp('slow');},3000);
			$('#btn-login').val("Acceder");
		}
	})

	.fail(function(resp) {console.log(resp.responseText);})

	.always(function() {console.log("complete");});
});