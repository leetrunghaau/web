package com.webmvc.chiken.model.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "bill_item", schema = "public", catalog = "darschqvp4f5i8")
public class BillItemEntity {
    private int itemId;
    private Integer quantity;
    private BillEntity billId;
    private ProductEntity productId;

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy= AUTO)
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillItemEntity item = (BillItemEntity) o;

        if (itemId != item.itemId) return false;
        if (quantity != null ? !quantity.equals(item.quantity) : item.quantity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemId;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }


    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    public ProductEntity getProductId(){return productId;}

    public void setProductId(ProductEntity productId) {
        this.productId = productId;
    }

    @ManyToOne
    @JoinColumn(name = "bill_id", referencedColumnName = "bill_id")
    public BillEntity getBillId() {
        return billId;
    }

    public void setBillId(BillEntity billId) {
        this.billId = billId;
    }
}
