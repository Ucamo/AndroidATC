package com.androidatc.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void addRecord(View view)
    {
        //Add a new record
        ContentValues values = new ContentValues();
        if((!((EditText)findViewById(R.id.name)).getText().toString().isEmpty()) &&
                (!((EditText)findViewById(R.id.nickname)).getText().toString().isEmpty()))
        {
            values.put(CustomContentProvider.NAME, ((EditText)findViewById(R.id.name)).getText().toString());
            values.put(CustomContentProvider.NICK_NAME, ((EditText)findViewById(R.id.nickname)).getText().toString());
            Uri uri = getContentResolver().insert(CustomContentProvider.CONTENT_URI, values);
            Toast.makeText(getBaseContext(), "Record Inserted", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getBaseContext(), "Please enter the records first", Toast.LENGTH_LONG).show();
        }
    }
    public void showAllRecords(View view){
        String URL = "content://com.androidatc.provider/nicknames";
        Uri friends = Uri.parse(URL);
        Cursor c = getContentResolver().query(friends, null, null, null, "name");
        String result = "Content Provider Results:";
        if(!c.moveToFirst())
        {
            Toast.makeText(this, result + " no content yet!", Toast.LENGTH_LONG).show();
        }
        else
        {
            do {
                result = result
                        + "\n"
                        + c.getString(c.getColumnIndex(CustomContentProvider.NAME))
                        + "With id"
                        + c.getString(c.getColumnIndex(CustomContentProvider.ID))
                        + "has nickname: "
                        + c.getString(c.getColumnIndex(CustomContentProvider.NICK_NAME));
            }
            while(c.moveToNext());
            if(!result.isEmpty())
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "No records present", Toast.LENGTH_LONG).show();
        }
    }
    public void deleteAllRecords(View view)
    {
        String URL = "content://com.androidatc.provider/nicknames";
        Uri friends = Uri.parse(URL);
        int count = getContentResolver().delete(friends, null, null);
        String countName = count + " records are deleted";
        Toast.makeText(getBaseContext(), countName, Toast.LENGTH_LONG).show();
    }
}
