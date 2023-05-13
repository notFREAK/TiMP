package Application.Files;

import Application.AppManager;

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
    void  saveConfig() throws IOException {
        Properties props = new Properties();
        props.setProperty("duration", "60");
        props.setProperty("step", "0.1");
        props.setProperty("initial_population", "100");
        props.setProperty("infectiousness", "0.8");
        props.setProperty("mortality", "0.05");

        FileOutputStream out = new FileOutputStream("config.ini");
        out.close();
    }

    void loadConfig() throws IOException {
        Properties props = new Properties();

        FileInputStream in = new FileInputStream("config.ini");
        props.load(in);
        in.close();

        String duration = props.getProperty("duration");
        String step = props.getProperty("step");
        String initialPopulation = props.getProperty("initial_population");
        String infectiousness = props.getProperty("infectiousness");
        String mortality = props.getProperty("mortality");
    }

    public void createLogs() throws IOException {
        try {
            fw = new FileWriter("logs-" + Calendar.getInstance().toString() + ".txt", true);
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
