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
@Table(name = "movie_celebrity")
@NamedQueries({
    @NamedQuery(name = "MovieCelebrity.findAll", query = "SELECT m FROM MovieCelebrity m"),
    @NamedQuery(name = "MovieCelebrity.findById", query = "SELECT m FROM MovieCelebrity m WHERE m.id = :id")})
public class MovieCelebrity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "celebrity_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CelebrityTb celebrityId;
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MovieTb movieId;

    public MovieCelebrity() {
    }

    public MovieCelebrity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CelebrityTb getCelebrityId() {
        return celebrityId;
    }

    public void setCelebrityId(CelebrityTb celebrityId) {
        this.celebrityId = celebrityId;
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
        if (!(object instanceof MovieCelebrity)) {
            return false;
        }
        MovieCelebrity other = (MovieCelebrity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.MovieCelebrity[ id=" + id + " ]";
    }
    
}
