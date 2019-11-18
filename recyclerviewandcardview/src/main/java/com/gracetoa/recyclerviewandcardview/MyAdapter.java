package com.gracetoa.recyclerviewandcardview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Movie> movies;
    private int layout;
    private OnItemClickListener itemClickListener;
    private Context context;

    public MyAdapter(List<Movie> movies,int layout,OnItemClickListener itemClickListener){
        this.movies = movies;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        context = parent.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(movies.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }




    public  class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textVieName;
        public TextView textViewDescript;
        public ImageView imageViewPoster;

        //Recibe una View, le pasa la constructor padre y enlaza referencias UI
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textVieName = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewDescript = (TextView) itemView.findViewById(R.id.textViewDescript);
            imageViewPoster = (ImageView)itemView.findViewById(R.id.imageViewPoster);
        }

        public void bind (final Movie movie, final  OnItemClickListener onItemClickListener){
            //procesamos datos a renderizar
            textVieName.setText(movie.getName());
            textViewDescript.setText(movie.getDescrip());
            Picasso.get().load(movie.getPoster()).fit().into(imageViewPoster);
            //imageViewPoster.setImageResource(movie.getPoster());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(movie, getAdapterPosition());
                }
            });
        }
    }




    public interface OnItemClickListener {
        void onItemClick(Movie movie, int position);
    }










}// end class
