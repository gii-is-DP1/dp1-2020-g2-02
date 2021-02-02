<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
    <div class="row">

    <h2>Bienvenido</h2>
    <br/>
    
    <h2>Project ${title} </h2>
    <h2>Group ${group}</h2>
    <ul>
    <c:forEach items="${persons}" var="person">
        <li>${person.firstName} ${person.lastName}</li>
    </c:forEach>
    </ul>
    </div>
    
    
        <div class="col-md-12">
            <spring:url value="/resources/images/logoDP.png" htmlEscape="true" var="biblioImage"/>
            <img class="img-responsive" src="${biblioImage}" width="500" height="250"/>
        </div>
</petclinic:layout>

<script>
function myFunction() {
    if("${prestamoUrgente}"){
        alert("Tiene un préstamo pendiente de devolver en los proximos días, por favor acuda a la biblioteca.");
    }
    if("${pedidoUrgente}"){
        alert("Envío pendiente en los próximos días, permanezca atento para su recogida.");
    }
}
window.onload = myFunction;
</script>