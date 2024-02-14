package Application;

import Application.TImer.PausableTask;
import Objects.Bee.Bee;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable{

    ClientManager() {
        new Thread(this).start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                command = "disconnect";
            }
        });
    }
    private Thread thread;
    private Socket server = null;
    private int id = 0;
    private int idFor;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private String command;
    private boolean isConnected = false;

    public ArrayList clientIds;
    public int N;
    public ArrayList getClientIds() {
        return clientIds;
    }

    public void setIdFor(int id) {
        idFor = id;
    }

    public void setCommand(String cmd) {
        command = cmd;
    }

    public void updateOtherIDs() {
        try {
            clientIds = (ArrayList) ois.readObject();
            if (id == 0)
                id = ois.readInt();
            for (int i = 0; i < clientIds.size(); ++i) {
                if (clientIds.get(i).equals(id)) {
                    clientIds.remove(i);
                    break;
                }
            }
            AppManager.getInstance().update(AppManager.getInstance().getTimer().getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        connectToServer();
        if (isConnected) {
            try {
                updateOtherIDs();
                while (isConnected) {
                    if (ois.available() == 0) {
                        if (command != null) {
                            switch (command) {
                                case "package":
                                    System.out.println(idFor);
                                    oos.writeUTF("package");
                                    oos.writeInt(idFor);
                                    oos.writeObject(AppManager.getInstance().getHabitat().getCollectionsBees().getRandomBees(N));
                                    oos.flush();
                                    break;
                                case "disconnect":
                                    oos.writeUTF("disconnect");
                                    oos.flush();
                                    break;
                                default:
                                    break;
                            }

                            command = null;
                        }
                    } else {
                        String commandServer = ois.readUTF();
                        System.out.println("Command " + commandServer);
                        switch (commandServer) {
                            case "package":
                                ArrayList<Bee> bee = (ArrayList) ois.readObject();
                                AppManager.getInstance().getHabitat().getCollectionsBees().addBees(bee);
                                break;
                            case "otherClients":
                                updateOtherIDs();
                                break;
                            default:
                                break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    ois.close();
                    oos.close();
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public void disconnect() {
        command = "disconnect";
    }

    public synchronized void connectToServer() {
        int port = 4004;
        try {
            server = new Socket("127.0.0.1", port);
            isConnected = true;
            oos = new ObjectOutputStream(server.getOutputStream());
            ois = new ObjectInputStream(server.getInputStream());
        }
        catch(Exception e) {
            System.out.println("Connection Refused Exception as the server state off : " + e.getMessage());
        }
    }

}
