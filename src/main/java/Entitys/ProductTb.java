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
@Table(name = "product_tb")
@NamedQueries({
    @NamedQuery(name = "ProductTb.findAll", query = "SELECT p FROM ProductTb p"),
    @NamedQuery(name = "ProductTb.findById", query = "SELECT p FROM ProductTb p WHERE p.id = :id"),
    @NamedQuery(name = "ProductTb.findByName", query = "SELECT p FROM ProductTb p WHERE p.name = :name"),
    @NamedQuery(name = "ProductTb.findByPrice", query = "SELECT p FROM ProductTb p WHERE p.price = :price"),
    @NamedQuery(name = "ProductTb.findByStock", query = "SELECT p FROM ProductTb p WHERE p.stock = :stock"),
    @NamedQuery(name = "ProductTb.findByImage", query = "SELECT p FROM ProductTb p WHERE p.image = :image")})
public class ProductTb implements Serializable {

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
    @Column(name = "price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock")
    private int stock;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "image")
    private String image;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CategoryTb categoryId;
    @JoinColumn(name = "celebrity_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CelebrityTb celebrityId;
    @JoinColumn(name = "designer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DesignerTb designerId;
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MovieTb movieId;
    @JoinColumn(name = "song_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SongTb songId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<UserCartTb> userCartTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<UserOrderTb> userOrderTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<ReviewTb> reviewTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<WishlistTb> wishlistTbCollection;

    public ProductTb() {
    }

    public ProductTb(Integer id) {
        this.id = id;
    }

    public ProductTb(Integer id, String name, int price, int stock, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CategoryTb getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(CategoryTb categoryId) {
        this.categoryId = categoryId;
    }

    public CelebrityTb getCelebrityId() {
        return celebrityId;
    }

    public void setCelebrityId(CelebrityTb celebrityId) {
        this.celebrityId = celebrityId;
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

    public SongTb getSongId() {
        return songId;
    }

    public void setSongId(SongTb songId) {
        this.songId = songId;
    }

    @JsonbTransient
    public Collection<UserCartTb> getUserCartTbCollection() {
        return userCartTbCollection;
    }

    @JsonbTransient
    public void setUserCartTbCollection(Collection<UserCartTb> userCartTbCollection) {
        this.userCartTbCollection = userCartTbCollection;
    }

    @JsonbTransient
    public Collection<UserOrderTb> getUserOrderTbCollection() {
        return userOrderTbCollection;
    }

    @JsonbTransient
    public void setUserOrderTbCollection(Collection<UserOrderTb> userOrderTbCollection) {
        this.userOrderTbCollection = userOrderTbCollection;
    }

    @JsonbTransient
    public Collection<ReviewTb> getReviewTbCollection() {
        return reviewTbCollection;
    }

    @JsonbTransient
    public void setReviewTbCollection(Collection<ReviewTb> reviewTbCollection) {
        this.reviewTbCollection = reviewTbCollection;
    }

    @JsonbTransient
    public Collection<WishlistTb> getWishlistTbCollection() {
        return wishlistTbCollection;
    }

    @JsonbTransient
    public void setWishlistTbCollection(Collection<WishlistTb> wishlistTbCollection) {
        this.wishlistTbCollection = wishlistTbCollection;
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
        if (!(object instanceof ProductTb)) {
            return false;
        }
        ProductTb other = (ProductTb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.ProductTb[ id=" + id + " ]";
    }
    
}
