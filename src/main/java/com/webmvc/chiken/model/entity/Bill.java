package com.webmvc.chiken.model.entity;

import com.webmvc.chiken.util.MyUtil;

import java.io.Serializable;
import java.util.ArrayList;

public class Bill implements Serializable {
    private ArrayList<ViewBillItemEntity> items;
    private Double discountValue;
    public Bill() {
        items = new ArrayList<ViewBillItemEntity>();
    }
    public void setBill(ArrayList<ViewBillItemEntity> listBillItem){
        items = listBillItem;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public ArrayList<ViewBillItemEntity> getItems() {
        return items;
    }
    public Double getSumTotal() {
        if (items == null){
            return 0.0;
        }else {
            double sumTotal = 0;
            for (int i = 0; i < items.size();i++){
                ViewBillItemEntity item = items.get(i);
                sumTotal = sumTotal + item.total();
            }
            return sumTotal;
        }

    }
    public String getSumTotalFormat(){
        return MyUtil.format(getSumTotal());
    }
    public Double getDiscountValue(){
        if (discountValue == null){
            return 0.0;
        }else {
            return discountValue;
        }

    }
    public String getDiscountValueFormat(){
        return MyUtil.format(getDiscountValue());
    }
    public Double getSumTotalFinal(){return getSumTotal() - discountValue;}
    public String getSumTotalFinalFormat(){
        return MyUtil.format(getSumTotalFinal());
    }
}
