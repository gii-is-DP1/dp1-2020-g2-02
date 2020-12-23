<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="editoriales">
    <h2> <c:out value="${editorial.nombre}"/></h2>
    
    
        <table id="editorialesTable" class="table table-striped">
        <thead>
        <tr>
            <th >ISBN</th>
            <th >Titulo</th>
            <th >Idioma</th>  
            <th >Géneros</th>         
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${libros}" var="libro">
            <tr>
                <td>                    
                    <c:out value="${libro.key.ISBN}"/>
                </td>
                <td>
                    <c:out value="${libro.key.titulo}"/>
                </td>
                    <td>
                    <c:out value="${libro.key.idioma}"/>
                </td>  
                <td> 
                <c:forEach items="${libro.value}" var="genero">
                		<c:out value="${genero.genero}"/> <br/>
                </c:forEach>  
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>