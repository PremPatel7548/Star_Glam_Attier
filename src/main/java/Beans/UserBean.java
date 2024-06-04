/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;

import Entitys.GroupMaster;
import Entitys.ProductTb;
import Entitys.UserCartTb;
import Entitys.UserOrderTb;
import Entitys.UserTb;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author Admin
 */
@Stateless
public class UserBean implements UserBeanLocal {

    @PersistenceContext(name = "my_persistence_unit")
    EntityManager em;

    @Override
    public void RegisterUser(String name, String password, String email, String mobileno, String gender, String address, String image, Date dob) {
        UserTb ut = new UserTb();
        ut.setName(name);
        Pbkdf2PasswordHashImpl pb = new Pbkdf2PasswordHashImpl();
        String hash = pb.generate(password.toCharArray());
        ut.setPassword(hash);
        ut.setEmail(email);
        ut.setMobileno(mobileno);
        ut.setGender(gender);
        ut.setAddress(address);
        ut.setImage(image);
        ut.setDob(dob);
        em.persist(ut);
    }

    @Override
    public void afterRegister(String email) {
        GroupMaster gp = new GroupMaster();
        gp.setGroupname("User");
        gp.setUsername(email);
        em.persist(gp);
    }

    @Override
    public Collection<UserTb> displayUsers() {
        Collection<UserTb> users = em.createNamedQuery("UserTb.findAll").getResultList();
        return users;
    }

    @Override
    public void deleteUser(Integer id) {
        UserTb ut = em.find(UserTb.class, id);
        em.remove(ut);
    }

    @Override
    public void editUser(Integer id, String name, String password, String email, String mobileno, String gender, String address, String image, Date dob) {
        UserTb ut = em.find(UserTb.class, id);
        ut.setName(name);
        ut.setPassword(password);
        ut.setEmail(email);
        ut.setMobileno(mobileno);
        ut.setGender(gender);
        ut.setAddress(address);
        ut.setImage(image);
        ut.setDob(dob);
        em.merge(ut);
    }

    @Override
    public UserTb getUserById(Integer id) {
        UserTb ut = em.find(UserTb.class, id);
        return ut;
    }

    @Override
    public Collection<ProductTb> getAllProduct() {
        Collection<ProductTb> products = em.createNamedQuery("ProductTb.findAll").getResultList();
        return products;
    }

    @Override
    public Collection<UserCartTb> getCartProducts(Integer uid) {
        UserTb u = em.find(UserTb.class, uid);
        Collection<UserCartTb> cartProducts = em.createQuery("select c from UserCartTb c where c.userId=:user")
                .setParameter("user", u)
                .getResultList();

        return cartProducts;
    }

    @Override
    public void addToCart(Integer uid, Integer pid, String size, Integer qty, Integer price) {
        UserTb user = em.find(UserTb.class, uid);
        ProductTb product = em.find(ProductTb.class, pid);
        Collection<UserCartTb> uc = em.createQuery("select c from UserCartTb c where c.userId=:user and c.productId=:product and c.size=:size")
                .setParameter("user", user)
                .setParameter("product", product)
                .setParameter("size",size)
                .getResultList();
        if (uc.isEmpty()) {
            UserCartTb uct = new UserCartTb();
            UserTb u = em.find(UserTb.class, uid);
            ProductTb p = em.find(ProductTb.class, pid);
            uct.setUserId(u);
            uct.setProductId(p);
            uct.setSize(size);
            uct.setQty(qty);
            uct.setPrice(price);
            Integer total = qty * price;
            uct.setTotal(total);
            em.persist(uct);
        } else {
            for (UserCartTb c : uc) {
                UserCartTb uct = em.find(UserCartTb.class, c.getId());
                uct.setQty(uct.getQty()+qty);
                uct.setTotal(uct.getQty()*uct.getPrice());
                em.merge(uct);
            }
        }
    }

    @Override
    public void removefromCart(Integer cid) {
        UserCartTb uct = em.find(UserCartTb.class, cid);
        em.remove(uct);
    }

