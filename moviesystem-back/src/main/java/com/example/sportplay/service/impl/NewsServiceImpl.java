package com.example.sportplay.service.impl;

import com.example.sportplay.bean.News;
import com.example.sportplay.bean.QueryNews;
import com.example.sportplay.bean.Type;
import com.example.sportplay.dao.NewsDao;
import com.example.sportplay.service.NewsService;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("newsService")
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;
    @Override
    public List<News> getAllNews(QueryNews queryNews) {
        int pageStart=(queryNews.getPageNum()-1) * queryNews.getPageSize();
        List<News> list = newsDao.getAllNews("%"+queryNews.getTitle()+"%", pageStart, queryNews.getPageSize());
        return list;
    }

    @Override
    public int getNewsCounts(String title) {
        int counts = newsDao.getNewsCounts("%"+title+"%");
        System.out.println(counts);
        return counts;
    }

    @Override
    public int addNews(News news) {
        int i = newsDao.addNews(news);
        return i;
    }

    @Override
    public News getUpdateNews(int id) {
        News updateNews = newsDao.getUpdateNews(id);
        return updateNews;
    }

    @Override
    public int editNews(News news) {
        System.out.println("ssss"+news);
        int i = newsDao.editNews(news);
        return i;
    }

    @Override
    public int deleteNews(int[] ids) {
        for (int i = 0; i < ids.length; i++) {
            newsDao.deleteNews(ids[i]);
        }
        return 1;
    }
}
