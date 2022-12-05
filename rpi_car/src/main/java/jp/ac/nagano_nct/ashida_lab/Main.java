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
import jp.ac.nagano_nct.ashida_lab.se.DrivingDivision;
import jp.ac.nagano_nct.ashida_lab.se.HeadLight;
import jp.ac.nagano_nct.ashida_lab.se.RightDirectionIndicator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 *
 * @author Ashida
 */
public class Main extends Application{


    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        launch();

        DrivingDivision dd = DrivingDivision.getInstance();

        dd.steer(50, 75);
        Thread.sleep(3000);
        dd.stopMotion();

        Pi4J.newAutoContext().shutdown();
    }

}
