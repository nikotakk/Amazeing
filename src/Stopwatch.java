/**
 *  Copied and modified from "http://stackoverflow.com/questions/238920/how-do-i-calculate-the-elapsed-time-of-an-event-in-java" by nigel on 3/12/16.
 */
public class Stopwatch {
    // Constructor
    public Stopwatch() {
    }

    /* Starts the Watch if it is not running already. */
    public void start() {
        if (!isRunning) {
            startTime = System.nanoTime();
            isRunning = true;
        }
    }
    /* Stops the Watch if it is running. */
    public void stop() {
        if (isRunning) {
            elapsedTime += System.nanoTime() - startTime;
            isRunning = false;
        }
    }

    /* Resets the elapsedTime in the Watch. */
    public void reset() {
        elapsedTime = 0;
        if (isRunning) {
            startTime = System.nanoTime();
        }
    }

    /* Returns boolean of isRunning. */
    public boolean isRunning() {
        return isRunning;
    }

    /* Returns the elapsed time if the Watch is running, else returns elapsedTime. */
    public long getElapsedTime() {
        if (isRunning) {
            return (System.nanoTime() - startTime) / 10000000;
        }
        return elapsedTime;
    }

    public long getTimeSeconds() {
        if (isRunning) {
            return (System.nanoTime() - startTime) / 1000000000;
        }
        return elapsedTime / 1000000000;
    }


    // Private Members
    private boolean isRunning = false;
    private long startTime = 0;
    private long elapsedTime = 0;
}
