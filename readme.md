<div align="center">
  <h1>⚡ JReactive Starter Template</h1>
  <p><b>La forma más rápida de empezar a construir con JReactive.</b></p>
  
  [![Java 21+](https://img.shields.io/badge/Java-21%2B-blue.svg)](https://www.oracle.com/java/)
  [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2+-brightgreen.svg)](https://spring.io/projects/spring-boot)
  [![JReactive](https://img.shields.io/badge/JReactive-v0.9.7-orange.svg)](https://github.com/cirodeleon-com/jreactive-parent)
</div>

---

Bienvenido a la plantilla oficial de **JReactive**. Este proyecto está preconfigurado con todo lo necesario para que empieces a construir aplicaciones web modernas, reactivas (SPAs) y en tiempo real escribiendo **solamente código Java**. 

Olvídate de REST APIs, de serialización manual y de los pesados ecosistemas de JavaScript. El estado vive en el servidor y la interfaz se sincroniza de forma transparente.

## 🚀 Inicio Rápido

### 1. Clonar y Ejecutar
Asegúrate de tener Java 21+ y Maven instalados.

```shell
git clone https://github.com/cirodeleon-com/jreactive-template.git mi-proyecto-reactivo
cd mi-proyecto-reactivo
mvn spring-boot:run
```

Abre tu navegador en [http://localhost:8080](http://localhost:8080) y verás la aplicación de demostración corriendo instantáneamente.

---

## 📂 Estructura del Proyecto

Esta plantilla incluye un par de ejemplos para que veas la magia en acción. Siéntete libre de borrarlos cuando estés listo para escribir tu propio código.

```text
src/main/java/com/example/demo/
 ├── pages/
 │    └── CounterPage.java       <-- Demo de interactividad básica y manejo de estado.
 ├── tutorial/
 │    ├── t01/Leccion01Page.java <-- Data Binding unidireccional.
 │    ├── t02/Leccion02Page.java <-- Eventos (@Call) y mutación.
 │    ├── t03/Leccion03Page.java <-- Formularios y validación nativa JSR-380 (@Valid).
 │    ├── t04/Leccion04Page.java <-- Smart Lists y emisión de Deltas (WebSocket).
 │    ├── t05/Leccion05Page.java <-- Optimistic UI (0ms de lag percibido).
 │    ├── t06/Leccion06Page.java <-- Escalabilidad infinita con @Stateless.
 │    └── t07/Leccion07Page.java <-- Co-localización de CSS y JS (Scoped).
 ├── AppPage.java                <-- Clase base que asigna el Layout global.
 ├── DemoApplication.java        <-- Entrypoint de Spring Boot.
 ├── MainLayout.java             <-- El "cascarón" HTML (Navbar, Footer) de tu SPA.
 └── PageController.java         <-- Controlador transparente para gestionar enrutamiento HTTP.
```

---

## 🪄 ¿Cómo funciona JReactive? (En 30 segundos)

Olvida las configuraciones complejas. Crea una clase anotada con `@Route`, define tus variables con `@State` y expón tus acciones con `@Call`. 

```java
@Route(path = "/")
public class CounterPage extends AppPage {

   // 1. Estado Reactivo (Sugar Syntax)
   // Cualquier cambio aquí actualiza la UI automáticamente.
   @State public int count = 0;
   @State public String nombre = "Mundo";

   // 2. Lógica del Servidor (RPC)
   @Call
   public void increment() {
       count++; // JReactive calcula el Delta y actualiza el DOM
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

## 📖 Guía de Funcionalidades Clave

### 1. Gestión del Estado (`@State`)
Anota tus campos con `@State`. El framework detecta los cambios mediante su motor AOT (*Ahead-Of-Time compilation*) y envía solo el delta exacto al frontend en milisegundos. Sin reflexión pesada.

### 2. Enrutamiento SPA (`@Route`)
JReactive incluye un router integrado. Navega entre páginas sin recargar el navegador usando el atributo `data-router`.
```html
<a href="/tutorial/leccion01" data-router>Ir a la lección 1</a>
```

### 3. Listas Reactivas Inteligentes (Smart Lists)
El framework optimiza las colecciones. Si agregas un ítem a una `List`, `Set` o `Map`, solo se envía ese ítem por la red; el DOM jamás se repinta por completo.

### 4. Validación Integrada (JSR-380)
Soporte nativo para `jakarta.validation`. Aplica anotaciones como `@NotBlank` o `@Email` a tus DTOs, pásalos a un método `@Call` con `@Valid`, y JReactive se encarga de mostrar los errores debajo del input correspondiente en la vista.

### 5. Escalabilidad @Stateless
Anota un componente con `@Stateless` y JReactive serializará, comprimirá (con LZ4) y firmará criptográficamente el estado, enviándolo al cliente en una meta-etiqueta. El componente consumirá **0 bytes de RAM** en el servidor entre peticiones.

### 6. Co-localización (CSS y JS)
Si creas un archivo `.css` o `.js` con el mismo nombre que tu clase Java (ej. `Boton.java`, `Boton.css`, `Boton.js`), JReactive los inyectará y aislará (scope) automáticamente para ese componente.

---

## ⚡ Rendimiento y Arquitectura

* **SSR + CSR Híbrido:** Primera carga renderizada en el servidor (SEO friendly), interactividad posterior vía WebSockets o HTTP fallback (0ms lag percibido).
* **Deltas Minimizados:** Solo viajan los datos que cambian (JSON mínimo).
* **DOM Morphing:** Las actualizaciones en el HTML son quirúrgicas. JReactive jamás robará el foco del usuario mientras escribe, gracias a su motor basado en *Idiomorph*.
* **100% Extensible:** Al exponer el `PageController` en esta plantilla, mantienes el control absoluto para integrar Spring Security o interceptores tradicionales.

---

### 🤝 Contribuir
JReactive nació para devolverle la alegría de programar interfaces a los desarrolladores Backend. Este starter es solo el principio.

Si te gusta lo que ves, ¡no olvides darle una ⭐ en el repositorio oficial de JReactive!

*Hecho con ❤️ y mucho Café.*