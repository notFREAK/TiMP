package Application.Controller;

import java.net.URL;
import java.util.*;

import Application.Manager.AppManager;
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
    private AnchorPane paneMain;

    @FXML
    private AnchorPane paneMenu;

    @FXML
    private Pane paneStage;
    @FXML
    private Button buttonStart;

    @FXML
    private Button buttonPause;

    @FXML
    private Button buttonStop;

    @FXML
    private Label textSecondsDrone;

    @FXML
    private Label textLifeTimeDrone;

    @FXML
    private Label textCoefficientDrone;

    @FXML
    private Label textSecondsWorker;

    @FXML
    private Label textLifeTimeWorker;

    @FXML
    private Label textProbabilityWorker;

    @FXML
    private Spinner<Integer>  spinnerSecondsDrone;

    @FXML
    private Spinner<Integer>  spinnerLifeTimeDrone;

    @FXML
    private Spinner<Integer> spinnerCoefficientDrone;

    @FXML
    private Spinner<Integer>  spinnerSecondsWorker;

    @FXML
    private Spinner<Integer>  spinnerLifeTimeWorker;

    @FXML
    private Spinner<Integer>  spinnerProbabilityWorker;
    @FXML
    private Label textTimer;

    private Boolean showLog = true;

    public Label getTextTimer() {
        return textTimer;
    }

    public AnchorPane getPaneMain() {
        return paneMain;
    }

    public AnchorPane getPaneMenu() {
        return paneMenu;
    }

    public Button getButtonPause() {
        return buttonPause;
    }

    public Button getButtonStart() {
        return buttonStart;
    }

    public Button getButtonStop() {
        return buttonStop;
    }

    public Pane getPaneStage() {
        return paneStage;
    }

    public int getValueSpinnerCoefficientDrone() {
        return spinnerCoefficientDrone.getValue();
    }

    public int getValueSpinnerLifeTimeDrone() {
        return spinnerLifeTimeDrone.getValue();
    }

    public int getValueSpinnerSecondsDrone() {
        return spinnerSecondsDrone.getValue();
    }

    public int getValueSpinnerLifeTimeWorker() {
        return spinnerLifeTimeWorker.getValue();
    }

    public int getValueSpinnerSecondsWorker() {
        return spinnerSecondsWorker.getValue();
    }

    public int getValueSpinnerProbabilityWorker() {
        return spinnerProbabilityWorker.getValue();
    }

    @FXML
    public void initialize(AppManager appManager) {
        try {
            initSpinners();
            initListeners(appManager);
        }
        catch (Exception e ){
            WindowError windowError = new WindowError(e.toString());
        }
    }

    private void initListeners(AppManager appManager){
        buttonStart.setOnAction(event ->
        {
                try {
                    appManager.appStart();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        });

        buttonStop.setOnAction(event ->
        {
            try {
                appManager.appStop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonPause.setOnAction(event ->
        {
            try {
                appManager.appPause();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        paneMain.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                try {
                    writeKeyCode(event.getCode(),appManager);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
        if(textField.getId() == textLifeTimeDrone.getId()){
            erroeMessage =
                    "Значание в поле: \n"
                            + "\"Время рождения Волка\" \n"
                            + "должно быть не пустим и целочисленным";
            WindowError windowError = new WindowError(erroeMessage);
            textField.requestFocus();
        }
        if(textField.getId() == textLifeTimeWorker.getId()){
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
                try {
                    textTimer.setText("");
                    appManager.appStart();
                } catch (Exception e) {
                    e.printStackTrace();
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
            textTimer.setVisible(true);
        }
        else
        {
            showLog = false;
            textTimer.setVisible(false);
        }
    }

    private void initSpinners(){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        spinnerLifeTimeDrone.setValueFactory(valueFactory);
        spinnerSecondsDrone.setValueFactory(valueFactory);
        spinnerLifeTimeDrone.setValueFactory(valueFactory);
        spinnerCoefficientDrone.setValueFactory(valueFactory);
        spinnerSecondsWorker.setValueFactory(valueFactory);
        spinnerLifeTimeWorker.setValueFactory(valueFactory);
        spinnerProbabilityWorker.setValueFactory(valueFactory);
    }
}
