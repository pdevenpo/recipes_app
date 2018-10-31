package edu.osu.recipe_app.ui.FindStore;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import edu.osu.recipe_app.MainActivity;

public class GetNearbyPlace extends AsyncTask<Object, String, String> {

    //import variables to be used with JSON parsing and google maps api
    private GoogleMap mMap;
    private String url;
    private InputStream is;
    private BufferedReader bufferedReader;
    private StringBuilder stringBuilder;
    private String data;

    @Override
    protected String doInBackground(Object... params){
        //grab the map we are altering
        mMap = (GoogleMap) params[0];
        //url = (String)params[1];
        //TODO create url based on current location
        //url to parse for information
        url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=40.0016332,-83.019707&radius=5000&type=supermarket&keyword=store&key=AIzaSyAQrWxqWmVFQ1n5kt6rrrb9Ujq5I5yC4OI";

        try{
            //Establish a connection with URL to be scraped
            URL myurl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)myurl.openConnection();
            httpURLConnection.connect();
            is = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(is));

            String line="";
            stringBuilder = new StringBuilder();
            while((line = bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }
            //store supermarket data in stringbuilder
            data = stringBuilder.toString();
        //handle exceptions
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }


        return data;
    }

    //pass and retrieve string data
    //s contains the json information
    @Override
    protected void onPostExecute(String s){
        try{
            JSONObject parentObject = new JSONObject(s);
            //results array contains all information on the store
            JSONArray resultArray = parentObject.getJSONArray("results");
            //for each store found by the query...
            for(int i = 0; i<resultArray.length();i++){
                //grab the json objects for its location and name
                JSONObject jsonObject = resultArray.getJSONObject(i);
                JSONObject locationObj = jsonObject.getJSONObject("geometry").getJSONObject("location");

                String latitude = locationObj.getString("lat");
                String longitude = locationObj.getString("lng");

                JSONObject nameObject = resultArray.getJSONObject(i);

                String name_store = nameObject.getString("name");

                LatLng latLng = new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.title(name_store);
                markerOptions.position(latLng);
                //put marker on the map for the store found
                mMap.addMarker(markerOptions);


            }



        }catch(JSONException e){
            e.printStackTrace();
        }


    }
}
