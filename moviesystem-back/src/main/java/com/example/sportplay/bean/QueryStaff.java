package com.example.sportplay.bean;

public class QueryStaff {

    private String name;
    private int pageNum = 1;
    private int pageSize = 1;

    public QueryStaff() {
    }

    public QueryStaff(String name, int pageNum, int pageSize) {
        this.name = name;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "QueryInfo{" +
                "username='" + name + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
