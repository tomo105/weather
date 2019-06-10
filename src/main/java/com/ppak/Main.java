package com.ppak;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        JSONReader reader = new JSONReader();

        reader.read();
        List<Weather> list = reader.getWeatherList();
        System.out.println(list);

    }
}