    @Override
    public void increasecartProductQuantity(Integer cid) {
        UserCartTb uct = em.find(UserCartTb.class, cid);
        uct.setQty(uct.getQty() + 1);
        uct.setTotal(uct.getQty() * uct.getPrice());
        em.merge(uct);
    }

    @Override
    public void decreasecartProductQuantity(Integer cid) {
        UserCartTb uct = em.find(UserCartTb.class, cid);
        uct.setQty(uct.getQty() - 1);
        uct.setTotal(uct.getQty() * uct.getPrice());
        em.merge(uct);
    }

    @Override
    public Collection<UserOrderTb> getOrderHistory(Integer uid) {
        UserTb u = em.find(UserTb.class, uid);
        Collection<UserOrderTb> orders = em.createQuery("select o from UserOrderTb o where o.userId=:user")
                .setParameter("user", u)
                .getResultList();
        return orders;
    }

    @Override
    public Integer countOfCartProduct(Integer uid) {
        UserTb u = em.find(UserTb.class, uid);
        TypedQuery<Long> query = em.createQuery("select count(c) from UserCartTb c where c.userId=:user", Long.class)
                .setParameter("user", u);
        Long count = query.getSingleResult();
        return count.intValue();
    }

    @Override
    public void addOrder(Integer uid, Integer pid, String size, Integer qty, Integer price) {
        UserOrderTb uot = new UserOrderTb();
        UserTb u = em.find(UserTb.class, uid);
        ProductTb p = em.find(ProductTb.class, pid);
        uot.setUserId(u);
        uot.setProductId(p);
        uot.setSize(size);
        uot.setQty(qty);
        uot.setPrice(price);
        Integer total = qty * price;
        uot.setTotal(total);
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());
        Date date = Date.from(zonedDateTime.toInstant());
        uot.setOrderDate(date);
        uot.setIsConfirmed(0);
        em.persist(uot);
    }

    @Override
    public ProductTb getProductDetails(Integer pid) {
        ProductTb p = em.find(ProductTb.class, pid);
        return p;
    }

    @Override
    public Integer getUserIdByUsername(String username) {
        TypedQuery<UserTb> query = em.createNamedQuery("UserTb.findByEmail", UserTb.class);
        query.setParameter("email", username);
        List<UserTb> users = query.getResultList();
        System.out.println("User ID ========= " + users.get(0).getId());

        return users.get(0).getId();
    }

    @Override
    public Collection<ProductTb> getProductByCategory(String category) {

        String queryStr = "SELECT p FROM ProductTb p WHERE p.categoryId.categoryName LIKE :category";
        TypedQuery<ProductTb> query = em.createQuery(queryStr, ProductTb.class);
        query.setParameter("category", "%" + category + "%");
        return query.getResultList();
    }

    @Override
    public Collection<ProductTb> getProductByMovie(String movie) {
        Collection<ProductTb> products = em.createQuery("select p from ProductTb p where p.movieId.name LIKE :movie")
                .setParameter("movie", "%" + movie + "%")
                .getResultList();
        return products;
    }

    @Override
    public Collection<ProductTb> getProductBySong(String song) {
        Collection<ProductTb> products = em.createQuery("select p from ProductTb p where p.songId.name LIKE :song")
                .setParameter("song", "%" + song + "%")
                .getResultList();
        return products;
    }

    @Override
    public Collection<ProductTb> getProductByCelebrity(String celebrity) {
        Collection<ProductTb> products = em.createQuery("select p from ProductTb p where p.celebrityId.name LIKE :celebrity")
                .setParameter("celebrity", "%" + celebrity + "%")
                .getResultList();
        return products;
    }

    @Override
    public Collection<ProductTb> getProductByName(String name) {
        Collection<ProductTb> products = em.createQuery("select p from ProductTb p where p.name LIKE :name")
                .setParameter("name", "%"+name+"%")
                .getResultList();
        return products;
    }

}
