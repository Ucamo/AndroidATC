package com.ranacalva.lab8.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ranacalva.lab8.BackgroundService;
import com.ranacalva.lab8.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyPreferences pref = new MyPreferences(MainActivity.this);
        if(!pref.isFirstTime()){
            Intent i = new Intent(getApplicationContext(),FortuneActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
            finish();
        }

        Button btn_startService = (Button)findViewById(R.id.btnStartService);
        final Intent serviceIntent= new Intent(MainActivity.this,BackgroundService.class);
        btn_startService.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startService(serviceIntent);
            }
        });

        Button btn_stopService = (Button)findViewById(R.id.btnStopService);
        btn_stopService.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                stopService(serviceIntent);
            }
        });


    }
    public void SaveUserName(View v){
        EditText usrName = (EditText)findViewById(R.id.txtNombre);
        MyPreferences pref = new MyPreferences(MainActivity.this);
        pref.setUserName(usrName.getText().toString().trim());
        Intent i = new Intent(getApplicationContext(),FortuneActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
        finish();
    }
    /*
    @Override
    public boolean onCreateOptionMenu(Menu menu){
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return true;
    }*/

}
