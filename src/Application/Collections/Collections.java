package Application.Collections;

import Objects.Bee.Bee;
import javafx.scene.layout.Pane;


import java.util.*;

public class Collections {
    int lastSecondsUpdate = 0;
    private ArrayList<Bee> arrayList;            //Коллекция для хранения объектов
    private HashSet<UUID> hashSet;               //Коллекция для хранения и поиска уникальных идентификаторов
    private TreeMap<UUID,Integer> TreeMap;       //Коллекция для хранения времени рождения объектов

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

    public  void  updateCollectionsPerTime(){
            Iterator<Bee> iteratorUpdate = arrayList.iterator();
            while (iteratorUpdate.hasNext()) {
                Bee animalUpdate = iteratorUpdate.next();
                animalUpdate.life.update();
            }
    }

    public void updateCollectionsForDead(Pane pane) {
        while (checkIsAmyAnimalDead()) {
            Bee deleteAnimal = findDeadAnimal();
            pane.getChildren().remove(deleteAnimal.getGraphic().getImageView());
            delete(deleteAnimal);
        }
    }

    private boolean checkIsAmyAnimalDead(){
        Iterator<Bee> iteratorDelete = arrayList.listIterator();
        while (iteratorDelete.hasNext()) {
            Bee element = iteratorDelete.next();
            if (element.life.isDead())
            {
                return true;
            }
        }
        return  false;
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

    public void clear(){
        arrayList.clear();
        hashSet.clear();
        TreeMap.clear();
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
}
