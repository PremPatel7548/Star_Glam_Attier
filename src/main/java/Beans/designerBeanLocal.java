/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Beans;

import Entitys.DesignerTb;
import Entitys.ProductTb;
import java.math.BigInteger;
import java.util.Collection;
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
    
    // Product
    public void addProduct(String name,Integer price,Integer stock,String size,String image,Integer cid,Integer mid,Integer cbid,Integer sid,Integer did);
    public Collection<ProductTb> getProducts();
    public void deleteProduct(Integer id);
    public void editProduct(Integer id,String name,Integer price,Integer stock,String size,String image,Integer cid,Integer mid,Integer cbid,Integer sid,Integer did);
}
