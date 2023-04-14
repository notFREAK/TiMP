package Application.Controller.FXML;

import Application.TImer.Time;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class FXMLObjectsGets extends FXMLObjects {
    public Label getLabelTimer() {
        return labelTimer;
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

    public Spinner<Integer> getSpinnerSecondsWorker() {
        return spinnerSecondsWorker;
    }

    public Spinner<Integer> getSpinnerSecondsDrone() {
        return spinnerSecondsDrone;
    }

    public Spinner<Integer> getSpinnerProbabilityWorker() {
        return spinnerProbabilityWorker;
    }

    public Spinner<Integer> getSpinnerLifeTimeWorker() {
        return spinnerLifeTimeWorker;
    }

    public Spinner<Integer> getSpinnerLifeTimeDrone() {
        return spinnerLifeTimeDrone;
    }

    public Label getTextLifeTimeWorker() {
        return textLifeTimeWorker;
    }

    public Label getTextLifeTimeDrone() {
        return textLifeTimeDrone;
    }

    public Label getTextCoefficientDrone() {
        return textCoefficientDrone;
    }


    public Label getTextProbabilityWorker() {
        return textProbabilityWorker;
    }

    public Label getTextSecondsDrone() {
        return textSecondsDrone;
    }

    public Label getTextSecondsWorker() {
        return textSecondsWorker;
    }

    public RadioButton getRadioButtonInformation() {
        return radioButtonInformation;
    }

    public RadioButton getRadioButtonTimer() {
        return radioButtonTimer;
    }

    public Spinner<Integer> getSpinnerCoefficientDrone() {
        return spinnerCoefficientDrone;
    }

    public void setButtonPause(Button buttonPause) {
        this.buttonPause = buttonPause;
    }

    public void setButtonStart(Button buttonStart) {
        this.buttonStart = buttonStart;
    }

    public void setButtonStop(Button buttonStop) {
        this.buttonStop = buttonStop;
    }

    public void setLabelTimer(Label labelTimer) {
        this.labelTimer = labelTimer;
    }

    public void setPaneMain(AnchorPane paneMain) {
        this.paneMain = paneMain;
    }

    public void setPaneMenu(AnchorPane paneMenu) {
        this.paneMenu = paneMenu;
    }

    public void setPaneStage(Pane paneStage) {
        this.paneStage = paneStage;
    }

    public void setRadioButtonInformation(RadioButton radioButtonInformation) {
        this.radioButtonInformation = radioButtonInformation;
    }

    public void setRadioButtonTimer(RadioButton radioButtonTimer) {
        this.radioButtonTimer = radioButtonTimer;
    }
    public void setSpinnerCoefficientDrone(Spinner<Integer> spinnerCoefficientDrone) {
        this.spinnerCoefficientDrone = spinnerCoefficientDrone;
    }

    public void setSpinnerLifeTimeDrone(Spinner<Integer> spinnerLifeTimeDrone) {
        this.spinnerLifeTimeDrone = spinnerLifeTimeDrone;
    }

    public void setSpinnerLifeTimeWorker(Spinner<Integer> spinnerLifeTimeWorker) {
        this.spinnerLifeTimeWorker = spinnerLifeTimeWorker;
    }

    public void setSpinnerProbabilityWorker(Spinner<Integer> spinnerProbabilityWorker) {
        this.spinnerProbabilityWorker = spinnerProbabilityWorker;
    }

    public void setSpinnerSecondsDrone(Spinner<Integer> spinnerSecondsDrone) {
        this.spinnerSecondsDrone = spinnerSecondsDrone;
    }

    public void setSpinnerSecondsWorker(Spinner<Integer> spinnerSecondsWorker) {
        this.spinnerSecondsWorker = spinnerSecondsWorker;
    }

    public void setTextCoefficientDrone(Label textCoefficientDrone) {
        this.textCoefficientDrone = textCoefficientDrone;
    }

    public void setTextLifeTimeDrone(Label textLifeTimeDrone) {
        this.textLifeTimeDrone = textLifeTimeDrone;
    }

    public void setTextLifeTimeWorker(Label textLifeTimeWorker) {
        this.textLifeTimeWorker = textLifeTimeWorker;
    }

    public void setTextProbabilityWorker(Label textProbabilityWorker) {
        this.textProbabilityWorker = textProbabilityWorker;
    }

    public void setTextSecondsDrone(Label textSecondsDrone) {
        this.textSecondsDrone = textSecondsDrone;
    }

    public void setTextSecondsWorker(Label textSecondsWorker) {
        this.textSecondsWorker = textSecondsWorker;
    }

    public void setLabelTimerValue(Time time) {
        labelTimer.setText(new String(time.getTimeString()));
    }
}
