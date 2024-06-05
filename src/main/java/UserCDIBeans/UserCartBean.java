/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package UserCDIBeans;

import Beans.UserBeanLocal;
import Entitys.UserCartTb;
import Entitys.UserTb;
import RestFullClient.RestClient;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import loginBean.LoginBean;

/**
 *
 * @author Admin
 */
@Named(value = "userCartBean")
@SessionScoped
public class UserCartBean implements Serializable {

    @EJB 
    UserBeanLocal ub;
    RestClient rc;
    Collection<UserCartTb> carts;
    GenericType<Collection<UserCartTb>> gc;
    GenericType<UserTb> gut;
    GenericType<Integer> gi;
    Response res;
    @Inject 
    LoginBean lb;
    Integer cartProductCount;
    Integer totalPrice;
    UserCartTb uc = new UserCartTb();
    UserTb ut = new UserTb();
    
    public UserCartBean() {
        rc = new RestClient();
        carts = new ArrayList<>();
        gc = new GenericType<Collection<UserCartTb>>(){};
        gi = new GenericType<Integer>(){};
        gut = new GenericType<UserTb>(){};
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
        for (UserCartTb c : getCarts()) {
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

    public UserTb getUt() {
        res = rc.getUserById(Response.class,String.valueOf(lb.getuId()));
        ut = res.readEntity(gut);
        return ut;
    }

    public void setUt(UserTb ut) {
        this.ut = ut;
    }
    
    public String removeCart(Integer cid) {
        rc.removefromCart(String.valueOf(cid));
        // Recalculate totals after removal
        getCarts();
        return "Shoppingcart";
    }
    
    public void addQuantity(Integer cid) {
        System.out.println("Quantity ++");
        rc.increasecartProductQuantity(String.valueOf(cid));
        // Recalculate totals after quantity change
        getCarts();
    }
    
    public void decreaseQuantity(Integer cid) {
        System.err.println("Quantity --");
        rc.decreasecartProductQuantity(String.valueOf(cid));
        // Recalculate totals after quantity change
        getCarts();
    }
    
    public String processPayment(String mode)
    {
        for(UserCartTb c : carts)
        {
//            rc.addOrder(String.valueOf(lb.getuId()),String.valueOf(c.getProductId().getId()),c.getSize(),String.valueOf(c.getQty()), String.valueOf(c.getPrice()));
            res = rc.addOrder(Response.class,String.valueOf(lb.getuId()),String.valueOf(c.getProductId().getId()),c.getSize(),String.valueOf(c.getQty()), String.valueOf(c.getPrice()));
            Integer orderId = res.readEntity(Integer.class);
            System.out.println("Order Id :- "+orderId+" -----------------------------");
//            Integer orderId = ub.addOrder(lb.getuId(),c.getProductId().getId(),c.getSize(), c.getQty(),c.getTotal());
            rc.removefromCart(String.valueOf(c.getId()));
            rc.addPayment(String.valueOf(lb.getuId()),String.valueOf(orderId),mode);
        }
        
        return "Products";
    }
    
}
