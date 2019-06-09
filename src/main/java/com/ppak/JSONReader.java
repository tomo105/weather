package com.ppak;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {

    private List<Weather> weatherList;


    void read() throws IOException {

        JSONParser parser = new JSONParser();
        weatherList = new ArrayList<>();
        try {
            URL oracle = new URL("http://api.openweathermap.org/data/2.5/forecast?q=krakow&APPID=af5a49d238b6b58fe81dab2fb9f327b3"); // URL to Parse
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            String inputLine;
            String data="";
            while ((inputLine = in.readLine()) != null) {
                data += inputLine;

            }
            in.close();

            JSONObject jsonObject = (JSONObject) parser.parse(data);
            JSONArray list = (JSONArray) jsonObject.get("list");

            for(Object a : list){
                JSONObject tutorials = (JSONObject) a;

                String date = (String) tutorials.get("dt_txt");


                System.out.println("Date : " + date );
                Weather weather = new Weather();
                weather.setData(date);
                weatherList.add(weather);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
