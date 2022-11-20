package com.example.sportplay.controller;

import com.alibaba.fastjson.JSON;
import com.example.sportplay.bean.News;
import com.example.sportplay.bean.QueryNews;
import com.example.sportplay.bean.QueryType;
import com.example.sportplay.bean.Type;
import com.example.sportplay.service.NewsService;
import com.example.sportplay.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class NewsController {
    @Autowired
    private NewsService newsService;
    /**
     * 获取新闻列表
     * @param queryNews
     * @return
     */
    @RequestMapping("/allNews")
    public @ResponseBody
    Map<String ,Object> getNewsList(QueryNews queryNews){
        System.out.println(queryNews);
        //获取最大列表数和当前编号
        List<News> allNews = newsService.getAllNews(queryNews);
        int newsCounts = newsService.getNewsCounts(queryNews.getTitle());
        System.out.println(newsCounts);
        Map<String,Object> map = new HashMap<>();
        map.put("number",newsCounts);
        map.put("data",allNews);
        return  map;
    }
    /**
     * 添加新闻
     * @param news
     * @return
     */
    @RequestMapping("/addNews")
    public String add(@RequestBody News news){
        int i = newsService.addNews(news);
        String str = i >0?"success":"error";
        return str;
    }
    /**
     * 查询新闻详情
     * @param id
     * @return
     */
    @RequestMapping("/getUpdateNews")
    public String getUpdateNews(int id){
        System.out.println("编号:"+id);
        News news= newsService.getUpdateNews(id);
        System.out.println(news);
        String users_json = JSON.toJSONString(news);
        return users_json;
    }
    /**
     * 修改新闻信息
     * @param news
     * @return
     */
    @RequestMapping("/editNews")
    public String editUser(@RequestBody News news){
        System.out.println(news);
        int i = newsService.editNews(news);
        String str = i >0?"success":"error";
        return str;
    }

    /**
     * 删除新闻
     * @param id
     * @return
     */
    @RequestMapping("/deleteNews")
    public String deleteUser(int[] id){
        System.out.println(id);
        int i = newsService.deleteNews(id);
        String str = i >0?"success":"error";
        return str;
    }



}
