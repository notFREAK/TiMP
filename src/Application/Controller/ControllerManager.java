package Application.Controller;

import Application.Manager.AppManager;
import Application.Windows.WindowError;
import javafx.event.EventHandler;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControllerManager {

    ControllerManager() {

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
