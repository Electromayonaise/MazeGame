package main_module.model.util;

/**
 * The {@code Timer} class provides a simple timer functionality for measuring elapsed time.
 */
public class Timer {
    private long start; // The start time of the timer
    private long time;  // The duration for which the timer is set

    /**
     * Constructs a timer with the specified duration.
     *
     * @param time The duration of the timer in milliseconds.
     */
    public Timer(long time) {
        this.time = time;
    }

    /**
     * Resets the timer, marking the current time as the starting point.
     */
    public void reset() {
        start = System.currentTimeMillis();
    }

    /**
     * Checks if the elapsed time has exceeded the timer's duration.
     *
     * @return {@code true} if the elapsed time is greater than or equal to the timer's duration, {@code false} otherwise.
     */
    public boolean check() {
        return System.currentTimeMillis() - start >= time;
    }
}

