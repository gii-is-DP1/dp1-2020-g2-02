<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="sugerencias">
    <h1>Sugerencias</h1>
        	<div id="caja"></div>
</petclinic:layout>

<script>var pathArray = window.location.pathname.split('/');
		if (pathArray[3] == null){var ruta = "http://localhost:8080/api/sugerencias/get"}
		else{var ruta = "http://localhost:8080/api/sugerencias/get/"+pathArray[3]}
</script>


<script>
        
        $(document).ready(function() {
            $.ajax({
                url: ruta,
                type: "GET",
                dataType: "json",
            	success: function(data) {
            		var len = data.length;
            		if(len == null){document.getElementById('caja').innerHTML += '<div style="border-radius: 10px;border-color: black;border-style: solid;background-color: white;padding: 1.5%;margin: 1.5%;"> <h3>' + data.tituloLibro + '</h3>\n<em> Autor: '+ data.nombreAutor +'</em></div>';}
            		else{
                		for(var i=0; i<len; i++){
                			document.getElementById('caja').innerHTML += '<div style="border-radius: 10px;border-color: black;border-style: solid;background-color: white;padding: 1.5%;margin: 1.5%;"> <h3>' + data[i].tituloLibro + '</h3>\n<em> Autor: '+ data[i].nombreAutor +'</em></div>';
                		}
            		}
            	}
 			});
        });
</script>