/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package AdminCDIBeans;

import Beans.adminBeanLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Admin
 */
@Named(value = "adminDashboardBean")
@RequestScoped
public class AdminDashboardBean {

    @EJB adminBeanLocal al;
    Integer category_count;
    Integer movie_count;
    Integer song_count;
    Integer celebrity_count;
    Integer designer_count;
    Integer movie_category_count;
    Integer movie_celebrity_count;
    Integer movie_designer_count;
    
    public AdminDashboardBean() {
    }

    public Integer getCategory_count() {
        category_count = al.countCategory();
        return category_count;
    }

    public void setCategory_count(Integer category_count) {
        this.category_count = category_count;
    }

    public Integer getMovie_count() {
        movie_count = al.countMovie();
        return movie_count;
    }

    public void setMovie_count(Integer movie_count) {
        this.movie_count = movie_count;
    }

    public Integer getSong_count() {
        song_count = al.countSong();
        return song_count;
    }

    public void setSong_count(Integer song_count) {
        this.song_count = song_count;
    }

    public Integer getCelebrity_count() {
        celebrity_count = al.countCelebrity();
        return celebrity_count;
    }

    public void setCelebrity_count(Integer celebrity_count) {
        this.celebrity_count = celebrity_count;
    }

    public Integer getDesigner_count() {
        designer_count = al.countDesigners();
        return designer_count;
    }

    public void setDesigner_count(Integer designer_count) {
        this.designer_count = designer_count;
    }

    public Integer getMovie_category_count() {
        movie_category_count = al.countMovieCategory();
        return movie_category_count;
    }

    public void setMovie_category_count(Integer movie_category_count) {
        this.movie_category_count = movie_category_count;
    }

    public Integer getMovie_celebrity_count() {
        movie_celebrity_count = al.countMovieCelebrity();
        return movie_celebrity_count;
    }

    public void setMovie_celebrity_count(Integer movie_celebrity_count) {
        this.movie_celebrity_count = movie_celebrity_count;
    }

    public Integer getMovie_designer_count() {
        movie_designer_count = al.countMovieDesigners();
        return movie_designer_count;
    }

    public void setMovie_designer_count(Integer movie_designer_count) {
        this.movie_designer_count = movie_designer_count;
    }
    
}
