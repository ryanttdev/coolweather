package com.coolweather.android.gson;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {

    public String status;

    public Basic basic;

    public AQI aqi;

    public Now now;

    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;      //引用的时候使用集合类型 初始化的时候需要声明类型

}
