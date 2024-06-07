/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package UserCDIBeans;

import Beans.UserBeanLocal;
import Entitys.UserTb;
import RestFullClient.RestClient;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import loginBean.LoginBean;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Admin
 */
@Named(value = "userProfileBean")
@RequestScoped
public class UserProfileBean {
    
    @EJB UserBeanLocal ul;
    @Inject LoginBean lb;
    RestClient rc;
    GenericType<UserTb> gu;
    Response res;
    UploadedFile file;
    UserTb ut = new UserTb();
    
    public UserProfileBean() {
        rc = new RestClient();
        gu = new GenericType<UserTb>(){};
    }
    
     @PostConstruct
    public void init() {
        res = rc.getUserById(Response.class,String.valueOf(lb.getuId()));
        ut = res.readEntity(gu);
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UserTb getUt() {
        return ut;
    }

    public void setUt(UserTb ut) {
        this.ut = ut;
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
        ul.editUser(lb.getuId(), ut.getName(), ut.getPassword(), ut.getEmail(), ut.getMobileno(),ut.getGender(), ut.getAddress(),fileName, ut.getDob());
//        rc.editUser(String.valueOf(ut.getId()),ut.getName(),ut.getPassword(),ut.getEmail(),ut.getMobileno(),ut.getGender(), ut.getAddress(),ut.getImage(),String.valueOf(ut.getDob()));
        return "userProfile";
    }
    
    public String editProfile()
    {
        
        ul.editUser(lb.getuId(), ut.getName(), ut.getPassword(), ut.getEmail(), ut.getMobileno(),ut.getGender(), ut.getAddress(), ut.getImage(), ut.getDob());
//        rc.editUser(String.valueOf(ut.getId()),ut.getName(),ut.getPassword(),ut.getEmail(),ut.getMobileno(),ut.getGender(), ut.getAddress(),ut.getImage(),String.valueOf(ut.getDob()));
        return "userProfile";
    }
}
