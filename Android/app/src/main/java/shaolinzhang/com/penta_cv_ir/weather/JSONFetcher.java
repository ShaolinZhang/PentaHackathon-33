package shaolinzhang.com.penta_cv_ir.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class JSONFetcher {

    private String jsonText = "";

    //本方法通过指定url访问网络数据，并返回JSON格式的string。
    public  String getJSONText(final URL url){


        Thread thread = new Thread(new Runnable(){

            @Override
            public void run() {
                InputStream is =null;
                BufferedReader in = null;

                try {
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();

                    is = conn.getInputStream();
                    in = new BufferedReader(new InputStreamReader(is));
                    String line = "";
                    while((line = in.readLine()) != null){
                        jsonText += line;
                    }
                    Log.i("------------------", jsonText);

                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                    try {
                        in.close();
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }

        });

        thread.start();

        //等待上述线程完成执行后再返回jsonText。
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return jsonText;
    }

}