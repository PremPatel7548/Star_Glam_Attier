/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package DesignerCDIBeans;

import Beans.designerBeanLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@Named(value = "designerLoginBean")
@RequestScoped
public class DesignerLoginBean {

    @EJB designerBeanLocal dl;
    boolean isLogin;
    String email,password;
    String errorMessage;
    /**
     * Creates a new instance of DesignerLoginBean
     */
    public DesignerLoginBean() {
    }

    public boolean isIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    
    public String checkLogin()
    {
       isLogin = dl.checkLogin(email, password);
       
       if(isLogin == true)
       {
           return "Dashboard";
       }
       else
       {
           errorMessage = "* Invalid Email Or Password";
           return "loginDesigner";
       }
    }
}
