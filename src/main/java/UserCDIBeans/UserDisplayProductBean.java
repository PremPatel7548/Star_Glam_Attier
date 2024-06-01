/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package UserCDIBeans;

import Entitys.ProductTb;
import RestFullClient.RestClient;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
@Named(value = "userDisplayProductBean")
@Dependent
public class UserDisplayProductBean {

    RestClient rc;
    Collection<ProductTb> products;
    GenericType<Collection<ProductTb>> gp;
    Response res;
    ProductTb p = new ProductTb();
    
    public UserDisplayProductBean() {
        rc = new RestClient();
        products = new ArrayList<>();
        gp = new GenericType<Collection<ProductTb>>(){};
    }

    public Collection<ProductTb> getProducts() {
        res = rc.getAllProduct(Response.class);
        products = res.readEntity(gp);
        return products;
    }

    public void setProducts(Collection<ProductTb> products) {
        this.products = products;
    }

    public ProductTb getP() {
        return p;
    }

    public void setP(ProductTb p) {
        this.p = p;
    }
    
    
}
