# SiPlanilla SV — Backend

Stack: Spring Boot 3.5 · Java 21 · Oracle XE 21c · MapStruct · SpringDoc OpenAPI

## Requisitos

- Java 21+
- Maven 3.8+ (incluido via `mvnw.cmd`)
- Oracle XE con schema `PLANILLAS_DB` creado y script DDL ejecutado

## Configuración

1. Verificar que Oracle XE corra en `localhost:1521` (servicio `XEPDB1`)
2. Verificar credenciales en `src/main/resources/application.properties`
3. Ejecutar:

```bash
.\mvnw.cmd clean spring-boot:run
```

La app queda disponible en `http://localhost:8080/api`

## Estructura del proyecto

```
src/main/java/sv/edu/ues/fia/siplanilla_backend/
├── config/
│   ├── CorsConfig.java              # CORS para localhost:4200
│   ├── MapperConfig.java            # Configuración MapStruct
│   └── OpenApiConfig.java           # Swagger / OpenAPI
├── exception/
│   ├── BusinessException.java       # Error de negocio → 400
│   ├── GlobalExceptionHandler.java  # Manejador global de errores
│   └── ResourceNotFoundException.java # Recurso no encontrado → 404
├── modules/
│   ├── auth/          (pendiente — Plan General)
│   ├── bitacora/      (pendiente — Plan General)
│   ├── catalogo/
│   │   ├── controller/
│   │   │   └── TipoIngresoController.java
│   │   ├── dto/
│   │   │   ├── request/TipoIngresoRequest.java
│   │   │   └── response/TipoIngresoResponse.java
│   │   ├── entity/
│   │   │   ├── EstadoCivil.java
│   │   │   ├── ProfesionOficio.java
│   │   │   ├── RangoSalario.java
│   │   │   ├── Sexo.java
│   │   │   ├── TipoDescuento.java
│   │   │   └── TipoIngreso.java
│   │   ├── mapper/
│   │   │   └── TipoIngresoMapper.java
│   │   ├── repository/
│   │   │   └── TipoIngresoRepository.java
│   │   └── service/
│   │       ├── TipoIngresoService.java
│   │       └── TipoIngresoServiceImpl.java
│   ├── empleado/      (pendiente — Plan General)
│   ├── empresa/       (pendiente — Plan General)
│   ├── geografico/    (pendiente — Plan General)
│   ├── organizacion/  (pendiente — Plan General)
│   ├── planilla/      (pendiente — Plan General)
│   └── seguridad/     (pendiente — Plan General)
├── security/          (pendiente — Plan General)
├── util/
│   └── ApiResponse.java             # Wrapper genérico de respuesta
├── ServletInitializer.java
└── SiplanillaBackendApplication.java
```

## Patrón de módulo (para compañeros)

Cada módulo sigue este orden de implementación:

```
entity → repository → dto/request + dto/response → mapper → service (interfaz + impl) → controller
```

Ver `modules/catalogo/TipoIngreso*` como referencia completa — todos los archivos tienen el comentario `// PATRÓN DE REFERENCIA`.

### ApiResponse

Todos los endpoints retornan `ResponseEntity<ApiResponse<T>>`:

```json
{
  "success": true,
  "message": "Operación exitosa",
  "data": { ... },
  "timestamp": "2026-06-13T21:00:00"
}
```

Métodos disponibles: `ApiResponse.ok(data)`, `ApiResponse.ok(message, data)`, `ApiResponse.error(message)`

### Endpoints del patrón

```
GET    /api/catalogos/tipos-ingreso          Lista todos (o filtra con ?search=)
GET    /api/catalogos/tipos-ingreso/{id}     Busca por ID
POST   /api/catalogos/tipos-ingreso          Crea nuevo
PUT    /api/catalogos/tipos-ingreso/{id}     Actualiza
DELETE /api/catalogos/tipos-ingreso/{id}     Elimina
```

## Swagger UI

```
http://localhost:8080/api/swagger-ui.html
```

## Estado actual

- [x] Entidades JPA (27 tablas mapeadas)
- [x] CRUD TipoIngreso (patrón de referencia)
- [x] Configuración CORS (localhost:4200)
- [x] Manejo global de excepciones
- [x] Swagger: http://localhost:8080/api/swagger-ui.html
- [ ] Spring Security + JWT (Plan General)
- [ ] Módulos pendientes (Plan General)

## Módulos pendientes

```
[ ] auth         - Login, recuperar password, desbloqueo
[ ] empleado     - CRUD empleados y direcciones
[ ] organizacion - Unidades organizativas y centros de costo
[ ] planilla     - Generación y detalle de planilla
[ ] seguridad    - Usuarios, roles, permisos
[ ] geografico   - País, departamento, municipio
[ ] bitacora     - Auditoría de acciones
```
