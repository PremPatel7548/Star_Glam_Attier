/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package AdminCDIBeans;

import Entitys.MovieTb;
import RestFullClient.RestClient;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
@Named(value = "adminMoviesBean")
@RequestScoped
public class AdminMoviesBean {

    /**
     * Creates a new instance of AdminMoviesBean
     */
    Collection<MovieTb> MovieList;
    GenericType<Collection<MovieTb>> gc;
    Response res;
    RestClient rc;
    MovieTb movie = new MovieTb();
    Integer MoviecategoryID;

    public AdminMoviesBean() {
        rc = new RestClient();
        gc = new GenericType<Collection<MovieTb>>() {
        };
        MovieList = new ArrayList<>();
    }

    public Collection<MovieTb> getMovieList() {
        res = rc.displayMovie(Response.class);
        MovieList = res.readEntity(gc);
        return MovieList;
    }

    public void setMovieList(Collection<MovieTb> MovieList) {
        this.MovieList = MovieList;
    }

    public MovieTb getMovie() {
        return movie;
    }

    public void setMovie(MovieTb movie) {
        this.movie = movie;
    }

    public Integer getMoviecategoryID() {
        return MoviecategoryID;
    }

    public void setMoviecategoryID(Integer MoviecategoryID) {
        this.MoviecategoryID = MoviecategoryID;
    }

    public String addMovies() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String relesedate = dateFormat.format(movie.getReleaseDate());
        rc.addMovie(movie.getName(), String.valueOf(relesedate), String.valueOf(MoviecategoryID));
        return "MovieList";
    }

    public String deletemovie(Integer movieid) {
        rc.deleteMovie(String.valueOf(movieid));
        return "MovieList";
    }

    public String getdataForeditmovie(MovieTb movie) {
        this.movie = movie;
        return "EditMovie";
    }

    public String editmovie() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String relesedate = dateFormat.format(movie.getReleaseDate());
        rc.updateMovie(String.valueOf(movie.getId()), movie.getName(), String.valueOf(relesedate), String.valueOf(MoviecategoryID));
        return "MovieList";
    }

}
