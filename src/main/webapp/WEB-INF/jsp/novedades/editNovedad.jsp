<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="novedades">
<jsp:body>
    <h2>
        Publicar novedad
    </h2>
    <form:form modelAttribute="novedad" class="form-horizontal" id="add-novedad-form" action="/novedades/save">
        <div class="form-group has-feedback">
            <petclinic:inputField label="T�tulo" name="titulo"/>
            <petclinic:areaField label="Contenido" name="contenido"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            
            <c:set var = "now" value = "<%= new java.util.Date()%>" />
               ${novedad.bibliotecario.nombre}
               <input type="hidden" name="fechaPublicacion" value="<fmt:formatDate pattern = "yyyy/MM/dd" value = "${now}" />"/>
               <button class="btn btn-default" type="submit">Guardar</button>
            </div>
        </div>
    </form:form>
    </jsp:body>
</petclinic:layout>