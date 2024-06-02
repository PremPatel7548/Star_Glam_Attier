package UserCDIBeans;

import Entitys.ProductTb;
import RestFullClient.RestClient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import loginBean.LoginBean;

@Named(value = "userDisplayProductBean")
@SessionScoped
public class UserDisplayProductBean implements Serializable {
    private RestClient rc;
    private Collection<ProductTb> products;
    private GenericType<Collection<ProductTb>> gp;
    private Response res;
    private GenericType<ProductTb> gpp;
    private ProductTb p;
    String size;
    @Inject LoginBean lb;
    Integer qty;

    public UserDisplayProductBean() {
        rc = new RestClient();
        products = new ArrayList<>();
        gp = new GenericType<Collection<ProductTb>>() {};
        gpp = new GenericType<ProductTb>() {};
        p = new ProductTb();
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public void getProductDetail(Integer pid) {
        System.out.println("Fetching Product Details for ID: " + pid);
        res = rc.getProductDetails(Response.class, String.valueOf(pid));
        this.p = res.readEntity(gpp);
        System.out.println("Product Name: " + p.getName());
    }
    
    public String addToCart()
    {
        rc.addToCart(String.valueOf(lb.getuId()),String.valueOf(p.getId()),this.size,String.valueOf(this.qty), String.valueOf(p.getPrice()));
        return "Products";
    }
}
