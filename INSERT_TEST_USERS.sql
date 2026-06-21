-- Script SQL para crear usuario de prueba en Oracle
-- Ejecuta esto en SQL Developer o SQL*Plus

-- Usuario de prueba: admin / password123
-- La contraseña está encriptada con BCrypt
INSERT INTO Usuario (usu_username, usu_password, usu_correo, usu_estado)
VALUES (
    'admin',
    '$2a$10$slYQmyNdGzin7olVyeIS2OPST9/PgBkqquzi.Ss8jC8kK8iyUSA',  -- BCrypt de "password123"
    'admin@example.com',
    1
);

COMMIT;

-- Verificar que se insertó correctamente
SELECT id_usuario, usu_username, usu_correo, usu_estado FROM Usuario WHERE usu_username = 'admin';

-- Si necesitas más usuarios de prueba:

INSERT INTO Usuario (usu_username, usu_password, usu_correo, usu_estado)
VALUES (
    'usuario1',
    '$2a$10$slYQmyNdGzin7olVyeIS2OPST9/PgBkqquzi.Ss8jC8kK8iyUSA',  -- password123
    'usuario1@example.com',
    1
);

INSERT INTO Usuario (usu_username, usu_password, usu_correo, usu_estado)
VALUES (
    'usuario2',
    '$2a$10$slYQmyNdGzin7olVyeIS2OPST9/PgBkqquzi.Ss8jC8kK8iyUSA',  -- password123
    'usuario2@example.com',
    1
);

COMMIT;

-- Ver todos los usuarios
SELECT id_usuario, usu_username, usu_correo, usu_estado FROM Usuario;
