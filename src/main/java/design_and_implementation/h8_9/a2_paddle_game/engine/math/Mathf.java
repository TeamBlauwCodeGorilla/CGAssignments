package design_and_implementation.h8_9.a2_paddle_game.engine.math;

import java.awt.*;

public final class Mathf {

    public static final float Epsilon = 1.401298E-45f;

    private Mathf() { }

    public static float abs(float value) {
        return Math.abs(value);
    }

    public static double acos(float value) {
        return Math.acos(value);
    }

    public static float ulp(float f) {
        return Math.ulp(f);
    }

    public static boolean approximately(float fA, float fB) {
        float diff = fA - fB;
        return diff >= 0? diff <= ulp(fA) : diff >= -ulp(fA);
    }

    public static double asin(float value) {
        return Math.asin(value);
    }

    public static double atan(float value) {
        return Math.atan(value);
    }

    public static double atan2(float value) {
        return Math.atan(value);
    }

    public static int ceilToInt(float value) {
        return (int) Math.ceil(value);
    }

    public static float clamp(float value, float min, float max) {
        return Math.max(Math.min(value, min), max);
    }

    public static float clamp01(float value) {
        return Math.max(Math.min(value, 0), 1);
    }

    public static int ClosestPowerOfTwo(int value) {
        return (int) Math.pow(2, Math.ceil(Math.log(value) / Math.log(2)));
    }

    /**
     * Convert a color temperature in Kelvin to RGB color.
     * Correlated color temperature is defined as the color temperature of the electromagnetic radiation emitted from an ideal black body with its surface temperature given in degrees Kelvin.
     * @param kelvin temperature in Kelvin. Range 1000 to 40000 Kelvin.
     * @return correlated Color Temperature as floating point RGB color.
     */
    public static Color correlatedColorTemperatureToRGB(float kelvin) {
        kelvin = clamp(kelvin, 1000.0f, 40000.0f) / 1000.0f;
        float kelvin2 = kelvin * kelvin;

        // Red
        float r = kelvin < 6.570f ? 1.0f : clamp((1.35651f + 0.216422f * kelvin + 0.000633715f * kelvin2) / (-3.24223f + 0.918711f * kelvin), 0.0f, 1.0f);

        // Green
        float g = kelvin < 6.570f ?
                clamp((-399.809f + 414.271f * kelvin + 111.543f * kelvin2) / (2779.24f + 164.143f * kelvin + 84.7356f * kelvin2), 0.0f, 1.0f) :
                clamp((1370.38f + 734.616f * kelvin + 0.689955f * kelvin2) / (-4625.69f + 1699.87f * kelvin), 0.0f, 1.0f);

        //Blue
        float b = kelvin > 6.570f ? 1.0f : clamp((348.963f - 523.53f * kelvin + 183.62f * kelvin2) / (2848.82f - 214.52f * kelvin + 78.8614f * kelvin2), 0.0f, 1.0f);

        return new Color(r, g, b, 1.0f);
    }

    public static double cos(float value) {
        return Math.cos(value);
    }

    public static float deltaAngle(float current, float target) {
        float difference = target - current;
        while (difference < -180) difference += 360;
        while (difference > 180) difference -= 360;
        return difference;
    }

    public static double exp(float power) {
        return Math.exp(power);
    }

    public static double floatToHalf(float value) {
        return value;
    }

    public static boolean isPointInsideAABB(Vector2D point, Vector2D minPos, Vector2D maxPos) {
        return (
                point.x >= minPos.x &&
                        point.x <= maxPos.x &&
                        point.y >= minPos.y &&
                        point.y <= maxPos.x
        );
    }

    public static boolean isAABBInsideAABB(Vector2D minPosA, Vector2D maxPosA, Vector2D minPosB, Vector2D maxPosB) {
        return (minPosA.x <= maxPosB.x &&
                maxPosA.x >= minPosB.x &&
                minPosA.y <= maxPosB.y &&
                maxPosA.y >= minPosB.y
        );
    }
}
