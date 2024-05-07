/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;

import Entitys.GroupMaster;
import Entitys.UserTb;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public void RegisterUser(String name,String password,String email,String mobileno,String gender,String address,String image,Date dob)
    {
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
    public void afterRegister(String username)
    {
        GroupMaster gp = new GroupMaster();
        gp.setGroupname("User");
        gp.setUsername(username);
        em.persist(gp);
    }
    
    @Override
    public Collection<UserTb> displayUsers()
    {
        Collection<UserTb> users = em.createNamedQuery("UserTb.findAll").getResultList();
        return users;
    }
    
    @Override
    public void deleteUser(Integer id)
    {
        UserTb ut = em.find(UserTb.class, id);
        em.remove(ut);
    }
    
    @Override
    public void editUser(Integer id,String name,String password,String email,String mobileno,String gender,String address,String image,Date dob)
    {
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
    public UserTb getUserById(Integer id)
    {
        UserTb ut = em.find(UserTb.class, id);
        return ut;
    }
}
