package com.example.websocket.service;

import com.alibaba.fastjson.JSONObject;
import com.example.websocket.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class WeatherService {

    public Map<String, String> getWeatherInfo(String city) {
        String host = "http://jisutqybmf.market.alicloudapi.com";
        String path = "/weather/query";
        String method = "GET";
        String appcode = "d0882aaf20b0401d842a632152814a8a";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("city", city);
        querys.put("citycode", "citycode");
        querys.put("cityid", "cityid");
        querys.put("ip", "ip");
        querys.put("location", "location");
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
            // {"status":"0","msg":"ok","result":{"city":"安仁县","cityid":"1679","citycode":"101250509","date":"2018-03-25","week":"星期日","weather":"雨","temp":"14","temphigh":"18","templow":"13","img":"301","humidity":"100","pressure":"1009","windspeed":"1.2","winddirect":"西北风","windpower":"1级","updatetime":"2018-03-25 20:10:00","index":[{"iname":"空调指数","ivalue":"较少开启","detail":"您将感到很舒适，一般不需要开启空调。"},{"iname":"运动指数","ivalue":"较不宜","detail":"有较强降水，建议您选择在室内进行健身休闲运动。"},{"iname":"紫外线指数","ivalue":"最弱","detail":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"},{"iname":"感冒指数","ivalue":"易发","detail":"天冷空气湿度大，易发生感冒，请注意适当增加衣服，加强自我防护避免感冒。"},{"iname":"洗车指数","ivalue":"不宜","detail":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},{"iname":"空气污染扩散指数","ivalue":"优","detail":"气象条件非常有利于空气污染物稀释、扩散和清除，可在室外正常活动。"},{"iname":"穿衣指数","ivalue":"较冷","detail":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"}],"aqi":{"so2":"7","so224":"12","no2":"28","no224":"27","co":"1.380","co24":"1.200","o3":"43","o38":"37","o324":"54","pm10":"29","pm1024":"71","pm2_5":"20","pm2_524":"37","iso2":"3","ino2":"14","ico":"14","io3":"14","io38":"19","ipm10":"29","ipm2_5":"28","aqi":"29","primarypollutant":"PM10","quality":"优","timepoint":"2018-03-25 19:00:00","aqiinfo":{"level":"一级","color":"#00e400","affect":"空气质量令人满意，基本无空气污染","measure":"各类人群可正常活动"}},"daily":[{"date":"2018-03-25","week":"星期日","sunrise":"06:27","sunset":"18:40","night":{"weather":"小雨","templow":"13","img":"7","winddirect":"北风","windpower":"微风"},"day":{"weather":"中雨","temphigh":"18","img":"8","winddirect":"北风","windpower":"微风"}},{"date":"2018-03-26","week":"星期一","sunrise":"06:26","sunset":"18:41","night":{"weather":"多云","templow":"13","img":"1","winddirect":"北风","windpower":"微风"},"day":{"weather":"阴","temphigh":"23","img":"2","winddirect":"北风","windpower":"微风"}},{"date":"2018-03-27","week":"星期二","sunrise":"06:25","sunset":"18:41","night":{"weather":"晴","templow":"15","img":"0","winddirect":"北风","windpower":"微风"},"day":{"weather":"多云","temphigh":"26","img":"1","winddirect":"北风","windpower":"微风"}},{"date":"2018-03-28","week":"星期三","sunrise":"06:24","sunset":"18:42","night":{"weather":"雷阵雨","templow":"17","img":"4","winddirect":"北风","windpower":"微风"},"day":{"weather":"多云","temphigh":"27","img":"1","winddirect":"北风","windpower":"微风"}},{"date":"2018-03-29","week":"星期四","sunrise":"06:23","sunset":"18:42","night":{"weather":"雷阵雨","templow":"14","img":"4","winddirect":"北风","windpower":"微风"},"day":{"weather":"雷阵雨","temphigh":"27","img":"4","winddirect":"北风","windpower":"微风"}},{"date":"2018-03-30","week":"星期五","sunrise":"06:22","sunset":"18:43","night":{"weather":"小雨","templow":"14","img":"7","winddirect":"北风","windpower":"微风"},"day":{"weather":"雷阵雨","temphigh":"22","img":"4","winddirect":"北风","windpower":"微风"}},{"date":"2018-03-31","week":"星期六","sunrise":"06:21","sunset":"18:43","night":{"weather":"阴","templow":"15","img":"2","winddirect":"北风","windpower":"微风"},"day":{"weather":"阴","temphigh":"26","img":"2","winddirect":"北风","windpower":"微风"}}],"hourly":[{"time":"20:00","weather":"小雨","temp":"16","img":"7"},{"time":"21:00","weather":"小雨","temp":"16","img":"7"},{"time":"22:00","weather":"多云","temp":"16","img":"1"},{"time":"23:00","weather":"多云","temp":"16","img":"1"},{"time":"0:00","weather":"多云","temp":"15","img":"1"},{"time":"1:00","weather":"多云","temp":"15","img":"1"},{"time":"2:00","weather":"多云","temp":"14","img":"1"},{"time":"3:00","weather":"多云","temp":"14","img":"1"},{"time":"4:00","weather":"多云","temp":"14","img":"1"},{"time":"5:00","weather":"多云","temp":"14","img":"1"},{"time":"6:00","weather":"多云","temp":"14","img":"1"},{"time":"7:00","weather":"多云","temp":"13","img":"1"},{"time":"8:00","weather":"多云","temp":"15","img":"1"},{"time":"9:00","weather":"多云","temp":"17","img":"1"},{"time":"10:00","weather":"多云","temp":"18","img":"1"},{"time":"11:00","weather":"多云","temp":"20","img":"1"},{"time":"12:00","weather":"多云","temp":"21","img":"1"},{"time":"13:00","weather":"多云","temp":"22","img":"1"},{"time":"14:00","weather":"多云","temp":"22","img":"1"},{"time":"15:00","weather":"多云","temp":"23","img":"1"},{"time":"16:00","weather":"多云","temp":"23","img":"1"},{"time":"17:00","weather":"多云","temp":"22","img":"1"},{"time":"18:00","weather":"多云","temp":"21","img":"1"},{"time":"19:00","weather":"多云","temp":"19","img":"1"}]}}
            Map<String, String> resMap = new HashMap<>();
            if (jsonObject != null && jsonObject.containsKey("status") && jsonObject.get("status").equals("0")) {
                JSONObject result = jsonObject.getJSONObject("result");
                resMap.put("city",result.getString("city"));
                resMap.put("date",result.getString("date"));
                resMap.put("weather",result.getString("weather"));
                resMap.put("temp",result.getString("temp"));
                return resMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
