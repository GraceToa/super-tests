package com.gracetoa.recyclerviewandcardview;

public class Movie {

    private String name;
    private int poster;
    private String descrip;

    public Movie(String name, int poster, String descrip) {
        this.name = name;
        this.poster = poster;
        this.descrip = descrip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", poster=" + poster +
                ", descrip='" + descrip + '\'' +
                '}';
    }
}
