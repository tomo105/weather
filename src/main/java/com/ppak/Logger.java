package com.ppak;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static Logger instance;

    private Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    private String date() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(format);
    }

    public void info(String info) {
        System.out.println("LOG" + date() + " INFO: " + info);
    }

    public void error(String error) {
        System.out.println(date() + " ERROR: " + error);
    }



}