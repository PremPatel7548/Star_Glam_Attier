/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitys;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "movie_tb")
@NamedQueries({
    @NamedQuery(name = "MovieTb.findAll", query = "SELECT m FROM MovieTb m"),
    @NamedQuery(name = "MovieTb.findById", query = "SELECT m FROM MovieTb m WHERE m.id = :id"),
    @NamedQuery(name = "MovieTb.findByName", query = "SELECT m FROM MovieTb m WHERE m.name = :name"),
    @NamedQuery(name = "MovieTb.findByReleaseDate", query = "SELECT m FROM MovieTb m WHERE m.releaseDate = :releaseDate")})
public class MovieTb implements Serializable {

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
    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId")
    private Collection<MovieCelebrity> movieCelebrityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId")
    private Collection<ProductTb> productTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId")
    private Collection<SongTb> songTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId")
    private Collection<MovieDesigner> movieDesignerCollection;
    @JoinColumn(name = "movie_category_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MovieCategoryTb movieCategoryId;

    public MovieTb() {
    }

    public MovieTb(Integer id) {
        this.id = id;
    }

    public MovieTb(Integer id, String name, Date releaseDate) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Collection<MovieCelebrity> getMovieCelebrityCollection() {
        return movieCelebrityCollection;
    }

    public void setMovieCelebrityCollection(Collection<MovieCelebrity> movieCelebrityCollection) {
        this.movieCelebrityCollection = movieCelebrityCollection;
    }

    public Collection<ProductTb> getProductTbCollection() {
        return productTbCollection;
    }

    public void setProductTbCollection(Collection<ProductTb> productTbCollection) {
        this.productTbCollection = productTbCollection;
    }

    public Collection<SongTb> getSongTbCollection() {
        return songTbCollection;
    }

    public void setSongTbCollection(Collection<SongTb> songTbCollection) {
        this.songTbCollection = songTbCollection;
    }

    public Collection<MovieDesigner> getMovieDesignerCollection() {
        return movieDesignerCollection;
    }

    public void setMovieDesignerCollection(Collection<MovieDesigner> movieDesignerCollection) {
        this.movieDesignerCollection = movieDesignerCollection;
    }

    public MovieCategoryTb getMovieCategoryId() {
        return movieCategoryId;
    }

    public void setMovieCategoryId(MovieCategoryTb movieCategoryId) {
        this.movieCategoryId = movieCategoryId;
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
        if (!(object instanceof MovieTb)) {
            return false;
        }
        MovieTb other = (MovieTb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.MovieTb[ id=" + id + " ]";
    }
    
}
