/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package DesignerCDIBeans;

import Entitys.ProductTb;
import RestFullClient.RestClient;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
@Named(value = "designerProductStockBean")
@SessionScoped
public class DesignerProductStockBean implements Serializable {

    RestClient rc;
    ProductTb pt = new ProductTb();
    Collection<ProductTb> products;
    GenericType<Collection<ProductTb>> gc;
    Response rs;
    Integer pstock;
    Integer sessionid;
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            
    HttpSession session = request.getSession();

    /**
     * Creates a new instance of DesignerProductStockBean
     */
    public DesignerProductStockBean() {
        rc = new RestClient();
        products = new ArrayList<>();
        gc = new GenericType<Collection<ProductTb>>() {
        };
    }
    
    @PostConstruct
    public void init() {
        sessionid = (Integer)session.getAttribute("designerId");
    }


    public ProductTb getPt() {
        return pt;
    }

    public void setPt(ProductTb pt) {
        this.pt = pt;
    }

    public Collection<ProductTb> getProducts() {
        rs = rc.getProductByDesigner(Response.class, String.valueOf(sessionid));
        products = rs.readEntity(gc);
        return products;
    }

    public void setProducts(Collection<ProductTb> products) {
        this.products = products;
    }

    public Integer getPstock() {
        return pstock;
    }

    public void setPstock(Integer pstock) {
        this.pstock = pstock;
    }

    public Integer getSessionid() {
        return sessionid;
    }

    public void setSessionid(Integer sessionid) {
        this.sessionid = sessionid;
    }

    public String addStockView(ProductTb p) {
        this.pt = p;
        return "addProductStock";
    }
    
    public String addStock() {
        System.out.println("Category ID: " + pt.getCategoryId());
        int stock = pt.getStock() + pstock;
        System.out.println("Updating product: ID=" + pt.getId() + ", Name=" + pt.getName() + ", Stock=" + stock);
        rc.updateDesignerProducts(String.valueOf(pt.getId()), pt.getName(), String.valueOf(pt.getPrice()), String.valueOf(stock), pt.getImage(), String.valueOf(pt.getCategoryId().getId()), String.valueOf(pt.getMovieId().getId()), String.valueOf(pt.getCelebrityId().getId()), String.valueOf(pt.getSongId().getId()), String.valueOf(pt.getDesignerId().getId()));

        return "productStock";
    }

}
