/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Beans;

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
    public void RegisterUser(String name,String password,String email,String mobileno,String address,String image,Date dob);
    public void afterRegister(String username);
    public Collection<UserTb> displayUsers();
    public void deleteUser(Integer id);
    public void editUser(Integer id,String name,String password,String email,String mobileno,String address,String image,Date dob);
    public UserTb getUserById(Integer id);
}
