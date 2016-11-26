package shaolinzhang.com.penta_cv_ir.weather;

import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class JSONUtils {

    public static WeatherBean getWeatherBean(URL url){

        String jsonText = new JSONFetcher().getJSONText(url);
        System.out.println(jsonText);
        WeatherBean weather = new WeatherBean();

        try {
            JSONTokener jsonParser = new JSONTokener(jsonText);
            JSONObject object = (JSONObject) jsonParser.nextValue();
//            String weatherinfo=object.getString("weatherinfo");
            JSONObject details = object.getJSONObject("weatherinfo");
            String city = details.getString("city");
            String weather1=details.getString("weather");
            String temp1=details.getString("temp1");
            String weatherToday=details.getString("weather");

            weather.setCity(city);
            weather.setWeather1(weather1);
            weather.setTemp1(temp1);
            weather.setWeather1(weatherToday);

        } catch (JSONException e) {
            System.out.println("test");
            e.printStackTrace();
        }

        return weather;
    }
}