package com.example.demo.tutorial.t09;

import com.ciro.jreactive.State;
import com.ciro.jreactive.annotations.Call;
import com.ciro.jreactive.router.Route;
import com.example.demo.AppPage;

@Route(path = "/tutorial/leccion09")
public class Leccion09Page extends AppPage {

    @State
    public String tituloDinamico = "Tarjeta Reutilizable";

    @State
    public int contador = 0;

    @Call
    public void cambiarTitulo() {
        contador++;
        this.tituloDinamico = "Tarjeta Reutilizable (" + contador + " clics)";
    }

    @Override
    protected String template() {
        return """
            <section style="max-width: 600px; padding: 20px; font-family: sans-serif; margin: 0 auto;">
                <h1>🧩 Composición: Props y Slots</h1>
                <p style="color: #666; line-height: 1.5;">
                    En JReactive, puedes crear componentes reutilizables y componerlos como si fueran piezas de Lego. 
                    Usa <strong>:prop</strong> para pasar datos reactivos hacia abajo, y <strong>&lt;slot /&gt;</strong> para inyectar HTML dentro del hijo.
                </p>
                
                <div style="margin: 30px 0;">
                    
                    <JCard :title="tituloDinamico" subtitle="Ejemplo de Named Slots y Props">
                        
                        <div style="padding: 10px; background: #f8f9fa; border-left: 4px solid #11998e; border-radius: 4px;">
                            <p style="margin: 0;">
                                Este texto viaja desde <code>Leccion09Page</code> y aterriza directamente en el <code>&lt;slot /&gt;</code> principal del componente <code>JCard</code>.
                            </p>
                        </div>

                        <template slot="footer">
                            <button @click="cambiarTitulo()" style="padding: 8px 15px; background: #111; color: white; border: none; border-radius: 6px; cursor: pointer; transition: transform 0.1s;">
                                Mutar Prop desde el Padre 🔄
                            </button>
                        </template>
                        
                    </JCard>

                </div>

                <hr style="margin-top: 40px; border-top: 1px solid #eee;">
                <div style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 15px;">
                    <a data-router href="/tutorial/leccion08/basicos" style="color: #666; text-decoration: none; font-weight: 500;">
                        ⬅️ Anterior: Rutas y URL
                    </a>
                    
                    <a data-router href="/" style="padding: 12px 24px; background: linear-gradient(90deg, #11998e, #38ef7d); color: white; text-decoration: none; border-radius: 8px; font-weight: bold; transition: transform 0.2s; box-shadow: 0 4px 15px rgba(17,153,142,0.3);">
                        🎉 ¡Terminar Tutorial! 🏠
                    </a>
                </div>
            </section>
        """;
    }
}