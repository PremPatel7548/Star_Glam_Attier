/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "movie_designer")
@NamedQueries({
    @NamedQuery(name = "MovieDesigner.findAll", query = "SELECT m FROM MovieDesigner m"),
    @NamedQuery(name = "MovieDesigner.findById", query = "SELECT m FROM MovieDesigner m WHERE m.id = :id")})
public class MovieDesigner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "designer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DesignerTb designerId;
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MovieTb movieId;

    public MovieDesigner() {
    }

    public MovieDesigner(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DesignerTb getDesignerId() {
        return designerId;
    }

    public void setDesignerId(DesignerTb designerId) {
        this.designerId = designerId;
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
        if (!(object instanceof MovieDesigner)) {
            return false;
        }
        MovieDesigner other = (MovieDesigner) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.MovieDesigner[ id=" + id + " ]";
    }
    
}
