package com.example.sportplay.service;


import com.example.sportplay.bean.QueryType;
import com.example.sportplay.bean.Type;

import java.util.List;


public interface TypeService {
    /**
     * 获取所有电影类型
     * @return
     */
    public List<Type> getTypes();


    /**
     * 查询所有电影类别和模糊查询类别
     * @param queryType
     * @return
     */
    public List<Type> getAllType(QueryType queryType);

    /**
     * 查询电影类别记录数
     * @param name
     * @return
     */
    public int getTypeCounts(String name);

    /**
     * 添加电影类别
     * @param type
     * @return
     */
    public int addType(Type type);

    /**
     * 查询电影类别详细信息
     * @param id
     * @return
     */
    public Type getUpdateType(int id);

    /**
     * 修改电影类别
     * @param type
     * @return
     */
    public int editType(Type type);


    /**
     * 删除电影类别
     * @param ids
     * @return
     */
    public int deleteType(int[] ids);

}
