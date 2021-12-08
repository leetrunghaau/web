package com.webmvc.chiken.model.entity;

import javax.persistence.*;
import java.sql.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "discount", schema = "public", catalog = "darschqvp4f5i8")
public class DiscountEntity {
    private Date endDiscount;
    private Date beginDiscount;
    private Double discountValue;
    private String discountName;
    private String discountCode;
    private int discountId;

    @Basic
    @Column(name = "end_discount")
    public Date getEndDiscount() {
        return endDiscount;
    }

    public void setEndDiscount(Date endDiscount) {
        this.endDiscount = endDiscount;
    }

    @Basic
    @Column(name = "begin_discount")
    public Date getBeginDiscount() {
        return beginDiscount;
    }

    public void setBeginDiscount(Date beginDiscount) {
        this.beginDiscount = beginDiscount;
    }

    @Basic
    @Column(name = "discount_value")
    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    @Basic
    @Column(name = "discount_name")
    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    @Basic
    @Column(name = "discount_code")
    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    @Id
    @Column(name = "discount_id")
    @GeneratedValue(strategy= AUTO)
    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiscountEntity that = (DiscountEntity) o;

        if (discountId != that.discountId) return false;
        if (endDiscount != null ? !endDiscount.equals(that.endDiscount) : that.endDiscount != null) return false;
        if (beginDiscount != null ? !beginDiscount.equals(that.beginDiscount) : that.beginDiscount != null)
            return false;
        if (discountValue != null ? !discountValue.equals(that.discountValue) : that.discountValue != null)
            return false;
        if (discountName != null ? !discountName.equals(that.discountName) : that.discountName != null) return false;
        if (discountCode != null ? !discountCode.equals(that.discountCode) : that.discountCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = endDiscount != null ? endDiscount.hashCode() : 0;
        result = 31 * result + (beginDiscount != null ? beginDiscount.hashCode() : 0);
        result = 31 * result + (discountValue != null ? discountValue.hashCode() : 0);
        result = 31 * result + (discountName != null ? discountName.hashCode() : 0);
        result = 31 * result + (discountCode != null ? discountCode.hashCode() : 0);
        result = 31 * result + discountId;
        return result;
    }
}
