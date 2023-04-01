package Application.Collections;

import Objects.Bee.Bee;
import javafx.scene.layout.Pane;


import java.util.*;

public class Collections {
    /*private ArrayList<Bee> arrayList;            //Коллекция для хранения объектов
    private HashSet<Integer> hashSet;               //Коллекция для хранения и поиска уникальных идентификаторов
    private TreeMap<Integer,Integer> TreeMap;       //Коллекция для хранения времени рождения объектов

    public Collections(){
        this.arrayList = new ArrayList<Bee>();
        this.hashSet = new HashMap<Integer>();
        this.TreeMap = new TreeSet<Integer,Integer>();
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

    public  void  updateCollectionsPerTime(Pane pane){
        Iterator<Bee> iteratorUpdate = arrayList.iterator();
        while (iteratorUpdate.hasNext())
        {
            Bee animalUpdate = iteratorUpdate.next();
            animalUpdate.life.updateTimeLiveAnimals();
        }



        while(checkIsAmyAnimalDead()){
            Bee deletAnimal = findDeadAnimal();
            //delete(deletAnimal);
            pane.getChildren().remove(deletAnimal.image.getImageView());
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
        return  null; // NEVER RETURN NULL!!
    }

    public void clear(){
        arrayList.clear();
        hashSet.clear();
        TreeMap.clear();
    }

    public  String getAliveAnimals(){
        String resultString = new String();
        Iterator<Bee> iteratorDelete = arrayList.listIterator();
        int count = 0;
        while (iteratorDelete.hasNext()) {
            Bee element = iteratorDelete.next();
            count++;
            if (element.life.isDead() == false)
            {
                resultString += String.valueOf(count) + ". " +
                                "Type: "+element.getTypeAnimals() +
                                "; TimeBorn: "+ String.valueOf(element.life.getTimeBorn()) +
                                "; Id: "+ String.valueOf(element.getIdentifier()) + "\n" ;
            }
        }
        return resultString;
    }*/
}
