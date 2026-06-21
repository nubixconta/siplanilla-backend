# Configuración de Autenticación - Siplanilla Backend

## Estructura Implementada

### Entidades
- **Usuario** (`modules/seguridad/entity/Usuario.java`) - Entidad existente adaptada con implementación de `UserDetails`
  - Campos: `idUsuario`, `usuUsername`, `usuPassword`, `usuEstado`, `usuCorreo`, `empleado`
  - Implementa `UserDetails` de Spring Security

### Repositorios
- **UsuarioRepository** (`modules/seguridad/repository/`) - Para acceder a la BD con métodos:
  - `findByUsuUsername(String usuUsername)`
  - `findByUsuCorreo(String usuCorreo)`
  - `existsByUsuUsername(String usuUsername)`
  - `existsByUsuCorreo(String usuCorreo)`

### Servicios
- **AuthService** (`modules/auth/service/`) - Lógica de autenticación
  - `login(LoginRequest)` - Autenticación de usuario
  - `register(RegisterRequest)` - Registro de nuevo usuario

- **UserDetailsServiceImpl** (`modules/seguridad/service/`) - Carga usuarios de BD
  - Implementa `UserDetailsService` de Spring Security

### DTOs
- **LoginRequest** - username, password
- **RegisterRequest** - username, email, password, **idEmpleado** (ID del empleado existente)
- **AuthResponse** - accessToken, tokenType, expiresIn, user

### Seguridad
- **JwtProvider** (`security/jwt/`) - Generación y validación de tokens JWT
  - Algoritmo: HS512
  - Expiración: 24 horas (configurables)

- **JwtAuthenticationFilter** (`security/jwt/`) - Filtro para validar token en cada request

- **SecurityConfig** (`config/`) - Configuración de Spring Security
  - Endpoints públicos: `/api/v1/auth/**`, `/swagger-ui/**`, `/v3/api-docs/**`
  - Protección STATELESS con JWT
  - CORS habilitado para `http://localhost:4200`

### Controlador
- **AuthController** (`modules/auth/controller/`)
  - `POST /api/v1/auth/login`
  - `POST /api/v1/auth/register`

---

## Archivos a Limpiar

Los siguientes archivos fueron creados erróneamente y deben ser eliminados:
```
src/main/java/sv/edu/ues/fia/siplanilla_backend/modules/auth/entity/Usuario.java (DUPLICADO - NO USAR)
src/main/java/sv/edu/ues/fia/siplanilla_backend/modules/auth/repository/UsuarioRepository.java (DUPLICADO - NO USAR)
src/main/resources/db/migration/001_create_usuario_table.sql (La tabla ya existe)
AUTH_API_DOCUMENTATION.md (Reemplazado por este archivo)
```

---

## Cómo Usar

### 1. Compilar el proyecto
```bash
cd c:\Users\PC\IdeaProjects\siplanilla-backend
./mvnw clean install
```

### 2. Ejecutar el proyecto
```bash
./mvnw spring-boot:run
```

---

## 🧪 Cómo Probar el Login

### Opción 1: Usar Swagger UI (OpenAPI - La forma más fácil)

1. Abre tu navegador: `http://localhost:8080/api/swagger-ui.html`
2. Busca la sección `auth-controller`
3. Expande `POST /api/v1/auth/login`
4. Haz clic en "Try it out"
5. Ingresa en el body:
   ```json
   {
     "username": "admin",
     "password": "123456"
   }
   ```
   ⚠️ **Reemplaza con un usuario que exista en tu BD**
6. Haz clic en "Execute"
7. Verás la respuesta con el token JWT

---

### Opción 2: Usar Postman

**Descarga Postman:** https://www.postman.com/downloads/

**Pasos:**

1. **Nueva Request**
   - Método: `POST`
   - URL: `http://localhost:8080/api/v1/auth/login`

2. **Tab "Headers":**
   ```
   Content-Type: application/json
   ```

3. **Tab "Body" → raw → JSON:**
   ```json
   {
     "username": "admin",
     "password": "123456"
   }
   ```

