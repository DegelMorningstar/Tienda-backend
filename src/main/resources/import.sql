/* Populate tabla clientes */
INSERT INTO usuarios (nombre, apellidos, email,password,verificado, create_at,update_at) VALUES('Andrés', 'Guzmán', 'profesor@bolsadeideas.com','$2a$10$gXjv5HrDXbBH/RVcUPX1wegzeiNMwU0Kz48GKhFBvgOsQlTFH.zM.',true, '2018-01-01','2018-01-01');
INSERT INTO usuarios (nombre, apellidos, email,password,verificado, create_at,update_at) VALUES('Yael', 'Montes', 'yael_montes@outlook.com','$2a$10$gXjv5HrDXbBH/RVcUPX1wegzeiNMwU0Kz48GKhFBvgOsQlTFH.zM.',true, '2018-01-01','2018-01-01');
INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO usuarios_roles (usuario_id,roles_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id,roles_id) VALUES (2,2);
INSERT INTO categorias (nombre,descripcion,image_url,create_at,update_at) VALUES ('categoria prueba','es una categoria de prueba','','2018-01-01','2018-01-01');
INSERT INTO descuentos (nombre,descripcion,porcentaje,active,create_at,update_at) VALUES ('descuento prueba','',10.0,true,'2018-01-01','2018-01-01');
INSERT INTO productos (slug,portada,titulo,descripcion,sku,precio,stock,nventas,peso,estado,create_at,update_at,descuento_id,categoria_id) VALUES('slug','portada','titulo','descripcion','sku',100,10,1,10,true,'2018-01-01','2018-01-01',1,1);
INSERT INTO productos (slug,portada,titulo,descripcion,sku,precio,stock,nventas,peso,estado,create_at,update_at,descuento_id,categoria_id) VALUES('slug2','portada2','titulo2','descripcion2','sku2',100,10,1,10,true,'2018-01-01','2018-01-01',1,1);
INSERT INTO productos (slug,portada,titulo,descripcion,sku,precio,stock,nventas,peso,estado,create_at,update_at,descuento_id,categoria_id) VALUES('slug3','portada3','titulo3','descripcion3','sku3',100,10,1,10,true,'2018-01-01','2018-01-01',1,1);