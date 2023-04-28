package Application.TImer;

import Application.AppManager;
import Application.Simulation.Simulation;
import javafx.application.Platform;
import javafx.scene.SubScene;

public class Timer {
    private Thread thread;

    private AppManager manager;
    Time time;
    long timePause = 0;
    boolean pause;
    private PausableTask runnable = new PausableTask()
    {
        @Override
        public  void task() {
            if (manager.getSimulation().getState().isRunning()) {
                try {
                    while (true) {
                            double speedSimulation = Simulation.getSimulationSpeed();
                            Thread.sleep((int)Math.round(speedSimulation));
                            Platform.runLater(new Runnable() {
                                @Override
                                public synchronized void run() {
                                    manager.update(time);
                                }
                            });
                    }

                }
                catch (InterruptedException e) {
                    // Restore the interrupted status
                    Thread.currentThread().interrupt();
                }
            }
        }
    };
    public Timer(AppManager manager) {
        thread = new Thread(runnable);
        this.manager = manager;
        time = new Time();
        pause = false;
    }

    public Time Stop() {
        Time timeFinal = time;
        time.setNull();
        runnable.pause();
        return timeFinal;
    }

    public void Pause() {
        pause = true;
        timePause = time.getTimeLong();
        runnable.pause();
    }

    public void Play() {
        if (pause == true) {
            pause = false;
            time.setTimePause(timePause);
        }
        else {
            time.setNull();
        }
        if (!thread.isAlive()) {
            runnable.start();
        }
        else {
            runnable.resume();
        }
    }

    public Time getTime() {return time;}
    public int getMinutes() {
        return time.getMinutes();
    }

    public int getSeconds() {
        return time.getSeconds();
    }

    public int getMilliseconds() {
        return time.getMillisecond();
    }
}
