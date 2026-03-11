package com.example.demo.tutorial.t04;

import com.ciro.jreactive.State;
import com.ciro.jreactive.annotations.Call;
import com.ciro.jreactive.router.Route;
import com.example.demo.AppPage;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/tutorial/leccion04")
public class Leccion04Page extends AppPage {

    // JReactive convierte automáticamente este ArrayList en un 'SmartList'.
    // Cualquier cambio generará Deltas JSON (ADD/REMOVE) ultraligeros.
    @State 
    public List<String> tareas = new ArrayList<>(List.of("Estudiar JReactive"));

    @State 
    public String nuevaTarea = "";

    @Call
    public void agregarTarea(String tarea) {
        if (tarea == null || tarea.isBlank()) return;
        tareas.add(tarea); // ¡Magia! Solo viaja la nueva tarea por la red.
        this.nuevaTarea = ""; 
    }

    @Call
    public void borrarTarea(String tarea) {
        tareas.remove(tarea); // ¡Magia! Solo viaja el comando de borrado.
    }

    @Override
    protected String template() {
        return """
            <section style="max-width: 500px; padding: 20px; font-family: sans-serif;">
                <h1>Lista de Tareas Inteligente</h1>
                <p><em>Abre la pestaña "Network/Red" en tus DevTools. Verás que al agregar una tarea, no viaja todo el HTML de regreso, ¡solo un pequeño Delta JSON!</em></p>

                <div style="display: flex; gap: 10px; margin-bottom: 20px;">
                    <input type="text" name="nuevaTarea" placeholder="¿Qué harás hoy?">
                    <button @click="agregarTarea(nuevaTarea)">Agregar</button>
                </div>

                <ul style="list-style: none; padding: 0;">
                    {{#each tareas as tarea}}
                        <li style="padding: 10px; background: #f4f4f4; margin-bottom: 5px; display: flex; justify-content: space-between;">
                            {{tarea}}
                            <button @click="borrarTarea('{{tarea}}')" style="color: red; border: none; cursor: pointer;">X</button>
                        </li>
                    {{/each}}
                </ul>
            </section>
        """;
    }
}