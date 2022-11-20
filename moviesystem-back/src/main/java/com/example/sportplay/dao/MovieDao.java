package com.example.sportplay.dao;

import com.example.sportplay.bean.Movie;
import com.example.sportplay.bean.Type;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDao {

    /**
     * 查询电影和模糊查询电影
     * @param pageStart
     * @param pageSize
     * @return
     */
    public List<Movie> getAllMovie(@Param("movieName") String movieName,
                                  @Param("type") String type,
                                  @Param("actor") String actor,
                                  @Param("pageStart") int pageStart,@Param("pageSize") int pageSize);

    /**
     * 查询电影记录数
     * @return
     */
    public int getMovieCounts(@Param("movieName") String movieName,
                              @Param("type") String type,
                              @Param("actor") String actor
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
     * @param id
     * @return
     */
    public int deleteMovie(int id);
}
