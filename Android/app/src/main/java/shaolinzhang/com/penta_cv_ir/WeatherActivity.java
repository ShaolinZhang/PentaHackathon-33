package shaolinzhang.com.penta_cv_ir;

import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Locale;

public class WeatherActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener{

    //EditText editTextCityName;
    //Button btnByCityName;
    TextView textViewResult;
    // TextView textViewInfo;
    private Button mTTSButton;
    private TextToSpeech mTextToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        //editTextCityName = (EditText) findViewById(R.id.cityname);
        //btnByCityName = (Button) findViewById(R.id.bycityname);
        mTTSButton = (Button) findViewById(R.id.btnTTS);
        mTTSButton.setOnClickListener(this);
        mTextToSpeech = new TextToSpeech(this, this);
        textViewResult = (TextView) findViewById(R.id.result);

        new OpenWeatherMapTask(
                "Shanghai, CN",
                textViewResult).execute();

        // textViewInfo = (TextView)findViewById(R.id.info);

        //btnByCityName.setOnClickListener(new View.OnClickListener() {
        // @Override
         //   public void onClick(View v) {
         //       new OpenWeatherMapTask(
         //               editTextCityName.getText().toString(),
         //               textViewResult).execute();
          //  }
       // });
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = mTextToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "Data lost or unsupported", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (mTextToSpeech != null && !mTextToSpeech.isSpeaking()) {
            mTextToSpeech.setPitch(0.5f);// 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
            mTextToSpeech.speak(textViewResult.getText().toString(),
                    TextToSpeech.QUEUE_FLUSH, null);
        }
    }

class OpenWeatherMapTask extends AsyncTask<Void, Void, String> {

        String cityName;
        TextView tvResult;

        String dummyAppid = "76033d8bb8b8c15f47c615357123219d";
        String queryWeather = "http://api.openweathermap.org/data/2.5/weather?units=metric&q=";
        String queryDummyKey = "&appid=" + dummyAppid;

        OpenWeatherMapTask(String cityName, TextView tvResult) {
            this.cityName = cityName;
            this.tvResult = tvResult;
        }

        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            String queryReturn;

            String query = null;
            try {
                query = queryWeather + URLEncoder.encode(cityName, "UTF-8") + queryDummyKey;
                queryReturn = sendQuery(query);
                result += ParseJSON(queryReturn);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                queryReturn = e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                queryReturn = e.getMessage();
            }


            final String finalQueryReturn = query + "\n\n" + queryReturn;
            runOnUiThread(new Runnable() {
              @Override
            public void run() {
                            textViewResult.setText(finalQueryReturn);
                }
            });


            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            tvResult.setText(s);
        }

        private String sendQuery(String query) throws IOException {
            String result = "";

            URL searchURL = new URL(query);

            HttpURLConnection httpURLConnection = (HttpURLConnection) searchURL.openConnection();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(
                        inputStreamReader,
                        8192);

                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
            }

            return result;
        }

        private String ParseJSON(String json) {
            String jsonResult = "";

            try {
                JSONObject JsonObject = new JSONObject(json);
                String cod = jsonHelperGetString(JsonObject, "cod");

                if (cod != null) {
                    if (cod.equals("200")) {

                        jsonResult += jsonHelperGetString(JsonObject, "name") + "\n";
                        JSONObject sys = jsonHelperGetJSONObject(JsonObject, "sys");
                        if (sys != null) {
                            jsonResult += jsonHelperGetString(sys, "country") + "\n";
                        }
                  //      jsonResult += "\n";

                        JSONObject coord = jsonHelperGetJSONObject(JsonObject, "coord");
                        if (coord != null) {
                            String lon = jsonHelperGetString(coord, "lon");
                            String lat = jsonHelperGetString(coord, "lat");
                            //    jsonResult += "lon: " + lon + "\n";
                            //    jsonResult += "lat: " + lat + "\n";
                        }
                        jsonResult += "\n";

                        JSONArray weather = jsonHelperGetJSONArray(JsonObject, "weather");
                        if (weather != null) {
                            for (int i = 0; i < weather.length(); i++) {
                                JSONObject thisWeather = weather.getJSONObject(i);
                    //            jsonResult += "weather " + i + ":\n";
                    //            jsonResult += "id: " + jsonHelperGetString(thisWeather, "id") + "\n";
                                jsonResult += jsonHelperGetString(thisWeather, "main") + "\n";
                    //            jsonResult += jsonHelperGetString(thisWeather, "description") + "\n";
                                jsonResult += "\n";
                            }
                        }

                        JSONObject main = jsonHelperGetJSONObject(JsonObject, "main");
                        if (main != null) {
                            jsonResult += "temp: " + jsonHelperGetString(main, "temp") + "\n";
                     //       jsonResult += "pressure: " + jsonHelperGetString(main, "pressure") + "\n";
                            jsonResult += "humidity: " + jsonHelperGetString(main, "humidity") + "\n";
                               jsonResult += "temp_min: " + jsonHelperGetString(main, "temp_min") + "\n";
                            jsonResult += "temp_max: " + jsonHelperGetString(main, "temp_max") + "\n";
                     //       jsonResult += "sea_level: " + jsonHelperGetString(main, "sea_level") + "\n";
                     //       jsonResult += "grnd_level: " + jsonHelperGetString(main, "grnd_level") + "\n";
                            jsonResult += "\n";
                        }

                        jsonResult += "visibility: " + jsonHelperGetString(JsonObject, "visibility") + "\n";
                        jsonResult += "\n";

                        JSONObject wind = jsonHelperGetJSONObject(JsonObject, "wind");
                        if (wind != null) {
                     //       jsonResult += "wind:\n";
                     //       jsonResult += "speed: " + jsonHelperGetString(wind, "speed") + "\n";
                     //       jsonResult += "deg: " + jsonHelperGetString(wind, "deg") + "\n";
                     //       jsonResult += "\n";
                        }

                        //...incompleted

                    } else if (cod.equals("404")) {
                        String message = jsonHelperGetString(JsonObject, "message");
                        jsonResult += "cod 404: " + message;
                    }
                } else {
                    jsonResult += "cod == null\n";
                }

            } catch (JSONException e) {
                e.printStackTrace();
                jsonResult += e.getMessage();
            }

            return jsonResult;
        }

        private String jsonHelperGetString(JSONObject obj, String k) {
            String v = null;
            try {
                v = obj.getString(k);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return v;
        }

        private JSONObject jsonHelperGetJSONObject(JSONObject obj, String k) {
            JSONObject o = null;

            try {
                o = obj.getJSONObject(k);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return o;
        }

        private JSONArray jsonHelperGetJSONArray(JSONObject obj, String k) {
            JSONArray a = null;

            try {
                a = obj.getJSONArray(k);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return a;
        }
    }
}