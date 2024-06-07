/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;

import Entitys.CategoryTb;
import Entitys.CelebrityTb;
import Entitys.DesignerTb;
import Entitys.MovieCelebrity;
import Entitys.MovieDesigner;
import Entitys.MovieTb;
import Entitys.ProductTb;
import Entitys.SongTb;
import Entitys.UserOrderTb;
import Entitys.UserTb;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

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
    public void addDesigner(String name, String password, String email, String mobileno, String gender, String image) {
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
    public void editDesigner(Integer id, String name, String password, String email,String mobileno, String gender, String image, Integer is_approved) {
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
    public void addProduct(String name, Integer price, Integer stock, String image, Integer cid, Integer mid, Integer cbid, Integer sid, Integer did) {
       ProductTb p = new ProductTb();
        CategoryTb c = em.find(CategoryTb.class, cid);
        MovieTb m = em.find(MovieTb.class, mid);
        CelebrityTb cb = em.find(CelebrityTb.class, cbid);
        SongTb s = em.find(SongTb.class, sid);
        DesignerTb d = em.find(DesignerTb.class, did);
        p.setName(name);
        p.setPrice(price);
        p.setStock(stock);
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
    public void editProduct(Integer id, String name, Integer price, Integer stock, String image, Integer cid, Integer mid, Integer cbid, Integer sid, Integer did) {
        ProductTb p = em.find(ProductTb.class, id);
        CategoryTb c = em.find(CategoryTb.class, cid);
        MovieTb m = em.find(MovieTb.class, mid);
        CelebrityTb cb = em.find(CelebrityTb.class, cbid);
        SongTb s = em.find(SongTb.class, sid);
        DesignerTb d = em.find(DesignerTb.class, did);
        p.setName(name);
        p.setPrice(price);
        p.setStock(stock);
        p.setImage(image);
        p.setCategoryId(c);
        p.setMovieId(m);
        p.setCelebrityId(cb);
        p.setSongId(s);
        p.setDesignerId(d);
        em.merge(p);
    }

    @Override
    public Collection<ProductTb> getProductByDesigner(Integer did) {
        DesignerTb dt = em.find(DesignerTb.class, did);
        Collection<ProductTb> products = em.createQuery("select p from ProductTb p where p.designerId =:designer")
                .setParameter("designer",dt)
                .getResultList();
        return products;
    }

    @Override
    public Collection<MovieDesigner> getMovieByDesigner(Integer did) {
        DesignerTb dt = em.find(DesignerTb.class,did);
        Collection<MovieDesigner> movieDesigner = em.createQuery("select m from MovieDesigner m where m.designerId=:designer")
                .setParameter("designer",dt)
                .getResultList();
        return movieDesigner;
    }

    @Override
    public Collection<MovieCelebrity> getCelebrityByMovie(Integer mid) {
       MovieTb mt = em.find(MovieTb.class,mid);
       Collection<MovieCelebrity> movieCelebrity = em.createQuery("select m from MovieCelebrity m where m.movieId=:movie")
               .setParameter("movie", mt)
               .getResultList();
       return movieCelebrity;
    }

    @Override
    public Collection<SongTb> getSongByMovie(Integer mid) {
        MovieTb mt = em.find(MovieTb.class, mid);
        Collection<SongTb> songs = em.createQuery("select s from SongTb s where s.movieId=:movie")
                .setParameter("movie", mt)
                .getResultList();
        return songs;
    }

    @Override
    public Collection<CategoryTb> getCategorys() {
        Collection<CategoryTb> categorys = em.createNamedQuery("CategoryTb.findAll").getResultList();
        return categorys;
    }

    @Override
    public boolean checkLogin(String email, String password) {
        Collection<DesignerTb> designers = em.createQuery("select d from DesignerTb d where d.email=:email and d.password=:password and d.isApproved=1")
                .setParameter("email",email)
                .setParameter("password", password)
                .getResultList();
        
        if(designers.isEmpty())
        {
            return false;
        }
        else
        {
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            
            for(DesignerTb d : designers)
            {
                session.setAttribute("designerId", d.getId());
            }
            return true;
        }
    }

    @Override
    public Collection<UserOrderTb> getUserOrders(Integer did) {
        DesignerTb dt = em.find(DesignerTb.class,did);
        
        Collection<UserOrderTb> orders = em.createQuery("select o from UserOrderTb o where o.productId.designerId=:designer")
                  .setParameter("designer", dt)
                  .getResultList();
        
        return orders;
    }

    @Override
    public void acceptOrder(Integer id, Integer uid, Integer pid, String size, Integer qty, Integer price, Integer total, Date odate, Integer ic) {
        UserOrderTb uot = em.find(UserOrderTb.class,id);
        UserTb u = em.find(UserTb.class,uid);
        ProductTb p = em.find(ProductTb.class,pid);
        uot.setUserId(u);
        uot.setProductId(p);
        uot.setSize(size);
        uot.setQty(qty);
        uot.setPrice(price);
        uot.setTotal(total);
        uot.setOrderDate(odate);
        uot.setIsConfirmed(ic);
        em.merge(uot);
    }

    @Override
    public void editStock(Integer pid, Integer qty) {
        ProductTb p = em.find(ProductTb.class,pid);
        p.setStock(p.getStock() - qty);
        em.merge(p);
    }

    @Override
    public DesignerTb getDesignerById(Integer id) {
        DesignerTb dt = em.find(DesignerTb.class, id);
        return dt;
    }

    @Override
    public Integer countProducts(Integer did) {
        DesignerTb dt = em.find(DesignerTb.class,did);
        TypedQuery<Long> query = em.createQuery("select count(c) from ProductTb c WHERE c.designerId=:designer",Long.class)
                .setParameter("designer", dt);
        Long count = query.getSingleResult();
        return count.intValue();
    }

    @Override
    public Integer countPendingOrders(Integer did) {
        DesignerTb dt = em.find(DesignerTb.class,did);
        TypedQuery<Long> query = em.createQuery("select count(c) from UserOrderTb c WHERE c.productId.designerId=:designer AND c.isConfirmed=0",Long.class)
                .setParameter("designer", dt);
        Long count = query.getSingleResult();
        return count.intValue();
    }

    @Override
    public Integer countEmptyProducts(Integer did) {
        DesignerTb dt = em.find(DesignerTb.class,did);
        TypedQuery<Long> query = em.createQuery("select count(c) from ProductTb c WHERE c.designerId=:designer AND c.stock=0",Long.class)
                .setParameter("designer", dt);
        Long count = query.getSingleResult();
        return count.intValue();
    }
    
 
}
