package Application.Controller;

import Application.Controller.Music.Music;
import Application.Manager.AppManager;
import Application.TImer.Time;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class ControllerManager{

    private Controller controller;

    private Music MED;

    public ControllerManager(FXMLLoader loader, AppManager Application) {
        controller = loader.getController();
        MED = new Music();
        initListeners(Application);
    }

    public void setBackground(ImageView imageBackground) {
        controller.getPaneStage().getChildren().addAll(new Node[]{imageBackground});
    }
    public void MusicPlay() {
        MED.MusicPlay();
    }

    public void MusicPause() {
        MED.MusicPause();
    }

    public void MusicStop() {
        MED.MusicStop();
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

    public void setTime(Time time) {
        controller.setLabelTimerValue(time);
    }
    public Controller getController() {
        return controller;
    }

    public void setButtonState(boolean ButtonStopDisableState, boolean ButtonPauseDisableState, boolean ButtonStartDisableState, boolean SpinnersState) {
        controller.getButtonStop().setDisable(ButtonStopDisableState);
        controller.getButtonPause().setDisable(ButtonPauseDisableState);
        controller.getButtonStart().setDisable(ButtonStartDisableState);
        controller.getSpinnerSecondsWorker().setDisable(SpinnersState);
        controller.getSpinnerCoefficientDrone().setDisable(SpinnersState);
        controller.getSpinnerLifeTimeDrone().setDisable(SpinnersState);
        controller.getSpinnerProbabilityWorker().setDisable(SpinnersState);
        controller.getSpinnerSecondsDrone().setDisable(SpinnersState);
        controller.getSpinnerLifeTimeWorker().setDisable(SpinnersState);
    }
}
