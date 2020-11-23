<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="libros">
	<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fecha_publicacion").datepicker({dateFormat: 'dd/mm/yy'});
            });
        </script>
    </jsp:attribute>
<jsp:body>
    <h2>
        <c:if test="${libro['new']}">Nuevo </c:if> Libro
    </h2>
    <form:form modelAttribute="libro" class="form-horizontal" id="add-libro-form" action="/libros/save">
        <input type="hidden" name="id" value="${libro.id}"/>
        <div class="form-group has-feedback">
            <petclinic:inputField label="ISBN" name="ISBN"/>
            <petclinic:inputField label="Título" name="titulo"/>
            <petclinic:inputField label="Idioma" name="idioma"/>
            <petclinic:inputField label="Fecha de publicación" name="fecha_publicacion"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
					<c:choose>
                        <c:when test="${libro['new']}">
                            <button class="btn btn-default" type="submit">Añadir libro</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Actualizar libro</button>
                        </c:otherwise>
                    </c:choose>
        </div>
    </form:form>
        <c:if test="${!pet['new']}">
        </c:if>
    </jsp:body>
</petclinic:layout>