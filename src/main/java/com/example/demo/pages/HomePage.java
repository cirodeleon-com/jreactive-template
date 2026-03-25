package com.example.demo.pages;

import com.ciro.jreactive.State;
import com.ciro.jreactive.annotations.Call;
import com.ciro.jreactive.annotations.Client;
import com.ciro.jreactive.annotations.StatefulRam;
import com.ciro.jreactive.router.Route;
import com.example.demo.AppPage;

@Route(path = "/")
@Client 
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

   
}