package Application.Windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public abstract class ModalWindows {
    private void Show(Window window, String title) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ModalWindows.fxml"));
        stage.setScene(new Scene(root));
        Controller();
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(window);
        stage.show();
    }

    public abstract void Controller();

}
