package com.example.sportplay.dao;

import com.example.sportplay.bean.News;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsDao {

    /**
     * 查询所有新闻和模糊查询新闻
     * @param title
     * @param pageStart
     * @param pageSize
     * @return
     */
    public List<News> getAllNews(@Param("title") String title, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    /**
     * 查询新闻记录数
     * @param title
     * @return
     */
    public int getNewsCounts(@Param("title")String title);

    /**
     * 添加新闻类别
     * @param news
     * @return
     */
    public int addNews(News news);

    /**
     * 查询新闻详细信息
     * @param id
     * @return
     */
    public News getUpdateNews(int id);

    /**
     * 修改新闻
     * @param news
     * @return
     */
    public int editNews(News news);


    /**
     * 删除新闻
     * @param id
     * @return
     */
    public int deleteNews(int id);
}
