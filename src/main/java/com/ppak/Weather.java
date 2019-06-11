package com.ppak;

public class Weather {

    private Integer id;
    private Double temp;
    private Double pressure;
    private Long humidity;
    private String data; // dt_txt

    //    wind
    private Double speed;
    private Double deg;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getHumidity() {
        return humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public Double getSpeed() {
        return speed;
    }

    public Double getTemp() {
        return temp;
    }

    public Integer getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public Double getDeg() {
        return deg;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public void setDeg(Double deg) {
        this.deg = deg;
    }
}
