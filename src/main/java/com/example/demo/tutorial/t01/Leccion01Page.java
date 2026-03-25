package com.example.demo.tutorial.t01;


import com.ciro.jreactive.State;
import com.ciro.jreactive.router.Route;
import com.example.demo.AppPage;


@Route(path = "/tutorial/leccion01")
public class Leccion01Page extends AppPage{
   //definimos la variable reactiva	
   @State String saludoState="";
   
   @Override
   protected String template() {
       return """
           <p>aqui viene el texto que escribes : 
              <strong>{{saludoState}}</strong> <!-- mostramos la variable reactiva -->
           </p>
           <!--  llamamos a nuestra variable reactiva usando el name del input -->
           <input type="text" name="saludoState" />
           
           <hr style="margin-top: 40px; border-top: 1px solid #eee;">
            <div style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 15px;">
                <a data-router href="/" style="color: #666; text-decoration: none; font-weight: 500;">
                    ⬅️ Anterior: Inicio
                </a>
                <a data-router href="/" style="color: #11998e; text-decoration: none; font-weight: bold;">
                    🏠 Índice
                </a>
                <a data-router href="/tutorial/leccion02" style="padding: 10px 20px; background: #111; color: white; text-decoration: none; border-radius: 8px; font-weight: bold;">
                    Siguiente: Eventos (@Call) ➡️
                </a>
            </div>
           
       """;
   }
}
