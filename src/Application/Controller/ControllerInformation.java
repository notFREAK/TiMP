package Application.Controller;

import Application.Controller.FXML.InformationFXMLObjectsGets;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import Application.Manager.Main.AppManager;
import javafx.stage.Stage;


public class ControllerInformation extends InformationFXMLObjectsGets {

    Stage stage;

    public ControllerInformation(Stage stage) {

    }

    private void initButtons(AppManager appManager) {
        buttonCancel.setOnAction(event -> {
            try {
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        buttonOK.setOnAction(event -> {
            try {
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        stage.setOnCloseRequest(event -> {
            try {
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
