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
    protected MenuBar menuBarMain;

    @FXML
    protected MenuItem menuItemSimulationStart;

    @FXML
    protected MenuItem menuItemSimulationPause;

    @FXML
    protected MenuItem menuItemSimulationStop;

    @FXML
    protected MenuItem menuItemSaveConfig;

    @FXML
    protected MenuItem menuItemLoadConfig;

    @FXML
    protected MenuItem menuItemClose;

    @FXML
    protected MenuItem menuItemConsole;

    @FXML
    protected MenuItem menuItemAIWorker;

    @FXML
    protected MenuItem menuItemAIDrone;

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

    @FXML
    protected ComboBox<Integer> comboBoxCoefficientDrone;

    @FXML
    protected CheckBox checkboxDetailObjects;

    @FXML
    protected CheckBox checkboxInformation;

    @FXML
    protected RadioButton radioButtonTimerOn;

    @FXML
    protected RadioButton radioButtonTimerOff;

    @FXML
    protected CheckMenuItem checkMenuItemViewDetailObjects;

    @FXML
    protected CheckMenuItem checkMenuItemViewInformation;

    @FXML
    protected RadioMenuItem radioMenuItemViewTimerOn;

    @FXML
    protected RadioMenuItem radioMenuItemViewTimerOff;

    @FXML
    protected Label labelVolume;

    @FXML
    protected Label labelTime;

    @FXML
    protected Slider sliderVolume;

    @FXML
    protected Button buttonAIWorker;

    @FXML
    protected Button buttonAIDrone;

    @FXML
    protected ComboBox<Integer> comboBoxWorkerPriority;

    @FXML
    protected ComboBox<Integer> comboBoxDronePriority;

    @FXML
    protected TextArea textAreaConnection;

    @FXML
    protected ListView<String> listViewConnections;

    @FXML
    protected Button buttonConnection;

    @FXML
    protected Spinner<Integer>  spinnerPackageCount;

    @FXML
    protected Pane paneBackStage;
}
