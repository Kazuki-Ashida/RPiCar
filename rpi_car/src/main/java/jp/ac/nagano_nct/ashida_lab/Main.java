/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.ac.nagano_nct.ashida_lab;

import com.pi4j.Pi4J;


import jp.ac.nagano_nct.ashida_lab.se.HeadLight;



/**
 *
 * @author luca
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        HeadLight head_light = HeadLight.getInstance();
        
        for(int i=0; i<10; i++){
            head_light.turnOn();
            Thread.sleep(1000);
            head_light.turnOff();
            Thread.sleep(1000);
        }

        Pi4J.newAutoContext().shutdown();

    }

}
