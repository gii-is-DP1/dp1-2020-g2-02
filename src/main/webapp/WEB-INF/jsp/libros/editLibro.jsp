<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="libros">
<jsp:body>
    <h2>
        A�adir libro
    </h2>
    <form:form modelAttribute="libro" class="form-horizontal" id="add-libro-form" action="/libros/save">
        <div class="form-group has-feedback">
        	<petclinic:inputField label="ISBN" name="ISBN" />
            <petclinic:inputField label="T�tulo" name="titulo"/>
            <petclinic:inputField label="Idioma" name="idioma"/>
            <petclinic:selectFieldMap name="autores" label="Autores" names="${autores}" size="5"/>
            <petclinic:selectFieldMap name="generos" label="Generos" names="${generos}" size="5"/>
            <petclinic:selectFieldMap name="editorial" label="Editorial" names="${editoriales}" size="5"/>
            <petclinic:inputField label="Fecha publicaci�n" name="fecha_publicacion"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            
           		<input type="hidden" name="id" value="${libro.id}"/>
               	<button class="btn btn-default" type="submit">Guardar</button>
            </div>
        </div>
    </form:form>
    </jsp:body>
</petclinic:layout>