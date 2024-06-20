/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package UserCDIBeans;

import Beans.UserBeanLocal;
import Entitys.UserCancelOrderTb;
import Entitys.UserOrderTb;
import RestFullClient.RestClient;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import loginBean.LoginBean;

/**
 *
 * @author Admin
 */
@Named(value = "userHistoryBean")
@Dependent
public class UserHistoryBean {

    @EJB UserBeanLocal ubl;
    Collection<UserCancelOrderTb> cancels;
    RestClient rc;
    Collection<UserOrderTb> historys;
    GenericType<Collection<UserOrderTb>> gh;
    Response res;
    @Inject LoginBean lb;
    UserOrderTb uot = new UserOrderTb();
    
    public UserHistoryBean() {
        rc = new RestClient();
        historys = new ArrayList<>();
        gh = new GenericType<Collection<UserOrderTb>>(){};
        cancels = new ArrayList<>();
    }

    public UserOrderTb getUot() {
        return uot;
    }

    public void setUot(UserOrderTb uot) {
        this.uot = uot;
    }

    public Collection<UserOrderTb> getHistorys() {
        res = rc.getOrderHistory(Response.class,String.valueOf(lb.getuId()));
        historys = res.readEntity(gh);
        return historys;
    }

    public void setHistorys(Collection<UserOrderTb> historys) {
        this.historys = historys;
    }
    
    public String cancelOrder(UserOrderTb uo)
    {
//        rc.addCancelOrder(String.valueOf(uo.getUserId().getId()), String.valueOf(uo.getProductId().getId()),uo.getSize(),String.valueOf(uo.getQty()), String.valueOf(uo.getPrice()), String.valueOf(uo.getTotal()),String.valueOf(uo.getOrderDate()));
        ubl.addCancelOrder(uo.getUserId().getId(), uo.getProductId().getId(), uo.getSize(), uo.getQty(), uo.getPrice(), uo.getTotal(),uo.getOrderDate());
        rc.cancelOrder(String.valueOf(uo.getId()));
        return "userHistory";
    }

    public Collection<UserCancelOrderTb> getCancels() {
        cancels = ubl.getCancelOrders(lb.getuId());
        return cancels;
    }

    public void setCancels(Collection<UserCancelOrderTb> cancels) {
        this.cancels = cancels;
    }
    
    
}
