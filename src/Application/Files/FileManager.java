package Application.Files;

import Application.AppManager;
import Application.Collections.Collections;
import Application.Controller.IController;
import Application.Manager.ControllerManager;
import Application.Simulation.StateSimulation;
import Application.Simulation.Value;
import Application.Simulation.State;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
public class FileManager {

    private static class FileManagerHolder {
        public static final FileManager HOLDER_INSTANCE = new FileManager();
    }

    public static FileManager getInstance() {
        return FileManagerHolder.HOLDER_INSTANCE;
    }
    FileWriter fw;
    BufferedWriter bw;
    public void  saveConfig() throws IOException {
        Properties props = new Properties();
        String ObjProp = "NONE";
        switch (AppManager.getInstance().getSimulation().getState().getStateSimulation()) {
            case RUNNING:
                ObjProp = AppManager.getInstance().getHabitat().getCollectionsBees().getAliveBees();
                props.setProperty("APP_STATE", "RUNNING");
                break;
            case PAUSE:
                ObjProp = AppManager.getInstance().getHabitat().getCollectionsBees().getAliveBees();
                props.setProperty("APP_STATE", "PAUSE");
                break;
            default:
                props.setProperty("APP_STATE", "STOP");
                break;
        }
        props.setProperty("TIMER", AppManager.getInstance().getTimer().getTime().getTimeString());
        props.setProperty("COEFFICIENT_DRONE", ((Integer)AppManager.getInstance().getSimulation().getSimulationValue().getValueCoefficientDrone()).toString());
        props.setProperty("PROBABILITY_WORKER", ((Integer)AppManager.getInstance().getSimulation().getSimulationValue().getValueProbabilityWorker()).toString());
        props.setProperty("SECONDS_DRONE", ((Integer)AppManager.getInstance().getSimulation().getSimulationValue().getValueSecondsDrone()).toString());
        props.setProperty("SECONDS_WORKER", ((Integer)AppManager.getInstance().getSimulation().getSimulationValue().getValueSecondsWorker()).toString());
        props.setProperty("LIFE_TIME_DRONE", ((Integer)AppManager.getInstance().getSimulation().getSimulationValue().getValueLifeTimeDrone()).toString());
        props.setProperty("LIFE_TIME_WORKER", ((Integer)AppManager.getInstance().getSimulation().getSimulationValue().getValueLifeTimeWorker()).toString());
        props.setProperty("CURRENT_OBJECTS", ObjProp);
        FileOutputStream out = new FileOutputStream("config.ini");
        props.store(out, null);
        out.close();
    }

    public void loadConfig() throws IOException {
        Properties props = new Properties();
        FileInputStream in = new FileInputStream("config.ini");
        props.load(in);
        in.close();
        String appState = props.getProperty("APP_STATE");
        switch (appState) {
            case "STOP":
                AppManager.getInstance().appState(StateSimulation.STOP);
                break;
            default:
                AppManager.getInstance().appState(StateSimulation.PAUSE);
                break;
        }
        //AppManager.getInstance().getTimer().setTimeS(props.getProperty("TIMER"));
        Value stat = new Value();
        stat.setValueCoefficientDrone(Integer.parseInt(props.getProperty("COEFFICIENT_DRONE")));
        stat.setValueProbabilityWorker(Integer.parseInt(props.getProperty("PROBABILITY_WORKER")));
        stat.setValueSecondsDrone(Integer.parseInt(props.getProperty("SECONDS_DRONE")));
        stat.setValueSecondsWorker(Integer.parseInt(props.getProperty("SECONDS_WORKER")));
        stat.setValueLifeTimeWorker(Integer.parseInt(props.getProperty("LIFE_TIME_DRONE")));
        stat.setValueLifeTimeDrone(Integer.parseInt(props.getProperty("LIFE_TIME_WORKER")));
        AppManager.getInstance().getHabitat().setSimulationValue(stat);
        if (ControllerManager.getInstance().checkIsType(IController.ControllerType.MAIN)) {
            ControllerManager.getInstance().getController(IController.ControllerType.MAIN).setValue(stat);
        }
        String currentObjects = props.getProperty("CURRENT_OBJECTS").toString();

    }

    public void createLogs() throws IOException {
        try {
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            Date now = new Date();
            String strDate = sdfDate.format(now);
            fw = new FileWriter("logs-" + strDate + ".txt", true);
            bw = new BufferedWriter(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveLogs(String line) {
        try {
            SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
            Date now = new Date();
            String strDate = sdfDate.format(now);
            bw.write("[" + strDate + "]" + line);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeLogs() {
        try {
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
