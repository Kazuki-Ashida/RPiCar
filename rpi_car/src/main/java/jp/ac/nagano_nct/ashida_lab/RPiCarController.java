package jp.ac.nagano_nct.ashida_lab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.TouchEvent;
import javafx.scene.shape.Circle;

public class RPiCarController {

    @FXML
    private Circle centerPhotoReflectorCircle;

    @FXML
    private Label distanceLabel;

    @FXML
    private ToggleButton goBackToggleButton;

    @FXML
    private ToggleButton goForwardToggleButton;

    @FXML
    private ToggleButton headLightToggleButton;

    @FXML
    private Button hornButton;

    @FXML
    private Circle leftPhotoReflectorCircle;

    @FXML
    private Label leftSpeedLabel;

    @FXML
    private Slider leftSpeedSlider;

    @FXML
    private ImageView pictureImageView;

    @FXML
    private Circle rightPhotoReflectorCircle;

    @FXML
    private Label rightSpeedLabel;

    @FXML
    private Slider rightSpeedSlider;

    @FXML
    private ToggleButton rotateLeftToggleButton;

    @FXML
    private ToggleButton rotateRightToggleButton;

    @FXML
    private Button stopButton;

    @FXML
    private CheckBox synchronizeMotorCheckBox;

    @FXML
    private Button takePictureButton;

    @FXML
    void handleGoBackToggleButtonAction(ActionEvent event) {

    }

    @FXML
    void handleGoForwardToggleButtonAction(ActionEvent event) {

    }

    @FXML
    void handleHeadLightToggleButtonAction(ActionEvent event) {

    }

    @FXML
    void handleHornButtonTouchPressed(TouchEvent event) {

    }

    @FXML
    void handleHornButtonTouchReleased(TouchEvent event) {

    }

    @FXML
    void handleRotateLeftToggleButtonAction(ActionEvent event) {

    }

    @FXML
    void handleRotateRightToggleButtonAction(ActionEvent event) {

    }

    @FXML
    void handleStopButtonTouchPressed(TouchEvent event) {

    }

    @FXML
    void handleStopButtonTouchReleased(TouchEvent event) {

    }

    @FXML
    void handleSynchronizeMotorCheckBoxAction(ActionEvent event) {

    }

    @FXML
    void handleTakePictureButtonAction(ActionEvent event) {

    }

}
