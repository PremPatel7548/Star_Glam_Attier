/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package AdminCDIBeans;

import Beans.adminBeanLocal;
import Entitys.CategoryTb;
import RestFullClient.RestClient;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
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
@Named(value = "adminCategoryBean")
@RequestScoped
public class AdminCategoryBean {

    /**
     * Creates a new instance of AdminCategoryBean
     */
     Collection<CategoryTb> categoryList;
    GenericType<Collection<CategoryTb>> gc;
    Response res;
    RestClient rc;
    CategoryTb category=new CategoryTb();
    @EJB adminBeanLocal al;
    String successmessage;
    
    public AdminCategoryBean() {
         rc=new RestClient();
        gc=new  GenericType<Collection<CategoryTb>>(){};
        categoryList=new ArrayList<>();
    }

    public String getSuccessmessage() {
        return successmessage;
    }

    public void setSuccessmessage(String successmessage) {
        this.successmessage = successmessage;
    }

    public Collection<CategoryTb> getCategoryList() {
         res=rc.displayCategorys(Response.class);
        categoryList=res.readEntity(gc);
//        categoryList=al.getCategorys();
        return categoryList;
    }

    public void setCategoryList(Collection<CategoryTb> categoryList) {
        this.categoryList = categoryList;
    }

    public CategoryTb getCategory() {
        return category;
    }

    public void setCategory(CategoryTb category) {
        this.category = category;
    }
    
     public String addCategory(){
        rc.addCategory(category.getCategoryName());
        this.category = null;
        successmessage = "Category Added Successfully";
        return "CategoryList";
    }
    
     public String getdataForeditCategory(CategoryTb category){
         this.category=category;
        return "EditCategory";
    }
    
     public String editCategory(){
        rc.updateCategory(String.valueOf(category.getId()),category.getCategoryName());
        successmessage = "Category Edited Successfully";
        return "CategoryList";
    }
     
     public String deleteCategory(Integer categoryid){
        rc.deleteCategory(String.valueOf(categoryid));
        successmessage = "Category Deleted Successfully";
        return "CategoryList";
    }
     
      public void clearSuccessMessage() {
        successmessage = null;
    }
}
