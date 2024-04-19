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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "payment_tb")
@NamedQueries({
    @NamedQuery(name = "PaymentTb.findAll", query = "SELECT p FROM PaymentTb p"),
    @NamedQuery(name = "PaymentTb.findById", query = "SELECT p FROM PaymentTb p WHERE p.id = :id"),
    @NamedQuery(name = "PaymentTb.findByPaymentMode", query = "SELECT p FROM PaymentTb p WHERE p.paymentMode = :paymentMode")})
public class PaymentTb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "payment_mode")
    private String paymentMode;
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UserOrderTb orderId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UserTb userId;

    public PaymentTb() {
    }

    public PaymentTb(Integer id) {
        this.id = id;
    }

    public PaymentTb(Integer id, String paymentMode) {
        this.id = id;
        this.paymentMode = paymentMode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public UserOrderTb getOrderId() {
        return orderId;
    }

    public void setOrderId(UserOrderTb orderId) {
        this.orderId = orderId;
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
        if (!(object instanceof PaymentTb)) {
            return false;
        }
        PaymentTb other = (PaymentTb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.PaymentTb[ id=" + id + " ]";
    }
    
}
