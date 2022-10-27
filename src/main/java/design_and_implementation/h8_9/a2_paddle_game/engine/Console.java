package design_and_implementation.h8_9.a2_paddle_game.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

public final class Console {

    private static final Logger LOG = LoggerFactory.getLogger("CodeGorilla Engine");

    private Console() { }

    public static String getName() {
        return LOG.getName();
    }

    public static boolean isTraceEnabled() {
        return LOG.isTraceEnabled();
    }

    public static void trace(String s) {
        LOG.trace(s);
    }

    public static void trace(String s, Object o) {
        LOG.trace(s, o);
    }

    public static void trace(String s, Object o, Object o1) {
        LOG.trace(s, o, o1);
    }

    public static void trace(String s, Object... objects) {
        LOG.trace(s, objects);
    }

    public static void trace(String s, Throwable throwable) {
        LOG.trace(s, throwable);
    }

    public static boolean isTraceEnabled(Marker marker) {
        return LOG.isTraceEnabled(marker);
    }

    public static void trace(Marker marker, String s) {
        LOG.trace(marker, s);
    }

    public static void trace(Marker marker, String s, Object o) {
        LOG.trace(marker, s, o);
    }

    public static void trace(Marker marker, String s, Object o, Object o1) {
        LOG.trace(marker, s, o, o1);
    }

    public static void trace(Marker marker, String s, Object... objects) {
        LOG.trace(marker, s, objects);
    }

    public static void trace(Marker marker, String s, Throwable throwable) {
        LOG.trace(marker, s, throwable);
    }

    public static boolean isDebugEnabled() {
        return LOG.isDebugEnabled();
    }

    public static void debug(String s) {
        LOG.debug(s);
    }

    public static void debug(String s, Object o) {
        LOG.debug(s, o);
    }

    public static void debug(String s, Object o, Object o1) {
        LOG.debug(s, o, o1);
    }

    public static void debug(String s, Object... objects) {
        LOG.debug(s, objects);
    }

    public static void debug(String s, Throwable throwable) {
        LOG.debug(s, throwable);
    }

    public static boolean isDebugEnabled(Marker marker) {
        return LOG.isDebugEnabled(marker);
    }

    public static void debug(Marker marker, String s) {
        LOG.debug(marker, s);
    }

    public static void debug(Marker marker, String s, Object o) {
        LOG.debug(marker, s, o);
    }

    public static void debug(Marker marker, String s, Object o, Object o1) {
        LOG.debug(marker, s, o, o1);
    }

    public static void debug(Marker marker, String s, Object... objects) {
        LOG.debug(marker, s, objects);
    }

    public static void debug(Marker marker, String s, Throwable throwable) {
        LOG.debug(marker, s, throwable);
    }

    public static boolean isInfoEnabled() {
        return LOG.isInfoEnabled();
    }

    public static void info(String s) {
        LOG.info(s);
    }

    public static void info(String s, Object o) {
        LOG.info(s, o);
    }

    public static void info(String s, Object o, Object o1) {
        LOG.info(s, o, o1);
    }

    public static void info(String s, Object... objects) {
        LOG.info(s, objects);
    }

    public static void info(String s, Throwable throwable) {
        LOG.info(s, throwable);
    }

    public static boolean isInfoEnabled(Marker marker) {
        return LOG.isInfoEnabled(marker);
    }

    public static void info(Marker marker, String s) {
        LOG.info(marker, s);
    }

    public static void info(Marker marker, String s, Object o) {
        LOG.info(marker, s, o);
    }

    public static void info(Marker marker, String s, Object o, Object o1) {
        LOG.info(marker, s, o, o1);
    }

    public static void info(Marker marker, String s, Object... objects) {
        LOG.info(marker, s, objects);
    }

    public static void info(Marker marker, String s, Throwable throwable) {
        LOG.info(marker, s, throwable);
    }

    public static boolean isWarnEnabled() {
        return LOG.isWarnEnabled();
    }

    public static void warn(String s) {
        LOG.warn(s);
    }

    public static void warn(String s, Object o) {
        LOG.warn(s, o);
    }

    public static void warn(String s, Object... objects) {
        LOG.warn(s, objects);
    }

    public static void warn(String s, Object o, Object o1) {
        LOG.warn(s, o, o1);
    }

    public static void warn(String s, Throwable throwable) {
        LOG.warn(s, throwable);
    }

    public static boolean isWarnEnabled(Marker marker) {
        return LOG.isWarnEnabled(marker);
    }

    public static void warn(Marker marker, String s) {
        LOG.warn(marker, s);
    }

    public static void warn(Marker marker, String s, Object o) {
        LOG.warn(marker, s, o);
    }

    public static void warn(Marker marker, String s, Object o, Object o1) {
        LOG.warn(marker, s, o, o1);
    }

    public static void warn(Marker marker, String s, Object... objects) {
        LOG.warn(marker, s, objects);
    }

    public static void warn(Marker marker, String s, Throwable throwable) {
        LOG.warn(marker, s, throwable);
    }

    public static boolean isErrorEnabled() {
        return LOG.isErrorEnabled();
    }

    public static void error(String s) {
        LOG.error(s);
    }

    public static void error(String s, Object o) {
        LOG.error(s, o);
    }

    public static void error(String s, Object o, Object o1) {
        LOG.error(s, o, o1);
    }

    public static void error(String s, Object... objects) {
        LOG.error(s, objects);
    }

    public static void error(String s, Throwable throwable) {
        LOG.error(s, throwable);
    }

    public static boolean isErrorEnabled(Marker marker) {
        return LOG.isErrorEnabled(marker);
    }

    public static void error(Marker marker, String s) {
        LOG.error(marker, s);
    }

    public static void error(Marker marker, String s, Object o) {
        LOG.error(marker, s, o);
    }

    public static void error(Marker marker, String s, Object o, Object o1) {
        LOG.error(marker, s, o, o1);
    }

    public static void error(Marker marker, String s, Object... objects) {
        LOG.error(marker, s, objects);
    }

    public static void error(Marker marker, String s, Throwable throwable) {
        LOG.error(marker, s, throwable);
    }
}