4. **Haz clic en "Send"**

5. **Respuesta exitosa:**
   ```json
   {
     "success": true,
     "message": "Login exitoso",
     "data": {
       "access_token": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9...",
       "token_type": "Bearer",
       "expires_in": 86400000,
       "user": {
         "id": 1,
         "username": "admin",
         "email": "admin@example.com"
       }
     }
   }
   ```

**Para probar endpoints protegidos con el token:**

1. Copia el `access_token` de la respuesta anterior
2. En otra Request, ve a **Headers** y agrega:
   ```
   Authorization: Bearer <pega_el_token_aqui>
   ```
3. Ahora puedes acceder a endpoints protegidos

---

### Opción 3: Usar cURL (Línea de comandos)

**En PowerShell:**

```powershell
# 1. Login
$response = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/auth/login" `
  -Method Post `
  -ContentType "application/json" `
  -Body (ConvertTo-Json @{
    username = "admin"
    password = "123456"
  })

# 2. Ver respuesta
$response | ConvertTo-Json

# 3. Guardar el token
$token = $response.data.access_token
Write-Host "Token: $token"

# 4. Usar el token en otro endpoint
Invoke-RestMethod -Uri "http://localhost:8080/api/v1/usuario/perfil" `
  -Method Get `
  -Headers @{
    Authorization = "Bearer $token"
  }
```

**En Command Prompt (cmd):**

```batch
curl -X POST http://localhost:8080/api/v1/auth/login ^
  -H "Content-Type: application/json" ^
  -d "{\"username\":\"admin\",\"password\":\"123456\"}"
```

**En Linux/Mac:**

```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"123456"}'
```

---

### Opción 4: Usar VS Code - REST Client Extension

**Instalación:**
1. Ctrl+Shift+X (Extensions)
2. Busca "REST Client" (por Huachao Mao)
3. Instala

**Crea archivo `auth.http`:**

```http
### 1. LOGIN
POST http://localhost:8080/api/v1/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "123456"
}

### 2. REGISTER (crear nuevo usuario)
POST http://localhost:8080/api/v1/auth/register
Content-Type: application/json

{
  "username": "usuario_nuevo",
  "email": "usuario@example.com",
  "password": "password123",
  "idEmpleado": 1
}

### 3. USA EL TOKEN (reemplaza XXX con el token del login)
GET http://localhost:8080/api/v1/usuario/perfil
Authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9XXX...
```

**Luego:**
- Haz clic en "Send Request" sobre cada endpoint
- El token se guarda automáticamente

---

## ⚠️ Problemas Comunes y Soluciones

### Error: "Credenciales inválidas"
```
❌ El usuario o contraseña son incorrectos

✅ Solución:
- Verifica que el usuario existe en la BD:
  SELECT id_usuario, usu_username, usu_estado 
  FROM Usuario 
  WHERE usu_username = 'admin';
- Verifica que el usuario está activo (usu_estado = 1)
```

### Error: "Usuario no encontrado"
```
❌ El username no existe en BD

✅ Solución:
- Verifica el nombre de usuario exacto
- Consulta la BD para ver qué usuarios existen:
  SELECT usu_username FROM Usuario;
```

### Error: "El usuario ya existe" (al registrar)
```
❌ Ya existe un usuario con ese username

✅ Solución:
- Intenta con otro nombre de usuario
- O verifica si el usuario ya existe:
  SELECT id_usuario FROM Usuario WHERE usu_username = 'nuevo_usuario';
```

### Error: "El email ya está registrado"
```
❌ Ya existe un usuario con ese email

✅ Solución:
- Usa otro email
- O consulta la BD:
  SELECT id_usuario FROM Usuario WHERE usu_correo = 'email@example.com';
```

### Error: 401 Unauthorized en otros endpoints
```
❌ El token no está siendo enviado o es inválido

✅ Soluciones:
1. Verifica que incluyes el header:
   Authorization: Bearer <token>

