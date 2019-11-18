package com.gracetoa.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {

    private GridView gridView;
    private List<String> names;
    private MyAdapter myAdapter;
    private  int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        gridView = (GridView)findViewById(R.id.gridView);

        names = new ArrayList<String>();
        names.add("Grace");
        names.add("Carlo");
        names.add("Lucas");


        //enlazamos con adaptador por defecto
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String >(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);*/

        //enlazamos el adaptador personalizado
        myAdapter = new MyAdapter(this, R.layout.grid_item, names);
        gridView.setAdapter(myAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(GridActivity.this,"Clicked" + names.get(position), Toast.LENGTH_SHORT).show();

            }
        });

        //le pasamos el grid que contiene los elementos donde queremos lanzar el context menu
        registerForContextMenu(gridView);

    }//onCreate


    //Inflamos el layout del menu opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.action_bar_menu,menu);

        return true;

    }

    //Manejamos eventos click en el menu de opciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.add_item:
                //añadimos nuevo nombre
                this.names.add("Added nº" + (++count));
                //notificamos al adaptador de los cambios
                this.myAdapter.notifyDataSetChanged();
            return true;

            default:  return super.onOptionsItemSelected(item);

        }


    }

    // Inflamos el layout del context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();

        // personalizamos la ventana del menu
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.names.get(info.position));

        menuInflater.inflate(R.menu.context_menu,menu);
    }

    // Manejo de eventos click en el contex menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){

            case R.id.delete_item:
                // borramos item clickeado, info nos da la posicion
                this.names.remove(info.position);
                //notificamos al adaptador de los cambios
                this.myAdapter.notifyDataSetChanged();
                return true;

            default:  return super.onOptionsItemSelected(item);

        }

    }
}//class
