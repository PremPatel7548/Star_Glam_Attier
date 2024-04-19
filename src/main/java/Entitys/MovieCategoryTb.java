/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitys;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "movie_category_tb")
@NamedQueries({
    @NamedQuery(name = "MovieCategoryTb.findAll", query = "SELECT m FROM MovieCategoryTb m"),
    @NamedQuery(name = "MovieCategoryTb.findById", query = "SELECT m FROM MovieCategoryTb m WHERE m.id = :id"),
    @NamedQuery(name = "MovieCategoryTb.findByCategoryName", query = "SELECT m FROM MovieCategoryTb m WHERE m.categoryName = :categoryName")})
public class MovieCategoryTb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "category_name")
    private String categoryName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieCategoryId")
    private Collection<MovieTb> movieTbCollection;

    public MovieCategoryTb() {
    }

    public MovieCategoryTb(Integer id) {
        this.id = id;
    }

    public MovieCategoryTb(Integer id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @JsonbTransient
    public Collection<MovieTb> getMovieTbCollection() {
        return movieTbCollection;
    }

    @JsonbTransient
    public void setMovieTbCollection(Collection<MovieTb> movieTbCollection) {
        this.movieTbCollection = movieTbCollection;
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
        if (!(object instanceof MovieCategoryTb)) {
            return false;
        }
        MovieCategoryTb other = (MovieCategoryTb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.MovieCategoryTb[ id=" + id + " ]";
    }
    
}
