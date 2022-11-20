package com.example.sportplay.bean;

import java.util.Date;

public class Movie {
    private Integer id;
    private String movieName;
    private String type;
    private String actor;
    private String director;
    private String minS;
    private Date movieDate;
    private String film;

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", type='" + type + '\'' +
                ", actor='" + actor + '\'' +
                ", director='" + director + '\'' +
                ", minS='" + minS + '\'' +
                ", movieDate=" + movieDate +
                ", film='" + film + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getMinS() {
        return minS;
    }

    public void setMinS(String minS) {
        this.minS = minS;
    }

    public Date getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(Date movieDate) {
        this.movieDate = movieDate;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }
}
