package com.ppak;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        JSONReader reader = new JSONReader();

        reader.read();
        List<Weather> list = reader.getWeatherList();
        ConnectionDb connectionDb = new ConnectionDb();
        connectionDb.createDB();
        connectionDb.createTables();
        connectionDb.addToDatabase(list);
        List <Weather> listFromDB = connectionDb.getFromDatabase(list);

        System.out.println(list);
    }
}
