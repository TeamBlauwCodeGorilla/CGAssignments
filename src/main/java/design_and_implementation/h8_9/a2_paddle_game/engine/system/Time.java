package design_and_implementation.h8_9.a2_paddle_game.engine.system;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Time {

    static Instant startup;
    static double lastFrameTime, lastFixedFrameTime;
    static double deltaTime, fixedDeltaTime;

    Instant lastFrame, lastFixedFrame;

    long framesElapsed;
    static long fps;

    Time() {
        reset();
    }

    public void reset() {
        startup = Instant.now();
        lastFrame = Instant.now();
        lastFixedFrame = Instant.now();
        framesElapsed = 0;
        fps = 60;
        lastFrameTime = 0;
        lastFixedFrameTime = 0;
    }

    static double getTime() {
        return 0.000000001 * ChronoUnit.NANOS.between(startup, Instant.now());
    }

    void calcDeltaTime() {
        double startTime = getTime();
        deltaTime = startTime - lastFrameTime;
        lastFrameTime = startTime;

        framesElapsed++;
    }

    void calcFixedDeltaTime() {
        double startTime = getTime();
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
