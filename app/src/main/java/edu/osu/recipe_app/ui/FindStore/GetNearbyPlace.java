package edu.osu.recipe_app.ui.FindStore;

import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetNearbyPlace extends AsyncTask<Object, String, String> {

    GoogleMap mMap;
    String url;
    BufferedReader bufferedReader;
    StringBuilder stringBuilder;
    String data;


    @Override
    protected String doInBackground(Object... params){
        mMap = (GoogleMap) params[0];
        url = (String)params[1];
        InputStream is;
        StringBuffer stringBuffer;
        try{
            URL myurl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)(myurl.openConnection());
            httpURLConnection.connect();
            is = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(is));

            String line="";
            stringBuilder = new StringBuilder();
            while((line = bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }

            data = stringBuilder.toString();

        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }



        return data;
    }

    //pass and retreive string data
    //s contains the json
    @Override
    protected void onPostExecute(String s){
        
    }
}
