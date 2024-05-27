/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package DesignerCDIBeans;

import Beans.UserBeanLocal;
import Beans.designerBeanLocal;
import Entitys.DesignerTb;
import RestFullClient.RestClient;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Admin
 */
@Named(value = "registerCDI")
@RequestScoped
public class RegisterCDI {

    @EJB designerBeanLocal db;
    RestClient rc;
    DesignerTb dt = new DesignerTb();
    UploadedFile file;
    String cpass;
    String ErrorMsg;
    /**
     * Creates a new instance of RegisterCDI
     */
    public RegisterCDI() {
        rc = new RestClient();
    }

    public designerBeanLocal getDb() {
        return db;
    }

    public void setDb(designerBeanLocal db) {
        this.db = db;
    }

    public DesignerTb getDt() {
        return dt;
    }

    public void setDt(DesignerTb dt) {
        this.dt = dt;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getCpass() {
        return cpass;
    }

    public void setCpass(String cpass) {
        this.cpass = cpass;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }
    
    
    public void registerDesigner()
    {
        if(cpass == null ? dt.getPassword() != null : !cpass.equals(dt.getPassword()))
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
        rc.addDesigner(dt.getName(), dt.getPassword(), dt.getEmail(),String.valueOf(dt.getMobileno()), dt.getGender(),fileName);
            ErrorMsg = "";
        }
    }
    
}
