package com.ranacalva.androidatccourses;

import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    private String[] namearray = {
            "Java Fundamentals for Android Development",
            "Android Applicaction Development",
            "Android Security Essentials",
            "Monetize Android Applications",
            "Java Fundamentals for Android Development",
            "Android Application Development",
            "Android Security Essentials",
            "Monetize Android Applications"};
    private Integer[] id_ = {0,1,2,3,4,5,6,7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton btn_add = (FloatingActionButton) findViewById(R.id.btn_add);
        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        myOnClickListener = new MyOnClickListener();
        btn_add.setOnClickListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayotManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data = new ArrayList<>();
        for(int i=0;i<namearray.length;i++)
        {
            data.add(new DataModel(namearray[i],id_[i]));
        }

        removedItems = new ArrayList<>();
        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v)
    {
        if(removedItems.size()!=0){
            addRemovedItemToList();
        }else{
            Toast.makeText(this,"Nothing to add",Toast.LENGTH_SHORT).show();
        }
    }

    private class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            removeItem();
        }

        private void removeItem(View v){
            int selectedItemPosition = recyclerView.getChildLayoutPosition(v);
            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(selectedItemPosition);
            TextView textViewName = (TextView) viewHolder.itemView.findViewById(R.id.textViewName);
            String selectedName = (String) textViewName.getText();
            int selectedItemId=-1;
            for(int i=0;i<namearray.length;i++){
                if(selectedName.equals(namearray[i])){
                    selectedItemId = id_[i];
                }
            }
            removedItems.add(selectedItemId);
            data.remove(selectedItemPosition);
            adapter.notifyItemRemoved(selectedItemPosition);
        }
    }

    private void addRemovedItemToList(){
        int addItemAtListPosition = 0;
        data.add(addItemAtListPosition,new DataModel(namearray[removedItems.get(0)],id_[removedItems.get(0)]));
        adapter.notifyItemInserted(addItemAtListPosition);
        removedItems.remove(0);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


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
