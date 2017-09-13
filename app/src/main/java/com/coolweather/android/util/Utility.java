package com.coolweather.android.util;


import android.text.TextUtils;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;
import com.coolweather.android.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    /*
   解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);   //转化成json数组
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);  //每个元素都可获取到jsonobject
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));   //Object对象才可以抽取字符串
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;                     //如果成功解析就返回true
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

 /*
   解析和处理服务器返回的市级数据
     */

    public static boolean handleCityResponse(String response, int provinceId) {   //得到了用户想要请求的数据
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++ ){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

     /*
   解析和处理服务器返回的县级数据
     */
     public static boolean handleCountyResponse(String response, int cityId){
         if(!TextUtils.isEmpty(response)){
             try {
                 JSONArray allCounties = new JSONArray(response);
                 for(int i = 0; i < allCounties.length(); i++){
                     JSONObject countyObject = allCounties.getJSONObject(i);
                     County county = new County();
                     county.setCountyName(countyObject.getString("name"));
                     county.setWeatherId(countyObject.getString("weather_id"));
                     county.setCityId(cityId);
                     county.save();
                 }
                 return true;
             }catch (JSONException e){
                 e.printStackTrace();
             }
         }
         return false;
     }


     /*
   将返回的JSON数据解析成weather实体类
     */
     public static Weather handleWeatherResponse(String response){
         try {
             JSONObject jsonObject = new JSONObject(response);
             JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");   //？？？？？？？？？？？
             String weatherContent = jsonArray.getJSONObject(0).toString();  //获取json数组中的第一项
             return new Gson().fromJson(weatherContent, Weather.class);   //将json数据转换成weather对象
         }catch (Exception e){
             e.printStackTrace();
         }
         return null;
     }












}
