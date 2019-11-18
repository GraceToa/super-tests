package com.gracetoa.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private List<String> names;

    public MyAdapter(Context context,int layout, List<String>names){
        this.context = context;
        this.layout = layout;
        this.names = names;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return this.names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //View Holder Pattern
        ViewHolder holder;

        if (convertView == null){
            //inflamos la vista que nos ha llegado con nuestro layout personalizado
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.layout,null);

            holder = new ViewHolder();
            //referenciamos el elemento a modificar y lo rellenamos
            holder.nameTexView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }


        //nos traemos el valor actual dependiendo de la posición
        String currenName = names.get(position);

        //si queremos usar este método, pero no se usa en general
        //currenName = (String) getItem(position);

        //Referenciamos el elemento a modificar y lo rellenamos
        holder.nameTexView.setText(currenName);


        return convertView;
    }


    static  class ViewHolder {
        private TextView nameTexView;
    }
}


