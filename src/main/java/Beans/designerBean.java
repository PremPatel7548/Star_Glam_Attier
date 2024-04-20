/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Beans;

import Entitys.DesignerTb;
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
    
}