2. Verifica que el token no expiró (24 horas)

3. Copia exactamente el access_token sin espacios extra

4. Verifica el formato:
   ❌ Incorrecto: "token": "Bearer eyJ..."
   ✅ Correcto: Authorization: Bearer eyJ...
```

### Servidor no responde
```
❌ El servidor Spring no está ejecutándose

✅ Solución:
- Abre una terminal y ejecuta:
  cd c:\Users\PC\IdeaProjects\siplanilla-backend
  .\mvnw spring-boot:run
  
- Espera a ver:
  "Siplanilla application started in X seconds"
```

---

## 📝 Endpoints Disponibles

| Método | Endpoint | Público | Descripción |
|--------|----------|---------|-------------|
| POST | `/api/v1/auth/login` | ✅ Sí | Inicia sesión, retorna token |
| POST | `/api/v1/auth/register` | ✅ Sí | Registra nuevo usuario |
| *GET/POST* | `/api/v1/*` | ❌ No | Otros endpoints (requieren token) |

---

## 🔑 Entender el Token JWT

**El token contiene:**
- Algoritmo: HS512
- Expiración: 24 horas
- Usuario: el username
- Firma digital para validación

**Ver contenido del token:**
1. Ve a https://jwt.io
2. Pega tu token en "Encoded"
3. Verás el contenido en "Decoded"

**Ejemplo de payload decodificado:**
```json
{
  "username": "admin",
  "iat": 1718656800,
  "exp": 1718743200
}
```

---

## 📊 Flujo de Autenticación

```
1. Cliente envía credenciales
   POST /api/v1/auth/login
   { "username": "admin", "password": "123456" }
   
2. Server valida credenciales en BD
   
3. Server genera token JWT
   
4. Client recibe token
   { "access_token": "eyJ...", "token_type": "Bearer", ... }
   
5. Client usa token en requests posteriores
   Authorization: Bearer eyJ...
   
6. Server valida token en cada request
   - Si es válido: procesa request
   - Si es inválido: retorna 401 Unauthorized
```

---

## ✅ Checklist de Prueba

- [ ] El servidor está ejecutándose (`http://localhost:8080/api/swagger-ui.html` abre)
- [ ] Puedo acceder a Swagger UI
- [ ] Puedo hacer login con un usuario válido
- [ ] Recibo un token JWT en la respuesta
- [ ] El token no está vacío
- [ ] Puedo usar el token en otros endpoints
- [ ] Sin token recibo error 401
- [ ] Puedo registrar un nuevo usuario
- [ ] Las contraseñas están encriptadas en la BD



## Configuraciones en application.properties

```properties
# JWT Configuration
jwt.secret=mySecretKeyForJwtTokenGenerationAndValidationPurposeOnly123456789SecretKeyMustBeAtLeast32CharactersLongForHS512
jwt.expiration=86400000
```

---

## Dependencias Agregadas al pom.xml

- `spring-boot-starter-security` - Spring Security
- `jjwt-api` - JWT Token Generation
- `jjwt-impl` - JWT Implementation
- `jjwt-jackson` - JWT Jackson support

---

## Notas Importantes

1. ✅ La tabla `USUARIO` ya existe en Oracle (no fue necesario crear)
2. ✅ La entidad Usuario existente fue adaptada para ser compatible con Spring Security
3. ✅ Los campos tienen nomenclatura específica de BD (usu_username, usu_password, etc.)
4. ✅ El token JWT se valida en cada request mediante el filtro `JwtAuthenticationFilter`
5. ✅ Las contraseñas se almacenan encriptadas con BCrypt
6. ⚠️ Cambiar `jwt.secret` en producción por una clave más segura
7. ⚠️ El CORS está configurado solo para `localhost:4200` (desarrollo)

---

## Próximos Pasos

1. ✅ Backend de autenticación completado
2. ⏭️ Conectar desde Angular
3. ⏭️ Implementar endpoints protegidos en otros módulos
4. ⏭️ Agregar roles y permisos si es necesario
