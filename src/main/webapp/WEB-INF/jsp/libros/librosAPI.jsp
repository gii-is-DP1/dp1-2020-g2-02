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

<script>
        
        $(document).ready(function() {
            $.ajax({
                url: "http://localhost:8080/api/libros/getAll",
                type: "GET",
                dataType: "json",
            	success: function(data) {
            		var len = data.length;
            		
            		for(var i=0; i<len; i++){
                		document.getElementById('caja').innerHTML += '<div style="border-radius: 10px;border-color: black;border-style: solid;background-color: white;padding: 1.5%;margin: 1.5%;"> <h2>' 
                		+ data[i].titulo + '</h2>\n<em> Autores: '
                		+ data[i].autores.nombre +'</em>\n<p style="margin-top:1%;">Géneros: '
                		+ data[i].generos + '</p>\n<p style="margin-top:1%;">Editorial: '
                		+ data[i].editorial.nombre + '</p>\n<p style="margin-top:1%;">Idioma: '
                		+ data[i].idioma + '</p>\n<p style="margin-top:1%;">Fecha publicación: '
                		+ data[i].fecha_publicacion + '</p>\n<p style="margin-top:1%;">ISBN: '
                		+ data[i].isbn + '</div>';
                	}
                
            	}
 			});
        });
</script>