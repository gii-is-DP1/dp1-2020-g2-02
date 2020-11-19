<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Ejemplares">
    <h2>Ejemplares</h2>
        <table id="EjemplaresTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 18%;">Disponibilidad</th>
            <th style="width: 18%;">Estado</th>
            <th style="width: 18%;">ID</th>               
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ejemplares}" var="ejemplar">
            <tr>
                <td>                    
                    <c:out value="${ejemplar.disponibilidad}"/>
                </td>
                <td>
                    <c:out value="${ejemplar.estado}"/>
                </td>
                <td>
                    <c:out value="${ejemplar.libro_id}"/>
                </td>  
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>