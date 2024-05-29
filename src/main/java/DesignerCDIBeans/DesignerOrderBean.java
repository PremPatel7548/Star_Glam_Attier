/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package DesignerCDIBeans;

import Beans.designerBeanLocal;
import Entitys.UserOrderTb;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@Named(value = "designerOrderBean")
@RequestScoped
public class DesignerOrderBean {

    @EJB designerBeanLocal dl;
    Collection<UserOrderTb> orders;
    UserOrderTb uot = new UserOrderTb();
    Integer sessionid;
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    HttpSession session = request.getSession();
    String successmessage;
    String errormessage;
    
    /**
     * Creates a new instance of DesignerOrderBean
     */
    public DesignerOrderBean() {
        orders = new ArrayList<>();
    }
    
    @PostConstruct
    public void init() {
        sessionid = (Integer) session.getAttribute("designerId");
    }
    public Integer getSessionid() {
        return sessionid;
    }

    public void setSessionid(Integer sessionid) {
        this.sessionid = sessionid;
    }

    public Collection<UserOrderTb> getOrders() {
        orders = dl.getUserOrders(sessionid);
        return orders;
    }

    public void setOrders(Collection<UserOrderTb> orders) {
        this.orders = orders;
    }

    public String getSuccessmessage() {
        return successmessage;
    }

    public void setSuccessmessage(String successmessage) {
        this.successmessage = successmessage;
    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }
    
    public String acceptOrder(UserOrderTb uot)
    {
        this.uot = uot;
        
        if(uot.getProductId().getStock() < uot.getQty())
        {
            errormessage = "Product Out Of Stock !! Order Not Confirmed";
            return "userOrders";
        }
        else
        {
            dl.acceptOrder(uot.getId(),uot.getUserId().getId(),uot.getProductId().getId() ,uot.getSize(),uot.getQty(), uot.getPrice(), uot.getTotal(),uot.getOrderDate(),1);
            dl.editStock(uot.getProductId().getId(),uot.getQty());
            successmessage = "Order Confirmed";
            return "userOrders";
        }
    }
    
    public void clearSuccessMessage() {
        successmessage = null;
        errormessage = null;
    }
    
    
}
