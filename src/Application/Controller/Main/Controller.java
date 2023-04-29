package Application.Controller.Main;

import Application.AppManager;
import Application.Controller.IController;
import Application.Controller.Main.FXML.ApplicationFXMLObjectsGets;
import Application.Controller.Main.Music.Music;
import Application.Habitat.HabitatSize;
import Application.Manager.ControllerManager;
import Application.Simulation.StateSimulation;
import Application.TImer.Timer;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;
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

        menuItemSimulationStart.setOnAction(event ->
        {
            try {
                AppManager.getInstance().appState(StateSimulation.RUNNING);
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

        menuItemSimulationPause.setOnAction(event ->
        {
            try {
                AppManager.getInstance().appState(StateSimulation.PAUSE);
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

        menuItemSimulationStop.setOnAction(event ->
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

        paneMain.setOnKeyPressed(event -> {
            try {
                writeKeyCode(event.getCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        radioButtonTimerOn.setOnAction(event ->
        {
            try {
                radioMenuItemViewTimerOn.setSelected(true);
                swapTimerShowState();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        radioButtonTimerOff.setOnAction(event ->
        {
            try {
                radioMenuItemViewTimerOff.setSelected(true);
                swapTimerShowState();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        radioMenuItemViewTimerOn.setOnAction(event ->
        {
            try {
                radioButtonTimerOn.setSelected(true);
                swapTimerShowState();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        radioMenuItemViewTimerOff.setOnAction(event ->
        {
            try {
                radioButtonTimerOff.setSelected(true);
                swapTimerShowState();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        checkboxDetailObjects.setOnAction(event -> {
            try {
                if (checkboxDetailObjects.isSelected()) {
                    checkMenuItemViewDetailObjects.setSelected(true);
                    ControllerManager.getInstance().ControllerCreate(ControllerType.DETAIL_OBJECTS);
                }
                else {
                    checkMenuItemViewDetailObjects.setSelected(false);
                    ControllerManager.getInstance().getController(ControllerType.DETAIL_OBJECTS).hide();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        checkMenuItemViewDetailObjects.setOnAction(event -> {
            try {
                if (!checkboxDetailObjects.isSelected()) {
                    checkboxDetailObjects.setSelected(true);
                    ControllerManager.getInstance().ControllerCreate(ControllerType.DETAIL_OBJECTS);
                }
                else {
                    checkboxDetailObjects.setSelected(false);
                    ControllerManager.getInstance().getController(ControllerType.DETAIL_OBJECTS).hide();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        sliderVolume.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldValue,
                    Number newValue) {
                MED.setMusicVolume((double)newValue);
            }
        });

        checkboxInformation.setOnAction(event -> {
            try {
                checkMenuItemViewInformation.setSelected(!checkMenuItemViewInformation.isSelected());
                showLog  = !showLog;
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        checkMenuItemViewInformation.setOnAction(event -> {
            try {
                checkboxInformation.setSelected(!checkboxInformation.isSelected());
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
    private void initComboBox() {
        ObservableList<Integer> CoefficientDrone =
                FXCollections.observableArrayList(0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
        ObservableList<Integer> ProbabilityWorker =
                FXCollections.observableArrayList(0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
        comboBoxCoefficientDrone.setItems(CoefficientDrone);
        comboBoxProbabilityWorker.setItems(ProbabilityWorker);
        comboBoxCoefficientDrone.getSelectionModel().select(0);
        comboBoxProbabilityWorker.getSelectionModel().select(0);
    }

    private void initSlider() {
         sliderVolume.setMin(0);
         sliderVolume.setMax(100);
         sliderVolume.setShowTickLabels(true);

    }
    private void initSpinners() {
        spinnerLifeTimeDrone.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        spinnerSecondsDrone.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        spinnerSecondsWorker.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        spinnerLifeTimeWorker.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
    }
    public void setButtonState(boolean ButtonStopDisableState, boolean ButtonPauseDisableState, boolean ButtonStartDisableState, boolean SpinnersState) {
        buttonStop.setDisable(ButtonStopDisableState);
        buttonPause.setDisable(ButtonPauseDisableState);
        buttonStart.setDisable(ButtonStartDisableState);
        menuItemSimulationStart.setDisable(ButtonStartDisableState);
        menuItemSimulationPause.setDisable(ButtonPauseDisableState);
        menuItemSimulationStop.setDisable(ButtonStopDisableState);
        spinnerSecondsWorker.setDisable(SpinnersState);
        comboBoxProbabilityWorker.setDisable(SpinnersState);
        spinnerLifeTimeDrone.setDisable(SpinnersState);
        comboBoxCoefficientDrone.setDisable(SpinnersState);
        spinnerSecondsDrone.setDisable(SpinnersState);
        spinnerLifeTimeWorker.setDisable(SpinnersState);
    }

    public void initLabelsFont() {
        buttonPause.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        buttonStart.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        buttonStop.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        spinnerSecondsWorker.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        spinnerLifeTimeDrone.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        spinnerSecondsDrone.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        spinnerLifeTimeWorker.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        textSecondsDrone.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        textLifeTimeDrone.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        textCoefficientDrone.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        textSecondsWorker.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        textLifeTimeWorker.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        textProbabilityWorker.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
        labelTimer.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 25;");
    }

    @Override
    public void init(Stage stage) throws IOException {
        stage.setTitle("TiMP");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        initSpinners();
        initComboBox();
        initLabelsFont();
        initListeners();
        MED = new Music();
        MED.setMusicVolume((double)0);
        setButtonState(true, false, false, true);
        stage.show();
        this.stage = stage;
        HabitatSize.setHeight((int)paneStage.getHeight());
        HabitatSize.setWidth((int)paneStage.getWidth());
    }

    @Override
    public void hide() {
        stage.close();
    }

    @Override
    public void update(Timer timer) {
        if(ControllerManager.getInstance().checkIsType(ControllerType.DETAIL_OBJECTS)) {
            checkboxDetailObjects.setSelected(false);
            checkMenuItemViewDetailObjects.setSelected(false);
        }
        labelTimer.setText(timer.getTime().getTimeString());
        AppManager.getInstance().getHabitat().Update(timer.getSeconds(), paneStage);
    }

    @Override
    public void swapState() {
        switch (AppManager.getInstance().getSimulation().getState().getStateSimulation()) {
            case RUNNING:
                setButtonState(false, false, true, true);
                paneStage.getChildren().addAll(new Node[]{getImageViewBackground()});
                setSimulationValue();
                MED.MusicPlay();
                break;
            case PAUSE:
                setButtonState(false, true, false, true);
                MED.MusicPause();
                break;
            default:
                setButtonState(true, true, false, false);
                paneStage.getChildren().removeAll();
                MED.MusicStop();
                break;
        }
    }


    private void setSimulationValue()
    {
        AppManager.getInstance().getSimulation().getSimulationValue().setValue(
                getValueSpinnerSecondsDrone(),
                getValueComboBoxCoefficientDrone(),
                getValueSpinnerLifeTimeDrone(),
                getValueSpinnerSecondsWorker(),
                getValueComboBoxProbabilityWorker(),
                getValueSpinnerLifeTimeWorker()
        );
        AppManager.getInstance().getHabitat().setSimulationValue(AppManager.getInstance().getSimulation().getSimulationValue());
    }


}
