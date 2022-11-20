package com.example.sportplay.bean;

public class QueryInfo {

    private String username;
    private int pageNum = 1;
    private int pageSize = 1;

    public QueryInfo() {
    }

    public QueryInfo(String username, int pageNum, int pageSize) {
        this.username = username;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                "username='" + username + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
