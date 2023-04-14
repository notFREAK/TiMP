package Application.Manager;

import Application.Controller.ControllerManager;
import Application.Controller.FXML.FXMLObjectsGets;
import Application.Habitat.Habitat;
import Application.Simulation.Simulation;
import Application.Simulation.Value;
import Application.TImer.Time;
import Application.TImer.Timer;
import Objects.Bee.Bee;
import Objects.Drone.Drone;
import Objects.Worker.Worker;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppManager extends Application {
    private ControllerManager controllerManager;
    public FXMLLoader mainLoader;
    private Habitat habitat;
    private Simulation simulation;
    private Timer timer;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("../Application.fxml"));
        Parent root = mainLoader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("TiMP");
        stage.show();
        timer = new Timer(this);
        habitat = new Habitat();
        controllerManager = new ControllerManager(mainLoader,this);
        simulation = new Simulation();
    }

    public ControllerManager getControllerManager() {
        return controllerManager;
    }

    public FXMLLoader getMainLoader() {
        return mainLoader;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulationRunningTime(Time time) {
        controllerManager.setTime(time);
    }

    public void appStart() {
        setSimulationValue();
        if (!simulation.getState().isRunning()) {
            if (simulation.getState().isStop())
                controllerManager.setBackground(habitat.getSpaceSize().getImageViewBackground());
            simulation.getState().setRunning();
            controllerManager.MusicPlay();
            timer.Play();
            disableButtons();
        }
    }
    public void appPause(){
        if (simulation.getState().isRunning())
        {
            simulation.getState().setPause();
            timer.Pause();
            controllerManager.MusicPause();
            disableButtons();
        }
    }

    public void appStop() {
        if (!simulation.getState().isStop())
        {
            simulation.getState().setStop();
            timer.Stop();
            controllerManager.MusicStop();
            removeAllHabitat();
            disableButtons();
        }
    }

    private void setSimulationValue()
    {
        simulation.getSimulationValue().setValueSecondsDrone(controllerManager.getController().getValueSpinnerSecondsDrone());
        simulation.getSimulationValue().setValueCoefficientDrone(controllerManager.getController().getValueSpinnerCoefficientDrone());
        simulation.getSimulationValue().setValueLifeTimeDrone(controllerManager.getController().getValueSpinnerLifeTimeDrone());
        simulation.getSimulationValue().setValueSecondsWorker(controllerManager.getController().getValueSpinnerSecondsWorker());
        simulation.getSimulationValue().setValueProbabilityWorker(controllerManager.getController().getValueSpinnerProbabilityWorker());
        simulation.getSimulationValue().setValueLifeTimeWorker(controllerManager.getController().getValueSpinnerLifeTimeWorker());
        habitat.setSimulationValue(simulation.getSimulationValue());
    }
    public void removeAllHabitat(){
        habitat.clear();
        controllerManager.getController().getPaneStage().getChildren().addAll(habitat.getSpaceSize().getImageViewBackground());
    }

   public void disableButtons(){
       if (simulation.getState().isStop()) {
            controllerManager.setButtonState(true, true, false, false);
       }
       if (simulation.getState().isRunning()) {
           controllerManager.setButtonState(false, false, true, true);
       }
       if (simulation.getState().isPause()) {
           controllerManager.setButtonState(false, true, false, true);
       }
    }

    public void update(Time time) {
        habitat.update(time.getSeconds(), controllerManager.getController().getPaneStage());
        setSimulationRunningTime(time);
    }
    private String makeResultLog(){
        return new String(
                "Total Bee: " + Bee.countsAllBees +
                        ";"+ '\n' +"Drone: " + Drone.countDrone +
                        ";"+ '\n' +"Worker: " + Worker.countWorker +
                        ";"+ '\n' +"Time of simulation Min:" + this.timer.getMinutes() + " Sec: " +this.timer.getSeconds() + "MSd:" + timer.getMilliseconds()
        );
    }

}

