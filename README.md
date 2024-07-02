# ARSW-Parcial1

#### Servidor SpringBoot consultando API externa

Este proyecto crea un servidor APIRest que consulta un API externa y el cual es consultado por un cliente javascript index.html por medio de un metodo get, a través de un formulario, y un cliente concurrente que 
genera threads los cuales hacen sus propias peticiones

### Arquitectura

El proyecto sigue una arquitectura básica de aplicaciones web utilizando Java, Spring Framework y Thymeleaf:

- **Controlador**: `ApiController.java`
  - Este controlador gestiona las peticiones HTTP a la API externa de Alpha Vantage.
  - Define un método `getMethod(String name)` que maneja las solicitudes GET en la ruta `/api/get/{name}`.
  - Utiliza anotaciones de Spring (`@RestController` y `@GetMapping`) para mapear la URL y los parámetros de la solicitud HTTP a los métodos de Java.
  - Utiliza la anotación `@PathVariable` para recibir el parametro del nombre de la empresa.
  - Procesa el parámetro `name` el cual es el nombre de la empresa.

- **Cliente JS**: `index.html`
  - Esta plantilla HTML utiliza scripts para poder enviar la petición get por medio de un form para obtener los resultados de la api.

- **Despliegue en AWS EC2**:
  - La aplicación está desplegada en un servidor EC2 de AWS.
  - Se accede a la aplicación a través de la IP pública o el nombre de dominio asignado al servidor EC2.
    
![image](https://github.com/Knight072/ARSW-Lab3/assets/116401447/62a85e07-c27f-4f5f-b0fd-0c7469059a03)


### Uso

1. **Configuración**:
   - Clona el repositorio.
   - Importa el proyecto en tu IDE.

2. **Servidor SpringBoot**
   - Después de correr la aplicación Main se puede consultar la API externa al usar la URL `http://localhost:8080/api/get/{name}` donde name va a ser la empresa que se quiere consultar.

3. **Cliente JS**
   - Al usar la URL `http://localhost:8080` se carga la pagina html que tiene el form para poder consultar el API. En el campo del get ponemos la empresa que deseamos consultar y nos mostrara los datos consultados.

4. **Cliente Concurrente**
   - Al correr la clase ConcurrentServer nos va mostrar por consola los datos consultados y el codigo de respuesta que nos devuelve.
