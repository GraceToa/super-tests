package com.gracetoa.reciclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contact> contacts;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ManagerHelper managerHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //contacts = this.getContacts();
        //managerHelper = new ManagerHelper();
        //contacts = managerHelper.contacts;

        contacts = ManagerHelper.shared.getContacts();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);

        //otros Layour Manager
        //layoutManager = new GridLayoutManager(this,2);
        //puede servir para fotos
        //layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        myAdapter = new MyAdapter(contacts,R.layout.recicler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Contact contact, int position) {
                Toast.makeText(MainActivity.this, contact.toString() + "-" + position, Toast.LENGTH_LONG).show();

                ManagerHelper.shared.deleteContact(position);
                myAdapter.notifyItemRemoved(position);
            }
        });

        //si sabemos que el tamaño no cambiara del item
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(myAdapter);

    }

    //menu options

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.add_name:
                //0 para ponerlo siempre top
                ManagerHelper.shared.addContact(0);
                myAdapter.notifyItemInserted(0);
                layoutManager.scrollToPosition(0);
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }









}//class
