<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="Libros">
    <h2>Libros</h2>
    
 <form action="/libros" method="get">
    <div class="row" style="margin-bottom:1%">
        <label class="col-sm-2 control-label">T�tulo</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="q" placeholder= "Buscar por t�tulo" value="${param.q}"/>
        </div>
        
    </div>
    <div class="row" style="margin-bottom:1%">
        <label class="col-sm-2 control-label">Autor</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="qAutor" placeholder= "Buscar por autor" value="${param.qAutor}"/>
        </div>
    </div>
    <div class="row" style="margin-bottom:1%">
        <label class="col-sm-2 control-label">Editorial</label>
        <div class="col-sm-10">
            <input class="form-control" type="text" name="qEditorial" placeholder= "Buscar por editorial" value="${param.qEditorial}"/>
        </div>
    </div> 
    <div class="row" style="margin-bottom:1%">
        <div class="col-sm-2">
            <input class="btn btn-default" type="submit"  value="BUSCAR">
        </div>
    </div>
    </form>
    
        <table id="LibrosTable" class="table table-striped">
        <thead>
        <tr>
            <th >ISBN</th>
            <th >T�tulo</th>
            <th >Autor</th>
            <th >Idioma</th>   
            <th>G�neros</th>
            <th>Editorial</th>
            <th>Fecha de publicaci�n</th>
            <th>Puntuaci�n</th>
            <th>Disponibilidad</th>      
        </tr>
        </thead>
        <tbody>
       
        
        <c:forEach items="${libros}" var="libro">
         	<tr> 
                <td>                    
                    <c:out value="${libro.ISBN}"/>
                </td>
                <td>
                    <c:out value="${libro.titulo}"/>
                </td>
                
                
                <td>
                	<c:forEach items="${libro.autores}" var="autorLibro">
                		<spring:url value="/autores/{autorId}" var="autorUrl">
                        	<spring:param name="autorId" value="${autorLibro.id}"/>
                    	</spring:url>
                    	<a href ="${fn:escapeXml(autorUrl)}"><c:out value="${autorLibro.nombre} ${autorLibro.apellidos}"/></a>
                    	<br/>
                	</c:forEach>
                </td>  
                <td>
                    <c:out value="${libro.idioma}"/>
                </td>               
                <td>
                	<c:forEach items="${libro.generos}" var="generoLibro">
                    	<c:out value="${generoLibro.nombreGenero}"/><br/>
        			</c:forEach>
                </td>   
                <td>
                    <spring:url value="/editoriales/{editorialId}" var="autorUrl">
                        <spring:param name="editorialId" value="${libro.editorial.id}"/>
                    </spring:url>
                    <a href ="${fn:escapeXml(autorUrl)}"><c:out value="${libro.editorial.nombre}"/></a>
                </td> 
                <td>
                    <c:out value="${libro.fecha_publicacion}"/>
                </td>
                
                <td>
                     <c:forEach items="${puntuaciones}" var="entry">
                     	<c:if test="${entry.key.id == libro.id }">
                     		<fmt:formatNumber type="number" maxFractionDigits="2" value="${entry.value}" />
                     	</c:if>
         			</c:forEach>
                 </td>
                 
                <td>
               		<c:if test="${disponibilidad[libro.id]}">
               			<sec:authorize access="!hasAuthority('miembro')">
               				Disponible
               			</sec:authorize>
               			<sec:authorize access="hasAuthority('miembro')">
               				<spring:url value="/libros/reservar/{libroId}" var="libroUrl">
                        		<spring:param name="libroId" value="${libro.id}"/>
                    		</spring:url>
                    		<a href ="${fn:escapeXml(libroUrl)}">Reservar</a>
               			</sec:authorize>
               		</c:if>
               		<c:if test="${!disponibilidad[libro.id]}">
               			No disponible

               		</c:if>

                </td>
        	</tr>
        </c:forEach>
        
        </tbody>
    </table>


    <br/> 
	
    <sec:authorize access="hasAuthority('admin') || hasAuthority('bibliotecario')">
    	<a class="btn btn-default" href='<spring:url value="/generos" htmlEscape="true"/>'>Consultar g�neros disponibles</a>
		<br/> 
		<br/> 
		<a class="btn btn-default" href='<spring:url value="/libros/new" htmlEscape="true"/>'>A�adir libro</a>
		
	</sec:authorize>
</petclinic:layout>