package Application.Controller.ModalWindows.Console;

import Application.AppManager;
import Application.Controller.IController;
import Application.Controller.Main.Controller;
import Application.Controller.ModalWindows.Console.FXML.FXMLObjects;
import Application.Simulation.Simulation;
import Application.Simulation.Value;
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
    private String string;


    public void executeCommand(String command) {
        String[] words = command.split(" ");
        switch (words[0]) {
            case "alive":
                String answer;
                if (words.length != 2) {
                    answer = "Unknown arg. Use \"bee\",\"worker\" or \"drone\"\n";
                    break;
                }
                switch (words[1]) {
                    case "drone":
                        answer = AppManager.getInstance().getHabitat().getCollectionsBees().getAliveDrone();
                        break;
                    case "worker":
                        answer = AppManager.getInstance().getHabitat().getCollectionsBees().getAliveWorker();
                        break;
                    case "bee":
                        answer = AppManager.getInstance().getHabitat().getCollectionsBees().getAliveAnimals();
                        break;
                    default:
                        answer = "Unknown arg. Use \"bee\",\"worker\" or \"drone\"\n";
                        break;
                    }
                if (answer.equals("".toString()))
                {
                    answer = "No alive bees\n";
                }
                appendText(answer);
                break;
            default:
                appendText("Unknown command\n");
                break;
        }
        textAreaLength = textAreaConsole.getLength();
    }

    @Override
    public void appendText(String string) {
        textAreaConsole.appendText(string);
        textAreaLength = textAreaConsole.getLength();
    }

    @Override
    public ControllerType getType() {
        return ControllerType.CONSOLE;
    }

    @Override
    public void init(Stage stage) throws IOException {
        this.stage = stage;
        textAreaLength = 0;
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

    @Override
    public void setValue(Value v) {

    }
}

