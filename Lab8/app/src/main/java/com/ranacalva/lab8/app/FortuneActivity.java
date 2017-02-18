package com.ranacalva.lab8.app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ranacalva.lab8.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FortuneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune);

        MyPreferences pref = new MyPreferences(FortuneActivity.this);
        if(pref.isFirstTime())
        {
            Toast.makeText(FortuneActivity.this,"Hi "+pref.getUserName(),Toast.LENGTH_LONG).show();
            pref.setOld(true);
        }else{
            Toast.makeText(FortuneActivity.this,"Welcome back "+pref.getUserName(),Toast.LENGTH_LONG).show();
        }

        ConnectionDetector cd = new ConnectionDetector(this);
        if(cd.isConnectingToInternet())
            getFortuneOnline();
        else
            readFortuneFromFile();
    }

    private void getFortuneOnline(){
        //set the fortune text to loadding
        final TextView fortuneTxt = (TextView)findViewById(R.id.fortune);
        fortuneTxt.setText("Loading...");
        //create an instance of the request
        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.GET, "http://quotes.rest/qod.json", (String) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());
                        String fortune;
                        //parse the quote
                        try {
                            JSONObject contents = response.getJSONObject("contents");
                            JSONArray quotes = contents.getJSONArray("quotes");
                            fortune = quotes.getJSONObject(0).getString("quote");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            fortune = "Error";
                        }
                        //Set the fortune text to the parsed quote
                        fortuneTxt.setText(fortune);
                        writeToFile(fortune);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Response","Error: "+error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        //Add request to request queue
        AppController.getInstance().addToRequestQueue(request);
    }
    private void writeToFile(String data){
        try{
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("Fortune.json", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }catch (IOException e){
            Log.e("Message:","File write failed "+e.toString());
        }
    }
    private void readFortuneFromFile(){
        String fortune = " ";
        try{
            InputStream inputStream = openFileInput("Fortune.json");
            if(inputStream!=null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                Log.v("Message:", "reading...");
                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                fortune = stringBuilder.toString();
            }
        }catch (FileNotFoundException e){
            Log.e("Message:","File not found: "+e.toString());
        }catch (IOException e){
            Log.e("Message:","Can not read file: "+e.toString());
        }
        TextView fortuneTxt = (TextView)findViewById(R.id.fortune);
        fortuneTxt.setText(fortune);
    }
}
