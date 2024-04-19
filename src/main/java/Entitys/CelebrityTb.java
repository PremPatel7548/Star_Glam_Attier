/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitys;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "celebrity_tb")
@NamedQueries({
    @NamedQuery(name = "CelebrityTb.findAll", query = "SELECT c FROM CelebrityTb c"),
    @NamedQuery(name = "CelebrityTb.findById", query = "SELECT c FROM CelebrityTb c WHERE c.id = :id"),
    @NamedQuery(name = "CelebrityTb.findByName", query = "SELECT c FROM CelebrityTb c WHERE c.name = :name"),
    @NamedQuery(name = "CelebrityTb.findByDob", query = "SELECT c FROM CelebrityTb c WHERE c.dob = :dob"),
    @NamedQuery(name = "CelebrityTb.findByGender", query = "SELECT c FROM CelebrityTb c WHERE c.gender = :gender"),
    @NamedQuery(name = "CelebrityTb.findByImage", query = "SELECT c FROM CelebrityTb c WHERE c.image = :image")})
public class CelebrityTb implements Serializable {

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
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "celebrityId")
    private Collection<MovieCelebrity> movieCelebrityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "celebrityId")
    private Collection<ProductTb> productTbCollection;

    public CelebrityTb() {
    }

    public CelebrityTb(Integer id) {
        this.id = id;
    }

    public CelebrityTb(Integer id, String name, Date dob, String gender, String image) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.image = image;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
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

    @JsonbTransient
    public Collection<MovieCelebrity> getMovieCelebrityCollection() {
        return movieCelebrityCollection;
    }

    @JsonbTransient
    public void setMovieCelebrityCollection(Collection<MovieCelebrity> movieCelebrityCollection) {
        this.movieCelebrityCollection = movieCelebrityCollection;
    }

    @JsonbTransient
    public Collection<ProductTb> getProductTbCollection() {
        return productTbCollection;
    }

    @JsonbTransient
    public void setProductTbCollection(Collection<ProductTb> productTbCollection) {
        this.productTbCollection = productTbCollection;
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
        if (!(object instanceof CelebrityTb)) {
            return false;
        }
        CelebrityTb other = (CelebrityTb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.CelebrityTb[ id=" + id + " ]";
    }
    
}
