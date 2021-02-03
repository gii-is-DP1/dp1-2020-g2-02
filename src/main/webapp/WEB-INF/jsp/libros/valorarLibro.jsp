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
         <c:out value="Valorar libro: ${libro.titulo}"/>
     </h2>
     <form:form modelAttribute="puntuacion" class="form-horizontal" id="valorar-libro-form" action="/puntuacion/save">
         <div class="form-group">
         	<petclinic:inputField label="Puntuación" name="puntuacion" />
         </div>
         <div class="form-group">
             <div class="col-sm-offset-2 col-sm-10">

            		<input type="hidden" name="libro" value="${libro.id}"/>
            		<input type="hidden" name="id" value="${puntuacion.id}"/>
                	<button class="btn btn-default" type="submit">Valorar</button>
             </div>
         </div>

     </form:form>
     </jsp:body>
 </petclinic:layout>
