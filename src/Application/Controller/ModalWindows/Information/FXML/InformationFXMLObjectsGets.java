package Application.Controller.ModalWindows.Information.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class InformationFXMLObjectsGets extends InformationFXMLObjects {

    public Button getButtonCancel() {
        return buttonCancel;
    }

    public Button getButtonOK() {
        return buttonOK;
    }

    public TextArea getTextAreaInformation() {return textAreaInformation;}

    public static String getFXMLresourse() {
        return FXMLresoursePath;
    }
}
