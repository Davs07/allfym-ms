## **🏗️ Arquitectura Hexagonal - Guía de Implementación**

### **📋 Estructura General de Capas**

```
src/main/java/com/grupo/allfym/ms/{microservicio}/
├── domain/           # CAPA DE DOMINIO (Núcleo del negocio)
├── application/      # CAPA DE APLICACIÓN (Casos de uso)
└── infrastructure/   # CAPA DE INFRAESTRUCTURA (Adaptadores)

```

---

## **🎯 DOMAIN LAYER (Capa de Dominio)**

**Es el corazón del microservicio, NO depende de frameworks externos**

### **📁 Estructura:**

```
domain/
├── models/
│   ├── entities/     # Entidades principales del negocio
│   ├── enums/        # Enumeraciones del dominio
│   ├── vo/           # Value Objects
│   └── [Modelos.java] # Modelos de otros microservicios
└── ports/
    ├── in/           # Interfaces de casos de uso (entrada)
    └── out/          # Interfaces de servicios externos (salida)

```

### **📝 Contenido de cada carpeta:**

**`models/entities/`** - Entidades del negocio

```java
// Ejemplo: Usuario.java, Producto.java, Pedido.java
public class Producto {
    private final Long id;
    private final String nombre;
    private final BigDecimal precio;

    // Constructor con validaciones de negocio
    // Solo getters (inmutable)
    // Métodos de negocio (cambiarPrecio, activar, etc.)
}

```

**`models/enums/`** - Estados y tipos del dominio

```java
// Ejemplo: Estado.java, TipoUsuario.java
public enum Estado {
    ACTIVO, INACTIVO, PENDIENTE, CANCELADO
}

```

**`models/vo/`** - Value Objects (objetos valor)

```java
// Ejemplo: Email.java, Telefono.java, Direccion.java
public class Email {
    private final String valor;

    public Email(String email) {
        // Validaciones del formato email
        this.valor = email;
    }
}

```

**`ports/in/`** - Casos de uso (lo que puede hacer el sistema)

```java
// Ejemplo: CrearUsuarioUseCase.java, BuscarProductoUseCase.java
public interface CrearUsuarioUseCase {
    Usuario crear(Usuario usuario);
}

```

**`ports/out/`** - Contratos para servicios externos

```java
// Ejemplo: UsuarioRepositoryPort.java, EmailServicePort.java
public interface UsuarioRepositoryPort {
    Usuario guardar(Usuario usuario);
    Optional<Usuario> buscarPorId(Long id);
}

```

---

## **⚙️ APPLICATION LAYER (Capa de Aplicación)**

**Orquesta los casos de uso del dominio**

### **📁 Estructura:**

```
application/
├── services/         # Servicios de aplicación (facades)
└── usecases/        # Implementaciones de casos de uso

```

### **📝 Contenido:**

**`services/`** - Servicios que agrupan casos de uso

```java
// Ejemplo: UsuarioService.java
@Component
public class UsuarioService {
    private final CrearUsuarioUseCase crearUsuarioUseCase;
    private final BuscarUsuarioUseCase buscarUsuarioUseCase;

    // Métodos que delegan a los casos de uso
}

```

**`usecases/`** - Implementaciones concretas de casos de uso

```java
// Ejemplo: CrearUsuarioUseCaseImpl.java
public class CrearUsuarioUseCaseImpl implements CrearUsuarioUseCase {
    private final UsuarioRepositoryPort repositoryPort;
    private final EmailServicePort emailServicePort;

    @Override
    public Usuario crear(Usuario usuario) {
        // Lógica del caso de uso
        // Validaciones de negocio
        // Coordinación entre puertos
    }
}

```

---

## **🔌 INFRASTRUCTURE LAYER (Capa de Infraestructura)**

**Implementa los adaptadores para frameworks y servicios externos**

### **📁 Estructura:**

```
infrastructure/
├── adapters/         # Adaptadores para servicios externos
├── clients/          # Clientes para otros microservicios (Feign)
├── config/          # Configuración de Spring/Framework
├── controllers/     # Controladores REST (adaptadores primarios)
├── dtos/           # DTOs para transferencia de datos
├── entities/       # Entidades JPA/BD
└── repositories/   # Repositorios JPA (adaptadores secundarios)

```

### **📝 Contenido de cada carpeta:**

**`controllers/`** - Endpoints REST

```java
// Ejemplo: UsuarioController.java
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDto> crear(@RequestBody UsuarioDto dto) {
        // Convierte DTO a entidad de dominio
        // Llama al servicio
        // Convierte respuesta a DTO
    }
}

```

**`entities/`** - Entidades JPA para BD

