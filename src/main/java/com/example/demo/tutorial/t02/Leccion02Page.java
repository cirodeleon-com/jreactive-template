package com.example.demo.tutorial.t02;

import com.ciro.jreactive.State;
import com.ciro.jreactive.annotations.Call;
import com.ciro.jreactive.router.Route;
import com.example.demo.AppPage;

@Route(path = "/tutorial/leccion02")
public class Leccion02Page extends AppPage{
   //definimos la variable reactiva	
   @State String saludoState="";
   
   // usamos la anotacion call para poder llamar a este metodo
   @Call
   public void saludar(String saludo) {
	 saludoState = "jreactive te dice hello world "+ saludo;  
   }
   
   @Override
   protected String template() {
       return """
           <p>aqui viene el texto que escribes : 
              <!-- mostramos la variable reactiva -->
              <strong>{{saludoState}}</strong> 
           </p>
           
           <!--  llamamos a nuestra variable reactiva usando el name del input -->
           <input type="text" name="saludoState" />
           
           
           <!-- usamos el evento @click para llamar al metodo y
            pasamos como parametro el valor de nuestra variable reactiva -->
            
           <button type="button" @click="saludar(saludoState)">saludar</button>
           
           <hr style="margin-top: 40px; border-top: 1px solid #eee;">
            <div style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 15px;">
                <a data-router href="/tutorial/leccion01" style="color: #666; text-decoration: none; font-weight: 500;">
                    ⬅️ Anterior: Estado y Binding
                </a>
                <a data-router href="/" style="color: #11998e; text-decoration: none; font-weight: bold;">
                    🏠 Índice
                </a>
                <a data-router href="/tutorial/leccion03" style="padding: 10px 20px; background: #111; color: white; text-decoration: none; border-radius: 8px; font-weight: bold;">
                    Siguiente: Validación (JSR) ➡️
                </a>
            </div>
           
       """;
   }
}
