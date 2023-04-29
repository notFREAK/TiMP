package Application.Controller.Main.FXML;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class ApplicationFXMLObjects {
    final static String FXMLresoursePath = "/resourses/FXML/ApplicationNew.fxml";
    @FXML
    protected AnchorPane paneMain;

    @FXML
    protected AnchorPane paneMenu;

    @FXML
    protected Pane paneStage;
    @FXML
    protected Button buttonStart;

    @FXML
    protected Button buttonPause;

    @FXML
    protected Button buttonStop;

    @FXML
    protected Label textSecondsDrone;

    @FXML
    protected Label textLifeTimeDrone;

    @FXML
    protected Label textCoefficientDrone;

    @FXML
    protected Label textSecondsWorker;

    @FXML
    protected Label textLifeTimeWorker;

    @FXML
    protected Label textProbabilityWorker;

    @FXML
    protected Label labelTimer;
    @FXML
    protected Spinner<Integer> spinnerSecondsDrone;

    @FXML
    protected Spinner<Integer>  spinnerLifeTimeDrone;

    @FXML
    protected Spinner<Integer>  spinnerSecondsWorker;

    @FXML
    protected Spinner<Integer>  spinnerLifeTimeWorker;

    @FXML
    protected ComboBox<Integer> comboBoxProbabilityWorker;
    @FXML protected ComboBox<Integer> comboBoxCoefficientDrone;
    @FXML protected CheckBox checkboxDetailObjects;
    @FXML protected CheckBox checkboxInformation;
    @FXML protected RadioButton radioButtonTimerOn;
    @FXML protected RadioButton radioButtonTimerOff;
    @FXML protected Label labelVolume;
    @FXML protected Label labelTime;
    @FXML protected Slider sliderVolume;

}
