package DesignerCDIBeans;

import Beans.designerBeanLocal;
import Entitys.CategoryTb;
import Entitys.MovieCelebrity;
import Entitys.MovieDesigner;
import Entitys.ProductTb;
import Entitys.SongTb;
import RestFullClient.RestClient;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.primefaces.model.file.UploadedFile;

@Named(value = "designerProductBean")
@SessionScoped
public class DesignerProductBean implements Serializable {

    @EJB
    designerBeanLocal dl;
    RestClient rc;
    ProductTb pt;
    Collection<ProductTb> products;
    Collection<CategoryTb> categorys;
    Collection<MovieDesigner> mds;
    Collection<MovieCelebrity> mcs;
    Collection<SongTb> songs;
    GenericType<Collection<ProductTb>> gc;
    GenericType<Collection<CategoryTb>> gcc;
    Response rs;
    Integer cid, mid, celid, sid;
    UploadedFile file;
    String successmessage;
    Integer sessionid;
    HttpServletRequest request;
    HttpServletResponse response;
    HttpSession session;

    public DesignerProductBean() {
        rc = new RestClient();
        products = new ArrayList<>();
        gc = new GenericType<Collection<ProductTb>>() {};
        categorys = new ArrayList<>();
        gcc = new GenericType<Collection<CategoryTb>>() {};
        mds = new ArrayList<>();
        mcs = new ArrayList<>();
        songs = new ArrayList<>();
        pt = new ProductTb(); // Initialize the ProductTb object
    }

    @PostConstruct
    public void init() {
        categorys = dl.getCategorys();
        request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        session = request.getSession();
        sessionid = (Integer) session.getAttribute("designerId");
        mds = dl.getMovieByDesigner(sessionid);
        System.out.println("DesignerProductBean initialized with categories and movies.");
    }

    public ProductTb getPt() {
        if (pt == null) {
            pt = new ProductTb(); // Ensure pt is initialized
        }
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

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getCelid() {
        return celid;
    }

    public void setCelid(Integer celid) {
        this.celid = celid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Collection<CategoryTb> getCategorys() {
        return categorys;
    }

    public void setCategorys(Collection<CategoryTb> categorys) {
        this.categorys = categorys;
    }

    public Collection<MovieDesigner> getMds() {
        return mds;
    }

    public void setMds(Collection<MovieDesigner> mds) {
        this.mds = mds;
    }

    public Collection<MovieCelebrity> getMcs() {
        return mcs;
    }

    public void setMcs(Collection<MovieCelebrity> mcs) {
        this.mcs = mcs;
    }

    public Collection<SongTb> getSongs() {
        return songs;
    }

    public void setSongs(Collection<SongTb> songs) {
        this.songs = songs;
    }

    public String getSuccessmessage() {
        return successmessage;
    }

    public void setSuccessmessage(String successmessage) {
        this.successmessage = successmessage;
    }

    public Integer getSessionid() {
        return sessionid;
    }

    public void setSessionid(Integer sessionid) {
        this.sessionid = sessionid;
    }

    public void onMovieChange() {
        if (mid != null) {
            mcs = dl.getCelebrityByMovie(mid);
            songs = dl.getSongByMovie(mid);
        } else {
            mcs = new ArrayList<>();
            songs = new ArrayList<>();
        }
        System.out.println("Movie changed: " + mid);
    }

    public String addProduct() {
        String fileName = "";
        if (file != null) {
            try (InputStream input = file.getInputStream()) {
                fileName = file.getFileName();
                OutputStream output = new FileOutputStream("D:/college/8th Sem/Start Glam Attire Project/Star_Glam_Attier/Star_Glam_Attier/src/main/webapp/public/uploads/" + fileName);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        rc.addDesignerProducts(pt.getName(), String.valueOf(pt.getPrice()), String.valueOf(0), fileName, String.valueOf(cid), String.valueOf(mid), String.valueOf(celid), String.valueOf(sid), String.valueOf(sessionid));
        System.out.println("Product added successfully");
        successmessage = "Product Added Successfully";
        clearForm();
        return "ProductList";
    }

    public String removeProduct(Integer id) {
        rc.deleteDesignerProduct(String.valueOf(id));
        successmessage = "Product Deleted Successfully";
        return "ProductList";
    }

    public String editView(ProductTb p) {
        this.pt = p;
        this.cid = p.getCategoryId().getId();
        this.mid = p.getMovieId().getId();
        this.celid = p.getCelebrityId().getId();
        this.sid = p.getSongId().getId();
        onMovieChange();
        System.out.println("Editing product: " + pt.getName());
        return "editProduct";
    }
    
    public String editProduct() {
        String fileName = "";
        if (file != null) {
            try (InputStream input = file.getInputStream()) {
                fileName = file.getFileName();
                OutputStream output = new FileOutputStream("D:/college/8th Sem/Start Glam Attire Project/Star_Glam_Attier/Star_Glam_Attier/src/main/webapp/public/uploads/" + fileName);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        rc.updateDesignerProducts(String.valueOf(pt.getId()), pt.getName(), String.valueOf(pt.getPrice()), String.valueOf(pt.getStock()), fileName, String.valueOf(cid), String.valueOf(mid), String.valueOf(celid), String.valueOf(sid), String.valueOf(sessionid));
        System.out.println("Product Edited successfully");
        successmessage = "Product Edited Successfully";
        clearForm();
        return "ProductList";
    }

    public void clearForm() {
        this.pt = new ProductTb();
        this.cid = null;
        this.celid = null;
        this.mid = null;
        this.sid = null;
        this.file = null;
    }

    public void clearSuccessMessage() {
        successmessage = null;
    }
}