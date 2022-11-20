package com.example.sportplay.service;

import com.example.sportplay.bean.News;
import com.example.sportplay.bean.QueryNews;
import java.util.List;


public interface NewsService {
    /**
     * 查询所有新闻和模糊查询新闻
     * @return
     */
    public List<News> getAllNews(QueryNews queryNews);

    /**
     * 查询新闻记录数
     * @param title
     * @return
     */
    public int getNewsCounts(String title);

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
     * @param ids
     * @return
     */
    public int deleteNews(int[] ids);

}
