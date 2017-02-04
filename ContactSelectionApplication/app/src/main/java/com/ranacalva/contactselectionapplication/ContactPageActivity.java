package com.ranacalva.contactselectionapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ContactPageActivity extends AppCompatActivity implements View.OnClickListener {

    private ContactObject contactObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);

        TextView contactName = (TextView)findViewById(R.id.contactName);
        TextView contactPhone = (TextView)findViewById(R.id.contactPhone);
        TextView contactWebsite = (TextView)findViewById(R.id.contactWebsite);

        Bundle extras = getIntent().getExtras();
        if(extras==null)
            return;
        contactObject = (ContactObject)getIntent().getSerializableExtra("Object");
        contactName.setText(contactObject.getName());
        contactPhone.setText(String.format("Phone:%s", contactObject.getPhone()));
        contactWebsite.setText(String.format("Website:%s", contactObject.getWebsite()));
        contactPhone.setOnClickListener(this);
        contactWebsite.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.contactPhone:
                Intent i = new Intent();
                i.putExtra("value",contactObject.getPhone());
                setResult(Constants.PHONE,i);
                finish();
                break;
            case R.id.contactWebsite:
                Intent i = new Intent();
                i.putExtra("value",contactObject.getWebsite());
                setResult(Constants.WEBSITE, i);
                finish();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_page, menu);
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
