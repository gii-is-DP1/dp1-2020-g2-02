<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Libros">
    <h2>Libros</h2>
        <table id="LibrosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 18%;">ISBN</th>
            <th style="width: 18%;">Título</th>
            <th style="width: 18%;">Idioma</th>   
            <th style="width: 5%;">Fecha de publicación</th>              
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
                    <c:out value="${libro.idioma}"/>
                </td>  
                <td>
                    <c:out value="${libro.fecha_publicacion}"/>
                </td> 
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout>