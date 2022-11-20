package com.example.sportplay.dao;

import com.example.sportplay.bean.Staff;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffDao {
    public List<Staff> getAllStaff();
    public int addStaff(Staff staff);
    public int deleteStaff(int id);
    public int editStaff(Staff staff);
    public Staff getStaff(int id);

}
