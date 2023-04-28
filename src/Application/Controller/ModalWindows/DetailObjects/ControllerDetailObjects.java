package Application.Controller.ModalWindows.DetailObjects;

import Application.AppManager;
import Application.Controller.IController;
import Application.Controller.ModalWindows.DetailObjects.FXML.ObjectsFXMLObjects;
import Application.Controller.ModalWindows.DetailObjects.FXML.ObjectsFXMLObjectsGets;
import Application.Manager.ControllerManager;
import Application.TImer.Timer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerDetailObjects extends ObjectsFXMLObjectsGets implements IController {

    Stage stage;

    //Dependency injections (хранится в принципах solid) ???
    private void initButtons() {
        stage.setOnCloseRequest(event -> {
            try {
                hide();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public ControllerType getType() {
        return ControllerType.DETAIL_OBJECTS;
    }

    @Override
    public void init(Stage stage) throws IOException {
        this.stage = stage;
        stage.setTitle("Трудятся во славу мёда");
        stage.initModality(Modality.NONE);
        stage.initOwner(AppManager.getInstance().getStage());
        initButtons();
        initFont();
        textAreaObject.setEditable(false);
        stage.show();
    }

    @Override
    public void hide() {
        stage.close();
    }

    @Override
    public void update(Timer timer) {
        textAreaObject.setText(AppManager.getInstance().getHabitat().getCollectionsBees().getAliveAnimals());
    }

    @Override
    public void swapState() {
        if (AppManager.getInstance().getSimulation().getState().isStop()) {
            textAreaObject.setText("");
        }
    }

    private void initFont() {
        textAreaObject.setStyle("-fx-font-family: Smeshariki2007Fixed-Regular; -fx-font-size: 14;");
    }
}
