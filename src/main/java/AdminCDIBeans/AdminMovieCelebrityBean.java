/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package AdminCDIBeans;

import Entitys.MovieCelebrity;
import RestFullClient.RestClient;
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
@Named(value = "adminMovieCelebrityBean")
@RequestScoped
public class AdminMovieCelebrityBean {

    /**
     * Creates a new instance of AdminMovieCelebrityBean
     */
    Collection<MovieCelebrity> movieCelebrityList;
    GenericType<Collection<MovieCelebrity>> gc;
    Response res;
    RestClient rc;
    MovieCelebrity movieCelebrity = new MovieCelebrity();
    Integer movie_id;
    Integer celebrity_id;
    String successmessage;

    public AdminMovieCelebrityBean() {
         rc = new RestClient();
        gc = new GenericType<Collection<MovieCelebrity>>() {
        };
        movieCelebrityList = new ArrayList<>();
    }

    public String getSuccessmessage() {
        return successmessage;
    }

    public void setSuccessmessage(String successmessage) {
        this.successmessage = successmessage;
    }
    
    

    public Collection<MovieCelebrity> getMovieCelebrityList() {
        res=rc.displayMovieCelebrity(Response.class);
        movieCelebrityList=res.readEntity(gc);
        return movieCelebrityList;
    }

    public void setMovieCelebrityList(Collection<MovieCelebrity> movieCelebrityList) {
        this.movieCelebrityList = movieCelebrityList;
    }

    public MovieCelebrity getMovieCelebrity() {
        return movieCelebrity;
    }

    public void setMovieCelebrity(MovieCelebrity movieCelebrity) {
        this.movieCelebrity = movieCelebrity;
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    public Integer getCelebrity_id() {
        return celebrity_id;
    }

    public void setCelebrity_id(Integer celebrity_id) {
        this.celebrity_id = celebrity_id;
    }
    
    public String addmovieCelebrity(){
        rc.addMovieCelebrity(String.valueOf(movie_id), String.valueOf(celebrity_id));
        successmessage = "MovieCelebrity Added Successfully";
        return "MovieCelebrityList";
    }
    
      public String deletemovieCelebrity(Integer id) {
        rc.deleteMovieCelebrity(String.valueOf(id));
        successmessage = "MovieCelebrity Deleted Successfully";
        return "MovieCelebrityList";
    }

    public String getdataFormovieCelebrity(MovieCelebrity movieCelebrity) {
        this.movieCelebrity = movieCelebrity;
        this.movie_id = movieCelebrity.getMovieId().getId();
        this.celebrity_id = movieCelebrity.getCelebrityId().getId();
        return "EditmovieCelebrity";
    }
    
    public String editmovieCelebrity(){
        rc.updateMovieCelebrity(String.valueOf(movieCelebrity.getId()),String.valueOf(movie_id), String.valueOf(celebrity_id));
        successmessage = "MovieCelebrity Edited Successfully";
        return "MovieCelebrityList";
    }
    
     public void clearSuccessMessage() {
        successmessage = null;
    }
    
}

