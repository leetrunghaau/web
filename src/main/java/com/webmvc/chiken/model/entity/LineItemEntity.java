package com.webmvc.chiken.model.entity;

import com.webmvc.chiken.util.MyUtil;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
public class LineItemEntity implements Serializable{
    private ViewProductEntity product;
    private int quantity;
    public LineItemEntity(){}

    public void setProduct(ViewProductEntity product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public ViewProductEntity getProduct() {
        return product;
    }
    public Double getTotal(){
        return  (product.getProductPrice() - product.getProductDiscount()) * quantity;
    }
    public String getTotalCurrencyFormat(){
        return MyUtil.format(getTotal());
    }
    public String getPriceFormat(){

        return MyUtil.format(product.getProductPrice());
    }
}
