package Application.Controller;

import Application.Simulation.Value;
import Application.TImer.Timer;
import javafx.stage.Stage;

import java.io.IOException;

public interface IController {
    enum ControllerType {
        MAIN,
        END_INFORMATION,
        DETAIL_OBJECTS,
        CONSOLE;
    }
    public ControllerType getType();
    public void init(Stage stage) throws IOException;
    public void hide();
    public void update(Timer timer);
    public void swapState();

    public void setValue(Value v);
    public void appendText(String s);
}
