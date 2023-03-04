package Application;

import Application.Windows.WindowInformation;
import Objects.Bee;
import Objects.Drone;
import Objects.Worker;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Manager extends Application {

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
    public void start(Stage stage) throws Exception{
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        thread = new Thread(runnable);
        habitat = new Habitat();
        initPrimaryStageAndController(stage,mainLoader);
        disableButtons(stateSimulation);
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
        controller.getFieldTime().setText(minutes + ":" +seconds);
        habitat.update(seconds,controller.getMainPane());
    }

    private void initPrimaryStageAndController(Stage stage,FXMLLoader mainLoader) throws IOException {
        Parent root = mainLoader.load();
        this.controller = (Controller)mainLoader.getController();
        controller.initialize(this);
        controller.getMainPane().getChildren().addAll(habitat.getImageViewBackground());

        double width = controller.getMainStage().getPrefWidth();
        double height = controller.getMainStage().getPrefHeight();

        Scene scene = new Scene(root,width,height);
        String path = "test.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        stage.setTitle("Playing Audio");
        stage.show();
        stage.setTitle("Lab 3");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
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
                controller.getMainPane().getChildren().addAll(habitat.getImageViewBackground());
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
        int N1 = controller.getValueTimeBornWolf();
        int P1 = controller.getValueSliderVariationBornWolf();

        int N2 = controller.getValueTimeBornLion();
        int K2 = controller.getValueSliderVariationBornLion();

        int timeLifeLion = controller.getValuetTimeLifeLion();
        int timeLifeWolf = controller.getValuetTimeLifeWolf();

        habitat.setConditionsBornAnimals(N1,P1,N2,K2);
        habitat.setConditionsTimeLifeAnimals(timeLifeLion,timeLifeWolf);
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
        if(controller.getValueCheckBoxShowDialog() == true) {
            WindowInformation windows = new WindowInformation("Modal Window", makeResultLog(), this);
            stateSimulation = PAUSE;
            thread.interrupt();
        }
        else
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
                controller.getStartButton().setDisable(true);
                controller.getPauseButton().setDisable(false);
                controller.getStopButton().setDisable(false);
            }
            break;
            case PAUSE: {
                controller.getStartButton().setDisable(false);
                controller.getPauseButton().setDisable(true);
                controller.getStopButton().setDisable(false);
            }
            break;
            case STOP: {
                controller.getStartButton().setDisable(false);
                controller.getPauseButton().setDisable(true);
                controller.getStopButton().setDisable(true);
            }
            break;
            default:
                controller.getStartButton().setDisable(false);
                controller.getPauseButton().setDisable(true);
                controller.getStopButton().setDisable(true);
        }
    }

    public void removeAllHabitat(){
        habitat.clear();
        controller.getMainPane().getChildren().addAll(habitat.getImageViewBackground());
    }

    private String makeResultLog(){
        return new String(
                "Total Bee: " + Bee.countsAllBees +
                        ";"+ '\n' +"Drone: " + Drone.countDrone +
                        ";"+ '\n' +"Worker: " + Worker.countWorker +
                        ";"+ '\n' +"Time of simulation Min:" + this.minutes + " Sec: " +this.seconds
        );
    }

    public void showWindowCollectionsInformatos(){
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

    public int getStateOfTimer() {
        return stateSimulation;
    }
    public void setStateOfTimer(int stateOfSimulation) {
        this.stateSimulation = stateOfSimulation;
    }
}

