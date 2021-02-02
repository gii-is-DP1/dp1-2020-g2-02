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
    
    
</petclinic:layout>

<script>
function myFunction() {
    if("${prestamoUrgente}"){
        alert("Tiene un pr�stamo pendiente de devolver en los proximos d�as, por favor acuda a la biblioteca.");
    }
    if("${pedidoUrgente}"){
        alert("Env�o pendiente en los pr�ximos d�as, permanezca atento para su recogida.");
    }
}
window.onload = myFunction;
</script>