package Application.Files;

import Application.AppManager;
import Application.Collections.Collections;
import Application.Controller.IController;
import Application.Habitat.Habitat;
import Application.Manager.ControllerManager;
import Application.Simulation.StateSimulation;
import Application.Simulation.Value;
import Application.Simulation.State;
import Objects.Bee.Bee;
import Objects.Drone.Drone;
import Objects.Worker.Worker;
import javafx.concurrent.WorkerStateEvent;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

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
                ObjProp = "ObjectList.ini";
                props.setProperty("APP_STATE", "RUNNING");
                break;
            case PAUSE:
                ObjProp = "ObjectList.ini";
                props.setProperty("APP_STATE", "PAUSE");
                break;
            default:
                props.setProperty("APP_STATE", "STOP");
                break;
        }
        props.setProperty("TIMER", ((Long)AppManager.getInstance().getTimer().getTime().getTimeLong()).toString());
        props.setProperty("COEFFICIENT_DRONE", ((Integer)AppManager.getInstance().getSimulation().getSimulationValue().getValueCoefficientDrone()).toString());
        props.setProperty("PROBABILITY_WORKER", ((Integer)AppManager.getInstance().getSimulation().getSimulationValue().getValueProbabilityWorker()).toString());
        props.setProperty("SECONDS_DRONE", ((Integer)AppManager.getInstance().getSimulation().getSimulationValue().getValueSecondsDrone()).toString());
        props.setProperty("SECONDS_WORKER", ((Integer)AppManager.getInstance().getSimulation().getSimulationValue().getValueSecondsWorker()).toString());
        props.setProperty("LIFE_TIME_DRONE", ((Integer)AppManager.getInstance().getSimulation().getSimulationValue().getValueLifeTimeDrone()).toString());
        props.setProperty("LIFE_TIME_WORKER", ((Integer)AppManager.getInstance().getSimulation().getSimulationValue().getValueLifeTimeWorker()).toString());
        props.setProperty("OBJECTS_LIST_FILE", ObjProp);
        createObjLogs(ObjProp);
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
        Value stat = new Value();
        switch (appState) {
            case "STOP":
                AppManager.getInstance().appState(StateSimulation.STOP);
                break;
            default:
                AppManager.getInstance().getTimer().setTimeLong(Long.parseLong(props.getProperty("TIMER")));
                AppManager.getInstance().appState(StateSimulation.PAUSE);
                AppManager.getInstance().getHabitat().getCollectionsBees().setArrayList(readObjLogs(props.getProperty("OBJECTS_LIST_FILE")));
                break;
        }
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
    }

    public void createObjLogs(String FileName) throws IOException {
        try {
            FileOutputStream outputStream = new FileOutputStream(FileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(AppManager.getInstance().getHabitat().getCollectionsBees().getArrayList());
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Bee> readObjLogs(String FileName) throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(FileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Drone> arrayListDrone = new ArrayList<Drone>();
            ArrayList<Worker> arrayListWorker = new ArrayList<Worker>();
            ArrayList<Bee> savedGameObj = (ArrayList<Bee>) objectInputStream.readObject();
            objectInputStream.close();
            return savedGameObj;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
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
