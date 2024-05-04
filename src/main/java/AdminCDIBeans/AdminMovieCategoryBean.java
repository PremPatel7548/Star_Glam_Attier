/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package AdminCDIBeans;

import Entitys.MovieCategoryTb;
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
@Named(value = "adminMovieCategoryBean")
@RequestScoped
public class AdminMovieCategoryBean {

    /**
     * Creates a new instance of AdminMovieCategoryBean
     */
    Collection<MovieCategoryTb> moviecategoryList;
    GenericType<Collection<MovieCategoryTb>> gc;
    Response res;
    RestClient rc;
    MovieCategoryTb moviecategory = new MovieCategoryTb();

    public AdminMovieCategoryBean() {
        rc = new RestClient();
        gc = new GenericType<Collection<MovieCategoryTb>>() {
        };
        moviecategoryList = new ArrayList<>();
    }

    public Collection<MovieCategoryTb> getMoviecategoryList() {
        res = rc.displayMovieCategorys(Response.class);
        moviecategoryList = res.readEntity(gc);
        return moviecategoryList;
    }

    public void setMoviecategoryList(Collection<MovieCategoryTb> moviecategoryList) {
        this.moviecategoryList = moviecategoryList;
    }

    public MovieCategoryTb getMoviecategory() {
        return moviecategory;
    }

    public void setMoviecategory(MovieCategoryTb moviecategory) {
        this.moviecategory = moviecategory;
    }

    public String addmovieCategory() {
        rc.addMovieCategory(moviecategory.getCategoryName());
        return "MovieCategoryList";
    }

    public String getdataForeditmovieCategory(MovieCategoryTb moviecategory) {
        this.moviecategory = moviecategory;
        return "EditMovieCategory";
    }

    public String editmovieCategory() {
        rc.updateMovieCategory(String.valueOf(moviecategory.getId()), moviecategory.getCategoryName());
        return "MovieCategoryList";
    }

    public String deletemovieCategory(Integer moviecategoryid) {
        rc.deleteMovieCategory(String.valueOf(moviecategoryid));
        return "MovieCategoryList";
    }

}
