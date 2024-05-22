/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package AdminCDIBeans;

import Beans.adminBeanLocal;
import Entitys.CelebrityTb;
import Entitys.DesignerTb;
import Entitys.MovieTb;
import Entitys.ProductTb;
import RestFullClient.RestClient;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
@Named(value = "adminDesignerBean")
@Dependent
public class AdminDesignerBean {

    @EJB adminBeanLocal al;
    Collection<DesignerTb> designerTbList;
    GenericType<Collection<DesignerTb>> gc;
    Collection<MovieTb> movieTbList;
    Response res;
    RestClient rc;
    String successmessage;
    Integer mid;
    Integer did;
    DesignerTb dt = new DesignerTb();
    
    /**
     * Creates a new instance of AdminDesignerBean
     */
    public AdminDesignerBean() {
        rc = new RestClient();
        designerTbList = new ArrayList<>();
        gc = new GenericType<Collection<DesignerTb>>(){};
        movieTbList = new ArrayList<>();
    }

    public DesignerTb getDt() {
        return dt;
    }

    public void setDt(DesignerTb dt) {
        this.dt = dt;
    }
   

    public Collection<DesignerTb> getDesignerTbList() {
        res = rc.displayDesigners(Response.class);
        designerTbList = res.readEntity(gc);
        return designerTbList;
    }

    public void setDesignerTbList(Collection<DesignerTb> designerTbList) {
        this.designerTbList = designerTbList;
    }

    public String getSuccessmessage() {
        return successmessage;
    }

    public void setSuccessmessage(String successmessage) {
        this.successmessage = successmessage;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }
    
    public Collection<MovieTb> getMovieTbList() {
        movieTbList = al.getMovieForDesigner();
//        movieTbList = al.getMovies();
        return movieTbList;
    }

    public void setMovieTbList(Collection<MovieTb> movieTbList) {
        this.movieTbList = movieTbList;
    }
    
    public String approveDesigner(DesignerTb d)
    {
        rc.updateDesigner(String.valueOf(d.getId()),d.getName(),d.getPassword(),d.getEmail(), d.getMobileno(),d.getGender(),d.getImage(),String.valueOf(1));
        successmessage = "Designer Approved Successfully";
        return "DesignerList";
    }
    
    public void clearSuccessMessage() {
        successmessage = null;
    }
    
    
}
