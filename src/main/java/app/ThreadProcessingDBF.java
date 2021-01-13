package app;

import debtors.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadProcessingDBF implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadProcessingDBF.class);
    private Thread thread;

    public ThreadProcessingDBF() {
        thread = new Thread(this, "processing DBF");
        thread.start();
    }

    public void run() {
        try {
            BaseDBF data = new BaseDBF();
            data.readDBF();
        } catch(Exception e) {
            LOG.info(e.getMessage());
        }
    }

    public Thread getThread() {
        return thread;
    }
}