package Application.Controller.Main;

import Application.AppManager;
import Application.Controller.IController;
import Application.Controller.Main.FXML.ApplicationFXMLObjectsGets;
import Application.Controller.Main.Music.Music;
import Application.Files.FileManager;
import Application.Habitat.HabitatSize;
import Application.Manager.ControllerManager;
import Application.Simulation.StateSimulation;
import Application.TImer.Timer;
import Objects.Drone.Drone;
import Objects.Worker.Worker;
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
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
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
                AppManager.getInstance().logsPrint("Simulation is running");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        menuItemSimulationStart.setOnAction(event ->
        {
            try {
                AppManager.getInstance().appState(StateSimulation.RUNNING);
                AppManager.getInstance().logsPrint("Simulation is running");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonPause.setOnAction(event ->
        {
            try {
                AppManager.getInstance().appState(StateSimulation.PAUSE);
                AppManager.getInstance().logsPrint("Simulation was paused");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        menuItemSimulationPause.setOnAction(event ->
        {
            try {
                AppManager.getInstance().appState(StateSimulation.PAUSE);
                AppManager.getInstance().logsPrint("Simulation was paused");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        menuItemSaveConfig.setOnAction(event ->
        {
            try {
                FileManager.getInstance().saveConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        menuItemLoadConfig.setOnAction(event ->
        {
            try {
                FileManager.getInstance().loadConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        menuItemClose.setOnAction(event ->
        {
            try{
                Platform.exit();
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        menuItemConsole.setOnAction(event ->
        {
          try {
              ControllerManager.getInstance().ControllerCreate(ControllerType.CONSOLE);
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
                    AppManager.getInstance().logsPrint("Simulation was stopped. All bees removed");
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
                    AppManager.getInstance().logsPrint("Simulation was stopped. All bees removed");
                }
                else
                    AppManager.getInstance().appState(StateSimulation.STOP);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        paneMain.setOnKeyPressed(event ->
        {
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
                radioButtonTimerOn.setDisable(true);
                radioButtonTimerOff.setDisable(false);
                radioMenuItemViewTimerOn.setDisable(true);
                radioMenuItemViewTimerOff.setDisable(false);
                swapTimerShowState();
                AppManager.getInstance().logsPrint("Timer is now displayed");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        radioButtonTimerOff.setOnAction(event ->
        {
            try {
                radioMenuItemViewTimerOff.setSelected(true);
                radioButtonTimerOn.setDisable(false);
                radioButtonTimerOff.setDisable(true);
                radioMenuItemViewTimerOn.setDisable(false);
                radioMenuItemViewTimerOff.setDisable(true);
                swapTimerShowState();
                AppManager.getInstance().logsPrint("Timer no longer displayed");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        radioMenuItemViewTimerOn.setOnAction(event ->
        {
            try {
                radioButtonTimerOn.setSelected(true);
                radioButtonTimerOn.setDisable(true);
                radioButtonTimerOff.setDisable(false);
                radioMenuItemViewTimerOn.setDisable(true);
                radioMenuItemViewTimerOff.setDisable(false);
                swapTimerShowState();
                AppManager.getInstance().logsPrint("Timer is now displayed");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        radioMenuItemViewTimerOff.setOnAction(event ->
        {
            try {
                radioButtonTimerOff.setSelected(true);
                radioButtonTimerOn.setDisable(false);
                radioButtonTimerOff.setDisable(true);
                radioMenuItemViewTimerOn.setDisable(false);
                radioMenuItemViewTimerOff.setDisable(true);
                swapTimerShowState();
                AppManager.getInstance().logsPrint("Timer no longer displayed");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        checkboxDetailObjects.setOnAction(event -> {
            try {
                if (checkboxDetailObjects.isSelected()) {
                    checkMenuItemViewDetailObjects.setSelected(true);
                    ControllerManager.getInstance().ControllerCreate(ControllerType.DETAIL_OBJECTS);
                    AppManager.getInstance().logsPrint("The \"current objects\" window is now open");
                }
                else {
                    checkMenuItemViewDetailObjects.setSelected(false);
                    if (ControllerManager.getInstance().getController(ControllerType.DETAIL_OBJECTS) != null) {
                        ControllerManager.getInstance().getController(ControllerType.DETAIL_OBJECTS).hide();
                        ControllerManager.getInstance().ControllerDelete(ControllerType.DETAIL_OBJECTS);
                    }
                    AppManager.getInstance().logsPrint("The \"current objects\" window was closed");
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
                    AppManager.getInstance().logsPrint("The \"current objects\" window is now open");
                }
                else {
                    if (ControllerManager.getInstance().getController(ControllerType.DETAIL_OBJECTS) != null) {
                        ControllerManager.getInstance().getController(ControllerType.DETAIL_OBJECTS).hide();
                        ControllerManager.getInstance().ControllerDelete(ControllerType.DETAIL_OBJECTS);
                    }
                    AppManager.getInstance().logsPrint("The \"current objects\" window was closed");
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
                AppManager.getInstance().logsPrint("Volume has been changed from " + oldValue + "% to " + newValue + "%");
            }
        });

        checkboxInformation.setOnAction(event -> {
            try {
                if (!checkboxInformation.isSelected()) {
                    checkMenuItemViewInformation.setSelected(true);
                    showLog = true;
                    AppManager.getInstance().logsPrint("Now when simulation is paused, the information window will be shown");
                }
                else {
                    checkMenuItemViewInformation.setSelected(false);
                    showLog = false;
                    AppManager.getInstance().logsPrint("Now when simulation is paused, the information window will no longer be shown");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        checkMenuItemViewInformation.setOnAction(event -> {
            try {
                if (!checkMenuItemViewInformation.isSelected()) {
                    checkboxInformation.setSelected(true);
                    showLog = true;
                    AppManager.getInstance().logsPrint("Now when simulation is paused, the information window will be shown");
                }
                else {
                    checkboxInformation.setSelected(false);
                    showLog = false;
                    AppManager.getInstance().logsPrint("Now when simulation is paused, the information window will no longer be shown");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonAIDrone.setOnAction(event ->
        {
            try {
                if(Drone.DroneBaseAI.isActive()) {
                    Drone.DroneBaseAI.stopAI();
                    buttonAIDrone.setText("Включить трутней");
                    menuItemAIDrone.setText("Включить трутней");
                } else {
                    Drone.DroneBaseAI.startAI();
                    buttonAIDrone.setText("Отключить трутней");
                    menuItemAIDrone.setText("Отключить трутней");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonAIWorker.setOnAction(event ->
        {
            try {
                if(Worker.WorkerBaseAI.isActive()) {
                    Worker.WorkerBaseAI.stopAI();
                    buttonAIWorker.setText("Включить рабочих");
                    menuItemAIWorker.setText("Включить рабочих");
                } else {
                    Worker.WorkerBaseAI.startAI();
                    buttonAIWorker.setText("Отключить рабочих");
                    menuItemAIWorker.setText("Отключить рабочих");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        menuItemAIDrone.setOnAction(event ->
        {
            try {
                if(Drone.DroneBaseAI.isActive()) {
                    Drone.DroneBaseAI.stopAI();
                    buttonAIDrone.setText("Включить трутней");
                    menuItemAIDrone.setText("Включить трутней");
                } else {
                    Drone.DroneBaseAI.startAI();
                    buttonAIDrone.setText("Отключить трутней");
                    menuItemAIDrone.setText("Отключить трутней");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        menuItemAIWorker.setOnAction(event ->
        {
            try {
                if(Worker.WorkerBaseAI.isActive()) {
                    Worker.WorkerBaseAI.stopAI();
                    buttonAIWorker.setText("Включить рабочих");
                    menuItemAIWorker.setText("Включить рабочих");
                } else {
                    Worker.WorkerBaseAI.startAI();
                    buttonAIWorker.setText("Отключить рабочих");
                    menuItemAIWorker.setText("Отключить рабочих");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        comboBoxWorkerPriority.setOnAction(event ->
        {
            try {
                if(Worker.WorkerBaseAI.isActive()) {
                    Worker.WorkerBaseAI.setAIPriority(getValueComboBoxWorkerPriority());
                    AppManager.getInstance().logsPrint("Worker priority changed to " + getValueComboBoxWorkerPriority());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        comboBoxDronePriority.setOnAction(event ->
        {
            try {
                if(Drone.DroneBaseAI.isActive()) {
                    Drone.DroneBaseAI.setAIPriority(getValueComboBoxDronePriority());
                    System.out.println(Drone.DroneBaseAI.getAIPriority());
                    AppManager.getInstance().logsPrint("Drone priority changed to " + getValueComboBoxDronePriority());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void writeKeyCode(KeyCode key) throws Exception {

        if(key == KeyCode.T) {
            swapTimerShowState();
            AppManager.getInstance().logsPrint("Timer state switched from keyboard");
        }
        if (key == KeyCode.B){
            try {
                AppManager.getInstance().appState(StateSimulation.RUNNING);
                AppManager.getInstance().logsPrint("Simulation is running");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (key == KeyCode.BACK_QUOTE) {
            ControllerManager.getInstance().ControllerCreate(ControllerType.CONSOLE);
        }
        if (key == KeyCode.E){
            AppManager.getInstance().appState(StateSimulation.STOP);
            AppManager.getInstance().logsPrint("Simulation was stopped. All bees removed");
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
        ObservableList<Integer> WorkerPriority =
                FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        ObservableList<Integer> DronePriority =
                FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        comboBoxWorkerPriority.setItems(WorkerPriority);
        comboBoxDronePriority.setItems(DronePriority);
        comboBoxWorkerPriority.getSelectionModel().select(4);
        comboBoxDronePriority.getSelectionModel().select(4);
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
        stage.setMinWidth(1000+20); //РазмерОкна+Рамки
        stage.setMinHeight(630+40); //РазмерОкна+ЗаголовочнаяЧасть
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
        setButtonState(true, true, false, false);
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
            checkboxDetailObjects.setSelected(true);
            checkMenuItemViewDetailObjects.setSelected(true);
        }
        else {
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
