package design_and_implementation.h8_9.a2_paddle_game.engine.system;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Time {

    static Instant startup;
    static float lastFrameTime, lastFixedFrameTime;
    static double deltaTime, fixedDeltaTime;
    long framesElapsed;
    static long fps;

    Time() {
        reset();
    }

    public void reset() {
        startup = Instant.now();
        framesElapsed = 0;
        fps = 60;
        lastFrameTime = 0;
        lastFixedFrameTime = 0;
    }

    /**
     * Get the time elapsed since the program has started in millis.
     * @return The amount of milliseconds elapsed since the beginning of the program
     */
    static float getTime() {
        return (float) (0.001 * ChronoUnit.MICROS.between(startup, Instant.now()));
    }

    void calcDeltaTime() {
        float startTime = getTime();
        deltaTime = startTime - lastFrameTime;
        lastFrameTime = startTime;

        framesElapsed++;
    }

    void calcFixedDeltaTime() {
        float startTime = getTime();
        fixedDeltaTime = startTime - lastFixedFrameTime;
        lastFixedFrameTime = startTime;

        fps = framesElapsed;
        framesElapsed = 0;
    }

    public static long getFPS() {
        return fps;
    }

    public static double getDeltaTime() {
        return deltaTime;
    }

    public static double getFixedDeltaTime() {
        return fixedDeltaTime;
    }
}
