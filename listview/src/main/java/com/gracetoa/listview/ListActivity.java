package com.gracetoa.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView)findViewById(R.id.listView);

        names = new ArrayList<String>();
        names.add("Grace");
        names.add("Carlo");
        names.add("Lucas");
        names.add("Julian");
        names.add("Paquito");
        names.add("Lluna");
        names.add("Grace");
        names.add("Carlo");
        names.add("Lucas");
        names.add("Julian");
        names.add("Paquito");
        names.add("Lluna");
        names.add("Grace");
        names.add("Carlo");
        names.add("Lucas");
        names.add("Julian");
        names.add("Paquito");
        names.add("Lluna");

        //enlazamos con adaptador por defecto
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String >(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);*/

        //enlazamos el adaptador personalizado
        MyAdapter myAdapter = new MyAdapter(this, R.layout.list_item, names);
        listView.setAdapter(myAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



            }
        });

    }
}

