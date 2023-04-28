package Application.Controller.ModalWindows.Information;

import Application.Controller.IController;
import Application.Controller.ModalWindows.Information.FXML.InformationFXMLObjectsGets;
import Application.AppManager;
import Application.Simulation.StateSimulation;
import Application.TImer.Timer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerInformation extends InformationFXMLObjectsGets implements IController {

    Stage stage;
    private void initFont() {
        buttonOK.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 12;");
        buttonCancel.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 12;");
        textAreaInformation.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 20;");
    }
    private void initButtons() {
        getButtonCancel().setOnAction(event -> {
            try {
                AppManager.getInstance().appState(StateSimulation.RUNNING);
                hide();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        getButtonOK().setOnAction(event -> {
            try {
                AppManager.getInstance().appState(StateSimulation.STOP);
                hide();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        stage.setOnCloseRequest(event -> {
            try {
                AppManager.getInstance().appState(StateSimulation.STOP);
                hide();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public ControllerType getType() {
        return ControllerType.END_INFORMATION;
    }

    @Override
    public void init(Stage stage) throws IOException {
        stage.setTitle("Информ., бюро");
        stage.initOwner(AppManager.getInstance().getStage());
        stage.initModality(Modality.WINDOW_MODAL);
        textAreaInformation.setText(AppManager.getInstance().makeResultLog());
        textAreaInformation.setEditable(false);
        initButtons();
        initFont();
        stage.show();
    }

    @Override
    public void hide() {
        stage.close();
    }

    @Override
    public void update(Timer timer) {

    }

    @Override
    public void swapState() {

    }
}
