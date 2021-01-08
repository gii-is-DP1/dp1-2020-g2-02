-- BiblioNET --

INSERT INTO users(username,password,enabled) VALUES ('ferror1','Pass1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'ferror1','bibliotecario');
INSERT INTO users(username,password,enabled) VALUES ('lolati1','Pass1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'lolati1','bibliotecario');

INSERT INTO users(username,password,enabled) VALUES ('jorgon1','Pass1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'jorgon1','miembro');
INSERT INTO users(username,password,enabled) VALUES ('raulla1','Pass1234',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'raulla1','miembro');


INSERT INTO bibliotecarios(id,nombre,apellidos,dni,telefono,email,username) VALUES (0, 'Fernando', 'Romero Ruiz', '49387441P', 650606544, 'ferr@gmail.com', 'ferror1');
INSERT INTO bibliotecarios(id,nombre,apellidos,dni,telefono,email,username) VALUES (1, 'Lola', 'Atienza Ruiz', '49388441P', 650606604, 'lola@gmail.com', 'lolati1');

INSERT INTO miembros(id,nombre,apellidos,dni,telefono,email,username) VALUES (0, 'Jorge', 'González Pardo', '49387441P', 650606544, 'jorr@gmail.com', 'jorgon1');
INSERT INTO miembros(id,nombre,apellidos,dni,telefono,email,username) VALUES (1, 'Raúl', 'Llamas Costa', '49388442P', 650606604, 'rauw@gmail.com', 'raulla1');

INSERT INTO editoriales(nombre,direccion,telefono,email,web) VALUES ('Norma', 'calle Ejemplo, 13, Barcelona', 650606544, 'norma@mail.com', 'www.norma.com');
INSERT INTO editoriales(nombre,direccion,telefono,email,web) VALUES ('Planeta', 'calle Ejemplo, 14, Sevilla', 650606545, 'planeta@mail.com', 'www.planeta.com');

INSERT INTO autores(nombre,apellidos,fecha_nac) VALUES ('Emmanuel', 'Carriere', '1970-10-22');
INSERT INTO autores(nombre,apellidos,fecha_nac) VALUES ('George', 'R.R. Martin', '1981-02-11');

INSERT INTO libros(ISBN,titulo,idioma,fecha_publicacion, editorial_id) VALUES (1234567890, 'El adversario', 'Español', '2005-07-11', 1);
INSERT INTO libros(ISBN,titulo,idioma,fecha_publicacion, editorial_id) VALUES (0123456789, 'Juego de tronos', 'Español', '2004-10-11', 2);

INSERT INTO generos(libro_id,genero) VALUES (1, 'Biografía');
INSERT INTO generos(libro_id,genero) VALUES (2, 'Fantasía');
INSERT INTO generos(libro_id,genero) VALUES (1, 'Policiaco');

INSERT INTO es_autor(autor_id,libro_id) VALUES (1, 1);
INSERT INTO es_autor(autor_id,libro_id) VALUES (1, 2);
INSERT INTO es_autor(autor_id,libro_id) VALUES (2, 2);

INSERT INTO ejemplares(id,libro_id,estado,disponibilidad) VALUES (1,1,'Primera página arrancada.','DISPONIBLE');
INSERT INTO ejemplares(id,libro_id,estado,disponibilidad) VALUES (2,1,'Cubierta doblada.','RESERVADO');
INSERT INTO ejemplares(id,libro_id,estado,disponibilidad) VALUES (3,2,'En perfecto estado.','EN_PRESTAMO');

INSERT INTO prestamos(id,fecha_prestamo,fecha_devolucion,bibliotecario_id,miembro_id,ejemplar_id,finalizado) VALUES (0,'2020-11-15', '2020-12-01', 1, 1,1,TRUE);
INSERT INTO prestamos(id,fecha_prestamo,fecha_devolucion,bibliotecario_id,miembro_id,ejemplar_id,finalizado) VALUES (1,'2020-11-20', '2020-12-06', null, 0,2,FALSE);
INSERT INTO prestamos(id,fecha_prestamo,fecha_devolucion,bibliotecario_id,miembro_id,ejemplar_id,finalizado) VALUES (2,'2020-11-20', '2021-12-06', 0, 1,3,FALSE);

INSERT INTO novedades(id,titulo,contenido,fecha_publicacion,bibliotecario_id) VALUES (0,'Nuevos ejemplares', 'Hoy han llegado a la biblioteca 10 nuevos ejemplares de "La Celestina", ya están disponibles para su reserva.','2020-11-17',0);
INSERT INTO novedades(id,titulo,contenido,fecha_publicacion,bibliotecario_id) VALUES (1,'Biblioteca cerrada', 'Mañana día 19 la biblioteca permanecerá cerrada. Disculpen las molestias que esto pueda causar.','2020-11-18',1);

INSERT INTO proveedores(id,nombre,nif,direccion,telefono,email) VALUES (0, 'Francisco', '69060971K', 'C\Jerez Alta Nº6, Morón de la Frontera', 650606599, 'fran@gmail.com');
INSERT INTO proveedores(id,nombre,nif,direccion,telefono,email) VALUES (1, 'Eugenio', '36614739G', 'C\Giralda Nº12, Morón de la Frontera', 650606333, 'eu1@gmail.com');
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
