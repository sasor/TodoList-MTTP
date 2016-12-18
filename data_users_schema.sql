-- Por order de creacion
INSERT INTO modules (module_name, module_description) VALUES ('users', 'CRUD de users');
INSERT INTO modules (module_name, module_description) VALUES ('profiles', 'CRUD de profiles');
INSERT INTO modules (module_name, module_description) VALUES ('modules', 'CRUD de modules');
INSERT INTO modules (module_name, module_description) VALUES ('licenses', 'CRUD de licenses');

-- Datos para los roles en mi DB la tabla se llama Licenses
-- Esta tabla tiene 3 columnas
-- module_id  representa a que module pertenece el rol
-- license_action el nombre de la accion que puede realizar
-- license_description una descripcion del rol
INSERT INTO licenses (module_id, license_action, license_description) VALUES (NULL, 'add', 'permite crear');
INSERT INTO licenses (module_id, license_action, license_description) VALUES (NULL, 'read', 'permite ver');
INSERT INTO licenses (module_id, license_action, license_description) VALUES (NULL, 'update', 'permite actualizar');
INSERT INTO licenses (module_id, license_action, license_description) VALUES (NULL, 'delete', 'permite eliminar');

-- Esta tabla tiene 2 columnas
-- profile_name 
-- profile_active tiene valor por defecto 1 lo q mean activo por defecto
INSERT INTO profiles (profile_name) VALUES ('administrador');
INSERT INTO profiles (profile_name) VALUES ('propietario');
INSERT INTO profiles (profile_name) VALUES ('hijo');
INSERT INTO profiles (profile_name) VALUES ('residente');

