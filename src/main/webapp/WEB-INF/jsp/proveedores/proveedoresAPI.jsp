<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="sugerencias">
    <h1>Proveedores</h1>
        	<div id="caja"></div>
</petclinic:layout>

<script>var pathArray = window.location.pathname.split('/');
		if (pathArray[3] == null){var ruta = "http://localhost:8080/api/proveedores/get"}
		else{var ruta = "http://localhost:8080/api/proveedores/get/"+pathArray[3]}
</script>


<script>
        
        $(document).ready(function() {
            $.ajax({
                url: ruta,
                type: "GET",
                dataType: "json",
            	success: function(data) {
            		var len = data.length;
            		if(len == null){document.getElementById('caja').innerHTML += '<div style="border-radius: 10px;border-color: black;border-style: solid;background-color: white;padding: 1.5%;margin: 1.5%;"> <h2>' 
                		+ data.nombre + '</h2>\n<p> NIF: '
	            		+ data.nif +'</p>\n<p style="margin-top:1%;">Dirección: '
    	        		+ data.direccion + '</p>\n<p style="margin-top:1%;">Teléfono: '
        	    		+ data.telefono + '</p>\n<p style="margin-top:1%;">Email: '
            			+ data.email + '</div>'; }
            		
            		else{
            			for(var i=0; i<len; i++){
                			document.getElementById('caja').innerHTML += '<div style="border-radius: 10px;border-color: black;border-style: solid;background-color: white;padding: 1.5%;margin: 1.5%;"> <h2>' 
	                		+ data[i].nombre + '</h2>\n<p> NIF: '
    	            		+ data[i].nif +'</p>\n<p style="margin-top:1%;">Dirección: '
        	        		+ data[i].direccion + '</p>\n<p style="margin-top:1%;">Teléfono: '
            	    		+ data[i].telefono + '</p>\n<p style="margin-top:1%;">Email: '
                			+ data[i].email + '</div>';
                		}
            		}
            	}
 			});
        });
</script>