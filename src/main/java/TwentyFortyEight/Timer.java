package TwentyFortyEight;

public class Timer {
    private long startTime;
    private long stopTime;
    private boolean running;

    public Timer() {
        this.startTime = 0;
        this.stopTime = 0;
    }

    public void start() {
        startTime = System.currentTimeMillis();
        running = true;
    }

    public void stop() {
        stopTime = System.currentTimeMillis();
        running = false;
    }

    private int getElapsedTime() {
        if (running) {
            return (int) (System.currentTimeMillis() - startTime);
        }
        return (int) (stopTime - startTime);
    }

    public int getSeconds() {
        return (getElapsedTime() / 1000) % 60;
    }

    public int getMinutes() {
        return (int) ((getElapsedTime() / 1000) / 60);
    }

    public void reset() {
        startTime = 0;
        stopTime = 0;
    }

}