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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "user_order_tb")
@NamedQueries({
    @NamedQuery(name = "UserOrderTb.findAll", query = "SELECT u FROM UserOrderTb u"),
    @NamedQuery(name = "UserOrderTb.findById", query = "SELECT u FROM UserOrderTb u WHERE u.id = :id"),
    @NamedQuery(name = "UserOrderTb.findByQty", query = "SELECT u FROM UserOrderTb u WHERE u.qty = :qty"),
    @NamedQuery(name = "UserOrderTb.findByPrice", query = "SELECT u FROM UserOrderTb u WHERE u.price = :price"),
    @NamedQuery(name = "UserOrderTb.findByTotal", query = "SELECT u FROM UserOrderTb u WHERE u.total = :total"),
    @NamedQuery(name = "UserOrderTb.findByIsConfirmed", query = "SELECT u FROM UserOrderTb u WHERE u.isConfirmed = :isConfirmed")})
public class UserOrderTb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private int qty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private int total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_confirmed")
    private int isConfirmed;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Collection<PaymentTb> paymentTbCollection;
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProductTb productId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UserTb userId;

    public UserOrderTb() {
    }

    public UserOrderTb(Integer id) {
        this.id = id;
    }

    public UserOrderTb(Integer id, int qty, int price, int total, int isConfirmed) {
        this.id = id;
        this.qty = qty;
        this.price = price;
        this.total = total;
        this.isConfirmed = isConfirmed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(int isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public Collection<PaymentTb> getPaymentTbCollection() {
        return paymentTbCollection;
    }

    public void setPaymentTbCollection(Collection<PaymentTb> paymentTbCollection) {
        this.paymentTbCollection = paymentTbCollection;
    }

    public ProductTb getProductId() {
        return productId;
    }

    public void setProductId(ProductTb productId) {
        this.productId = productId;
    }

    public UserTb getUserId() {
        return userId;
    }

    public void setUserId(UserTb userId) {
        this.userId = userId;
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
        if (!(object instanceof UserOrderTb)) {
            return false;
        }
        UserOrderTb other = (UserOrderTb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.UserOrderTb[ id=" + id + " ]";
    }
    
}
