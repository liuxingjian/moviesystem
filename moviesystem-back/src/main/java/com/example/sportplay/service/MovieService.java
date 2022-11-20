package com.example.sportplay.service;


import com.example.sportplay.bean.Movie;
import com.example.sportplay.bean.QueryMovie;
import com.example.sportplay.bean.QueryType;
import com.example.sportplay.bean.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface MovieService {
    /**
     * 查询电影和模糊查询电影
     * @return
     */
    public List<Movie> getAllMovie(QueryMovie queryMovie);

    /**
     * 查询电影记录数
     * @return
     */
    public int getMovieCounts(String movieName,
                              String type,
                              String actor
    );

    /**
     * 添加电影
     * @param movie
     * @return
     */
    public int addMovie(Movie movie);

    /**
     * 查询电影详细信息
     * @param id
     * @return
     */
    public Movie getUpdateMovie(int id);

    /**
     * 修改电影
     * @param movie
     * @return
     */
    public int editMovie(Movie movie);


    /**
     * 删除电影
     * @param ids
     * @return
     */
    public int deleteMovie(int[] ids);

}
