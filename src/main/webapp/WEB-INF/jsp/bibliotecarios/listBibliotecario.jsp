<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="bibliotecarios">
    <h2>Bibliotecarios</h2>
        <table id="bibliotecariosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 18%;">Nombre</th>
            <th style="width: 18%;">Apellidos</th>
            <th style="width: 18%;">DNI</th>
            <th style="width: 18%;">Teléfono</th>
            <th style="width: 18%;">Email</th>   
            <th style="width: 5%;">Editar</th>              
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${bibliotecarios}" var="bibliotecario">
            <tr>
                <td>                    
                    <c:out value="${bibliotecario.nombre}"/>
                </td>
                <td>
                    <c:out value="${bibliotecario.apellidos}"/>
                </td>
                <td>
                    <c:out value="${bibliotecario.dni}"/>
                </td>  
                <td>
                    <c:out value="${bibliotecario.telefono}"/>
                </td> 
                <td>
                    <c:out value="${bibliotecario.email}"/>
                </td>
                <td>
                	<spring:url value="/bibliotecarios/delete/{bibliotecarioId}" var="bibliotecarioUrl">
                        <spring:param name="bibliotecarioId" value="${bibliotecario.id}"/>
                    </spring:url>
                    <a href ="${fn:escapeXml(bibliotecarioUrl)}">Borrar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>