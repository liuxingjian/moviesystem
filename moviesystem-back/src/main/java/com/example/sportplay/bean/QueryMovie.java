package com.example.sportplay.bean;

public class QueryMovie {
    private String movieName;
    private String type;
    private String actor;
    private int pageNum = 1;
    private int pageSize = 1;

    @Override
    public String toString() {
        return "QueryMovie{" +
                "movieName='" + movieName + '\'' +
                ", type='" + type + '\'' +
                ", actor='" + actor + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
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

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
