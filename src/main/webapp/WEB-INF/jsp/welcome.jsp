<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
    <div class="row">

    <h1>Bienvenido a BiblioNET</h1>
    <br/>
    <br/>
    
    <h2>Project ${title} </h2>
    <h2>Group ${group}</h2>
    <ul>
    <c:forEach items="${persons}" var="person">
        <li>${person.firstName} ${person.lastName}</li>
    </c:forEach>
    </ul>
    </div>
    
    <div style="border-radius: 10px;
    			border-color: black;
  				border-style: solid;
    			background-color: white;
    			padding: 1.5%;
    			margin: 1.5%;
    		">
    	<h2>Estadísticas del día anterior en la biblioteca:</h2>
    	<ul>
    		<li>Se realizaron ${datos.prestamos} préstamos.</li>
    		<li>Se publicaron ${datos.novedades} novedades.</li>
    		<li>Se realizaron ${datos.encargos} encargos.</li>
    	</ul>
    </div>
    
    <br>
    <br>
    <h2>APIs REST</h2>
    <a href='/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config'>Especificación con OpenAPI</a>
    <br>
    <a href='/api/libros'>Libros</a>
    <br>
    <a href='/api/novedades'>Novedades</a>
    <br>
    <a href='/api/sugerencias'>Sugerencias</a>
    <br>
    <a href='/api/proveedores'>Proveedores</a>
    
</petclinic:layout>

<script>
function myFunction() {
    if("${prestamoUrgente}"){
        alert("Tiene un préstamo pendiente de devolver en los próximos días, por favor acuda a la biblioteca.");
    }
    if("${pedidoUrgente}"){
        alert("Envío pendiente en los próximos días, permanezca atento para su recogida.");
    }
}
window.onload = myFunction;
</script>