package Application;

import Application.Controller.IController;
import Application.Files.FileManager;
import Application.Manager.ControllerManager;
import Application.Habitat.Habitat;
import Application.Simulation.Simulation;
import Application.Simulation.StateSimulation;
import Application.Simulation.Value;
import Application.TImer.PausableTask;
import Application.TImer.Time;
import Application.TImer.Timer;
import Objects.Bee.Bee;
import Objects.Coordinates.Vector.Cartesian;
import Objects.Coordinates.Vector.Polar;
import Objects.Drone.Drone;
import Objects.Worker.Worker;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

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

    private static ClientManager clientManager;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Font.loadFont(getClass().getResourceAsStream("/resourses/Font/smeshariki.ttf"), 12);
        mainStage = stage;
        timer = new Timer(this);
        simulation = new Simulation();
        habitat = new Habitat();
        ControllerManager.getInstance().ControllerCreate(IController.ControllerType.MAIN);
        clientManager = new ClientManager();
    }

    public Stage getStage() {
        return mainStage;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public ClientManager getClientManager() { return clientManager;}

    public Simulation getSimulation() {
        return simulation;
    }

    public Timer getTimer() {
        return timer;
    }


    public void logsPrint(String string) {
        FileManager.getInstance().saveLogs(string + '\n');
        if (ControllerManager.getInstance().checkIsType(IController.ControllerType.CONSOLE)) {
            ControllerManager.getInstance().getController(IController.ControllerType.CONSOLE).appendText(string + '\n');
        }
    }


    public void appState(StateSimulation state) {
        switch (state) {
            case RUNNING:
                if (!habitat.AI)
                    habitat.StartAi();
                simulation.getState().setRunning();
                timer.Play();
                break;
            case PAUSE:
                if (habitat.AI)
                    habitat.StopAi();
                simulation.getState().setPause();
                timer.Pause();
                break;
            case STOP:
                if (habitat.AI)
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


    public void removeAllHabitat() {
        habitat.clear();
    }

    public String makeResultLog() {
        return new String(
                "Количество пчёл: " + Bee.countsAllBees +
                        ";" + '\n' + "   Буржуев: " + Drone.countDrone +
                        ";" + '\n' + "   Трунтней: " + Worker.countWorker +
                        ";" + '\n' + "Время симмуляции: \nМин:" + this.timer.getMinutes() + " Сек:" + this.timer.getSeconds() + " Мс:" + timer.getMilliseconds()
        );
    }




    public void update(Time time) {
        ControllerManager.getInstance().ControllerUpdate(timer);

    }
}



