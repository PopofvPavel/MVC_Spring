package com.example.database;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class Logger {
    private final Map<Integer, String> logger = new HashMap<Integer, String>();
    private int idMessage = 1;
    public void log(String message) {
        logger.put(idMessage, message);
        ++idMessage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : logger.entrySet()) {
            sb.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        }
        return sb.toString();

    }
}
