<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="libros">
    <h1>Libros</h1>
        	<div id="caja"></div>
</petclinic:layout>


<script>var pathArray = window.location.pathname.split('/');
		if (pathArray[3] == null){var ruta = "http://localhost:8080/api/libros/get"}
		else{var ruta = "http://localhost:8080/api/libros/get/"+pathArray[3]}
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
                		+ data.titulo + '</h2>\n<em> Editorial: '
                		+ data.editorial.nombre + '</p>\n<p style="margin-top:1%;">Idioma: '
                		+ data.idioma + '</p>\n<p style="margin-top:1%;">Fecha publicación: '
                		+ data.fecha_publicacion + '</p>\n<p style="margin-top:1%;">ISBN: '
                		+ data.isbn + '</div>';
            		} else{
            			for(var i=0; i<len; i++){
            				document.getElementById('caja').innerHTML += '<div style="border-radius: 10px;border-color: black;border-style: solid;background-color: white;padding: 1.5%;margin: 1.5%;"> <h2>' 
                        		+ data[i].titulo + '</h2>\n<em> Editorial: '
                        		+ data[i].editorial.nombre + '</p>\n<p style="margin-top:1%;">Idioma: '
                        		+ data[i].idioma + '</p>\n<p style="margin-top:1%;">Fecha publicación: '
                        		+ data[i].fecha_publicacion + '</p>\n<p style="margin-top:1%;">ISBN: '
                        		+ data[i].isbn + '</div>';
                        	
            			}
            		}
                		
                
            	}
 			});
        });
</script>