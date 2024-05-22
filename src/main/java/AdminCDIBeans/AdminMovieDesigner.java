/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package AdminCDIBeans;

import Beans.adminBeanLocal;
import Entitys.DesignerTb;
import Entitys.MovieDesigner;
import Entitys.MovieTb;
import Entitys.ProductTb;
import RestFullClient.RestClient;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
@Named(value = "adminMovieDesigner")
@RequestScoped
public class AdminMovieDesigner {

    @EJB adminBeanLocal al;
    RestClient rc;
    Collection<MovieDesigner> mds ;
    GenericType<Collection<MovieDesigner>> gc;
    DesignerTb dt = new DesignerTb();
    Collection<MovieTb> movieTbList;
    Integer mid;
    Response rs;
    Integer did;
    /**
     * Creates a new instance of AdminMovieDesigner
     */
    public AdminMovieDesigner() {
        rc = new RestClient();
        movieTbList = new ArrayList<>();
        mds = new ArrayList<>();
        gc = new GenericType<Collection<MovieDesigner>>(){};
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
        return movieTbList;
    }

    public void setMovieTbList(Collection<MovieTb> movieTbList) {
        this.movieTbList = movieTbList;
    }

    public DesignerTb getDt() {
        return dt;
    }

    public void setDt(DesignerTb dt) {
        this.dt = dt;
    }

    public Collection<MovieDesigner> getMds() {
        rs = rc.displayMovieDesigner(Response.class);
        mds = rs.readEntity(gc);
        return mds;
    }

    public void setMds(Collection<MovieDesigner> mds) {
        this.mds = mds;
    }
    
    public String assignMovie(DesignerTb d)
    {
        this.dt = d;
        return "AddMovieDesigner";
    }
    
    public String addMovieDesigner()
    {
//        rc.addMovieDesigner(String.valueOf(7),String.valueOf(dt.getId()));
        System.out.println("Designer ID: " + dt.getId());
        System.out.println("Movie ID: " + mid);
        al.addMovieDesigner(mid,dt.getId());
        return "DesignerList";
    }
    
    public String removeMovieDesigner(Integer id)
    {
        rc.deleteMovieDesigner(String.valueOf(id));
        return "MovieDesignerList";
    }
    
}
