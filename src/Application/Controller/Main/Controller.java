package Application.Controller.Main;

import Application.Controller.Main.FXML.ApplicationFXMLObjectsGets;
import Application.Controller.Music.Music;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller extends ApplicationFXMLObjectsGets implements Initializable {
    protected Boolean showLog = true;

    public Boolean getShowLog() {
        return showLog;
    }

    public void setShowLog(Boolean showLog) {
        this.showLog = showLog;
    }

    protected Boolean showTime = true;

    public Boolean getShowTime() {
        return showTime;
    }


    public void setShowTime(Boolean showTime) {
        this.showTime = showTime;
    }

    public void swapTimerShowState(){
        setShowTime(!showTime);
        labelTimer.setVisible(showTime);
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
    public void setButtonState(boolean ButtonStopDisableState, boolean ButtonPauseDisableState, boolean ButtonStartDisableState, boolean SpinnersState) {
        buttonStop.setDisable(ButtonStopDisableState);
        buttonPause.setDisable(ButtonPauseDisableState);
        buttonStart.setDisable(ButtonStartDisableState);
        spinnerSecondsWorker.setDisable(SpinnersState);
        spinnerCoefficientDrone.setDisable(SpinnersState);
        spinnerLifeTimeDrone.setDisable(SpinnersState);
        spinnerProbabilityWorker.setDisable(SpinnersState);
        spinnerSecondsDrone.setDisable(SpinnersState);
        spinnerLifeTimeWorker.setDisable(SpinnersState);
    }
}
