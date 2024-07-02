# ARSW-Parcial1

## Servidor SpringBoot consultando API externa

Este proyecto crea un servidor APIRest que consulta un API externa y es consultado por un cliente JavaScript `index.html` a través de un método GET, utilizando un formulario, y un cliente concurrente que genera hilos los cuales realizan sus propias peticiones.

### Arquitectura

El proyecto sigue una arquitectura básica de aplicaciones web utilizando Java, Spring Framework y Thymeleaf:

- **Controlador**: `ApiController.java`
  - Este controlador gestiona las peticiones HTTP a la API externa de Alpha Vantage.
  - Define un método `getMethod(String name)` que maneja las solicitudes GET en la ruta `/api/get/{name}`.
  - Utiliza anotaciones de Spring (`@RestController` y `@GetMapping`) para mapear la URL y los parámetros de la solicitud HTTP a los métodos de Java.
  - Utiliza la anotación `@PathVariable` para recibir el parámetro del nombre de la empresa.
  - Procesa el parámetro `name`, que representa el nombre de la empresa.

- **Cliente JavaScript**: `index.html`
  - Esta plantilla HTML contiene scripts para enviar una solicitud GET mediante un formulario y obtener los resultados de la API.

- **Cliente Concurrente**: `Hilo.java`
  - Esta clase representa un hilo en Java que realiza una solicitud GET a la API externa de Alpha Vantage.
  - El hilo se instancia con un ID y el nombre de la empresa a consultar.
  - Utiliza `HttpURLConnection` para establecer la conexión y obtener la respuesta de la API.
  - Implementa el método `run()` que ejecuta la solicitud GET y maneja las respuestas y errores.

### Implementación de la extensibilidad

El proyecto está diseñado para ser extensible mediante los siguientes principios y prácticas:

1. **Configuración centralizada**: Utiliza constantes y propiedades externas para configurar la URL base de la API (`GET_URL`) y otros parámetros como el agente de usuario (`USER_AGENT`). Esto permite modificar fácilmente la configuración sin necesidad de cambiar el código fuente.

2. **Separación de responsabilidades**: El controlador (`ApiController`) está separado del cliente concurrente (`Hilo`). Cada uno maneja su lógica específica (gestión de peticiones HTTP y generación de hilos, respectivamente), lo cual facilita la modificación o expansión de cada componente sin afectar al otro.

3. **Interfaces claras y métodos reutilizables**: El método `executeGetRequest` en la clase `Hilo` encapsula la lógica para realizar solicitudes GET, lo que permite reutilizar esta funcionalidad en otros contextos dentro del proyecto.

### Uso

1. **Configuración**:
   - Clona el repositorio.
   - Importa el proyecto en tu IDE.

2. **Despliegue en AWS**:
   - El proyecto está desplegado en AWS y accesible en [http://ec2-3-81-145-22.compute-1.amazonaws.com:8080/](http://ec2-3-81-145-22.compute-1.amazonaws.com:8080/).
   - Puedes consultar la API externa utilizando la URL desplegada seguida de `/api/get/{name}`, donde `{name}` es el nombre de la empresa que deseas consultar.

3. **Cliente JavaScript**:
   - Accede a la URL desplegada en AWS, que carga la página HTML con un formulario para consultar la API. Introduce el nombre de la empresa deseada en el campo del formulario y se mostrarán los datos consultados.

4. **Cliente Concurrente**:
   - Al ejecutar la clase `ConcurrentServer`, se mostrarán por consola los datos consultados y el código de respuesta recibido de cada hilo que realiza su propia petición.


4. **Cliente Concurrente**:
   - Al ejecutar la clase `ConcurrentServer`, se mostrarán por consola los datos consultados y el código de respuesta recibido de cada hilo que realiza su propia petición.


