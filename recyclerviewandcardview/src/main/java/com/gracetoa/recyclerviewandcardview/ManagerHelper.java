package com.gracetoa.recyclerviewandcardview;

import java.util.ArrayList;
import java.util.List;

public class ManagerHelper {

    public List<Movie> movies = getMovies();
    private int counter ;


    public List<Movie> getMovies() {
        return new ArrayList<Movie>() {{
            add(new Movie("Jocker",R.drawable.jocker,"Joaquin Phoenix"));
            add(new Movie("IT",R.drawable.it,"the Crown"));
            add(new Movie("Terminator",R.drawable.terminator,"Arnold Swaneger"));
            add(new Movie("Jhon Wich",R.drawable.jhon,"Keanu Reeves"));
        }};
    }


    public void  addContact(int position){


    }

    public void deleteContact(int position){
        movies.remove(position);
    }
}
