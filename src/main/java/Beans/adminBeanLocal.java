/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Beans;

import Entitys.CategoryTb;
import Entitys.MovieCategoryTb;
import java.util.Collection;
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
}
