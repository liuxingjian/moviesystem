package com.example.sportplay.service.impl;

import com.example.sportplay.bean.QueryType;
import com.example.sportplay.bean.Type;
import com.example.sportplay.dao.TypeDao;
import com.example.sportplay.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("typeService")
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;


    @Override
    public List<Type> getTypes() {
        List<Type> types = typeDao.getTypes();
        return types;
    }

    @Override
    public List<Type> getAllType(QueryType queryType) {
        int pageStart=(queryType.getPageNum()-1) * queryType.getPageSize();
        List<Type> list = typeDao.getAllType("%"+queryType.getName()+"%", pageStart, queryType.getPageSize());
        return list;
    }

    @Override
    public int getTypeCounts(String name) {
        int counts = typeDao.getTypeCounts("%" + name+ "%");
        return counts;
    }

    @Override
    public int addType(Type type) {
        int count = typeDao.addType(type);
        return count;
    }

    @Override
    public Type getUpdateType(int id) {
        Type type = typeDao.getUpdateType(id);
        return type;
    }

    @Override
    public int editType(Type type) {
        int count = typeDao.editType(type);
        return count;
    }

    @Override
    public int deleteType(int[] ids) {
        for (int i = 0; i < ids.length; i++) {
            typeDao.deleteType(ids[i]);
        }
        return 1;
    }
}
