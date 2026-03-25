package com.example.demo.tutorial.t07;

import com.ciro.jreactive.State;
import com.ciro.jreactive.router.Route;
import com.example.demo.AppPage;

@Route(path = "/tutorial/leccion07")
public class Leccion07Page extends AppPage {

    @State 
    public String mensaje = "¡Hazme Clic!";

    
}