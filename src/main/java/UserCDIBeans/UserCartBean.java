/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package UserCDIBeans;

import Beans.UserBeanLocal;
import Entitys.UserCartTb;
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
@Named(value = "userCartBean")
@Dependent
public class UserCartBean {

    @EJB UserBeanLocal ub;
    RestClient rc;
    Collection<UserCartTb> carts;
    GenericType<Collection<UserCartTb>> gc;
    Response res;
    @Inject LoginBean lb;
    Integer cartProductCount;
    Integer totalPrice;
    UserCartTb uc = new UserCartTb();
    
    public UserCartBean() {
        rc = new RestClient();
        carts = new ArrayList<>();
        gc = new GenericType<Collection<UserCartTb>>(){};
    }

    public Collection<UserCartTb> getCarts() {
        res = rc.getCartProducts(Response.class,String.valueOf(lb.getuId()));
        carts = res.readEntity(gc);
        return carts;
    }

    public Integer getCartProductCount() {
        cartProductCount = ub.countOfCartProduct(lb.getuId());
        return cartProductCount;
    }

    public void setCartProductCount(Integer cartProductCount) {
        this.cartProductCount = cartProductCount;
    }

    public UserCartTb getUc() {
        return uc;
    }

    public void setUc(UserCartTb uc) {
        this.uc = uc;
    }

    public Integer getTotalPrice() {
        totalPrice = 0;
        getCarts();
        for(UserCartTb c : carts )
        {
            totalPrice += c.getTotal();
        }
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCarts(Collection<UserCartTb> carts) {
        this.carts = carts;
    }
    
    public String removeCart(Integer cid)
    {
        rc.removefromCart(String.valueOf(cid));
        return "Shoppingcart";
    }
    
    public void addQuantity()
    {
        
    }
    
    public void decreseQuantity()
    {
        
    }
}
