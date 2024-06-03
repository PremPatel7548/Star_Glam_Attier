/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Beans;

import Entitys.ProductTb;
import Entitys.UserCartTb;
import Entitys.UserOrderTb;
import Entitys.UserTb;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface UserBeanLocal {
    public void RegisterUser(String name,String password,String email,String mobileno,String gender,String address,String image,Date dob);
    public void afterRegister(String email);
    public Collection<UserTb> displayUsers();
    public void deleteUser(Integer id);
    public void editUser(Integer id,String name,String password,String email,String mobileno,String gender,String address,String image,Date dob);
    public UserTb getUserById(Integer id);
    public Integer getUserIdByUsername(String username);
    
    //products
    public Collection<ProductTb> getAllProduct();
    public ProductTb getProductDetails(Integer pid);
    
    //cart
    public Collection<UserCartTb> getCartProducts(Integer uid);
    public void addToCart(Integer uid,Integer pid,String size,Integer qty,Integer price);
    public void removefromCart(Integer cid);
    public void increasecartProductQuantity(Integer cid);
    public void decreasecartProductQuantity(Integer cid);
    public Integer countOfCartProduct(Integer uid);
    
    //user order
    public void addOrder(Integer uid,Integer pid,String size,Integer qty,Integer price);
    public Collection<UserOrderTb> getOrderHistory(Integer uid);
    
    public Collection<ProductTb> getProductByCategory(String category);
    public Collection<ProductTb> getProductByMovie(String movie);
    public Collection<ProductTb> getProductBySong(String song);
    public Collection<ProductTb> getProductByCelebrity(String celebrity);
}
