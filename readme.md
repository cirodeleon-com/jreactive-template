Como modelo de lenguaje no puedo generar un archivo `.md` directo para descargar (como un binario), pero puedo darte el \*\*contenido crudo exacto\*\* en un bloque de texto que funciona igual.



Solo tienes que:



1\. Crear un archivo llamado \*\*`README.md`\*\* en la carpeta `jreactive-template`.

2\. Copiar el siguiente bloque completo.

3\. Pegarlo en ese archivo.



Aquí tienes el archivo completo:



```markdown

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



\### 2. Instalación

Agrega el repositorio y las dependencias en tu `pom.xml`.



```xml

<repositories>

&nbsp;   <repository>

&nbsp;       <id>jitpack.io</id>

&nbsp;       <url>\[https://jitpack.io](https://jitpack.io)</url>

&nbsp;   </repository>

</repositories>



<dependencies>

&nbsp;   <dependency>

&nbsp;       <groupId>com.github.cirodeleon-com.jreactive-parent</groupId>

&nbsp;       <artifactId>jreactive-starter-spring</artifactId>

&nbsp;       <version>v0.1.0</version>

&nbsp;   </dependency>

&nbsp;   <dependency>

&nbsp;       <groupId>com.github.cirodeleon-com.jreactive-parent</groupId>

&nbsp;       <artifactId>jreactive-apt</artifactId>

&nbsp;       <version>v0.1.0</version>

&nbsp;       <scope>provided</scope>

&nbsp;   </dependency>

</dependencies>



```



\### 3. Tu Primera Página



Crea una clase Java anotada con `@Route` y `@Component`. JReactive se encarga del resto.



```java

@Component

@Route(path = "/")

public class CounterPage extends HtmlComponent {



&nbsp;   // 1. Estado Reactivo (Sugar Syntax)

&nbsp;   // Cualquier cambio aquí actualiza la UI automáticamente.

&nbsp;   @State public int count = 0;

&nbsp;   @State public String nombre = "Mundo";



&nbsp;   // 2. Lógica del Servidor (RPC)

&nbsp;   @Call

&nbsp;   public void increment() {

&nbsp;       count++;

&nbsp;   }



&nbsp;   // 3. La Vista (HTML)

&nbsp;   @Override

&nbsp;   protected String template() {

&nbsp;       return """

&nbsp;           <div class="card">

&nbsp;               <h1>Hola, {{nombre}}</h1>

&nbsp;               <p>Contador: <strong>{{count}}</strong></p>

&nbsp;               

&nbsp;               <input type="text" name="nombre" placeholder="Escribe tu nombre">

&nbsp;               

&nbsp;               <button @click="increment()">Sumar +1</button>

&nbsp;           </div>

&nbsp;       """;

&nbsp;   }

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

&nbsp;   @Param("id") 

&nbsp;   @State public String userId;

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

&nbsp;   tareas.add(nuevaTarea); // ¡Solo viaja el delta!

}



```



```html

<ul>

&nbsp;   {{#each tareas as tarea}}

&nbsp;       <li>{{tarea}}</li>

&nbsp;   {{/each}}

</ul>



```



\### 5. Componentes Reutilizables



Puedes incrustar componentes dentro de otros (como un Reloj, un Footer, o una Tarjeta).



```java

// En tu HTML principal

<div class="dashboard">

&nbsp;   <ClockLeaf />

&nbsp;   <UserCard ref="userProfile" />

</div>



```



\### 6. Subida de Archivos (`JrxFile`)



Maneja subida de archivos fácilmente. Los recibes como objetos `JrxFile` (Base64) listos para usar.



```java

@Call

public void subir(JrxFile archivo) {

&nbsp;   System.out.println("Recibido: " + archivo.name() + " (" + archivo.size() + " bytes)");

&nbsp;   // archivo.base64() contiene los datos binarios codificados

}



```



```html

<input type="file" @change="subir(file)">



```



\### 7. Estado Global (`Store`)



Comparte datos entre sesiones o componentes usando un Store global estático y reactivo.



```java

public static class AppStore {

&nbsp;   public String theme = "light";

}



@State public AppStore store = new AppStore();



@Call

public void toggleTheme() {

&nbsp;   store.theme = "dark";

&nbsp;   updateState("store"); // Notifica a todos los componentes suscritos

}



```



\### 8. Validación (JSR-380)



Soporte nativo para `jakarta.validation`. Si la validación falla en el servidor, los errores se muestran automáticamente en el HTML junto al input correspondiente.



```java

public class Form {

&nbsp;   @NotBlank(message = "Nombre requerido")

&nbsp;   public String nombre;

}



@Call

public void guardar(@Valid Form form) {

&nbsp;   // Solo entra aquí si es válido.

&nbsp;   // Si no, JReactive muestra los errores en la UI automáticamente.

&nbsp;   repo.save(form);

}



```



---



\## 🛠️ Configuración Spring Boot



Si tu aplicación está en un paquete diferente al del framework (ej. `com.miempresa`), asegúrate de escanear los componentes de JReactive en tu clase principal:



```java

@SpringBootApplication(scanBasePackages = {"com.miempresa", "com.ciro.jreactive"})

public class Application {

&nbsp;   public static void main(String\[] args) {

&nbsp;       SpringApplication.run(Application.class, args);

&nbsp;   }

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



```



```

