package com.andrewchokh.wtsg.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ApplicationLogger {

    private static final Logger logger = Logger.getLogger("AppLog");

    private static Boolean isEnabled = true;
    private static Boolean isShowInfo = true;
    private static Boolean isShowWarning = true;

    public static void init() {
        if (!isEnabled) {
            return;
        }

        FileHandler fh;

        String date = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"));

        try {
            fh = new FileHandler("./logs/%s.log".formatted(date));

            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            logger.info("Logger initialized.");
        } catch (Exception e) {
            logger.log(Level.WARNING, "%s: %s".formatted(e.getClass(), e.getMessage()));
        }
    }

    public static void setIsEnabled(Boolean isEnabled) {
        ApplicationLogger.isEnabled = isEnabled;
    }

    public static void setIsShowInfo(Boolean isShowInfo) {
        ApplicationLogger.isShowInfo = isShowInfo;
    }

    public static void setIsShowWarning(Boolean isShowWarning) {
        ApplicationLogger.isShowWarning = isShowWarning;
    }

    public static void info(String message) {
        if (!isEnabled || !isShowInfo) {
            return;
        }

        logger.log(Level.INFO, message);
    }

    public static void warning(String message) {
        if (!isEnabled || !isShowWarning) {
            return;
        }

        logger.log(Level.WARNING, message);
    }
}
