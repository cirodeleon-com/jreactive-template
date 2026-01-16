\# JReactive ⚡



\*\*El Framework Full-Stack para Java Developers.\*\*

\*Cero JavaScript. 100% Java. Estado en el Servidor. Reactividad en Tiempo Real.\*



\[!\[](https://jitpack.io/v/cirodeleon-com/jreactive-parent.svg)](https://jitpack.io/#cirodeleon-com/jreactive-parent)



JReactive te permite construir aplicaciones web modernas y reactivas (SPAs) escribiendo \*\*solamente código Java\*\*. Olvídate de REST APIs, serialización JSON manual o frameworks de frontend complejos. El estado vive en el servidor y la interfaz se sincroniza automáticamente vía WebSockets mediante \*\*Deltas Inteligentes\*\*.



---



\## 🚀 Inicio Rápido



\### 1. Requisitos

\* Java 17+

\* Maven 3.6+


\### 2. Instalacion 

```shell
git clone https://github.com/cirodeleon-com/jreactive-template.git mi-proyecto
cd mi-proyecto
mvn spring-boot:run

```

\### 3. Tu Primera Página



Crea una clase Java anotada con `@Route` y `@Component`. JReactive se encarga del resto.



```java

@Component

@Route(path = "/")

public class CounterPage extends HtmlComponent {



   // 1. Estado Reactivo (Sugar Syntax)

   // Cualquier cambio aquí actualiza la UI automáticamente.

   @State public int count = 0;

   @State public String nombre = "Mundo";



   // 2. Lógica del Servidor (RPC)

   @Call

   public void increment() {

       count++;

   }



   // 3. La Vista (HTML)

   @Override

   protected String template() {

       return """

           <div class="card">

               <h1>Hola, {{nombre}}</h1>

               <p>Contador: <strong>{{count}}</strong></p>

               

               <input type="text" name="nombre" placeholder="Escribe tu nombre">

               

               <button @click="increment()">Sumar +1</button>

           </div>

       """;

   }

}



```



---



\## 📖 Guía de Funcionalidades



\### 1. Gestión del Estado (`@State`)



Olvídate de los getters/setters manuales. Anota tus campos con `@State`. El framework detecta los cambios (\*\*Dirty Checking\*\*) y envía solo el delta exacto al frontend.



```java

@State public int contador = 0;          

@State public User usuario = new User(); 

@State public List<String> items = new ArrayList<>();



```



\### 2. Enrutamiento SPA (`@Route`)



JReactive incluye un router integrado. Navega entre páginas sin recargar el navegador usando el atributo `data-router`.



```java

// Java: Define la ruta y captura parámetros

@Route(path = "/usuarios/{id}")

public class UserPage extends HtmlComponent {

   @Param("id") 

   @State public String userId;

}



```



```html

<a href="/usuarios/42" data-router>Ver Usuario 42</a>



```



\### 3. Binding Bidireccional



Vincula inputs HTML directamente a variables Java.



```java

@State public String email = "";



```



```html

<input type="text" name="email" placeholder="Tu correo">



```



\### 4. Listas Reactivas (Smart Lists)



El framework optimiza las colecciones. Si agregas un ítem a una lista, solo se envía ese ítem por la red, no se renderiza toda la lista de nuevo.



```java

@State public List<String> tareas = new ArrayList<>();



@Call

public void agregar(String nuevaTarea) {

   tareas.add(nuevaTarea); // ¡Solo viaja el delta!

}



```



```html

<ul>

   {{#each tareas as tarea}}

       <li>{{tarea}}</li>

   {{/each}}

</ul>



```



\### 5. Componentes Reutilizables



Puedes incrustar componentes dentro de otros (como un Reloj, un Footer, o una Tarjeta).



```java

// En tu HTML principal

<div class="dashboard">

   <ClockLeaf />

   <UserCard ref="userProfile" />

</div>



```



\### 6. Subida de Archivos (`JrxFile`)



Maneja subida de archivos fácilmente. Los recibes como objetos `JrxFile` (Base64) listos para usar.



```java

@Call

public void subir(JrxFile archivo) {

   System.out.println("Recibido: " + archivo.name() + " (" + archivo.size() + " bytes)");

   // archivo.base64() contiene los datos binarios codificados

}



```



```html

<input type="file" @change="subir(file)">



```



\### 7. Estado Global (`Store`)



Comparte datos entre sesiones o componentes usando un Store global estático y reactivo.



```java

public static class AppStore {

   public String theme = "light";

}



@State public AppStore store = new AppStore();



@Call
public void toggleTheme() {

   store.theme = "dark";

   updateState("store"); // Notifica a todos los componentes suscritos

}



```



\### 8. Validación (JSR-380)



Soporte nativo para `jakarta.validation`. Si la validación falla en el servidor, los errores se muestran automáticamente en el HTML junto al input correspondiente.



```java

public class Form {

   @NotBlank(message = "Nombre requerido")

   public String nombre;

}



@Call

public void guardar(@Valid Form form) {

   // Solo entra aquí si es válido.

   // Si no, JReactive muestra los errores en la UI automáticamente.

   repo.save(form);

}



```



---



\## 🛠️ Configuración Spring Boot



Si tu aplicación está en un paquete diferente al del framework (ej. `com.miempresa`), asegúrate de escanear los componentes de JReactive en tu clase principal:



```java

@SpringBootApplication(scanBasePackages = {"com.miempresa", "com.ciro.jreactive"})

public class Application {

   public static void main(String\[] args) {

       SpringApplication.run(Application.class, args);

   }

}



```



---



\## ⚡ Rendimiento



\* \*\*Deltas:\*\* Solo viajan los datos que cambian (JSON mínimo). Nunca se envía HTML completo tras la carga inicial.

\* \*\*Backpressure:\*\* El servidor controla el flujo de eventos para no saturar la red en conexiones lentas.

\* \*\*Atomic Updates:\*\* Las actualizaciones en el DOM son quirúrgicas, preservando el foco y la selección de texto.



---



\### 🤝 Contribuir



Este es un proyecto Open Source. ¡Siéntete libre de abrir Issues o Pull Requests!



\*Hecho con ❤️ y mucho Café.\*

