package com.webmvc.chiken.model.entity;

import com.webmvc.chiken.util.MyUtil;

import javax.persistence.*;

@Entity
@Table(name = "view_product", schema = "public", catalog = "darschqvp4f5i8")
public class ViewProductEntity {
    private Double productDiscount;
    private Double productPrice;
    private String productNote;
    private String productName;
    private String productCode;
    private String categoryCode;
    private Integer productId;

    @Basic
    @Column(name = "product_discount")
    public Double getProductDiscount() {
        if (productDiscount == null){
            return 0.0;
        }else {
            return productDiscount;
        }

    }

    public void setProductDiscount(Double productDiscount) {
        this.productDiscount = productDiscount;
    }

    @Basic
    @Column(name = "product_price")
    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    @Basic
    @Column(name = "product_note")
    public String getProductNote() {
        return productNote;
    }

    public void setProductNote(String productNote) {
        this.productNote = productNote;
    }

    @Basic
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "product_code")
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Basic
    @Column(name = "category_code")
    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    @Basic
    @Id
    @Column(name = "product_id")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewProductEntity that = (ViewProductEntity) o;

        if (productDiscount != null ? !productDiscount.equals(that.productDiscount) : that.productDiscount != null)
            return false;
        if (productPrice != null ? !productPrice.equals(that.productPrice) : that.productPrice != null) return false;
        if (productNote != null ? !productNote.equals(that.productNote) : that.productNote != null) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (productCode != null ? !productCode.equals(that.productCode) : that.productCode != null) return false;
        if (categoryCode != null ? !categoryCode.equals(that.categoryCode) : that.categoryCode != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productDiscount != null ? productDiscount.hashCode() : 0;
        result = 31 * result + (productPrice != null ? productPrice.hashCode() : 0);
        result = 31 * result + (productNote != null ? productNote.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productCode != null ? productCode.hashCode() : 0);
        result = 31 * result + (categoryCode != null ? categoryCode.hashCode() : 0);
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        return result;
    }
    public String priceFormat(){
        return MyUtil.format(getProductPrice());
    }
    public String discountFormat(){
        return MyUtil.format(getProductDiscount());
    }
}
