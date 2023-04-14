package Application.Controller;

import Application.Controller.FXML.FXMLObjectsGets;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller extends FXMLObjectsGets implements Initializable {
    protected Boolean showLog = true;

    public Boolean getShowLog() {
        return showLog;
    }

    public void setShowLog(Boolean showLog) {
        this.showLog = showLog;
    }

    public void swapTimerShowState(){
        setShowLog(!showLog);
        labelTimer.setVisible(showLog);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initSpinners();
    }

    private void initSpinners() {
        spinnerLifeTimeDrone.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        spinnerSecondsDrone.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        spinnerCoefficientDrone.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        spinnerSecondsWorker.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        spinnerLifeTimeWorker.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        spinnerProbabilityWorker.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
    }


}
