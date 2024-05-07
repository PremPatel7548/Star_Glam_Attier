/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package loginBean;

import RestFullClient.RestClient;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import record.KeepRecord;

/**
 *
 * @author Admin
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @Inject SecurityContext sc;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrorstatus() {
        return errorstatus;
    }

    public void setErrorstatus(String errorstatus) {
        this.errorstatus = errorstatus;
    }

    public RestClient getRc() {
        return rc;
    }

    public void setRc(RestClient rc) {
        this.rc = rc;
    }

    public boolean isRememberme() {
        return rememberme;
    }

    public void setRememberme(boolean rememberme) {
        this.rememberme = rememberme;
    }

    /**
     * Creates a new instance of LoginBean
     */
    RestClient rc;
    boolean rememberme;
    String username,password;
    String errorstatus;
    public String pth1(){
        return rc.path();
    }
    public String pth2(){
        return rc.path2();
    }
    public LoginBean() {
        rc = new RestClient();
    }
    public void login(){
        FacesContext fc = FacesContext.getCurrentInstance();
        try{
            fc.getExternalContext().redirect("Login.jsf");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void logout(){
        username="";
        password="";
        KeepRecord.reset();
        KeepRecord.setLogout(true);
        FacesContext fc = FacesContext.getCurrentInstance();
        try{
            fc.getExternalContext().redirect("Login.jsf");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
