package com.ranacalva.contactselectionapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

public class ContactIntentActivity extends AppCompatActivity {

    private List<ContactObject> contactList;
    private int RUNTIME_PERMISSION_CODE=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inflate yiour view
        setContentView(R.layout.activity_contact_intent);
        ListView intentListView = (ListView) findViewById(R.id.listView1);

        contactList=new ArrayList<>();
        contactList.add(new ContactObject("Android One","1111-1111-1111","www.androidATC.com"));
        contactList.add(new ContactObject("Android Two","2222-2222-2222","www.androidATC.com"));
        contactList.add(new ContactObject("Android Three","3333-3333-11333311","www.androidATC.com"));
        contactList.add(new ContactObject("Android Four","4444-4444-4444","www.androidATC.com"));

        List<String> listName = new ArrayList<>();
        for(int i=0;i<contactList.size();i++){
            listName.add(contactList.get(i).getName());
        }
        //Initialize the ArrayAdapter object
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ContactIntentActivity.this,android.R.layout.simple_list_item_1,listName);
        intentListView.setAdapter(adapter);
        intentListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view, int position, long id){
                Intent i = new Intent(ContactIntentActivity.this,ContactPageActivity.class);
                i.putExtra("Object",contactList.get(position));
                startActivityForResult(i,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(data==null){
            return;
        }
        Bundle resultData = data.getExtras();
        String value = resultData.getString("value");
        switch (resultCode)
        {
            case Constants.PHONE:
                //implicit intent to make a call
                makeCall(value);
                break;
            case Constants.WEBSITE:
                //implicit intent to visit website
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+value)));
                break;
        }
    }

    //we are calling this method to check the permission status
    private void makeCall(String value){
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permissions.CALL_PHONE);
        //if permission is granted returning true
        if(result== PackageManager.PERMISSION_GRANTED){
            startActivity(new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+value)));
        }else{
            requestCallPermision();
        }
    }

    //Requesting permission
    private void requestCallPermision(){
        if(ActivityCompat.ShouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE)){
            explainPermissionDialog();
        }
        ActivityCompat.requestPermission(this,new String[]{Manifest.permission.CALL_PHONE},RUNTIME_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if(requestCode==RUNTIME_PERMISSION_CODE){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission granted now you can make the phone call", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void explainPermissionDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Call Permission Required");
        alertDialogBuilder.setMessage("This app requires Call permission to make phone calls. Please grant the permission")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_intent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
