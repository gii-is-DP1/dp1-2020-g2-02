<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="autores">
    <h2>Autores</h2>
        <table id="autoresTable" class="table table-striped">
        <thead>
        <tr>
            <th >Nombre</th>
            <th >Apellidos</th>
            <th >Fecha de nacimiento</th>  
            <th ></th>         
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${autores}" var="autor">
            <tr>
                <td>                    
                    <c:out value="${autor.nombre}"/>
                </td>
                <td>
                    <c:out value="${autor.apellidos}"/>
                </td>
                <td>
                    <c:out value="${autor.fecha_nac}"/>
                </td>  
                <td>
                	<spring:url value="/autores/{autorId}" var="autorUrl">
                        <spring:param name="autorId" value="${autor.id}"/>
                    </spring:url>
                    <a href ="${fn:escapeXml(autorUrl)}">Ver libros</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>