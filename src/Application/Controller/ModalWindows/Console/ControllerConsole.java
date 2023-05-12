package Application.Controller.ModalWindows.Console;

import Application.AppManager;
import Application.Controller.IController;
import Application.Controller.Main.Controller;
import Application.Controller.ModalWindows.Console.FXML.FXMLObjects;
import Application.Simulation.Simulation;
import Application.TImer.PausableTask;
import Application.TImer.Timer;
import Objects.Bee.Bee;
import Objects.IBehaviour;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class ControllerConsole extends FXMLObjects implements IController {

    Stage stage;
    int textAreaLength = 0;
    public void appendText(String string) {
        textAreaConsole.appendText(string);
        textAreaLength = textAreaConsole.getLength();
    }
    public void executeCommand(String command) {
        switch (command) {
            case "":

                break;
            default:
                break;
        }
        textAreaLength = textAreaConsole.getLength();
    }

    @Override
    public ControllerType getType() {
        return ControllerType.CONSOLE;
    }

    @Override
    public void init(Stage stage) throws IOException {
        this.stage = stage;
        textAreaConsole.setOnKeyPressed(event -> {
            if (textAreaConsole.getCaretPosition() >= textAreaLength) {
                textAreaConsole.setEditable(true);
                if (event.getCode() == KeyCode.ENTER) {
                    event.consume();
                    String text = textAreaConsole.getText().substring(textAreaLength).trim();
                    executeCommand(text);
                }
            }
                else
                {
                textAreaConsole.setEditable(false);
            }
        });
        stage.show();

    }

    @Override
    public void hide() {
        stage.close();
    }

    @Override
    public void update(Timer timer) {
        textAreaConsole.setEditable(true);
    }

    @Override
    public void swapState() {
        if (AppManager.getInstance().getSimulation().getState().isStop())
            textAreaConsole.setEditable(false);
    }
}

