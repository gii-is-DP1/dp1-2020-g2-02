<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="novedades">
    <h1>Novedades</h1>
        	<div id="caja"></div>
</petclinic:layout>

<script>
        
        $(document).ready(function() {
            $.ajax({
                url: "http://localhost:8080/api/novedades/getAll",
                type: "GET",
                dataType: "json",
            	success: function(data) {
            		var len = data.length;
            		
                	for(var i=0; i<len; i++){
                		document.getElementById('caja').innerHTML += '<div style="border-radius: 10px;border-color: black;border-style: solid;background-color: white;padding: 1.5%;margin: 1.5%;"> <h2>' + data[i].titulo + '</h2>\n<em> Publicada el '+ data[i].fechaPublicacion +'</em>\n<p style="margin-top:1%;">'+ data[i].contenido + '</p></div>';
                	}
            	}
 			});
        });
        </script>