package Application.TImer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public abstract class PausableTask implements  Runnable{

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Future<?> publisher;
    abstract void task();

    @Override
    public void run() {
        while(!Thread.currentThread().interrupted()){
            task();
        }
    }

    public void start(){
        publisher = executor.submit(this);
    }

    public void pause() {
        if (publisher != null)
        publisher.cancel(true);
    }

    public void resume() {
        start();
    }

    public void stop() {
        executor.shutdownNow();
    }
}
