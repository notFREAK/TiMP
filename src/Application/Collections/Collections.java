package Application.Collections;

import Application.AppManager;
import Application.Manager.ControllerManager;
import Objects.Bee.Bee;
import Objects.Drone.Drone;
import Objects.Worker.Worker;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.util.*;

import static Application.Habitat.HabitatSize.getImageViewBackground;

public class Collections {

    private static final Random random = new Random();
    private Pane MainPane;
    int lastSecondsUpdate = 0;
    private ArrayList<Bee> arrayList;            //Коллекция для хранения объектов
    private HashSet<UUID> hashSet;               //Коллекция для хранения и поиска уникальных идентификаторов
    private TreeMap<UUID,Integer> TreeMap;       //Коллекция для хранения времени рождения объектов

    public void setStage(Pane pane) {
        MainPane = pane;
    }
    public Collections(){
        this.arrayList = new ArrayList<Bee>();
        this.hashSet = new HashSet<UUID>();
        this.TreeMap = new TreeMap<UUID,Integer>();
    }

    public void adds(Bee bee){
        this.arrayList.add(bee);
        this.hashSet.add(bee.getIdentifier());
        this.TreeMap.put(bee.getIdentifier(), bee.life.getTimeBorn());
    }

    public void delete(Bee bee){
       arrayList.remove(bee);
       hashSet.remove(bee.getIdentifier());
       TreeMap.remove(bee.getIdentifier(), bee.life.getTimeBorn());
    }

    public void setArrayList(ArrayList<Bee> arrList) {
        synchronized (arrayList) {
            clear();
            Platform.runLater(() -> {
                MainPane.getChildren().removeAll();
            });
            Iterator<Bee> iterator = arrList.listIterator();
            while (iterator.hasNext()) {
                Bee bee = iterator.next();
                bee.updateGraphic();
                if (bee instanceof Drone) {
                    Drone drone = (Drone) bee;
                    Drone.countDrone++;
                    Bee.countsAllBees++;
                    adds(drone);
                } else if (bee instanceof Worker) {
                    Worker worker = (Worker) bee;
                    Worker.countWorker++;
                    Bee.countsAllBees++;
                    adds(worker);
                }
                Platform.runLater(() -> {
                    MainPane.getChildren().addAll(new Node[]{bee.getGraphic().getImageView()});
                });
            }
        }
    }
    public  void  updateCollectionsPerTime(){
            Iterator<Bee> iteratorUpdate = arrayList.iterator();
            while (iteratorUpdate.hasNext()) {
                Bee animalUpdate = iteratorUpdate.next();
                animalUpdate.life.update();
            }
    }

    public void updateCollectionsForDead(Pane pane) {
        synchronized (arrayList) {
        while (checkIsAmyAnimalDead()) {
            Bee deleteAnimal = findDeadAnimal();
            pane.getChildren().remove(deleteAnimal.getGraphic().getImageView());
            delete(deleteAnimal);
        }
        }
    }

    private boolean checkIsAmyAnimalDead(){
            Iterator<Bee> iteratorDelete = arrayList.listIterator();
            while (iteratorDelete.hasNext()) {
                Bee element = iteratorDelete.next();
                if (element.life.isDead()) {
                    return true;
                }
            }
            return false;
    }

    private Bee findDeadAnimal(){
        Iterator<Bee> iteratorDelete = arrayList.iterator();
        while (iteratorDelete.hasNext()) {
            Bee element = iteratorDelete.next();
            if (element.life.isDead() == true)
            {
                return element;
            }
        }
        return  null;
    }

    private Bee findAnimal(){
        Iterator<Bee> iteratorDelete = arrayList.iterator();
        while (iteratorDelete.hasNext()) {
            Bee element = iteratorDelete.next();
            return element;
        }
        return  null;
    }

    public void clear(){
        synchronized (arrayList) {
            while (!arrayList.isEmpty()) {
                Bee deleteAnimal = findAnimal();
                Platform.runLater(() -> {
                    MainPane.getChildren().remove(deleteAnimal.getGraphic().getImageView());
                });
                delete(deleteAnimal);
            }
        }
    }

    public ArrayList<Bee> getArrayList() {
        return arrayList;
    }

