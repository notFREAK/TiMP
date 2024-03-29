package Application.Controller.Main.FXML;

import Application.TImer.Time;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ApplicationFXMLObjectsGets extends ApplicationFXMLObjects {
    public Label getLabelTimer() {
        return labelTimer;
    }

    public AnchorPane getPaneMain() {
        return paneMain;
    }

    public AnchorPane getPaneMenu() {
        return paneMenu;
    }

    public MenuItem getMenuItemSimulationStart() {
        return menuItemSimulationStart;
    }

    public MenuItem getMenuItemSimulationPause() {
        return menuItemSimulationPause;
    }

    public MenuItem getMenuItemSimulationStop() {
        return menuItemSimulationStop;
    }

    public Button getButtonPause() {
        return buttonPause;
    }

    public Button getButtonStart() {
        return buttonStart;
    }

    public Button getButtonConnection() {
        return buttonConnection;
    }


    public Button getButtonStop() {
        return buttonStop;
    }

    public Pane getPaneStage() {
        return paneStage;
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

    public int getValueSpinnerPackageCount() {
        return spinnerPackageCount.getValue();
    }


    public Spinner<Integer> getSpinnerSecondsWorker() {
        return spinnerSecondsWorker;
    }

    public Spinner<Integer> getSpinnerSecondsDrone() {
        return spinnerSecondsDrone;
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

    public void setMenuBarMain(MenuBar menuBarMain) {
        this.menuBarMain = menuBarMain;
    }

    public void setPaneStage(Pane paneStage) {
        this.paneStage = paneStage;
    }


    public void setSpinnerLifeTimeDrone(Spinner<Integer> spinnerLifeTimeDrone) {
        this.spinnerLifeTimeDrone = spinnerLifeTimeDrone;
    }

    public void setSpinnerLifeTimeWorker(Spinner<Integer> spinnerLifeTimeWorker) {
        this.spinnerLifeTimeWorker = spinnerLifeTimeWorker;
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
        labelTimer.setText(time.getTimeString());
    }

    public static String getFXMLresourse() {
        return FXMLresoursePath;
    }

    protected Integer getValueComboBoxProbabilityWorker() {
        return comboBoxProbabilityWorker.getSelectionModel().getSelectedItem();
    }

    protected Integer getValueComboBoxWorkerPriority() {
        return comboBoxWorkerPriority.getSelectionModel().getSelectedItem();
    }

    protected Integer getValueComboBoxDronePriority() {
        return comboBoxDronePriority.getSelectionModel().getSelectedItem();
    }

    protected Integer getValueComboBoxCoefficientDrone() {
        return comboBoxCoefficientDrone.getSelectionModel().getSelectedItem();
    }

    protected TextArea getValueTextAreaConnection() {
        return textAreaConnection;
    }
    protected MultipleSelectionModel<String> getValueListViewConnections() {
        return listViewConnections.getSelectionModel();
    }

    protected String getSelectedValueListViewConnections() {
        return listViewConnections.getSelectionModel().getSelectedItem();
    }

    protected void setValueListViewConnections(ObservableList<String> value) {
        listViewConnections.setItems(value);
    }
}
