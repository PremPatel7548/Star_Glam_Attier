/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;

import Entitys.CategoryTb;
import Entitys.CelebrityTb;
import Entitys.DesignerTb;
import Entitys.MovieCategoryTb;
import Entitys.MovieCelebrity;
import Entitys.MovieDesigner;
import Entitys.MovieTb;
import Entitys.SongTb;
import java.util.Collection;
import java.util.Date;
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

    // Celebrity
    @Override
    public void addCelebrity(String name, Date dob, String gender, String image) {
       CelebrityTb c = new CelebrityTb();
       c.setName(name);
       c.setDob(dob);
       c.setGender(gender);
       c.setImage(image);
       em.persist(c);
    }

    @Override
    public Collection<CelebrityTb> getCelebritys() {
        Collection<CelebrityTb> celebritys = em.createNamedQuery("CelebrityTb.findAll").getResultList();
        return celebritys;
    }

    @Override
    public void deleteCelebrity(Integer id) {
        CelebrityTb c = em.find(CelebrityTb.class, id);
        em.remove(c);
    }

    @Override
    public void editCelebrity(Integer id, String name, Date dob, String gender, String image) {
        CelebrityTb c = em.find(CelebrityTb.class, id);
        c.setName(name);
        c.setDob(dob);
        c.setGender(gender);
        c.setImage(image);
       em.merge(c);
    }

    // Movie
    @Override
    public void addMovie(String name, Date date, Integer mcid) {
        MovieTb m = new MovieTb();
        MovieCategoryTb mc = em.find(MovieCategoryTb.class, mcid);
        m.setName(name);
        m.setReleaseDate(date);
        m.setMovieCategoryId(mc);
        em.persist(m);
    }

    @Override
    public Collection<MovieTb> getMovies() {
        Collection<MovieTb> movies = em.createNamedQuery("MovieTb.findAll").getResultList();
        return movies;
    }

    @Override
    public void deleteMovie(Integer id) {
        MovieTb m = em.find(MovieTb.class, id);
        em.remove(m);
    }

    @Override
    public void editMovie(Integer id, String name, Date date, Integer mcid) {
        MovieTb m = em.find(MovieTb.class, id);
        MovieCategoryTb mc = em.find(MovieCategoryTb.class, mcid);
        m.setName(name);
        m.setReleaseDate(date);
        m.setMovieCategoryId(mc);
        em.merge(m);
    }

     // Song
    @Override
    public void addSong(String name, Integer mid) {
        SongTb s = new SongTb();
        MovieTb m = em.find(MovieTb.class,mid);
        s.setName(name);
        s.setMovieId(m);
        em.persist(s);
    }

    @Override
    public Collection<SongTb> getSongs() {
     Collection<SongTb> songs = em.createNamedQuery("SongTb.findAll").getResultList();
     return songs;
    }

    @Override
    public void deleteSong(Integer id) {
     SongTb s = em.find(SongTb.class, id);
     em.remove(s);
    }

    @Override
    public void editSong(Integer id, String name, Integer mid) {
    SongTb s = em.find(SongTb.class, id);
    MovieTb m = em.find(MovieTb.class,mid);
        s.setName(name);
        s.setMovieId(m);
        em.merge(s);
    }

    // MovieCelebrity
    @Override
    public void addMovieCelebrity(Integer mid, Integer cid) {
        MovieCelebrity mc = new MovieCelebrity();
        MovieTb m = em.find(MovieTb.class, mid);
        CelebrityTb c = em.find(CelebrityTb.class, cid);
        mc.setMovieId(m);
        mc.setCelebrityId(c);
        em.persist(mc);
    }

    @Override
    public Collection<MovieCelebrity> getMovieCelebrity() {
        Collection<MovieCelebrity> mcs = em.createNamedQuery("MovieCelebrity.findAll").getResultList();
        return mcs;
    }

    @Override
    public void deleteMovieCelebrity(Integer id) {
        MovieCelebrity mc = em.find(MovieCelebrity.class, id);
        em.remove(mc);
    }

    @Override
    public void editMovieCelebrity(Integer id, Integer mid, Integer cid) {
       MovieCelebrity mc = em.find(MovieCelebrity.class, id);
       MovieTb m = em.find(MovieTb.class, mid);
        CelebrityTb c = em.find(CelebrityTb.class, cid);
        mc.setMovieId(m);
        mc.setCelebrityId(c);
        em.merge(mc);
    }

//   MovieDesigner 
    @Override
    public void addMovieDesigner(Integer mid, Integer did) {
        MovieDesigner md = new MovieDesigner();
        MovieTb m = em.find(MovieTb.class, mid);
        DesignerTb d = em.find(DesignerTb.class, did);
        md.setMovieId(m);
        md.setDesignerId(d);
        em.persist(md);
    }

    @Override
    public Collection<MovieDesigner> getMovieDesigner() {
        Collection<MovieDesigner> mds = em.createNamedQuery("MovieDesigner.findAll").getResultList();
        return mds;
    }

    @Override
    public void deleteMovieDesigner(Integer id) {
        MovieDesigner md = em.find(MovieDesigner.class, id);
        em.remove(md);
    }

    @Override
    public void editMovieDesigner(Integer id, Integer mid, Integer did) {
       MovieDesigner md = em.find(MovieDesigner.class, id);
       MovieTb m = em.find(MovieTb.class, mid);
        DesignerTb d = em.find(DesignerTb.class, did);
        md.setMovieId(m);
        md.setDesignerId(d);
        em.merge(md);
    }

    @Override
    public Collection<MovieTb> getMovieForDesigner() {
        String sql = "SELECT m.* FROM movie_tb m LEFT JOIN movie_designer md ON m.id = md.movie_id WHERE md.movie_id IS NULL";
        Collection<MovieTb> movies = em.createNativeQuery(sql,MovieTb.class).getResultList();
        return movies;
    }
    
}