```java
// Ejemplo: UsuarioEntity.java
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mapeo JPA
    // Constructores
    // Getters/Setters
}

```

**`repositories/`** - Implementaciones de repositorio

```java
// Ejemplo: JpaUsuarioRepository.java (Spring Data)
public interface JpaUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    // Consultas automáticas y personalizadas
}

// Ejemplo: JpaUsuarioRepositoryAdapter.java
@Component
public class JpaUsuarioRepositoryAdapter implements UsuarioRepositoryPort {
    private final JpaUsuarioRepository jpaRepository;

    @Override
    public Usuario guardar(Usuario usuario) {
        // Convierte dominio a entidad JPA
        // Guarda en BD
        // Convierte respuesta a dominio
    }
}

```

**`dtos/`** - DTOs para API REST

```java
// Ejemplo: UsuarioDto.java, CreateUsuarioRequest.java
public class UsuarioDto {
    private Long id;
    private String nombre;
    private String email;

    // Getters/Setters
    // Validaciones de entrada (@Valid, @NotNull, etc.)
}

```

**`clients/`** - Clientes Feign para otros microservicios

```java
// Ejemplo: ProductoClient.java
@FeignClient(name = "ms-productos", url = "<http://localhost:8080/api/productos>")
public interface ProductoClient {
    @GetMapping("/{id}")
    ProductoDto buscarPorId(@PathVariable Long id);
}

```

**`adapters/`** - Adaptadores para servicios externos

```java
// Ejemplo: ProductoServiceAdapter.java
@Component
public class ProductoServiceAdapter implements ProductoServicePort {
    private final ProductoClient productoClient;

    @Override
    public Optional<Producto> buscarPorId(Long id) {
        // Llama al cliente Feign
        // Convierte DTO a modelo de dominio
        // Maneja errores
    }
}

```

**`config/`** - Configuración de Spring

```java
// Ejemplo: ApplicationConfig.java
@Configuration
public class ApplicationConfig {

    @Bean
    public UsuarioService usuarioService(UsuarioRepositoryPort repositoryPort) {
        return new UsuarioService(
            new CrearUsuarioUseCaseImpl(repositoryPort),
            new BuscarUsuarioUseCaseImpl(repositoryPort)
        );
    }

    @Bean
    public UsuarioRepositoryPort usuarioRepositoryPort(JpaUsuarioRepository jpaRepository) {
        return new JpaUsuarioRepositoryAdapter(jpaRepository);
    }
}

```

---

## **🔄 Flujo de Datos Típico**

```
1. Controller (Infrastructure)
   ↓ recibe DTO
2. Service (Application)
   ↓ convierte a modelo de dominio
3. UseCase (Application)
   ↓ ejecuta lógica de negocio
4. Repository/Service Port (Domain)
   ↓ interfaz
5. Repository/Service Adapter (Infrastructure)
   ↓ implementación
6. Base de datos / Microservicio externo

```

---

## **📋 Template de Carpetas para Cualquier Microservicio**

```
ms-{nombre}/
└── src/main/java/com/grupo/allfym/ms/{nombre}/
    ├── MsApplication.java                    # Clase principal Spring Boot
    ├── domain/
    │   ├── models/
    │   │   ├── entities/
    │   │   │   └── {Entidad}.java
    │   │   ├── enums/
    │   │   │   └── {Enum}.java
    │   │   ├── vo/
    │   │   │   └── {ValueObject}.java
    │   │   └── {ModeloExterno}.java
    │   └── ports/
    │       ├── in/
    │       │   └── {Accion}UseCase.java
    │       └── out/
    │           ├── {Entidad}RepositoryPort.java
    │           └── {Servicio}ServicePort.java
    ├── application/
    │   ├── services/
    │   │   └── {Entidad}Service.java
    │   └── usecases/
    │       └── {Accion}UseCaseImpl.java
    └── infrastructure/
        ├── adapters/
        │   └── {Servicio}ServiceAdapter.java
        ├── clients/
        │   └── {Microservicio}Client.java
        ├── config/
        │   └── ApplicationConfig.java
        ├── controllers/
        │   └── {Entidad}Controller.java
        ├── dtos/
        │   └── {Entidad}Dto.java
        ├── entities/
        │   └── {Entidad}Entity.java
        └── repositories/
            ├── Jpa{Entidad}Repository.java
            └── Jpa{Entidad}RepositoryAdapter.java

```

Esta estructura te garantiza:

- ✅ **Separación clara de responsabilidades**
- ✅ **Testabilidad independiente de cada capa**
- ✅ **Flexibilidad para cambiar implementaciones**
- ✅ **Escalabilidad y mantenibilidad**
- ✅ **Independencia del dominio de frameworks**