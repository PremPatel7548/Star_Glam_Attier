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
@Table(name = "category_tb")
@NamedQueries({
    @NamedQuery(name = "CategoryTb.findAll", query = "SELECT c FROM CategoryTb c"),
    @NamedQuery(name = "CategoryTb.findById", query = "SELECT c FROM CategoryTb c WHERE c.id = :id"),
    @NamedQuery(name = "CategoryTb.findByCategoryName", query = "SELECT c FROM CategoryTb c WHERE c.categoryName = :categoryName")})
public class CategoryTb implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
    private Collection<ProductTb> productTbCollection;

    public CategoryTb() {
    }

    public CategoryTb(Integer id) {
        this.id = id;
    }

    public CategoryTb(Integer id, String categoryName) {
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
        if (!(object instanceof CategoryTb)) {
            return false;
        }
        CategoryTb other = (CategoryTb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.CategoryTb[ id=" + id + " ]";
    }
    
}
