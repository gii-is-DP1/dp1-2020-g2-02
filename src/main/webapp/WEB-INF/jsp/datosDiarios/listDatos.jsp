<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<petclinic:layout pageName="estadisticas">
	
	<h2>Estadísticas</h2>
        <table id="DatosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 25%;">Día</th>
            <th style="width: 25%;">N. Encargos</th>
            <th style="width: 25%;">N. Préstamos</th>
            <th style="width: 25%;">N. Novedades</th> 
                         
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${datosDiarios}" var="datos">
            <tr>
            	<td>                    
                    <c:out value="${datos.fecha}"/>
                </td>
                <td>                    
                    <c:out value="${datos.encargos}"/>
                </td>
                <td>                    
                    <c:out value="${datos.prestamos}"/>
                </td>
                <td>                    
                    <c:out value="${datos.novedades}"/>
                </td>
            </tr>
         </c:forEach>
       </tbody>
     </table>
</petclinic:layout>