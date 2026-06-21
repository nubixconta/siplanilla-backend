-- IMPORTANTE: Ahora el registro requiere un Empleado existente
-- Ejecuta este script ANTES de registrar usuarios

-- 1. VERIFICAR QUE EXISTAN EMPLEADOS
SELECT id_empleado, nombre, apellido, correo FROM Empleado LIMIT 5;

-- 2. SI NO EXISTEN EMPLEADOS, CREAR ALGUNOS DE PRUEBA
-- (Reemplaza los valores según tu estructura de Empleado)

INSERT INTO Empleado (nombre, apellido, correo, estado)
VALUES ('Juan', 'Pérez', 'juan@example.com', 1);

INSERT INTO Empleado (nombre, apellido, correo, estado)
VALUES ('María', 'García', 'maria@example.com', 1);

INSERT INTO Empleado (nombre, apellido, correo, estado)
VALUES ('Carlos', 'López', 'carlos@example.com', 1);

COMMIT;

-- 3. VER LOS EMPLEADOS CREADOS
SELECT id_empleado, nombre, apellido FROM Empleado;

-- 4. AHORA PUEDES USAR ESTOS ID_EMPLEADO PARA REGISTRAR USUARIOS
-- Por ejemplo, con idEmpleado = 1:
-- POST /api/v1/auth/register
-- {
--   "username": "usuario123",
--   "email": "usuario@example.com",
--   "password": "password123",
--   "idEmpleado": 1
-- }
