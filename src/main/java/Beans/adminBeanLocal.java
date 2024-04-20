/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Beans;

import Entitys.CategoryTb;
import Entitys.CelebrityTb;
import Entitys.MovieCategoryTb;
import Entitys.MovieCelebrity;
import Entitys.MovieDesigner;
import Entitys.MovieTb;
import Entitys.SongTb;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface adminBeanLocal {
    
    // Categorytb
    public void addCategory(String name);
    public Collection<CategoryTb> getCategorys();
    public void deleteCategory(Integer id);
    public void editCategory(Integer id,String name);
    
    // movie_category
    public void addMovieCategory(String name);
    public Collection<MovieCategoryTb> getMovieCategorys();
    public void deleteMovieCategory(Integer id);
    public void editMovieCategory(Integer id,String name);
    
    //celebrity
    public void addCelebrity(String name,Date dob,String gender,String image);
    public Collection<CelebrityTb> getCelebritys();
    public void deleteCelebrity(Integer id);
    public void editCelebrity(Integer id,String name,Date dob,String gender,String image);
    
    //Movie
    public void addMovie(String name,Date date,Integer mcid);
    public Collection<MovieTb> getMovies();
    public void deleteMovie(Integer id);
    public void editMovie(Integer id,String name,Date date,Integer mcid);
    
    //Song
    public void addSong(String name,Integer mid);
    public Collection<SongTb> getSongs();
    public void deleteSong(Integer id);
    public void editSong(Integer id,String name,Integer mid);
    
    // movie_celebrity
    public void addMovieCelebrity(Integer mid,Integer cid);
    public Collection<MovieCelebrity> getMovieCelebrity();
    public void deleteMovieCelebrity(Integer id);
    public void editMovieCelebrity(Integer id,Integer mid,Integer cid);
    
    //Movie_designer
    public void addMovieDesigner(Integer mid,Integer did);
    public Collection<MovieDesigner> getMovieDesigner();
    public void deleteMovieDesigner(Integer id);
    public void editMovieDesigner(Integer id,Integer mid,Integer did);
}
