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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "song_tb")
@NamedQueries({
    @NamedQuery(name = "SongTb.findAll", query = "SELECT s FROM SongTb s"),
    @NamedQuery(name = "SongTb.findById", query = "SELECT s FROM SongTb s WHERE s.id = :id"),
    @NamedQuery(name = "SongTb.findByName", query = "SELECT s FROM SongTb s WHERE s.name = :name")})
public class SongTb implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "songId")
    private Collection<ProductTb> productTbCollection;
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MovieTb movieId;

    public SongTb() {
    }

    public SongTb(Integer id) {
        this.id = id;
    }

    public SongTb(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @JsonbTransient
    public Collection<ProductTb> getProductTbCollection() {
        return productTbCollection;
    }

    @JsonbTransient
    public void setProductTbCollection(Collection<ProductTb> productTbCollection) {
        this.productTbCollection = productTbCollection;
    }

    public MovieTb getMovieId() {
        return movieId;
    }

    public void setMovieId(MovieTb movieId) {
        this.movieId = movieId;
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
        if (!(object instanceof SongTb)) {
            return false;
        }
        SongTb other = (SongTb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.SongTb[ id=" + id + " ]";
    }
    
}