    public  String getAliveAnimals(){
        String resultString = "";
        Iterator<Bee> iteratorDelete = arrayList.listIterator();
        int count = 1;
        while (iteratorDelete.hasNext()) {
            Bee element = iteratorDelete.next();
            if (!element.life.isDead())
            {
                resultString += String.valueOf(count++) + ". " +
                                "Тип: "+element.getTypeBees() +
                                ";\nВремя рождения(сек): "+ String.valueOf(element.life.getTimeBorn()) +
                                ";\nId: "+ String.valueOf(element.getIdentifier()) + "\n" ;
            }
        }
       /* AtomicInteger cnt = new AtomicInteger();
        var s = arrayList.stream().filter(x -> x.life.isDead()).map( element -> cnt.incrementAndGet() + ". " +
                "Тип: "+element.getTypeBees() +
                ";\nВремя рождения(сек): "+ String.valueOf(element.life.getTimeBorn()) +
                ";\nId: "+ String.valueOf(element.getIdentifier())).toList();
        String.join("\n", s);*/
        return resultString;
    }

    public  String getAliveDrone(){
        String resultString = "";
        Iterator<Bee> iteratorDelete = arrayList.listIterator();
        int count = 1;
        while (iteratorDelete.hasNext()) {
            Bee element = iteratorDelete.next();
            if (!element.life.isDead() && element.getTypeBees().equals("bee_drone".toString()))
            {
                resultString += String.valueOf(count++) + ". " +
                        "Тип: "+element.getTypeBees() +
                        ";\nВремя рождения(сек): "+ String.valueOf(element.life.getTimeBorn()) +
                        ";\nId: "+ String.valueOf(element.getIdentifier()) + "\n" ;
            }
        }
       /* AtomicInteger cnt = new AtomicInteger();
        var s = arrayList.stream().filter(x -> x.life.isDead()).map( element -> cnt.incrementAndGet() + ". " +
                "Тип: "+element.getTypeBees() +
                ";\nВремя рождения(сек): "+ String.valueOf(element.life.getTimeBorn()) +
                ";\nId: "+ String.valueOf(element.getIdentifier())).toList();
        String.join("\n", s);*/
        return resultString;
    }

    public  String getAliveWorker(){
        String resultString = "";
        Iterator<Bee> iteratorDelete = arrayList.listIterator();
        int count = 1;
        while (iteratorDelete.hasNext()) {
            Bee element = iteratorDelete.next();
            if (!element.life.isDead() && element.getTypeBees().equals("bee_worker".toString()))
            {
                resultString += String.valueOf(count++) + ". " +
                        "Тип: "+element.getTypeBees() +
                        ";\nВремя рождения(сек): "+ String.valueOf(element.life.getTimeBorn()) +
                        ";\nId: "+ String.valueOf(element.getIdentifier()) + "\n" ;
            }
        }
       /* AtomicInteger cnt = new AtomicInteger();
        var s = arrayList.stream().filter(x -> x.life.isDead()).map( element -> cnt.incrementAndGet() + ". " +
                "Тип: "+element.getTypeBees() +
                ";\nВремя рождения(сек): "+ String.valueOf(element.life.getTimeBorn()) +
                ";\nId: "+ String.valueOf(element.getIdentifier())).toList();
        String.join("\n", s);*/
        return resultString;
    }


    public  String getAliveBees(){
        String resultString = "";
        Iterator<Bee> iteratorDelete = arrayList.listIterator();
        int count = 1;
        while (iteratorDelete.hasNext()) {
            Bee element = iteratorDelete.next();
            if (!element.life.isDead())
            {
                if (resultString != "") {
                    resultString += "#";
                }
                resultString += element.toString();
            }
        }
        return resultString;
    }

    public ArrayList<Bee> getRandomBees(int N){
        synchronized (arrayList) {
            ArrayList<Bee> randomBees = new ArrayList<Bee>();
            if (N > arrayList.size())
                N = arrayList.size();
            for (int i = 0; i < N; i++) {
                int index = (int) (Math.random() * arrayList.size());
                Bee element = arrayList.get(index);
                randomBees.add(element);
                Platform.runLater(() -> {
                    MainPane.getChildren().remove(element.getGraphic().getImageView());
                });
                if (element instanceof Drone) {
                    Drone.countDrone--;
                    Bee.countsAllBees--;
                }
                else if (element instanceof Worker) {
                    Worker.countWorker--;
                    Bee.countsAllBees--;
                }
                delete(element);
            }
            return randomBees;
        }
    }

    public void addBees(ArrayList<Bee> beeArrayList){
        synchronized (arrayList) {
            for (Bee bee : beeArrayList) {
                bee.updateGraphic();
                if (bee instanceof Drone) {
                    Drone drone = (Drone) bee;
                    Drone.countDrone++;
                    Bee.countsAllBees++;
                    adds(drone);
                } else if (bee instanceof Worker) {
                    Worker worker = (Worker) bee;
                    Worker.countWorker++;
                    Bee.countsAllBees++;
                    adds(worker);
                }
                Platform.runLater(() -> {
                    MainPane.getChildren().addAll(new Node[]{bee.getGraphic().getImageView()});
                });
            }
        }
    }
}
