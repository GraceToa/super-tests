package com.gracetoa.reciclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Contact> contacts;
    private int layout;
    private OnItemClickListener itemClickListener;

    public MyAdapter(List<Contact> contacts,int layout,OnItemClickListener itemClickListener){
        this.contacts = contacts;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(contacts.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textVieName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textVieName = (TextView) itemView.findViewById(R.id.textViewName);
        }

        public void bind (final Contact contact, final  OnItemClickListener onItemClickListener){

            this.textVieName.setText(contact.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(contact, getAdapterPosition());
                }
            });
        }
    }




    public interface OnItemClickListener {
        void onItemClick(Contact contact, int position);
    }










}// end class
