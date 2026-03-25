package com.example.demo.tutorial.t06;

import com.ciro.jreactive.State;
import com.ciro.jreactive.annotations.Call;
import com.ciro.jreactive.annotations.Stateless;
import com.ciro.jreactive.router.Route;
import com.example.demo.AppPage;

@Route(path = "/tutorial/leccion06")
@Stateless // 👈 ¡EL SECRETO DE LA ESCALABILIDAD!
public class Leccion06Page extends AppPage {

    // Este estado NO vive en la RAM del servidor (Caffeine/Redis).
    // Viaja encriptado y comprimido con LZ4 en el HTML del cliente.
    @State public int pasosDados = 0;
    @State public String ultimoLog = "Esperando acción...";

    @Call
    public void caminar() {
        pasosDados++;
        ultimoLog = "Diste el paso #" + pasosDados + ". El servidor despertó, calculó, y volvió a morir (0 RAM).";
    }

    @Override
    protected String template() {
        return """
            <section style="max-width: 500px; padding: 20px; font-family: sans-serif;">
                <h1>☁️ Arquitectura Stateless (0 RAM)</h1>
                <p>Al anotar la clase con <code>@Stateless</code>, este componente no consume memoria en el backend. 
                El estado se serializa, se firma criptográficamente y viaja en una meta-etiqueta oculta en tu navegador.</p>
                
                <p>Ideal para páginas de alto tráfico o arquitecturas Serverless/Lambdas.</p>

                <div style="background: #e3f2fd; padding: 15px; border-radius: 8px;">
                    <h3>Pasos: {{pasosDados}}</h3>
                    <p style="color: #666; font-size: 14px;">{{ultimoLog}}</p>
                    
                    <button @click="caminar()" style="padding: 10px; background: #0d6efd; color: white; border: none; border-radius: 4px;">
                        🚶‍♂️ Dar un paso
                    </button>
                </div>
            </section>
            
            <hr style="margin-top: 40px; border-top: 1px solid #eee;">
                <div style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 15px;">
                    <a data-router href="/tutorial/leccion05" style="color: #666; text-decoration: none; font-weight: 500;">
                        ⬅️ Anterior: Optimistic UI
                    </a>
                    <a data-router href="/" style="color: #11998e; text-decoration: none; font-weight: bold;">
                        🏠 Índice
                    </a>
                    <a data-router href="/tutorial/leccion07" style="padding: 10px 20px; background: #111; color: white; text-decoration: none; border-radius: 8px; font-weight: bold;">
                        Siguiente: Co-localización ➡️
                    </a>
                </div>
            
        """;
    }
}