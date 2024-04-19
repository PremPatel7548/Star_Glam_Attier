/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;

import Entitys.CategoryTb;
import Entitys.MovieCategoryTb;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Admin
 */
@Stateless
public class adminBean implements adminBeanLocal {
    
    @PersistenceContext(name = "my_persistence_unit")
    EntityManager em;

    // Category
    @Override
    public void addCategory(String name) {
        CategoryTb c = new CategoryTb();
        c.setCategoryName(name);
        em.persist(c);
    }

    @Override
    public Collection<CategoryTb> getCategorys() {
        Collection<CategoryTb> categorys = em.createNamedQuery("CategoryTb.findAll").getResultList();
        return categorys;
    }

    @Override
    public void deleteCategory(Integer id) {
        CategoryTb c = em.find(CategoryTb.class, id);
        em.remove(c);
    }

    @Override
    public void editCategory(Integer id, String name) {
        CategoryTb c = em.find(CategoryTb.class, id);
        c.setCategoryName(name);
        em.merge(c);
    }

    // Movie_category
    @Override
    public void addMovieCategory(String name) {
        MovieCategoryTb mc = new MovieCategoryTb();
        mc.setCategoryName(name);
        em.persist(mc);
    }

    @Override
    public Collection<MovieCategoryTb> getMovieCategorys() {
        Collection<MovieCategoryTb> mCategorys = em.createNamedQuery("MovieCategoryTb.findAll").getResultList();
        return mCategorys;
    }

    @Override
    public void deleteMovieCategory(Integer id) {
        MovieCategoryTb mc = em.find(MovieCategoryTb.class, id);
        em.remove(mc);
    }

    @Override
    public void editMovieCategory(Integer id, String name) {
        MovieCategoryTb mc = em.find(MovieCategoryTb.class, id);
        mc.setCategoryName(name);
        em.merge(mc);
    }

}
