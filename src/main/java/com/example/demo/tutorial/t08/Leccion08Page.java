package com.example.demo.tutorial.t08;

import com.ciro.jreactive.State;
import com.ciro.jreactive.annotations.Call;
import com.ciro.jreactive.router.Route;
import com.ciro.jreactive.router.UrlParam;
import com.ciro.jreactive.router.UrlVariable;
import com.example.demo.AppPage;


// 🔥 Fíjate en el {categoria} en el path. Lo exige el @UrlVariable.
// Para que la lección cargue directo desde el índice, el link en el Home debe apuntar a un valor por defecto, ej: /tutorial/leccion08/frameworks
@Route(path = "/tutorial/leccion08/{categoria}")
public class Leccion08Page extends AppPage {

    // 1. Captura el path: /tutorial/leccion08/frameworks -> "frameworks"
    @UrlVariable("categoria")
    @State
    public String categoria;

    // 2. Captura la query: ?q=jreactive -> "jreactive"
    // Si modificas esta variable, JReactive actualizará la URL del navegador automáticamente.
    @UrlParam("q")
    @State
    public String busqueda = "";

    @Call
    public void limpiarBusqueda() {
        this.busqueda = "";
    }
    
    @Call
    public void sync() {
        // No necesitamos código aquí. La magia ocurre por la anotación @UrlParam en la variable 'busqueda'.
    }

    @Override
    protected String template() {
        return """
            <section style="max-width: 600px; padding: 20px; font-family: sans-serif; margin: 0 auto;">
                <h1>🔗 Parámetros de URL y Rutas Dinámicas</h1>
                <p>En JReactive, no necesitas inyectar objetos complejos para leer la URL. Todo ocurre por inyección directa.</p>
                
                <div style="background: #f8f9fa; padding: 20px; border-radius: 12px; border: 1px solid #e0e0e0; margin-bottom: 25px;">
                    <h3>1. Variables de Ruta (@UrlVariable)</h3>
                    <p>La categoría actual capturada del path es: <strong style="color: #11998e; text-transform: uppercase;">{{categoria}}</strong></p>
                    
                    <div style="display: flex; gap: 10px; margin-top: 15px;">
                        <a data-router href="/tutorial/leccion08/backend" style="padding: 8px 15px; background: #eee; text-decoration: none; color: #333; border-radius: 6px;">/backend</a>
                        <a data-router href="/tutorial/leccion08/frontend" style="padding: 8px 15px; background: #eee; text-decoration: none; color: #333; border-radius: 6px;">/frontend</a>
                    </div>
                </div>

                <div style="background: #e3f2fd; padding: 20px; border-radius: 12px; border: 1px solid #90caf9;">
                    <h3>2. Query Params (@UrlParam)</h3>
                    <p>Escribe abajo. Verás que la variable se actualiza y <strong>la URL del navegador cambia al instante</strong> (?q=...).</p>
                    
                    <input type="text" 
                           name="busqueda" 
                           placeholder="Busca algo..." 
                           @input="sync()"
                           style="width: 100%; padding: 10px; margin-bottom: 10px; border-radius: 6px; border: 1px solid #ccc; box-sizing: border-box;" />
                           
                    <p>Buscando: <strong>{{busqueda}}</strong></p>
                    
                    <button @click="limpiarBusqueda()" style="padding: 8px 15px; background: #007bff; color: white; border: none; border-radius: 6px; cursor: pointer;">
                        Limpiar URL
                    </button>
                </div>

            </section>
            
            <hr style="margin-top: 40px; border-top: 1px solid #eee;">
                <div style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 15px;">
                    <a data-router href="/tutorial/leccion07" style="color: #666; text-decoration: none; font-weight: 500;">
                        ⬅️ Anterior: Co-localización
                    </a>
                    
                    <a data-router href="/" style="color: #11998e; text-decoration: none; font-weight: bold;">
                        🏠 Índice
                    </a>
                    
                    <a data-router href="/tutorial/leccion09" style="padding: 10px 20px; background: #111; color: white; text-decoration: none; border-radius: 8px; font-weight: bold;">
                        Siguiente: Props y Slots ➡️
                    </a>
                </div>
            
        """;
    }
}