<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="Ejemplares">
    <h2>Ejemplares</h2>
        <table id="EjemplaresTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 20%;">ID</th>
            <th style="width: 20%;">Título</th>
            <th style="width: 20%;">Estado</th>
            <th style="width: 20%;">Disponibilidad</th> 
            <th style="width: 20%;">Acciones</th> 
                         
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ejemplares}" var="ejemplar">
        	<c:if test="${ejemplar.disponibilidad != 'DESCATALOGADO' }">
            <tr>
            	<td>                    
                    <c:out value="${ejemplar.id}"/>
                </td>
                <td>                    
                    <c:out value="${ejemplar.libro.titulo}"/>
                </td>
                <td>
                    <c:out value="${ejemplar.estado}"/>
                </td>
                <td>
                	<c:if test="${ejemplar.disponibilidad == 'DISPONIBLE' }">
                    	Disponible
                    </c:if>
                    <c:if test="${ejemplar.disponibilidad == 'EN_PRESTAMO' }">
                    	En préstamo
                    </c:if>
                    <c:if test="${ejemplar.disponibilidad == 'RESERVADO' }">
                    	Pendiente de recoger
                    </c:if>
                </td>  
                <td>
                	<spring:url value="/ejemplares/descatalogar/{ejemplarId}" var="ejemplarUrl">
                        <spring:param name="ejemplarId" value="${ejemplar.id}"/>
                    </spring:url>
                    <a href ="${fn:escapeXml(ejemplarUrl)}">Descatalogar ejemplar</a>
                </td>
            </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
    <br/> 
     <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/ejemplares/new" htmlEscape="true"/>'>Añadir ejemplar</a>
	</sec:authorize>

</petclinic:layout>