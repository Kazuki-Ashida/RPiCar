/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.ac.nagano_nct.ashida_lab;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.GenericDeclaration;

import com.pi4j.Pi4J;

import jp.ac.nagano_nct.ashida_lab.se.Beeper;
import jp.ac.nagano_nct.ashida_lab.se.Camera;
import jp.ac.nagano_nct.ashida_lab.se.HeadLight;
import jp.ac.nagano_nct.ashida_lab.se.RightDirectionIndicator;

/**
 *
 * @author Ashida
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        Camera camera = Camera.getInstance();
        camera.setWidth(3280)
                .setHeight(2464)
                .setFileName("Test")
                .setFormat(Camera.Format.JPG);

        File file = camera.takePicture();

        System.out.println(file.getAbsolutePath());
    }

}
