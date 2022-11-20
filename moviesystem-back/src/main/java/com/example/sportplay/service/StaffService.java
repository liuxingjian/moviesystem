package com.example.sportplay.service;

import com.example.sportplay.bean.Staff;

import java.util.List;

public interface StaffService {
    public List<Staff> getAllStaff();
    public int addStaff(Staff staff);
    public int deleteStaff(int id);
    public int editStaff(Staff staff);
    public Staff getStaff(int id);
}
