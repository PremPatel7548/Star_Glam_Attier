/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package AdminCDIBeans;

import Entitys.CelebrityTb;
import RestFullClient.RestClient;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Admin
 */
@Named(value = "adminCelebrityBean")
@RequestScoped
public class AdminCelebrityBean {

    /**
     * Creates a new instance of AdminCelebrityBean
     */
    Collection<CelebrityTb> CelebrityTbList;
    GenericType<Collection<CelebrityTb>> gc;
    Response res;
    RestClient rc;
    UploadedFile file;
    CelebrityTb celebrity = new CelebrityTb();

    public AdminCelebrityBean() {
        rc = new RestClient();
        gc = new GenericType<Collection<CelebrityTb>>() {
        };
        CelebrityTbList = new ArrayList<>();
    }

    public Collection<CelebrityTb> getCelebrityTbList() {
        res = rc.displayCelebrity(Response.class);
        CelebrityTbList = res.readEntity(gc);
        return CelebrityTbList;
    }

    public void setCelebrityTbList(Collection<CelebrityTb> CelebrityTbList) {
        this.CelebrityTbList = CelebrityTbList;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public CelebrityTb getCelebrity() {
        return celebrity;
    }

    public void setCelebrity(CelebrityTb celebrity) {
        this.celebrity = celebrity;
    }

    public String addCelebrity() {
        String fileName = "";
        if (file != null) {
            try (InputStream input = file.getInputStream()) {
                fileName = file.getFileName();
                OutputStream output = new FileOutputStream("D:/college/8th Sem/Start Glam Attire Project/Star_Glam_Attier/src/main/webapp/public/uploads" + fileName);
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
        String dob = dateFormat.format(celebrity.getDob());

        rc.addCelebrity(celebrity.getName(), String.valueOf(dob), celebrity.getGender(), fileName);
        return "CelebrityList";
    }

    public String deleteCelebrity(Integer id) {
        rc.deleteCelebrity(String.valueOf(id));
        return "CelebrityList";
    }

    public String getdataForCelebrity(CelebrityTb celebrity) {
        this.celebrity = celebrity;
        return "EditCelebrity";
    }

    public String updateCelebrity() {
        String fileName = "";
        if (file != null) {
            try (InputStream input = file.getInputStream()) {
                fileName = file.getFileName();
                OutputStream output = new FileOutputStream("D:/college/8th Sem/Start Glam Attire Project/Star_Glam_Attier/src/main/webapp/public/uploads/" + fileName);
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
        String dob = dateFormat.format(celebrity.getDob());

        rc.updateCelebrity(String.valueOf(celebrity.getId()),celebrity.getName(), String.valueOf(dob), celebrity.getGender(), fileName);
        return "CelebrityList";
    }
}
