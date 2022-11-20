package com.example.sportplay.service.impl;

import com.example.sportplay.bean.Staff;
import com.example.sportplay.dao.StaffDao;
import com.example.sportplay.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("staffService")
public class StaffServiceImpl implements StaffService {
    @Autowired
    public StaffDao staffDao;
    @Override
    public List<Staff> getAllStaff() {
   //     int pageStart=1;
        return staffDao.getAllStaff();
    }

    @Override
    public int addStaff(Staff staff) {
        return staffDao.addStaff(staff);
    }

    @Override
    public int deleteStaff(int id) {
        staffDao.deleteStaff(id);
        return id;
    }

    @Override
    public int editStaff(Staff staff) {
        return staffDao.editStaff(staff);
    }

    @Override
    public Staff getStaff(int id) {
        return staffDao.getStaff(id);
    }


}
