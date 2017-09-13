package com.coolweather.android.gson;


import com.google.gson.annotations.SerializedName;

public class Forecast {

    public String date;

    @SerializedName("tmp")
    public Temperature temperature;

    @SerializedName("cond")
    public More more;

    public class Temperature{             //最高温度和最低温度

        public String max;

        public String min;

    }

    public class More{

        @SerializedName("txt_d")
        public String info;

    }
}
