package Application.Controller.ModalWindows.Information;

import Application.Controller.ModalWindows.Information.FXML.InformationFXMLObjectsGets;
import Application.AppManager;
import javafx.stage.Stage;


public class ControllerInformation extends InformationFXMLObjectsGets {

    Stage stage;

    public ControllerInformation(Stage stage) {
        this.stage = stage;
    }

    private void initButtons(AppManager appManager) {
        getButtonCancel().setOnAction(event -> {
            try {
                appManager.appStart();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        getButtonCancel().setOnAction(event -> {
            try {
                appManager.appStop();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        stage.setOnCloseRequest(event -> {
            try {
                appManager.appStop();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
