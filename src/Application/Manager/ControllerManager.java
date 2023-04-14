package Application.Manager;

import Application.AppManager;
import Application.Controller.Main.Controller;
import Application.Controller.ModalWindows.DetailObjects.ControllerDetailObjects;
import Application.Controller.ModalWindows.Information.ControllerInformation;
import Application.Controller.Music.Music;
import Application.TImer.Time;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerManager{

    private Controller controller;

    private Music MED;

    public void MusicPlay() {
        MED.MusicPlay();
    }

    public void MusicPause() {
        MED.MusicPause();
    }

    public void MusicStop() {
        MED.MusicStop();
    }
    private ControllerInformation controllerInformation;

    private ControllerDetailObjects controllerDetailObjects;

    public ControllerManager(FXMLLoader loader, AppManager Application) {
        controller = loader.getController();
        initListeners(Application);
    }
    private void initListeners(AppManager appManager){
        controller.getButtonStart().setOnAction(event ->
        {
            try {
                appManager.appStart();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        controller.getButtonStop().setOnAction(event ->
        {
            try {
                appManager.appStop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        controller.getButtonPause().setOnAction(event ->
        {
            try {
                appManager.appPause();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        controller.getPaneMain().setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                try {
                    writeKeyCode(event.getCode(),appManager);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        controller.getRadioButtonTimer().setOnAction(event ->
        {
            try {
                controller.swapTimerShowState();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        controller.getRadioButtonInformationObjectDetail().setOnAction(event -> {
            try {
                createModalWindow(appManager, "Живущие объекты", "src/Application/Controller/ModalWindows/Information/FXML/Information.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        controller.getRadioButtonInformation().setOnAction(event -> {
            try {
                controller.setShowLog(!controller.getShowLog());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void writeKeyCode(KeyCode key, AppManager appManager) throws Exception {

        if(key == KeyCode.T) {
            controller.swapTimerShowState();
        }
        if (key == KeyCode.B){
            try {
                appManager.appStart();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (key == KeyCode.E){
            appManager.appStop();
        }
    }

    public void createModalWindow(AppManager appManager, String title, String resourse) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(resourse));
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(appManager.getStage());
        stage.show();
    }
    public void setBackground(ImageView imageBackground) {
        controller.getPaneStage().getChildren().addAll(new Node[]{imageBackground});
    }
    public void setTime(Time time) {
        controller.setLabelTimerValue(time);
    }
    public Controller getController() {
        return controller;
    }

    public void setButtonState(boolean ButtonStopDisableState, boolean ButtonPauseDisableState, boolean ButtonStartDisableState, boolean SpinnersState) {
        controller.setButtonState(ButtonStopDisableState, ButtonPauseDisableState, ButtonStartDisableState, SpinnersState);
    }

    public void showLog(AppManager appManager) throws IOException {
        if(controller.getShowLog()) {
            createModalWindow(appManager, "Информация", "src/Application/Controller/ModalWindows/Information/FXML/Information.fxml");
        }
    }
}
