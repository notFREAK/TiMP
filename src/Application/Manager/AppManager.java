package Application.Manager;

import Application.Habitat.Habitat;
import Objects.Bee.Bee;
import Objects.Drone.Drone;
import Objects.Worker.Worker;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import Application.Controller.Controller;

import java.io.File;
import java.io.IOException;

public class AppManager extends Application {
    private int seconds = 0;
    private int minutes = 0;
    private int speedSimulation = 700;
    private Thread thread;
    public static final int  RUNNING = 1;
    public static final int  PAUSE = 2;
    public static final int  STOP = 3;

    private int stateSimulation = -1;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("Application.fxml"));
        thread = new Thread(runnable);
        habitat = new Habitat();
        Parent root = mainLoader.load();
        Scene scene = new Scene(root);
        String path = "C:\\MyProjects\\TiMP\\src\\Pic\\Theme_MED.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        stage.setTitle("Playing Audio");
        stage.show();
        stage.setTitle("TiMP");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
       // disableButtons(stateSimulation);
    }

    private Habitat habitat;
    private Controller controller;

    private Runnable runnable = new Runnable()
    {
        @Override
        public  void run()
        {
            if (stateSimulation == RUNNING)
            {
                while (true) {
                    try {
                        Thread.sleep(speedSimulation);
                        seconds++;
                        if (seconds == 60) {
                            minutes++;
                            seconds = 0;
                        }

                        // Use runLater to update object PANE
                        Platform.runLater(new Runnable(){
                            @Override
                            public synchronized void run() {
                                updateAppPerSecond();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    };



    private void updateAppPerSecond(){
        controller.getTextTimer().setText(minutes + ":" +seconds);
        habitat.update(seconds,controller.getPaneStage());
    }

    public void appStart() {
        setConditionsBornAndDeadRabbit();
        switch (stateSimulation){
            case PAUSE:{
                stateSimulation = RUNNING;
                this.thread.start();
            } break;
            case STOP:{
                this.seconds = 0;
                this.minutes = 0;
                stateSimulation = RUNNING;
                controller.getPaneStage().getChildren().addAll(habitat.SpaceSize.getImageViewBackground());
                this.thread.start();
            } break;
            default: {
                stateSimulation = RUNNING;
                this.thread.start();
                this.seconds = 0;
                this.minutes = 0;
            }
        }
        disableButtons(stateSimulation);
    }

    private void setConditionsBornAndDeadRabbit(){
        int N1 = controller.getValueSpinnerSecondsWorker();
        int P1 = controller.getValueSpinnerProbabilityWorker();

        int N2 = controller.getValueSpinnerSecondsDrone();
        int K2 = controller.getValueSpinnerCoefficientDrone();

        int timeLifeDrone = controller.getValueSpinnerLifeTimeDrone();
        int timeLifeWorker = controller.getValueSpinnerLifeTimeWorker();

        habitat.setConditionsBornBees(N1,P1,N2,K2);
        habitat.setConditionsTimeLifeBees(timeLifeDrone,timeLifeWorker);
    }

    public void appPause(){
        if (stateSimulation == RUNNING)
        {
            stateSimulation = PAUSE;
            this.thread.interrupt();
        }
        disableButtons(stateSimulation);
    }

    public void appStop() {
        if (stateSimulation == RUNNING || stateSimulation == PAUSE )
        {
            stateSimulation = STOP;
            thread.interrupt();
            removeAllHabitat();
            disableButtons(stateSimulation);
        }
    }

    public void disableButtons(int stateTimer){
        switch (stateTimer) {
            case RUNNING: {
                controller.getButtonStop().setDisable(true);
                controller.getButtonPause().setDisable(false);
                controller.getButtonStart().setDisable(false);
            }
            break;
            case PAUSE: {
                controller.getButtonStop().setDisable(false);
                controller.getButtonPause().setDisable(true);
                controller.getButtonStart().setDisable(false);
            }
            break;
            case STOP: {
                controller.getButtonStop().setDisable(false);
                controller.getButtonPause().setDisable(true);
                controller.getButtonStart().setDisable(true);
            }
            break;
            default:
                controller.getButtonStop().setDisable(false);
                controller.getButtonPause().setDisable(true);
                controller.getButtonStart().setDisable(true);
        }
    }

    public void removeAllHabitat(){
        habitat.clear();
        controller.getPaneStage().getChildren().addAll(habitat.SpaceSize.getImageViewBackground());
    }

    private String makeResultLog(){
        return new String(
                "Total Bee: " + Bee.countsAllBees +
                        ";"+ '\n' +"Drone: " + Drone.countDrone +
                        ";"+ '\n' +"Worker: " + Worker.countWorker +
                        ";"+ '\n' +"Time of simulation Min:" + this.minutes + " Sec: " +this.seconds
        );
    }

   /* public void showWindowCollectionsInformation(){
        String message = habitat.getInfoAliveAnimals();
        WindowInformation windows = new WindowInformation(
                "Information about collections",
                700,
                700,
                message,
                this);
        stateSimulation = PAUSE;
        thread.interrupt();
    }
*/
    public int getStateOfTimer() {
        return stateSimulation;
    }
    public void setStateOfTimer(int stateOfSimulation) {
        this.stateSimulation = stateOfSimulation;
    }
}

