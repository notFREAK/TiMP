package Application;

import Application.Controller.IController;
import Application.Manager.ControllerManager;
import Application.Habitat.Habitat;
import Application.Simulation.Simulation;
import Application.Simulation.StateSimulation;
import Application.TImer.Time;
import Application.TImer.Timer;
import Objects.Bee.Bee;
import Objects.Drone.Drone;
import Objects.Worker.Worker;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AppManager extends Application {
    private static class AppManagerHolder {
        public static final AppManager HOLDER_INSTANCE = new AppManager();
    }

    public static AppManager getInstance() {
        return AppManager.AppManagerHolder.HOLDER_INSTANCE;
    }
    private static Stage mainStage;
    private static Habitat habitat;
    private static Simulation simulation;
    private static Timer timer;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception{
        Font.loadFont(getClass().getResourceAsStream("/resourses/Font/smeshariki.ttf"), 12);
        mainStage = stage;
        timer = new Timer(this);
        habitat = new Habitat();
        simulation = new Simulation();
        ControllerManager.getInstance().ControllerCreate(IController.ControllerType.MAIN);
    }

    public Stage getStage() {
        return mainStage;
    }
    public Habitat getHabitat() {
        return habitat;
    }

    public Simulation getSimulation() {
        return simulation;
    }
    public Timer getTimer() {
        return timer;
    }


    public void appState(StateSimulation state) {
        switch (state) {
            case RUNNING:
                habitat.StartAi();
                simulation.getState().setRunning();
                timer.Play();
                break;
            case PAUSE:
                habitat.StopAi();
                simulation.getState().setPause();
                timer.Pause();
                break;
            case STOP:
                habitat.StopAi();
                simulation.getState().setStop();
                timer.Stop();
                removeAllHabitat();
                break;
            default:
                break;
        }
        ControllerManager.getInstance().ControllerStateSwap();
    }


    public void removeAllHabitat(){
        habitat.clear();
    }



    public void update(Time time) {
        ControllerManager.getInstance().ControllerUpdate(timer);
    }
    public String makeResultLog(){
        return new String(
                "Количество пчёл: " + Bee.countsAllBees +
                        ";"+ '\n' +"   Буржуев: " + Drone.countDrone +
                        ";"+ '\n' +"   Трунтней: " + Worker.countWorker +
                        ";"+ '\n' +"Время симмуляции: \nМин:" + this.timer.getMinutes() + " Сек:" +this.timer.getSeconds() + " Мс:" + timer.getMilliseconds()
        );
    }


}

