package com.webmvc.chiken.model.entity;

import com.webmvc.chiken.util.MyUtil;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartEntity implements Serializable {
    private ArrayList<LineItemEntity> items;
    private String discountCode;
    private Double discountValue;


    public CartEntity() {
        items = new ArrayList<LineItemEntity>();
    }

    public ArrayList<LineItemEntity> getItems() {
        return items;
    }

    public LineItemEntity getItemByProductCode(String productCode) {
        for (int i = 0; i < items.size(); i++) {
            LineItemEntity lineItemEntity = items.get(i);
            if (lineItemEntity.getProduct().getProductCode().equals(productCode)) {
                return lineItemEntity;
            }
        }
        return null;
    }

    public int getCount() {
        return items.size();
    }

    public String getDiscountCode() {

        return discountCode;
    }

    public Double getDiscountValue() {
        if (discountValue == null) {
            return 0.0;
        } else {
            return discountValue;
        }

    }

    public String getDiscountValueFormat() {
        return MyUtil.format(getDiscountValue());
    }

    public void setDiscountValue(Double discountValue) {



        this.discountValue = discountValue;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public Double getSumTotal() {
        double sumTotal = 0;
        for (int i = 0; i < items.size(); i++) {
            LineItemEntity lineItemEntity = items.get(i);
            sumTotal = sumTotal + lineItemEntity.getTotal();
        }
        return sumTotal;
    }

    public String getSumTotalFormat() {
        return MyUtil.format(getSumTotal());
    }

    public void addItem(LineItemEntity item) {
        String code = item.getProduct().getProductCode();
        int quantity = item.getQuantity();
        for (int i = 0; i < items.size(); i++) {
            LineItemEntity lineItemEntity = items.get(i);
            if (lineItemEntity.getProduct().getProductCode().equals(code)) {
                lineItemEntity.setQuantity(quantity);
                return;
            }
        }
        items.add(item);
    }

    public Double getFinalTotal() {
        return getSumTotal() - getDiscountValue();
    }

    public String getFinalTotalFormat() {
        return MyUtil.format(getFinalTotal());
    }

    public void removeItem(LineItemEntity item) {
        String code = item.getProduct().getProductCode();
        for (int i = 0; i < items.size(); i++) {
            LineItemEntity lineItemEntity = items.get(i);
            if (lineItemEntity.getProduct().getProductCode().equals(code)) {
                items.remove(i);
                return;
            }
        }
    }


}
