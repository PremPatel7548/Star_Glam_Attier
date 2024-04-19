/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitys;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "designer_tb")
@NamedQueries({
    @NamedQuery(name = "DesignerTb.findAll", query = "SELECT d FROM DesignerTb d"),
    @NamedQuery(name = "DesignerTb.findById", query = "SELECT d FROM DesignerTb d WHERE d.id = :id"),
    @NamedQuery(name = "DesignerTb.findByName", query = "SELECT d FROM DesignerTb d WHERE d.name = :name"),
    @NamedQuery(name = "DesignerTb.findByPassword", query = "SELECT d FROM DesignerTb d WHERE d.password = :password"),
    @NamedQuery(name = "DesignerTb.findByEmail", query = "SELECT d FROM DesignerTb d WHERE d.email = :email"),
    @NamedQuery(name = "DesignerTb.findByMobileno", query = "SELECT d FROM DesignerTb d WHERE d.mobileno = :mobileno"),
    @NamedQuery(name = "DesignerTb.findByGender", query = "SELECT d FROM DesignerTb d WHERE d.gender = :gender"),
    @NamedQuery(name = "DesignerTb.findByImage", query = "SELECT d FROM DesignerTb d WHERE d.image = :image"),
    @NamedQuery(name = "DesignerTb.findByIsApproved", query = "SELECT d FROM DesignerTb d WHERE d.isApproved = :isApproved")})
public class DesignerTb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mobileno")
    private long mobileno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_approved")
    private int isApproved;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "designerId")
    private Collection<ProductTb> productTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "designerId")
    private Collection<MovieDesigner> movieDesignerCollection;

    public DesignerTb() {
    }

    public DesignerTb(Integer id) {
        this.id = id;
    }

    public DesignerTb(Integer id, String name, String password, String email, long mobileno, String gender, String image, int isApproved) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.mobileno = mobileno;
        this.gender = gender;
        this.image = image;
        this.isApproved = isApproved;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobileno() {
        return mobileno;
    }

    public void setMobileno(long mobileno) {
        this.mobileno = mobileno;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(int isApproved) {
        this.isApproved = isApproved;
    }

    public Collection<ProductTb> getProductTbCollection() {
        return productTbCollection;
    }

    public void setProductTbCollection(Collection<ProductTb> productTbCollection) {
        this.productTbCollection = productTbCollection;
    }

    public Collection<MovieDesigner> getMovieDesignerCollection() {
        return movieDesignerCollection;
    }

    public void setMovieDesignerCollection(Collection<MovieDesigner> movieDesignerCollection) {
        this.movieDesignerCollection = movieDesignerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DesignerTb)) {
            return false;
        }
        DesignerTb other = (DesignerTb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.DesignerTb[ id=" + id + " ]";
    }
    
}
