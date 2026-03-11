package com.example.demo.pages;

import com.ciro.jreactive.State;
import com.ciro.jreactive.annotations.Call;
import com.ciro.jreactive.annotations.Client;
import com.ciro.jreactive.annotations.StatefulRam;
import com.ciro.jreactive.router.Route;
import com.example.demo.AppPage;

@Route(path = "/")
@Client // 🔥 INDISPENSABLE: Activa el morphing y los hooks del cliente
@StatefulRam
public class HomePage extends AppPage {

    @State public String devName = "Desarrollador Java Ciro De León B.";
    @State public String role = "Full-Stack Dev";
    @State public int powerLevel = 10;

    @Call
    public void boostPower() {
        if (powerLevel < 100) {
            powerLevel += 15;
        } else {
            powerLevel = 10; 
        }
    }

    @Call
    public void sync() {
        // Dispara la reactividad al teclear
    }

    @Override
    protected String template() {
        return """
            <div class="home-container">
                
                <div class="controls-panel">
                    <h1 class="title">JReactive ⚡</h1>
                    <p class="subtitle">El futuro del Full-Stack en Java.</p>
                    
                    <div class="input-group">
                        <label>Creador:</label>
                        <input id="input-name" type="text" name="devName" autocomplete="off" @input="sync()" />
                    </div>
                    
                    <div class="input-group">
                        <label>Especialidad:</label>
                        <input id="input-role" type="text" name="role" autocomplete="off" @input="sync()" />
                    </div>
                    
                    <button type="button" class="btn-boost" @click="boostPower()">
                        🚀 Subir Nivel de Poder
                    </button>
                    
                    <p class="hint">Escribe en los campos y mira la tarjeta actualizarse al instante.</p>
                </div>

                <div class="card-showcase">
                    <div id="dev-card-ui" class="dev-card" onmousemove="tiltCard(event, this)" onmouseleave="resetTilt(this)">
                        <div class="card-glow"></div>
                        
                        <div class="card-header">
                            <span class="badge">SSR + WS</span>
                            <span id="level-text" class="level">Nivel {{powerLevel}}</span>
                        </div>
                        
                        <div class="card-body">
                            <h2 id="card-name-text" class="card-name">{{devName}}</h2>
                            <p id="card-role-text" class="card-role">{{role}}</p>
                        </div>
                        
                        <div class="card-footer">
                            <div class="progress-bar">
                                <div id="power-bar-fill" 
                                     class="progress-fill" 
                                     style="width: {{powerLevel}}%;"
                                     data-level="{{powerLevel}}"
                                     client:update="window.ConfettiDemo.check(this)">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
            </div>
        """;
    }
}