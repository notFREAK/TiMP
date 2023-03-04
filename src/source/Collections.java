package source;

import Objects.Bee;
import javafx.scene.layout.Pane;


import java.util.*;

public class Collections {
    private ArrayList<Bee> arrayList;            //Коллекция для хранения объектов
    private TreeSet<Integer> treeSet;               //Коллекция для хранения и поиска уникальных идентификаторов
    private HashMap<Integer,Integer> hashMap;       //Коллекция для хранения времени рождения объектов

    public Collections(){
        this.arrayList = new ArrayList<Bee>();
        this.treeSet = new TreeSet<Integer>();
        this.hashMap = new HashMap<Integer,Integer>();
    }

    public void adds(Bee bee){
        this.arrayList.add(bee);
        this.treeSet.add(bee.getIdentifier());
        this.hashMap.put(bee.getIdentifier(), bee.getTimeBorn());
    }

    public void delete(Bee bee){
        arrayList.remove(bee);
        treeSet.remove(bee.getIdentifier());
        hashMap.remove(bee.getIdentifier(), bee.getTimeBorn());
    }

    public  void  updateCollectionsPerTime(Pane pane){
        Iterator<Bee> iteratorUpdate = arrayList.iterator();
        while (iteratorUpdate.hasNext())
        {
            Bee animalUpdate = iteratorUpdate.next();
            animalUpdate.updaTimeLiveAnimals();
        }



        while(checkIsAmyAnimalDead()){
            Bee deletAnimal = findDeadAnimal();
            delete(deletAnimal);
            pane.getChildren().remove(deletAnimal.getImageView());
        }

    }

    private boolean checkIsAmyAnimalDead(){
        Iterator<Bee> iteratorDelete = arrayList.listIterator();
        while (iteratorDelete.hasNext()) {
            Bee element = iteratorDelete.next();
            if (element.isDead())
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
            if (element.isDead() == true)
            {
                return element;
            }
        }
        return  null; // NEVER RETURN NULL!!
    }

    public void clear(){
        arrayList.clear();
        treeSet.clear();
        hashMap.clear();
    }

    public  String getAliveAnimals(){
        String resultString = new String();
        Iterator<Bee> iteratorDelete = arrayList.listIterator();
        int count = 0;
        while (iteratorDelete.hasNext()) {
            Bee element = iteratorDelete.next();
            count++;
            if (element.isDead() == false)
            {
                resultString += String.valueOf(count) + ". " +
                                "Type: "+element.getTypeAnimals() +
                                "; TimeBorn: "+ String.valueOf(element.getTimeBorn()) +
                                "; Id: "+ String.valueOf(element.getIdentifier()) + "\n" ;
            }
        }

        return resultString;
    }
}
