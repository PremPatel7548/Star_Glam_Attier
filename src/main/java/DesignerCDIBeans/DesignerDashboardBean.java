/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package DesignerCDIBeans;

import Beans.designerBeanLocal;
import Entitys.DesignerTb;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Admin
 */
@Named(value = "designerDashboardBean")
@RequestScoped
public class DesignerDashboardBean {

    @EJB designerBeanLocal dl;
    DesignerTb dt = new DesignerTb();
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    HttpSession session = request.getSession();
    Integer sessionid;
    UploadedFile file;
    Integer product_count;
    Integer pending_count;
    Integer empty_product_count;
    
    
    public DesignerDashboardBean() {
    }
    
    @PostConstruct
    public void init() {
        sessionid = (Integer) session.getAttribute("designerId");
        dt = dl.getDesignerById(sessionid);
    }


    public void setDt(DesignerTb dt) {
        this.dt = dt;
    }

    public Integer getSessionid() {
        return sessionid;
    }

    public void setSessionid(Integer sessionid) {
        this.sessionid = sessionid;
    }
    public DesignerTb getDt() {
        return dt;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Integer getProduct_count() {
        product_count = dl.countProducts(sessionid);
        return product_count;
    }

    public void setProduct_count(Integer product_count) {
        this.product_count = product_count;
    }

    public Integer getPending_count() {
        pending_count = dl.countPendingOrders(sessionid);
        return pending_count;
    }

    public void setPending_count(Integer pending_count) {
        this.pending_count = pending_count;
    }

    public Integer getEmpty_product_count() {
        empty_product_count = dl.countEmptyProducts(sessionid);
        return empty_product_count;
    }

    public void setEmpty_product_count(Integer empty_product_count) {
        this.empty_product_count = empty_product_count;
    }
    
    public String editImage()
    {
            String fileName = "";
            if (file != null) {
                try (InputStream input = file.getInputStream()) {
                    fileName = file.getFileName();
                    OutputStream output = new FileOutputStream("D:/JWD/Project/SGA/src/main/webapp/public/uploads/" + fileName);
                    try {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = input.read(buffer)) != -1) {
                            output.write(buffer, 0, bytesRead);
                        }
                    } finally {
                        output.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        dl.editDesigner(sessionid,dt.getName(),dt.getPassword(),dt.getEmail(),dt.getMobileno(), dt.getGender(),fileName,dt.getIsApproved());
        return "designerProfile";
    }
    
    public String editProfile()
    {
        System.out.println("Designer Name :- "+dt.getName()+"---------------------------");
        dl.editDesigner(sessionid,dt.getName(),dt.getPassword(),dt.getEmail(),dt.getMobileno(), dt.getGender(),dt.getImage(),dt.getIsApproved());
        return "designerProfile";
    }
}
