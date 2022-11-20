package com.example.sportplay.dao;

import com.example.sportplay.bean.Type;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeDao {
    /**
     * 获取所有电影类型
     * @return
     */
    public List<Type> getTypes();
    /**
     * 查询所有电影类别和模糊查询类别
     * @param name
     * @param pageStart
     * @param pageSize
     * @return
     */
    public List<Type> getAllType(@Param("name") String name,@Param("pageStart") int pageStart,@Param("pageSize") int pageSize);

    /**
     * 查询电影类别记录数
     * @param name
     * @return
     */
    public int getTypeCounts(@Param("name")String name);

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
     * @param id
     * @return
     */
    public int deleteType(int id);
}
