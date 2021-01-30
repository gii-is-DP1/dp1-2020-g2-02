<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<br>
<div class="row" style="margin-bottom:1%">
	<form action="/libros" method="get">
		<div class="col-sm-10">
			<input class="form-control" type="text" name="q" placeholder= "Busca un libro" value="${param.q}"/>
		</div>
		<div class="col-sm-2">
			<input class="btn btn-default" type="submit"  value="BUSCAR">
		</div>
	</form>
</div>
<br>
