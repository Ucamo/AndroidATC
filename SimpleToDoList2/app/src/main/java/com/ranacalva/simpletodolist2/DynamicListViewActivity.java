package com.ranacalva.simpletodolist2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class DynamicListViewActivity extends AppCompatActivity {

    private EditText item;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_list_view);
        item = (EditText) findViewById(R.id.itemEditText);
        Button add = (Button)findViewById(R.id.addItemButton);
        ListView dynamicListView = new ListView(getApplicationContext());
        findViewById(R.id.itemListView);
        list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(DynamicListViewActivity.this,android.R.layout.simple_list_item_1,list);
        dynamicListView.setAdapter(adapter);
        //add item to the listView on click button (add)
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String todoItem = item.getText().toString();
                if(todoItem.length()>0)
                {
                    //add editText Value to the list
                    list.add(item.getText().toString());
                    //apply changes on the adapter tio refresh the listView
                    adapter.notifyDataSetChanged();
                    item.setText("");
                }
            }
        });
        dynamicListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                //remove the item from list
                list.remove(position);
                //apply changes on the adapter to refresh the listView
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dynamic_list_view, menu);
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
