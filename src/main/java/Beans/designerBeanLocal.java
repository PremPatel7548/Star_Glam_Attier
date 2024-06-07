/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Beans;

import Entitys.CategoryTb;
import Entitys.DesignerTb;
import Entitys.MovieCelebrity;
import Entitys.MovieDesigner;
import Entitys.MovieTb;
import Entitys.ProductTb;
import Entitys.SongTb;
import Entitys.UserOrderTb;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface designerBeanLocal {
    
    // Designer
    public void addDesigner(String name,String password,String email,String mobileno,String gender,String image);
    public Collection<DesignerTb> getDesigners();
    public void deleteDesigner(Integer id);
    public void editDesigner(Integer id,String name,String password,String email,String mobileno,String gender,String image,Integer is_approved);
    public DesignerTb getDesignerById(Integer id);
    
    // Product
    public void addProduct(String name,Integer price,Integer stock,String image,Integer cid,Integer mid,Integer cbid,Integer sid,Integer did);
    public Collection<ProductTb> getProducts();
    public void deleteProduct(Integer id);
    public void editProduct(Integer id,String name,Integer price,Integer stock,String image,Integer cid,Integer mid,Integer cbid,Integer sid,Integer did);
    
    public Collection<ProductTb> getProductByDesigner(Integer did);
    public Collection<MovieDesigner> getMovieByDesigner(Integer did);
    public Collection<MovieCelebrity> getCelebrityByMovie(Integer mid);
    public Collection<SongTb> getSongByMovie(Integer mid);
    public Collection<CategoryTb> getCategorys();
    
    public boolean checkLogin(String email,String password);
    
    public Collection<UserOrderTb> getUserOrders(Integer did);
    public void acceptOrder(Integer id,Integer uid,Integer pid,String size,Integer qty,Integer price,Integer total,Date odate,Integer ic);
    public void editStock(Integer pid,Integer qty);
    
    public Integer countProducts(Integer did);
    public Integer countPendingOrders(Integer did);
    public Integer countEmptyProducts(Integer did);
}
