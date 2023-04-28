package Application.Controller.Main;

import Application.AppManager;
import Application.Controller.IController;
import Application.Controller.Main.FXML.ApplicationFXMLObjectsGets;
import Application.Controller.Music.Music;
import Application.Manager.ControllerManager;
import Application.Simulation.StateSimulation;
import Application.TImer.Timer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import static Application.Habitat.HabitatSize.getImageViewBackground;

public class Controller extends ApplicationFXMLObjectsGets implements IController {
    protected Boolean showLog = false;
    public Boolean getShowLog() {
        return showLog;
    }
    protected Boolean showTime = true;
    private Music MED;
    private Stage stage;

    @Override
    public ControllerType getType() {
        return ControllerType.MAIN;
    }

    public Music getMusic() {
        return MED;
    }

    public void setShowTime(Boolean showTime) {
        this.showTime = showTime;
    }

    public void swapTimerShowState(){
        setShowTime(!showTime);
        labelTimer.setVisible(showTime);
    }
    private void initListeners(){
        buttonStart.setOnAction(event ->
        {
            try {
                AppManager.getInstance().appState(StateSimulation.RUNNING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonStop.setOnAction(event ->
        {
            try {
                if (showLog) {
                    AppManager.getInstance().appState(StateSimulation.PAUSE);
                    ControllerManager.getInstance().ControllerCreate(ControllerType.END_INFORMATION);
                }
                else
                    AppManager.getInstance().appState(StateSimulation.STOP);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonPause.setOnAction(event ->
        {
            try {
                AppManager.getInstance().appState(StateSimulation.PAUSE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        paneMain.setOnKeyPressed(event -> {
            try {
                writeKeyCode(event.getCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        radioButtonTimer.setOnAction(event ->
        {
            try {
                swapTimerShowState();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        radioButtonInformationObjectDetail.setOnAction(event -> {
            try {
                if (radioButtonInformationObjectDetail.isSelected()) {
                    ControllerManager.getInstance().ControllerCreate(ControllerType.DETAIL_OBJECTS);
                }
                else {
                    ControllerManager.getInstance().getController(ControllerType.DETAIL_OBJECTS).hide();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        radioButtonInformation.setOnAction(event -> {
            try {
                showLog  = !showLog;
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void writeKeyCode(KeyCode key) throws Exception {

        if(key == KeyCode.T) {
            swapTimerShowState();
        }
        if (key == KeyCode.B){
            try {
                AppManager.getInstance().appState(StateSimulation.RUNNING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (key == KeyCode.E){
            AppManager.getInstance().appState(StateSimulation.STOP);
        }
    }
    private void initSpinners() {
        spinnerLifeTimeDrone.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        spinnerSecondsDrone.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        spinnerCoefficientDrone.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        spinnerSecondsWorker.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        spinnerLifeTimeWorker.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        spinnerProbabilityWorker.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
    }
    public void setButtonState(boolean ButtonStopDisableState, boolean ButtonPauseDisableState, boolean ButtonStartDisableState, boolean SpinnersState) {
        buttonStop.setDisable(ButtonStopDisableState);
        buttonPause.setDisable(ButtonPauseDisableState);
        buttonStart.setDisable(ButtonStartDisableState);
        spinnerSecondsWorker.setDisable(SpinnersState);
        spinnerCoefficientDrone.setDisable(SpinnersState);
        spinnerLifeTimeDrone.setDisable(SpinnersState);
        spinnerProbabilityWorker.setDisable(SpinnersState);
        spinnerSecondsDrone.setDisable(SpinnersState);
        spinnerLifeTimeWorker.setDisable(SpinnersState);
    }

    public void initLabelsFont() {
        buttonPause.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        buttonStart.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        buttonStop.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        spinnerSecondsWorker.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        spinnerCoefficientDrone.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        spinnerLifeTimeDrone.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        spinnerProbabilityWorker.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        spinnerSecondsDrone.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        spinnerLifeTimeWorker.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        textSecondsDrone.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        textLifeTimeDrone.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        textCoefficientDrone.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        textSecondsWorker.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        textLifeTimeWorker.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        textProbabilityWorker.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        labelTimer.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 25;");
        radioButtonInformation.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        radioButtonTimer.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        radioButtonInformationObjectDetail.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
    }

    @Override
    public void init(Stage stage) throws IOException {
        this.stage = stage;
        stage.setTitle("TiMP");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        initSpinners();
        initLabelsFont();
        initListeners();
        MED = new Music();
        stage.show();
    }

    @Override
    public void hide() {
        stage.close();
    }

    @Override
    public void update(Timer timer) {
        if(ControllerManager.getInstance().checkIsType(ControllerType.DETAIL_OBJECTS)) {
            radioButtonInformationObjectDetail.setSelected(false);
        }
        labelTimer.setText(timer.getTime().getTimeString());
        AppManager.getInstance().getHabitat().Update(timer.getSeconds(), paneStage);
    }

    @Override
    public void swapState() {
        switch (AppManager.getInstance().getSimulation().getState().getStateSimulation()) {
            case RUNNING:
                setButtonState(true, true, false, false);
                paneStage.getChildren().addAll(new Node[]{getImageViewBackground()});
                setSimulationValue();
                MED.MusicPlay();
                break;
            case PAUSE:
                setButtonState(false, true, false, true);
                MED.MusicPause();
                break;
            default:
                setButtonState(false, false, true, true);
                paneStage.getChildren().removeAll();
                MED.MusicStop();
                break;
        }
    }


    private void setSimulationValue()
    {
        AppManager.getInstance().getSimulation().getSimulationValue().setValue(
                getValueSpinnerSecondsDrone(),
                getValueSpinnerCoefficientDrone(),
                getValueSpinnerLifeTimeDrone(),
                getValueSpinnerSecondsWorker(),
                getValueSpinnerProbabilityWorker(),
                getValueSpinnerLifeTimeWorker()
        );
        AppManager.getInstance().getHabitat().setSimulationValue(AppManager.getInstance().getSimulation().getSimulationValue());
    }
}