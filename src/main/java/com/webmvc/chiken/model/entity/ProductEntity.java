package com.webmvc.chiken.model.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "product", schema = "public", catalog = "darschqvp4f5i8")
public class ProductEntity {
    private int productId;
    private String productName;
    private Double productPrice;
    private Double productDiscount;
    private String productCode;
    private String productNote;
    private CategoriesEntity categoriesID;

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy= AUTO)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
    @Column(name = "product_price")
    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    @Basic
    @Column(name = "product_discount")
    public Double getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(Double productDiscount) {
        this.productDiscount = productDiscount;
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
    @Column(name = "product_note")
    public String getProductNote() {
        return productNote;
    }

    public void setProductNote(String productNote) {
        this.productNote = productNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (productId != that.productId) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (productPrice != null ? !productPrice.equals(that.productPrice) : that.productPrice != null) return false;
        if (productDiscount != null ? !productDiscount.equals(that.productDiscount) : that.productDiscount != null)
            return false;
        if (productCode != null ? !productCode.equals(that.productCode) : that.productCode != null) return false;
        if (productNote != null ? !productNote.equals(that.productNote) : that.productNote != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productPrice != null ? productPrice.hashCode() : 0);
        result = 31 * result + (productDiscount != null ? productDiscount.hashCode() : 0);
        result = 31 * result + (productCode != null ? productCode.hashCode() : 0);
        result = 31 * result + (productNote != null ? productNote.hashCode() : 0);
        return result;
    }
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    public CategoriesEntity getCategoriesID() {
        return categoriesID;
    }

    public void setCategoriesID(CategoriesEntity categoriesID) {
        this.categoriesID = categoriesID;
    }
}
