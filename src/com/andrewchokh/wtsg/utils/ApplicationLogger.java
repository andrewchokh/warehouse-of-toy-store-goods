package com.andrewchokh.wtsg.utils;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ApplicationLogger {

    private static final Logger logger = Logger.getLogger("AppLog");

    public static void initializeLogger() {
        FileHandler fh;

        try {
            fh = new FileHandler("./Logs.log");

            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            logger.info("The logger initialized.");
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception ::", e);
        }
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void warning(String message) {
        logger.warning(message);
    }
}
