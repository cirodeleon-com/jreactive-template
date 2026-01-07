package com.example.demo.pages;

import com.ciro.jreactive.HtmlComponent;
import com.ciro.jreactive.State;
import com.ciro.jreactive.annotations.Call;
import com.ciro.jreactive.router.Route;
import org.springframework.stereotype.Component;

@Component
@Route(path = "/")
public class CounterPage extends HtmlComponent {

    @State
    public int count = 0;
    
    @State
    public String nombrePersona="Ciro De Leon";

    @Call
    public void increment() {
        count++;
    }

    @Call
    public void testError() {
        throw new RuntimeException("¡El Toast de error funciona!");
    }

    @Override
    protected String template() {
        return """
            <div style="text-align: center; background: white; padding: 40px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1);">
                <h1>Hola, JReactive ⚡</h1>
                <p>Has hecho clic <b>{{count}}</b> veces <b> {{nombrePersona}} </b></p>
                <input type="text" name="nombrePersona" />
                
                <div style="display: flex; gap: 10px; justify-content: center; margin-top: 20px;">
                    <button @click="increment()">Sumar +1</button>
                    
                    <button @click="testError()" style="background: #dc3545;">
                        Probar Error
                    </button>
                </div>
            </div>
        """;
    }
}
