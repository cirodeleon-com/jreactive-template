<div align="center">
  <h1>⚡ JReactive Starter Template</h1>
  <p><b>La forma más rápida de empezar a construir con JReactive.</b></p>
  
  [![Java 21+](https://img.shields.io/badge/Java-21%2B-blue.svg)](https://www.oracle.com/java/)
  [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2+-brightgreen.svg)](https://spring.io/projects/spring-boot)
  [![JReactive](https://img.shields.io/badge/JReactive-v1.0.0-orange.svg)](https://github.com/cirodeleon-com/jreactive-parent)
</div>

---

Bienvenido a la plantilla oficial de **JReactive**. Este proyecto está preconfigurado con todo lo necesario para que empieces a construir aplicaciones web modernas, reactivas (SPAs) y en tiempo real escribiendo **solamente código Java**. 

Olvídate de REST APIs, de serialización manual y de los pesados ecosistemas de JavaScript. El estado vive en el servidor y la interfaz se sincroniza de forma transparente mediante deltas JSON ultraligeros.

## 🚀 Inicio Rápido

Asegúrate de tener Java 21+ y Maven instalados.

```shell
git clone [https://github.com/cirodeleon-com/jreactive-template.git](https://github.com/cirodeleon-com/jreactive-template.git) mi-proyecto-reactivo
cd mi-proyecto-reactivo
mvn spring-boot:run
```

Abre tu navegador en [http://localhost:8080](http://localhost:8080) y verás el *Dashboard* interactivo y el tutorial corriendo instantáneamente.

---

## 🎓 Tutorial de Inicio Rápido (9 Lecciones)

Esta plantilla incluye un curso intensivo inmersivo. Navega por las siguientes rutas en tu entorno local para entender el **100% de la arquitectura base en menos de 15 minutos**:

1. **`/tutorial/leccion01` - Estado y Binding:** Aprende a conectar variables de Java (`@State`) directamente al DOM en tiempo real.
2. **`/tutorial/leccion02` - Eventos (`@Call`):** Ejecuta métodos de backend desde botones en el frontend sin escribir llamadas AJAX.
3. **`/tutorial/leccion03` - Validación (JSR-380):** Usa anotaciones clásicas (`@NotBlank`, `@Email`) y deja que el framework inyecte los errores en la UI.
4. **`/tutorial/leccion04` - Smart Lists:** Maneja colecciones complejas. Si agregas un ítem, JReactive envía solo ese delta por la red, sin repintar toda la tabla.
5. **`/tutorial/leccion05` - Optimistic UI:** Haz que tus botones reaccionen en 0ms en el cliente mientras la petición viaja al servidor en segundo plano.
6. **`/tutorial/leccion06` - Arquitectura `@Stateless`:** Serializa y firma el estado para que viaje en la web. 0 bytes de RAM en el servidor entre peticiones.
7. **`/tutorial/leccion07` - Co-localización:** Escribe CSS y JS con el mismo nombre de tu clase Java y el framework los inyectará y aislará por ti.
8. **`/tutorial/leccion08` - Rutas y URL Params:** Lee parámetros del Path y Query Strings (`@UrlVariable`, `@UrlParam`) y sincroniza la URL al teclear.
9. **`/tutorial/leccion09` - Composición (Props y Slots):** Crea tu propio Design System con componentes reutilizables, inyectando datos de padres a hijos.

---

## 🪄 ¿Cómo funciona JReactive? (En 30 segundos)

Crea una clase anotada con `@Route`, define tus variables con `@State` y expón tus acciones con `@Call`. 

```java
@Route(path = "/")
public class CounterPage extends AppPage {

   // 1. Estado Reactivo (Lectura/Escritura en O(1) gracias al AOT)
   @State public int count = 0;
   @State public String nombre = "Mundo";

   // 2. Lógica del Servidor (RPC)
   @Call
   public void increment() {
       count++; // JReactive calcula el Delta y actualiza el DOM sin parpadeos
   }

   // 3. La Vista (HTML puro, sin JSX)
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

## ⚡ Rendimiento y Arquitectura

* **Compilador AOT:** Cero reflexión pesada en tiempo de ejecución. El framework genera código en la fase de compilación de Maven para accesos al estado en *O(1)*.
* **SSR + CSR Híbrido:** Primera carga renderizada en el servidor (SEO friendly), interactividad posterior vía WebSockets (o HTTP en modo Stateless).
* **DOM Morphing:** Las actualizaciones en el HTML son quirúrgicas. JReactive jamás robará el foco del usuario mientras escribe (Powered by Idiomorph).
* **Escudo Anti-Concurrencia:** Integración con Virtual Threads, Caffeine (L1) y Redis (L2) con *Optimistic Locking* para arquitecturas distribuidas.

---

### 🤝 Contribuir
JReactive nació para devolverle la alegría de programar interfaces a los desarrolladores Backend. Este starter es solo el principio.

Si te gusta lo que ves, ¡no olvides darle una ⭐ en el repositorio oficial de JReactive!

*Hecho con ❤️ y pura Verdad Funcional.*