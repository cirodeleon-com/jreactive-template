package com.example.demo.tutorial;

import org.springframework.stereotype.Component;

import com.ciro.jreactive.HtmlComponent;
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
       """;
   }
}
