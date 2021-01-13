package app;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadProgress implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadProgress.class);
    private Thread thread;

    private Button startButton;
    private ProgressIndicator progress;

    public ThreadProgress(Button startButton, ProgressIndicator progress) {
        this.startButton = startButton;
        this.progress = progress;
        thread = new Thread(this, "progress");
        thread.start();
    }

    public void run() {
        try {
            startButton.setDisable(true);
            progress.setVisible(true);
            ThreadProcessingDBF threadDbf = new ThreadProcessingDBF();
            threadDbf.getThread().join();
            startButton.setDisable(false);
            progress.setVisible(false);
        } catch(Exception e) {
            LOG.info(e.getMessage());
        }
    }

    public Thread getThread() {
        return thread;
    }
}