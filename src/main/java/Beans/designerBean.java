/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;

import Entitys.CategoryTb;
import Entitys.CelebrityTb;
import Entitys.DesignerTb;
import Entitys.MovieTb;
import Entitys.ProductTb;
import Entitys.SongTb;
import java.math.BigInteger;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Admin
 */
@Stateless
public class designerBean implements designerBeanLocal {
    
    @PersistenceContext(name = "my_persistence_unit")
    EntityManager em;

    // Designer
    @Override
    public void addDesigner(String name, String password, String email, Long mobileno, String gender, String image) {
      DesignerTb d = new DesignerTb();
      d.setName(name);
      d.setPassword(password);
      d.setEmail(email);
      d.setMobileno(mobileno);
      d.setGender(gender);
      d.setImage(image);
      d.setIsApproved(0);
      em.persist(d);
    }

    @Override
    public Collection<DesignerTb> getDesigners() {
     Collection<DesignerTb> designers = em.createNamedQuery("DesignerTb.findAll").getResultList();
     return designers;
    }

    @Override
    public void deleteDesigner(Integer id) {
       DesignerTb d = em.find(DesignerTb.class, id);
       em.remove(d);
    }

    @Override
    public void editDesigner(Integer id, String name, String password, String email, Long mobileno, String gender, String image, Integer is_approved) {
       DesignerTb d = em.find(DesignerTb.class, id);
       d.setName(name);
      d.setPassword(password);
      d.setEmail(email);
      d.setMobileno(mobileno);
      d.setGender(gender);
      d.setImage(image);
      d.setIsApproved(is_approved);
      em.merge(d);
    }

    // Product
    @Override
    public void addProduct(String name, Integer price, Integer stock, String size, String image, Integer cid, Integer mid, Integer cbid, Integer sid, Integer did) {
       ProductTb p = new ProductTb();
        CategoryTb c = em.find(CategoryTb.class, cid);
        MovieTb m = em.find(MovieTb.class, mid);
        CelebrityTb cb = em.find(CelebrityTb.class, cbid);
        SongTb s = em.find(SongTb.class, sid);
        DesignerTb d = em.find(DesignerTb.class, did);
        p.setName(name);
        p.setPrice(price);
        p.setStock(stock);
        p.setSize(size);
        p.setImage(image);
        p.setCategoryId(c);
        p.setMovieId(m);
        p.setCelebrityId(cb);
        p.setSongId(s);
        p.setDesignerId(d);
        em.persist(p);
    }

    @Override
    public Collection<ProductTb> getProducts() {
        Collection<ProductTb> products = em.createNamedQuery("ProductTb.findAll").getResultList();
        return products;
    }

    @Override
    public void deleteProduct(Integer id) {
        ProductTb p = em.find(ProductTb.class, id);
        em.remove(p);
    }

    @Override
    public void editProduct(Integer id, String name, Integer price, Integer stock, String size, String image, Integer cid, Integer mid, Integer cbid, Integer sid, Integer did) {
        ProductTb p = em.find(ProductTb.class, id);
        CategoryTb c = em.find(CategoryTb.class, cid);
        MovieTb m = em.find(MovieTb.class, mid);
        CelebrityTb cb = em.find(CelebrityTb.class, cbid);
        SongTb s = em.find(SongTb.class, sid);
        DesignerTb d = em.find(DesignerTb.class, did);
        p.setName(name);
        p.setPrice(price);
        p.setStock(stock);
        p.setSize(size);
        p.setImage(image);
        p.setCategoryId(c);
        p.setMovieId(m);
        p.setCelebrityId(cb);
        p.setSongId(s);
        p.setDesignerId(d);
        em.merge(p);
    }
    
}
