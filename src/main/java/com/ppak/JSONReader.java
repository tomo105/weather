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



            int i=0;
            for(Object a : list){
                JSONObject tutorials = (JSONObject) a;
                JSONObject main = (JSONObject) tutorials.get("main");
                JSONObject wind = (JSONObject) tutorials.get("wind");

                JSONArray weatherjson = (JSONArray) tutorials.get("weather");
                JSONObject descriptiontmp = (JSONObject) weatherjson.get(0);
                String description = (String) descriptiontmp.get("description");

                double temp=0.0;
                Object obj =  main.get("temp");
                if (obj instanceof Number) {
                    temp = ((Number) obj).doubleValue();
                }
                double pressure=0.0;
                Object obj1 =  main.get("pressure");
                if (obj1 instanceof Number) {
                    pressure = ((Number) obj1).doubleValue();
                }
                Long humidity = (Long) main.get("humidity");

                double speed=0.0;
                Object obj2 =  wind.get("speed");
                if (obj2 instanceof Number) {
                    speed = ((Number) obj2).doubleValue();
                }
                double deg=0.0;
                Object obj3 =  wind.get("deg");
                if (obj3 instanceof Number) {
                    deg = ((Number) obj3).doubleValue();
                }


                String date = (String) tutorials.get("dt_txt");


                Weather weather = new Weather();
                weather.setData(date);
                weather.setTemp(temp);
                weather.setHumidity(humidity);
                weather.setDeg(deg);
                weather.setSpeed(speed);
                weather.setPressure(pressure);
                weather.setDescription(description);
                weather.setId(i++);
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

    public List<Weather> getWeatherList() {
        return weatherList;
    }
}
