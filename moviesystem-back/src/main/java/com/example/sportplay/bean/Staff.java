package com.example.sportplay.bean;

public class Staff {
    private Integer id;
    private String name;
    private String sex;
    private String tel;
    public Staff(){}
    @Override
    public String toString() {
        return "staff{" +
                "id=" + id +
                ", admin='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }

    public Staff(Integer id, String name, String sex, String tel) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.tel = tel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
