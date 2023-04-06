/* Populate tabla clientes */
INSERT INTO usuarios (nombre, apellidos, email,password,verificado, create_at,update_at) VALUES('Andrés', 'Guzmán', 'profesor@bolsadeideas.com','$2a$10$gXjv5HrDXbBH/RVcUPX1wegzeiNMwU0Kz48GKhFBvgOsQlTFH.zM.',true, '2018-01-01','2018-01-01');
INSERT INTO usuarios (nombre, apellidos, email,password,verificado, create_at,update_at) VALUES('Yael', 'Montes', 'yael_montes@outlook.com','$2a$10$gXjv5HrDXbBH/RVcUPX1wegzeiNMwU0Kz48GKhFBvgOsQlTFH.zM.',true, '2018-01-01','2018-01-01');
INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO usuarios_roles (usuario_id,roles_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id,roles_id) VALUES (2,2);
