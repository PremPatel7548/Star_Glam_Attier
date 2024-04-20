/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Beans;

import Entitys.DesignerTb;
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
    public void addDesigner(String name,String password,String email,Long mobileno,String gender,String image);
    public Collection<DesignerTb> getDesigners();
    public void deleteDesigner(Integer id);
    public void editDesigner(Integer id,String name,String password,String email,Long mobileno,String gender,String image,Integer is_approved);
    
    
}
