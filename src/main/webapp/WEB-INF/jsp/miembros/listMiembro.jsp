<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="miembros">
    <h2>Miembros</h2>
        <table id="miembrosTable" class="table table-striped">
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
        <c:forEach items="${miembros}" var="miembro">
            <tr>
                <td>                    
                    <c:out value="${miembro.nombre}"/>
                </td>
                <td>
                    <c:out value="${miembro.apellidos}"/>
                </td>
                <td>
                    <c:out value="${miembro.dni}"/>
                </td>  
                <td>
                    <c:out value="${miembro.telefono}"/>
                </td> 
                <td>
                    <c:out value="${miembro.email}"/>
                </td>              
                <td>
                	<spring:url value="/miembros/delete/{miembroId}" var="miembroUrl">
                        <spring:param name="miembroId" value="${miembro.id}"/>
                    </spring:url>
                    <a href ="${fn:escapeXml(miembroUrl)}">Borrar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <sec:authorize access="hasAuthority('admin')">
        <a class="btn btn-default" href='<spring:url value="/miembros/new" htmlEscape="true"/>'>Añadir miembro</a>
    </sec:authorize>

</petclinic:layout>