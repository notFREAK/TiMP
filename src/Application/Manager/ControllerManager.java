package Application.Manager;

import Application.AppManager;
import Application.Controller.IController;
import Application.Controller.Main.Controller;
import Application.Controller.ModalWindows.DetailObjects.ControllerDetailObjects;
import Application.Controller.ModalWindows.Information.ControllerInformation;
import Application.TImer.Timer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ControllerManager{
    private static class ControllerManagerHolder {
        public static final ControllerManager HOLDER_INSTANCE = new ControllerManager();
    }

    public static ControllerManager getInstance() {
        return ControllerManagerHolder.HOLDER_INSTANCE;
    }

    private ArrayList<IController> controllers;

    private ControllerManager() {
        controllers = new ArrayList<IController>();
    };

    public IController getController(IController.ControllerType type) {
        if (checkIsType(type)) {
            Iterator<IController> iterator = controllers.listIterator();
            while (iterator.hasNext()) {
                IController controller = iterator.next();
                if (controller.getType() == type)
                {
                    return controller;
                }
            }
        }
        return null;
    }

    public void ControllerUpdate(Timer time) {
            Iterator<IController> iterator = controllers.listIterator();
            while (iterator.hasNext()) {
                IController controller = iterator.next();
                controller.update(time);
            }
    }

    public void ControllerStateSwap() {
        Iterator<IController> iterator = controllers.listIterator();
        while (iterator.hasNext()) {
            IController controller = iterator.next();
            controller.swapState();
        }
    }
    public boolean checkIsType(IController.ControllerType type) {
        Iterator<IController> iterator = controllers.listIterator();
        while (iterator.hasNext()) {
            IController controller = iterator.next();
            if (controller.getType() == type)
            {
                return true;
            }
        }
        return  false;
    }


    public void StateSwap() {
        Iterator<IController> iterator = controllers.listIterator();
        while (iterator.hasNext()) {
            IController controller = iterator.next();
            controller.swapState();
        }
    }
    public void ControllerCreate(IController.ControllerType controllerType) throws IOException {
        FXMLLoader loader;
        Stage stage;
        switch (controllerType) {
            case DETAIL_OBJECTS:
                loader = new FXMLLoader(getClass().getResource("/resourses/FXML/Objects.fxml"));
                stage = new Stage();
                stage.initModality(Modality.NONE);
                stage.initOwner(AppManager.getInstance().getStage());
                break;
            case END_INFORMATION:
                loader = new FXMLLoader(getClass().getResource("/resourses/FXML/Information.fxml"));
                stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(AppManager.getInstance().getStage());
                break;
            default:
                loader = new FXMLLoader(getClass().getResource("/resourses/FXML/Application.fxml"));
                stage = AppManager.getInstance().getStage();
                break;
        }
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        IController iController;
        switch (controllerType) {
            case DETAIL_OBJECTS:
                ControllerDetailObjects controllerDetailObjects  = loader.getController();
                iController = controllerDetailObjects;
                break;
            case END_INFORMATION:
                ControllerInformation controllerInformation = loader.getController();
                iController = controllerInformation;
                break;
            default:
                Controller controller = loader.getController();
                iController = controller;
                break;
        }
        controllers.add(iController);
        iController.init(stage);
    }

    public void ControllerDelete(IController.ControllerType controllerType) {
        controllers.remove(getController(controllerType));
    }
}
