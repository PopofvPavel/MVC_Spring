package com.example.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class Logger {
    final
    DataBase dataBase;

    public Logger(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void log(String message) {
        dataBase.saveLogMessage(message);
        System.out.println(message);
    }

}
