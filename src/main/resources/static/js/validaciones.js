const POSTUSER = "http://localhost:8080/usuariodb/addUsers"

function validarEmail(dato) {
    var letras = ['T','R','W','A','G','M','Y','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E','T'];
    var finalEmail = ['.COM', '.NET','.ORG'];
    var finalEmail2 = ['.ES'];
    var numero;

    dato = dato.toUpperCase();
    /*Hago la comprobación de la longitud */
    if(dato.length < 9 || dato.length > 50 ) {
        document.getElementById('mensaje').innerHTML = "Longitud no válida debe tener + que 9 carácteres y menos de 50";
        /*Hago la comprobación que empieze por letra */
    } else if(!letras.includes(dato.charAt(0))) {
        document.getElementById('mensaje').innerHTML = "Debe empezar por una letra";
        /*Hago la comprobación que el @ no esteé en las distancias 3 del inicio y 5 del fin */
    } else if(!dato.split('@').length -1 === 1 || dato.substring(0, 3).includes('@') || dato.slice(-5).includes('@')) {
        document.getElementById('mensaje').innerHTML = "Debe tener 1 @ y no puede estar en los 3 primeros caracteres ni en los 5 últimos";
        /*Hago la comprobación de que termine con los finales: '.com', '.es', '.net','.org' */
        /*Si el final termina con 4 carácteres o si el final termina con 3 carácteres */
    } else if(!finalEmail.includes(dato.slice(-4)) && !finalEmail2.includes(dato.slice(-3))) {
        console.log("entro")
        document.getElementById('mensaje').innerHTML = "Debe finalizar con: '.com', '.es', '.net','.org'";
    } else {
        document.getElementById('mensaje').innerHTML = "Válido";
    }
}
    
$("button").click(function() {
    validar(document.getElementById('email').value);
});

function validarDni(dato) {
    var letras = ['T','R','W','A','G','M','Y','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E','T'];
    var numero;

    console.log(dato)
    if(dato.length != 12) {
        document.getElementById('mensaje').innerHTML = "Longitud no válida";

    } else {
        if (dato.charAt(2) != '.' || dato.charAt(6) != '.') {
            document.getElementById('mensaje').innerHTML = "Debe tener puntos en la 2 posición y la 6 posición";
        } else {
            if(dato.charAt(10) != '-') {
                document.getElementById('mensaje').innerHTML = "Debe tener - en la posición 10";
            } else {
                if(!letras.includes(dato.charAt(11))) {
                    document.getElementById('mensaje').innerHTML = "Debe tener Una letra Mayúuscula al final";
                } else {
                    numero=dato.substr(0,2) + dato.substr(3,3) + dato.substr(7,3);
                    if(isNaN(numero)) {
                        console.log(numero)
                        document.getElementById('mensaje').innerHTML = "Debe tener numeros entre los puntos y antes del -";
                    } else {
                        document.getElementById('mensaje').innerHTML = "Todo Ok";
                    }
                    
                }

            }
        }
    }
        
    }
    
    $("button").click(function() {
        validar(document.getElementById('dni').value);
    });

    function imprimirError(error) {
        document.getElementById("imprimirError").innerHTML = error;
    }
    
    function enviar() {
	if(nombre.value != " " && apellidos.value != " " && validarDni(dni.value) && validarEmail(e_email.value)) {
		fecth(POSTUSER, {
			method: 'POST',
			body: JSON.stringify({
				name: nombre.value,
				lasName: apellidos.value,
				e_mail: email.value,
				dni: dni.value,
			}), headers: {
				'Content-Type': 'application/json'
			}, redirect:'follow'
		})
			.then(response => {
				if(response.status == 201){
					alert("Usuario introducido correcatmente")
				}else if(response.status == 400) {
					alert("Este usuario ya está registrado")
				}else {
					alert("Error en la Api")
				}
					
			})

}
}