/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package UserCDIBeans;

import Beans.UserBean;
import Beans.UserBeanLocal;
import Entitys.UserTb;
import RestFullClient.RestClient;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Admin
 */
@Named(value = "registerBean")
@RequestScoped
public class RegisterBean {

    @EJB UserBeanLocal ub;
    RestClient rc;
    UserTb ut = new UserTb();
    UploadedFile file;
    String cpass;
    String ErrorMsg;
    public RegisterBean() {
        rc = new RestClient();
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }
    
    

    public UserTb getUt() {
        return ut;
    }

    public String getCpass() {
        return cpass;
    }

    public void setCpass(String cpass) {
        this.cpass = cpass;
    }

    public void setUt(UserTb ut) {
        this.ut = ut;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public String registerUser()
    {
        if(cpass == null ? ut.getPassword() != null : !cpass.equals(ut.getPassword()))
        {
            ErrorMsg = "* Password and confirm password must be same";
        }
        else
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dob = dateFormat.format(ut.getDob());
        
//        rc.registerUser(ut.getName(), ut.getPassword(),ut.getEmail(),ut.getMobileno(),ut.getGender(),"India", fileName, String.valueOf(new Date()));
         ub.RegisterUser(ut.getName(),ut.getPassword(),ut.getEmail(),ut.getMobileno(),ut.getGender(),ut.getAddress(),fileName,ut.getDob());
//         rc.afterRegister(ut.getName()); 
         ub.afterRegister(ut.getEmail());
            ErrorMsg = "";
        }
        
        return "Login";
    }
    
}
