package Application.Controller.Music;

import Application.Simulation.Simulation;
import Application.Simulation.State;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class Music {
    MediaPlayer mainThemeMediaPlayer = new MediaPlayer(new Media(new File("C:\\MyProjects\\TiMP\\src\\Pic\\Theme_MED.mp3").toURI().toString()));

    MediaPlayer beginThemeMediaPlayer = new MediaPlayer(new Media(new File("C:\\MyProjects\\TiMP\\src\\Pic\\Theme_MED_begin.mp3").toURI().toString()));

    MediaPlayer endThemeMediaPlayer = new MediaPlayer(new Media(new File("C:\\MyProjects\\TiMP\\src\\Pic\\Theme_MED_end.mp3").toURI().toString()));

    private enum MusicState {
        NOTHING,
        BEGIN,
        MAIN,
        END
    };

    MusicState flag = MusicState.NOTHING;
    public Music() {
        mainThemeMediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                flag = MusicState.MAIN;
                mainThemeMediaPlayer.seek(new Duration(0.0));
                mainThemeMediaPlayer.play();
            }
        });
        beginThemeMediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                beginThemeMediaPlayer.seek(new Duration(0.0));
                beginThemeMediaPlayer.stop();
                mainThemeMediaPlayer.play();
                flag = MusicState.MAIN;
            }
        });

        endThemeMediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                endThemeMediaPlayer.seek(new Duration(0.0));
                endThemeMediaPlayer.stop();
                flag = MusicState.NOTHING;
            }
        });
    }

    public void MusicPlay() {
        switch (flag) {
            case END:
                EndThemeStop();
                BeginThemePlay();
                break;
            case MAIN:
                MainThemePlay();
                break;
            default:
                BeginThemePlay();
                break;
        }
    }

    public void MusicPause() {

        switch (flag) {
            case MAIN:
                MainThemePause();
                break;
            default:
                BeginThemePause();
                break;
        }
    }

    public void MusicStop() {
        switch (flag) {
            case BEGIN:
                BeginThemeStop();
                break;
            case MAIN:
                MainThemeStop();
                break;
        }
        EndThemePlay();
    }

    private void MainThemePlay() {
        mainThemeMediaPlayer.play();
        flag = MusicState.MAIN;
    }

    private void MainThemePause() {
        mainThemeMediaPlayer.pause();
        flag = MusicState.MAIN;
    }

    private void MainThemeStop() {
        mainThemeMediaPlayer.seek(new Duration(0.0));
        mainThemeMediaPlayer.stop();
    }

    private void BeginThemeStop() {
        beginThemeMediaPlayer.seek(new Duration(0.0));
        beginThemeMediaPlayer.stop();
    }

    private void BeginThemePlay() {
        beginThemeMediaPlayer.play();
        flag = MusicState.BEGIN;
    }

    private void BeginThemePause() {
        beginThemeMediaPlayer.pause();
        flag = MusicState.BEGIN;
    }

    private void EndThemeStop() {
        endThemeMediaPlayer.seek(new Duration(0.0));
        endThemeMediaPlayer.stop();
        flag = MusicState.NOTHING;
    }

    private void EndThemePlay() {
        endThemeMediaPlayer.play();
        flag = MusicState.END;
    }
}
