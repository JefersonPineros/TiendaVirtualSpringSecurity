insert into role (nombre_roll) values ('ROLE_USER');
insert into role (nombre_roll) values ('ROLE_ADMIN');
insert into ciudades (codigo_postal, nombre_ciudad) values ('Bogota', '111111');
insert into metodos_pago (tipo_pago) values ('CREDITO');
insert into datos_entrega (numero_contacto,direccion_contacto, id_ciudad) values ('3022224085', 'cll 143 a # 141 d 15' , 1);
insert into usuarios (nombre_usuario, apellido_usuario, email_usuario, password_usuario, fecha_creacion, fecha_logueo, imagen_usuario, state,id_entrega) values ('Jeferson', 'Pineros', 'jeferson91pineros@gmail.com', '$2a$10$C0MmnuPmWr.s3bM2Lp5WCOEtVbYkeN3zDp9srVHXD7AmLoZ4a2VqC', '2021-06-01', '2021-06-12', 'hola.jpg', true, 1);
insert into usuario_roles (usuario_id, role_id) values (1, 2);
insert into productos(codigo, descripcion, nombre, nombre_imagen, state, stock, valor) values ('asd4466', 'hola mundo', 'Cerveza', 'cerveza.jpg', 1, 30, 44974);
insert into categorias_productos(nombre_categoria) values ('CERVEZAS');
insert into estado_factura(nombre_estado) values ('ACTIVO');