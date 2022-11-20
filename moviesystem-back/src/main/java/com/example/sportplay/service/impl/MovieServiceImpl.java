package com.example.sportplay.service.impl;

import com.example.sportplay.bean.Movie;
import com.example.sportplay.bean.QueryMovie;
import com.example.sportplay.dao.MovieDao;
import com.example.sportplay.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("movieService")
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieDao movieDao;
    @Override
    public List<Movie> getAllMovie(QueryMovie queryMovie) {
        int pageStart=(queryMovie.getPageNum()-1) * queryMovie.getPageSize();
        List<Movie> list = movieDao.getAllMovie("%"+queryMovie.getMovieName()+"%",
                                                "%"+queryMovie.getType()+"%",
                                                "%"+queryMovie.getActor()+"%",
                                               pageStart, queryMovie.getPageSize());
        return list;
    }

    @Override
    public int getMovieCounts(String movieName, String type, String actor) {
        int counts = movieDao.getMovieCounts("%" + movieName+ "%","%" +type + "%",
                                              "%" +actor+ "%");
        return counts;
    }

    @Override
    public int addMovie(Movie movie) {
        System.out.println("逻辑层");
        System.out.println(movie);
        int i = movieDao.addMovie(movie);
        return i;
    }

    @Override
    public Movie getUpdateMovie(int id) {
        Movie movie = movieDao.getUpdateMovie(id);
        return movie;
    }

    @Override
    public int editMovie(Movie movie) {
        int i = movieDao.editMovie(movie);
        return i;
    }

    @Override
    public int deleteMovie(int[] ids) {
        for (int i = 0; i < ids.length; i++) {
           movieDao.deleteMovie(ids[i]);
        }
        return 1;
    }
}
