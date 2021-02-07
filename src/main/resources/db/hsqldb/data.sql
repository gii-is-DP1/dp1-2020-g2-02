-- BiblioNET --

INSERT INTO users(username,password,enabled) VALUES ('ferror1','Pass1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'ferror1','bibliotecario');
INSERT INTO users(username,password,enabled) VALUES ('lolati1','Pass1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'lolati1','bibliotecario');

INSERT INTO users(username,password,enabled) VALUES ('jorgon1','Pass1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'jorgon1','miembro');
INSERT INTO users(username,password,enabled) VALUES ('raulla1','Pass1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'raulla1','miembro');
INSERT INTO users(username,password,enabled) VALUES ('ivasan1','Pass1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'ivasan1','miembro');
INSERT INTO users(username,password,enabled) VALUES ('alecai1','Pass1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9,'alecai1','miembro');


INSERT INTO bibliotecarios(id,nombre,apellidos,dni,telefono,email,username) VALUES (0, 'Fernando', 'Romero Ruiz', '49387441P', 650606544, 'ferr@gmail.com', 'ferror1');
INSERT INTO bibliotecarios(id,nombre,apellidos,dni,telefono,email,username) VALUES (1, 'Lola', 'Atienza Ruiz', '49388441P', 650606604, 'lola@gmail.com', 'lolati1');

INSERT INTO miembros(id,nombre,apellidos,dni,telefono,email,username) VALUES (0, 'Jorge', 'González Pardo', '49387441P', 650606544, 'jorr@gmail.com', 'jorgon1');
INSERT INTO miembros(id,nombre,apellidos,dni,telefono,email,username) VALUES (1, 'Raúl', 'Llamas Costa', '49388442P', 650606604, 'rauw@gmail.com', 'raulla1');
INSERT INTO miembros(id,nombre,apellidos,dni,telefono,email,username) VALUES (2, 'Iván', 'Sanabria García', '49388442P', 650177654, 'ivansg@gmail.com', 'ivasan1');
INSERT INTO miembros(id,nombre,apellidos,dni,telefono,email,username) VALUES (3, 'Alejandro', 'Sanabria Romero', '49388432P', 650177654, 'alexg@gmail.com', 'alecai1');

INSERT INTO sugerencias(id, titulo_libro, nombre_autor, miembro_id) VALUES(0, 'Abdel', 'Enrique Páez', 0);
INSERT INTO sugerencias(id, titulo_libro, nombre_autor, miembro_id) VALUES(1, 'El Camino de los Reyes', 'Brandon Sanderson', 0);
INSERT INTO sugerencias(id, titulo_libro, nombre_autor, miembro_id) VALUES(2, 'El Guardian Invisible', 'Dolores Redondo', 1);

INSERT INTO editoriales(nombre,nif,direccion,telefono,email,web) VALUES ('Norma', 'A1231123B', 'calle Ejemplo, 13, Barcelona', 650606544, 'norma@mail.com', 'www.norma.com');
INSERT INTO editoriales(nombre,nif,direccion,telefono,email,web) VALUES ('Planeta', 'B1234567C', 'calle Ejemplo, 14, Sevilla', 650606545, 'planeta@mail.com', 'www.planeta.com');

INSERT INTO autores(nombre,apellidos,fecha_nac) VALUES ('Emmanuel', 'Carrière', '1957-01-07');
INSERT INTO autores(nombre,apellidos,fecha_nac) VALUES ('George', 'R.R. Martin', '1948-09-20');
INSERT INTO autores(nombre,apellidos,fecha_nac) VALUES ('Stephen', 'King', '1947-09-21');
INSERT INTO autores(nombre,apellidos,fecha_nac) VALUES ('Fernando', 'Aramburu', '1959-04-17');

INSERT INTO libros(ISBN,titulo,idioma,fecha_publicacion, editorial_id) VALUES (1234567890, 'El adversario', 'Español', '2000-01-31', 1);
INSERT INTO libros(ISBN,titulo,idioma,fecha_publicacion, editorial_id) VALUES (0123456789, 'Juego de tronos', 'Español', '1996-08-06', 2);
INSERT INTO libros(ISBN,titulo,idioma,fecha_publicacion, editorial_id) VALUES (0123456433, 'El resplandor', 'Español', '1977-01-28', 2);
INSERT INTO libros(ISBN,titulo,idioma,fecha_publicacion, editorial_id) VALUES (1234543216, 'Patria', 'Español', '2016-09-06', 1);

INSERT INTO generos(id, nombre_genero) VALUES (1, 'Biografía');
INSERT INTO generos(id, nombre_genero) VALUES (2, 'Fantasía');
INSERT INTO generos(id, nombre_genero) VALUES (3, 'Policíaco');
INSERT INTO generos(id, nombre_genero) VALUES (4, 'Aventura');
INSERT INTO generos(id, nombre_genero) VALUES (5, 'Romántico');
INSERT INTO generos(id, nombre_genero) VALUES (6, 'Histórico');
INSERT INTO generos(id, nombre_genero) VALUES (7, 'Terror');


INSERT INTO es_autor(autor_id,libro_id) VALUES (1, 1);
INSERT INTO es_autor(autor_id,libro_id) VALUES (2, 2);
INSERT INTO es_autor(autor_id,libro_id) VALUES (3, 3);
INSERT INTO es_autor(autor_id,libro_id) VALUES (4, 4);

INSERT INTO pertenece_a(genero_id,libro_id) VALUES (1, 1);
INSERT INTO pertenece_a(genero_id,libro_id) VALUES (3, 1);
INSERT INTO pertenece_a(genero_id,libro_id) VALUES (2, 2);
INSERT INTO pertenece_a(genero_id,libro_id) VALUES (4, 2);
INSERT INTO pertenece_a(genero_id,libro_id) VALUES (6, 4);
INSERT INTO pertenece_a(genero_id,libro_id) VALUES (7, 3);


INSERT INTO puntuaciones(puntaje,miembro_id,libro_id) VALUES(5,2,2);
INSERT INTO puntuaciones(puntaje,miembro_id,libro_id) VALUES(2,3,1);
INSERT INTO puntuaciones(puntaje,miembro_id,libro_id) VALUES(5,3,2);
INSERT INTO puntuaciones(puntaje,miembro_id,libro_id) VALUES(3,1,1);

INSERT INTO ejemplares(id,libro_id,estado,disponibilidad) VALUES (1,1,'Primera página arrancada.','DISPONIBLE');
INSERT INTO ejemplares(id,libro_id,estado,disponibilidad) VALUES (2,1,'Cubierta doblada.','RESERVADO');
INSERT INTO ejemplares(id,libro_id,estado,disponibilidad) VALUES (3,2,'En perfecto estado.','EN_PRESTAMO');
INSERT INTO ejemplares(id,libro_id,estado,disponibilidad) VALUES (4,2,'Algunas páginas arrugadas.','EN_PRESTAMO');
INSERT INTO ejemplares(id,libro_id,estado,disponibilidad) VALUES (5,1,'Cubierta doblada.','EN_PRESTAMO');
INSERT INTO ejemplares(id,libro_id,estado,disponibilidad) VALUES (6,2,'En perfecto estado.','EN_PRESTAMO');
INSERT INTO ejemplares(id,libro_id,estado,disponibilidad) VALUES (7,3,'Algunas páginas arrugadas.','EN_PRESTAMO');
INSERT INTO ejemplares(id,libro_id,estado,disponibilidad) VALUES (8,4,'Algunas páginas arrugadas.','DISPONIBLE');
INSERT INTO ejemplares(id,libro_id,estado,disponibilidad) VALUES (9,1,'Segunda página arrugada.','DISPONIBLE');

INSERT INTO prestamos(id,fecha_prestamo,fecha_devolucion,bibliotecario_id,miembro_id,ejemplar_id,finalizado) VALUES (0,DATEADD(DAY, -30, CURRENT_TIMESTAMP()), DATEADD(DAY, -14, CURRENT_TIMESTAMP()), 1, 1,1,TRUE);
INSERT INTO prestamos(id,fecha_prestamo,fecha_devolucion,bibliotecario_id,miembro_id,ejemplar_id,finalizado) VALUES (1,CURRENT_TIMESTAMP(), DATEADD(DAY, 16, CURRENT_TIMESTAMP()), null, 0,2,FALSE);
INSERT INTO prestamos(id,fecha_prestamo,fecha_devolucion,bibliotecario_id,miembro_id,ejemplar_id,finalizado) VALUES (2,CURRENT_TIMESTAMP(), DATEADD(DAY, 16, CURRENT_TIMESTAMP()), 0, 1,3,FALSE);
INSERT INTO prestamos(id,fecha_prestamo,fecha_devolucion,bibliotecario_id,miembro_id,ejemplar_id,finalizado) VALUES (3,DATEADD(DAY, -20, CURRENT_TIMESTAMP()), DATEADD(DAY, -4, CURRENT_TIMESTAMP()), 0, 2,4,FALSE);
INSERT INTO prestamos(id,fecha_prestamo,fecha_devolucion,bibliotecario_id,miembro_id,ejemplar_id,finalizado) VALUES (4,DATEADD(DAY, -8, CURRENT_TIMESTAMP()), DATEADD(DAY, 8, CURRENT_TIMESTAMP()), 1, 3,5,FALSE);
INSERT INTO prestamos(id,fecha_prestamo,fecha_devolucion,bibliotecario_id,miembro_id,ejemplar_id,finalizado) VALUES (5,DATEADD(DAY, -8, CURRENT_TIMESTAMP()), DATEADD(DAY, 8, CURRENT_TIMESTAMP()), 0, 3,6,FALSE);
INSERT INTO prestamos(id,fecha_prestamo,fecha_devolucion,bibliotecario_id,miembro_id,ejemplar_id,finalizado) VALUES (6,DATEADD(DAY, -8, CURRENT_TIMESTAMP()), DATEADD(DAY, 8, CURRENT_TIMESTAMP()), 1, 3,7,FALSE);


INSERT INTO novedades(id,titulo,contenido,fecha_publicacion,bibliotecario_id) VALUES (0,'Nuevos ejemplares', 'Hoy han llegado a la biblioteca 10 nuevos ejemplares de "La Celestina", ya están disponibles para su reserva.',CURRENT_TIMESTAMP(),0);
INSERT INTO novedades(id,titulo,contenido,fecha_publicacion,bibliotecario_id) VALUES (1,'Biblioteca cerrada', 'Mañana día 19 la biblioteca permanecerá cerrada. Disculpen las molestias que esto pueda causar.','2020-11-18',1);

INSERT INTO proveedores(id,nombre,nif,direccion,telefono,email) VALUES (0, 'Libros Francisco', 'B9060971K', 'C\Jerez Alta Nº6, Morón de la Frontera', 650606599, 'fran@gmail.com');
INSERT INTO proveedores(id,nombre,nif,direccion,telefono,email) VALUES (1, 'Cuentos Eugenio', 'C6614739G', 'C\Giralda Nº12, Morón de la Frontera', 650606333, 'eu1@gmail.com');

INSERT INTO encargos(id, fecha_realizacion, fecha_entrega, proveedor_id) VALUES(0, DATEADD(DAY, -2, CURRENT_TIMESTAMP()), DATEADD(DAY, 8, CURRENT_TIMESTAMP()), 0);
INSERT INTO encargos(id, fecha_realizacion, fecha_entrega, proveedor_id) VALUES(1, DATEADD(DAY, -1, CURRENT_TIMESTAMP()), DATEADD(DAY, 5, CURRENT_TIMESTAMP()), 1);

INSERT INTO cantidad(id, unidades, precio_unitario, encargo_id, libro_id) VALUES(0, 5, 7, 0, 1);
INSERT INTO cantidad(id, unidades, precio_unitario, encargo_id, libro_id) VALUES(1, 3, 4, 1, 2);
INSERT INTO cantidad(id, unidades, precio_unitario, encargo_id, libro_id) VALUES(2, 7, 3, 0, 2);

INSERT INTO datos(id, encargos, fecha, novedades, prestamos) VALUES(1, 2, '2020-02-06', 3, 4);


-- Petclinic --

-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');

INSERT INTO vets VALUES (1, 'James', 'Carter');
INSERT INTO vets VALUES (2, 'Helen', 'Leary');
INSERT INTO vets VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');

INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);

INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');
