package com.example.sportplay.controller;

import com.alibaba.fastjson.JSON;
import com.example.sportplay.bean.Movie;
import com.example.sportplay.bean.QueryMovie;
import com.example.sportplay.bean.QueryType;
import com.example.sportplay.bean.Type;
import com.example.sportplay.dao.MovieDao;
import com.example.sportplay.service.MovieService;
import com.example.sportplay.service.TypeService;
import com.example.sportplay.springbootvue.bean.Book;
import com.example.sportplay.springbootvue.repository.BookRepository;
import com.example.sportplay.springbootvue.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private BookService bookService;

    /**
     * 获取电影列表
     * @param queryMovie
     * @return
     */
    @RequestMapping("/allMovie")
    public @ResponseBody
    Map<String ,Object> getMovieList( QueryMovie queryMovie){
        //获取最大列表数和当前编号
        System.out.println(queryMovie);
        System.out.println(queryMovie.getType());
        if (!Objects.equals(queryMovie.getType(), "")){
            int i=bookService.updataBook(queryMovie.getType());
            System.out.println(i);
        }
        List<Movie> allMovie = movieService.getAllMovie(queryMovie);
        int movieCounts = movieService.getMovieCounts(queryMovie.getMovieName(),
                                   queryMovie.getType(), queryMovie.getActor());
        Map<String,Object> map = new HashMap<>();
        map.put("number",movieCounts);
        map.put("data",allMovie);
        return  map;
    }
    /**
     * 添加电影
     * @param movie
     * @return
     */
    @RequestMapping("/addMovie")
    public String addMovie(@RequestBody Movie movie ){
        System.out.println(movie);
        int i = movieService.addMovie(movie);
        String str = i >0?"success":"error";
        return str;
    }
    /**
     * 查询电影详情
     * @param id
     * @return
     */
    @RequestMapping("/getUpdateMovie")
    public String getUpdateMovie(int id){
        System.out.println("编号:"+id);
        Movie movie = movieService.getUpdateMovie(id);
        String users_json = JSON.toJSONString(movie);
        return users_json;
    }
    /**
     * 修改电影信息
     * @param movie
     * @return
     */
    @RequestMapping("/editMovie")
    public String editUser(@RequestBody Movie movie){
        System.out.println(movie);
        int i = movieService.editMovie(movie);
        String str = i >0?"success":"error";
        return str;
    }

    /**
     * 删除电影
     * @param id
     * @return
     */
    @RequestMapping("/deleteMovie")
    public String deleteUser(int[] id){
        System.out.println(id);
        int i = movieService.deleteMovie(id);
        String str = i >0?"success":"error";
        return str;
    }
}
