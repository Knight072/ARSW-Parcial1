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

### Uso

1. **Configuración**:
   - Clona el repositorio.
   - Importa el proyecto en tu IDE.

2. **Servidor SpringBoot**:
   - Después de ejecutar la aplicación principal, puedes consultar la API externa utilizando la URL `http://localhost:8080/api/get/{name}`, donde `{name}` es el nombre de la empresa que deseas consultar.

3. **Cliente JavaScript**:
   - Accede a la URL `http://localhost:8080`, que carga la página HTML con un formulario para consultar la API. Introduce el nombre de la empresa deseada en el campo del formulario y se mostrarán los datos consultados.

4. **Cliente Concurrente**:
   - Al ejecutar la clase `ConcurrentServer`, se mostrarán por consola los datos consultados y el código de respuesta recibido de cada hilo que realiza su propia petición.


