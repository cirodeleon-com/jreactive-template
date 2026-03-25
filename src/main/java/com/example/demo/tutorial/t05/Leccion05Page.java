package com.example.demo.tutorial.t05;

import com.ciro.jreactive.State;
import com.ciro.jreactive.annotations.Call;
import com.ciro.jreactive.router.Route;
import com.example.demo.AppPage;

@Route(path = "/tutorial/leccion05")
public class Leccion05Page extends AppPage {

    @State public int likes = 0;
    @State public boolean hasLiked = false;

    @Call
    public void darLikeBackendLento() {
        // Simulamos que la Base de Datos es lenta y tarda 1 segundo en responder
        try { Thread.sleep(1000); } catch (Exception e) {}

        hasLiked = !hasLiked;
        likes += hasLiked ? 1 : -1;
    }

    @Override
    protected String template() {
        return """
            <section style="max-width: 500px; padding: 20px; font-family: sans-serif;">
                <h1>⚡ Optimistic UI (Cero Lag)</h1>
                <p>El servidor tiene un <strong>retraso programado de 1 segundo</strong>. A pesar de eso, el botón reaccionará en 0ms gracias a la predicción en el cliente.</p>

                <div style="padding: 20px; border: 1px solid #ccc; border-radius: 8px; text-align: center;">
                    <h2>Me gusta: {{likes}}</h2>

                    <button 
                        @click="darLikeBackendLento()"
                        data-optimistic="
                            likes: hasLiked ? -1 : +1;
                            hasLiked: toggle
                        "
                        style="padding: 10px 20px; font-size: 16px; cursor: pointer;
                               background: {{#if hasLiked}}#e25555{{else}}#eee{{/if}};
                               color: {{#if hasLiked}}white{{else}}black{{/if}};">
                        {{#if hasLiked}}❤️ Te gusta{{else}}🤍 Dar Like{{/if}}
                    </button>
                </div>
            </section>
            
            <hr style="margin-top: 40px; border-top: 1px solid #eee;">
                <div style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 15px;">
                    <a data-router href="/tutorial/leccion04" style="color: #666; text-decoration: none; font-weight: 500;">
                        ⬅️ Anterior: Smart Lists
                    </a>
                    <a data-router href="/" style="color: #11998e; text-decoration: none; font-weight: bold;">
                        🏠 Índice
                    </a>
                    <a data-router href="/tutorial/leccion06" style="padding: 10px 20px; background: #111; color: white; text-decoration: none; border-radius: 8px; font-weight: bold;">
                        Siguiente: Modo Stateless ➡️
                    </a>
                </div>
            
        """;
    }
}