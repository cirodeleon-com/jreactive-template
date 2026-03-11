package com.example.demo.tutorial.t07;

import com.ciro.jreactive.State;
import com.ciro.jreactive.router.Route;
import com.example.demo.AppPage;

@Route(path = "/tutorial/leccion07")
public class Leccion07Page extends AppPage {

    @State 
    public String mensaje = "¡Hazme Clic!";

    @Override
    protected String template() {
        return """
            <section class="contenedor">
                <h1>🎨 Co-localización (CSS y JS)</h1>
                <p>
                    El diseño y la interactividad nativa de esta página están separados en 
                    <code>Leccion07Page.css</code> y <code>Leccion07Page.js</code>. 
                    ¡JReactive los inyecta y aísla automáticamente!
                </p>
                
                <div class="caja-magica" onclick="animarCajaMagica(this)">
                    {{mensaje}}
                </div>
            </section>
        """;
    }
}