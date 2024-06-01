/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package UserCDIBeans;

import Entitys.UserOrderTb;
import RestFullClient.RestClient;
import java.util.ArrayList;
import java.util.Collection;
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

    RestClient rc;
    Collection<UserOrderTb> historys;
    GenericType<Collection<UserOrderTb>> gh;
    Response res;
    @Inject LoginBean lb;
    
    public UserHistoryBean() {
        rc = new RestClient();
        historys = new ArrayList<>();
        gh = new GenericType<Collection<UserOrderTb>>(){};
    }

    public Collection<UserOrderTb> getHistorys() {
        res = rc.getOrderHistory(Response.class,String.valueOf(lb.getuId()));
        historys = res.readEntity(gh);
        return historys;
    }

    public void setHistorys(Collection<UserOrderTb> historys) {
        this.historys = historys;
    }
    
    
    
}
