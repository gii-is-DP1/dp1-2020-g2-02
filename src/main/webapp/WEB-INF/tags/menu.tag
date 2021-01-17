<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>

<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand"
				href="<spring:url value="/" htmlEscape="true" />"><span></span></a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#main-navbar">
				<span class="sr-only"><os-p>Toggle navigation</os-p></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">

				<petclinic:menuItem active="${name eq 'home'}" url="/"
					title="Página inicial">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
					<span>Inicio</span>
				</petclinic:menuItem>
				
				<petclinic:menuItem active="${name eq 'Libros'}" url="/libros"
					title="Catálogo de libros">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Libros</span>
				</petclinic:menuItem>
				
				<petclinic:menuItem active="${name eq 'novedades'}" url="/novedades"
					title="Novedades">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Novedades</span>
				</petclinic:menuItem>
				
				<petclinic:menuItem active="${name eq 'autores'}" url="/autores"
					title="Autores">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Autores</span>
				</petclinic:menuItem>

				<petclinic:menuItem active="${name eq 'Editoriales'}" url="/editoriales"
					title="editoriales">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Editoriales</span>
				</petclinic:menuItem>

			</ul>




			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value="/login" />">Login</a></li>
					<li><a href="<c:url value="/users/new" />">Register</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span> 
							<strong><sec:authentication property="name" /></strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-lg-4">
											<p class="text-center">
												<span class="glyphicon glyphicon-user icon-size"></span>
											</p>
										</div>
										<div class="col-lg-8">
											<p class="text-left">
												<strong><sec:authentication property="name" /></strong>
											</p>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-12">
											<p class="text-left" style="padding:6%">
												<sec:authorize access="hasAuthority('admin')">
													<a href="<c:url value="/bibliotecarios" />"
													class="btn btn-primary btn-block btn-sm">Bibliotecarios</a>
												</sec:authorize>
												<sec:authorize access="hasAuthority('bibliotecario') || hasAuthority('admin')">
													<a href="<c:url value="/ejemplares" />"
													class="btn btn-primary btn-block btn-sm">Ejemplares</a>
													<a href="<c:url value="/miembros" />"
													class="btn btn-primary btn-block btn-sm">Miembros</a>
													<a href="<c:url value="/proveedores" />"
													class="btn btn-primary btn-block btn-sm">Proveedores</a>
												</sec:authorize>
												<sec:authorize access="hasAuthority('bibliotecario')">
													<a href="<c:url value="/prestamos" />"
													class="btn btn-primary btn-block btn-sm">Préstamos</a>
													<a href="<c:url value="/novedades/new" />"
													class="btn btn-primary btn-block btn-sm">Publicar novedad</a>
												</sec:authorize>
												<sec:authorize access="hasAuthority('miembro')">
													<a href="<c:url value="/" />"
													class="btn btn-primary btn-block btn-sm">Mis préstamos</a>
												</sec:authorize>
												<a href="<c:url value="/logout" />"
													class="btn btn-primary btn-block btn-sm">Cerrar sesión</a>
											
											</p>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
<!-- 							
                            <li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											<p>
												<a href="#" class="btn btn-primary btn-block">My Profile</a>
												<a href="#" class="btn btn-danger btn-block">Change
													Password</a>
											</p>
										</div>
									</div>
								</div>
							</li>
-->
						</ul></li>
				</sec:authorize>
			</ul>
		</div>



	</div>
</nav>
