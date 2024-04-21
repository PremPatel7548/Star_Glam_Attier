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
@Table(name = "user_tb")
@NamedQueries({
    @NamedQuery(name = "UserTb.findAll", query = "SELECT u FROM UserTb u"),
    @NamedQuery(name = "UserTb.findById", query = "SELECT u FROM UserTb u WHERE u.id = :id"),
    @NamedQuery(name = "UserTb.findByName", query = "SELECT u FROM UserTb u WHERE u.name = :name"),
    @NamedQuery(name = "UserTb.findByEmail", query = "SELECT u FROM UserTb u WHERE u.email = :email"),
    @NamedQuery(name = "UserTb.findByPassword", query = "SELECT u FROM UserTb u WHERE u.password = :password"),
    @NamedQuery(name = "UserTb.findByMobileno", query = "SELECT u FROM UserTb u WHERE u.mobileno = :mobileno"),
    @NamedQuery(name = "UserTb.findByAddress", query = "SELECT u FROM UserTb u WHERE u.address = :address"),
    @NamedQuery(name = "UserTb.findByImage", query = "SELECT u FROM UserTb u WHERE u.image = :image"),
    @NamedQuery(name = "UserTb.findByDob", query = "SELECT u FROM UserTb u WHERE u.dob = :dob")})
public class UserTb implements Serializable {

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
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "mobileno")
    private String mobileno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<UserCartTb> userCartTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<PaymentTb> paymentTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<UserOrderTb> userOrderTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<ReviewTb> reviewTbCollection;

    public UserTb() {
    }

    public UserTb(Integer id) {
        this.id = id;
    }

    public UserTb(Integer id, String name, String email, String password, String mobileno, String address, String image, Date dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobileno = mobileno;
        this.address = address;
        this.image = image;
        this.dob = dob;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
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
    public Collection<PaymentTb> getPaymentTbCollection() {
        return paymentTbCollection;
    }

    @JsonbTransient
    public void setPaymentTbCollection(Collection<PaymentTb> paymentTbCollection) {
        this.paymentTbCollection = paymentTbCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTb)) {
            return false;
        }
        UserTb other = (UserTb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.UserTb[ id=" + id + " ]";
    }
    
}
