package Application;

import java.net.URL;
import java.util.*;

import Objects.Bee.Bee;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import Application.Windows.WindowError;

public class Controller {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainStage;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private TextField fieldTime;

    @FXML
    private TextField timeBornWolf;

    @FXML
    private TextField timeBornLion;

    @FXML
    private Pane mainPane;

    @FXML
    private Button pauseButton;

    @FXML
    private Slider sliderVariationBornLion;

    @FXML
    private Slider sliderVariationBornWolf;

    @FXML
    private CheckBox checkBoxShowDialog;

    @FXML
    private CheckBox checkBoxShowTime;

    @FXML
    private Label labelTextTIMER;

    @FXML
    private TextField timeLifeWolf;

    @FXML
    private TextField timeLifeLion;

    @FXML
    private Button showInformationButton;

    private Boolean showLog = true;

    @FXML
    void initialize(AppManager appManager) {
        try {
            initSliders();
            initCheckBoxes();
            initListeners(appManager);
            showLog = checkBoxShowTime.isSelected();
        }
        catch (Exception e ){
            WindowError windowError = new WindowError(e.toString());
        }
    }

    private void initListeners(AppManager appManager){
        startButton.setOnAction(event ->
        {
            if (checkTextBooxsInputValue())
            {
                try {
                    fieldTime.setText("");
                    appManager.appStart();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        stopButton.setOnAction(event ->
        {
            try {
                appManager.appStop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        pauseButton.setOnAction(event ->
        {
            try {
                appManager.appPause();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        showInformationButton.setOnAction(event -> {
            appManager.showWindowCollectionsInformatos();
        });

        mainStage.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                try {
                    writeKeyCode(event.getCode(),appManager);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        checkBoxShowTime.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue ov, Boolean old_val, Boolean new_val) {
                showTimer();
            }
        });

    }

    private void initCheckBoxes(){
        checkBoxShowTime.setSelected(true);
        checkBoxShowDialog.setSelected(true);
    }

    private void initSliders(){
        sliderVariationBornLion.setMin(0);
        sliderVariationBornLion.setMax(100);
        sliderVariationBornLion.setBlockIncrement(10);
        sliderVariationBornLion.setMajorTickUnit(10);
        sliderVariationBornLion.setShowTickLabels(true);
        sliderVariationBornLion.setShowTickMarks(true);
        sliderVariationBornLion.setValue(70);

        sliderVariationBornWolf.setMin(0);
        sliderVariationBornWolf.setMax(100);
        sliderVariationBornWolf.setBlockIncrement(10);
        sliderVariationBornWolf.setMajorTickUnit(10);
        sliderVariationBornWolf.setShowTickLabels(true);
        sliderVariationBornWolf.setShowTickMarks(true);
        sliderVariationBornWolf.setValue(70);
    }

    private boolean isIntegerTextField(TextField textField){
        try{
            Integer.parseInt(textField.getText());
            return true;
        }
        catch (NumberFormatException e){
            showDialogError(textField);
            return false;
        }
    }

    private void showDialogError(TextField textField) {
        String erroeMessage;
        if(textField.getId() == timeBornWolf.getId()){
            erroeMessage =
                    "Значание в поле: \n"
                            + "\"Время рождения Волка\" \n"
                            + "должно быть не пустим и целочисленным";
            WindowError windowError = new WindowError(erroeMessage);
            textField.requestFocus();
        }
        if(textField.getId() == timeBornLion.getId()){
            erroeMessage =
                    "Значание в поле: \n"
                            + "\"Время рождения Льва\" \n"
                            + "должно быть не пустим и целочисленным";
            textField.requestFocus();
            WindowError windowError = new WindowError(erroeMessage);
        }
    }

    private void writeKeyCode(KeyCode key, AppManager appManager) throws Exception {

        if(key == KeyCode.T) {
            showTimer();
        }
        if (key == KeyCode.B){
            if (checkTextBooxsInputValue() == true)
            {
                try {
                    fieldTime.setText("");
                    appManager.appStart();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (key == KeyCode.B){
            appManager.appStop();
        }
    }

    private void showTimer(){
        if(showLog == false)
        {
            showLog = true;
            fieldTime.setVisible(true);
            labelTextTIMER.setVisible(true);
        }
        else
        {
            showLog = false;
            fieldTime.setVisible(false);
            labelTextTIMER.setVisible(false);
        }
    }

    private Boolean checkTextBooxsInputValue(){
        if( isIntegerTextField(timeBornWolf) &&
                isIntegerTextField(timeBornLion) &&
                isIntegerTextField(timeLifeLion) &&
                isIntegerTextField(timeLifeWolf)
        )
        {
            return true;
        }
        return false;
    }

    public TextField getFieldTime() {
        return fieldTime;
    }
    public Button getStartButton(){return startButton;}
    public Button getStopButton(){return stopButton;}
    public Button getPauseButton(){return pauseButton;}
    public Pane getMainPane() {
        return mainPane;
    }

    public int getValueSliderVariationBornLion(){return (int)sliderVariationBornLion.getValue();
    }

    public int getValueSliderVariationBornWolf() {
        return (int)sliderVariationBornWolf.getValue();
    }

    public int getValueTimeBornWolf() {
        return Integer.parseInt(timeBornWolf.getText());
    }

    public int getValueTimeBornLion() {
        return Integer.parseInt(timeBornLion.getText());
    }

    public int getValuetTimeLifeWorker() {
        return Integer.parseInt(timeLifeWolf.getText());
    }

    public int getValuetTimeLifeDrone() {
        return Integer.parseInt(timeLifeLion.getText());
    }

    public Boolean getValueCheckBoxShowDialog() {
        return checkBoxShowDialog.isSelected();
    }

    public Boolean getValueCheckBoxShowTime() {
        return checkBoxShowTime.isSelected();
    }

    public AnchorPane getMainStage() {
        return mainStage;
    }

    public static class Collections {
        private ArrayList<Bee> arrayList;            //Коллекция для хранения объектов
        private TreeSet<Integer> treeSet;               //Коллекция для хранения и поиска уникальных идентификаторов
        private HashMap<Integer,Integer> hashMap;       //Коллекция для хранения времени рождения объектов

        public Collections(){
            arrayList = new ArrayList<Bee>();
            treeSet = new TreeSet<Integer>();
            hashMap = new HashMap<Integer,Integer>();
        }

        public void add(Bee Animal){
            arrayList.add(Animal);
            treeSet.add(Animal.getIdentifier());
            hashMap.put(Animal.getIdentifier(),Animal.life.getTimeBorn());
        }

        public void delete(Bee Animal){
            arrayList.remove(Animal);
            treeSet.remove(Animal.getIdentifier());
            hashMap.remove(Animal.getIdentifier(),Animal.life.getTimeBorn());
        }

        public  void  updateCollectionsPerTime(Pane pane){
            Iterator<Bee> iteratorUpdate = arrayList.iterator();
            while (iteratorUpdate.hasNext())
            {
                Bee rabbitUpdate = iteratorUpdate.next();
                rabbitUpdate.life.updateTimeLiveAnimals();
            }



            while(checkIsAmyAnimalDead()){
                Bee deleBee = findDeadAnimal();
                delete(deleBee);
                pane.getChildren().remove(deleBee.image.getImageView());
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
                if (element.life.isDead() == false)
                {
                    resultString += String.valueOf(count) + ". " +
                            "Type: "+element.getTypeAnimals() +
                            "; TimeBorn: "+ String.valueOf(element.life.getTimeBorn()) +
                            "; Id: "+ String.valueOf(element.getIdentifier()) + "\n" ;
                }
            }

            return resultString;
        }
    }
}
