package com.webmvc.chiken.model.entity;

import com.webmvc.chiken.util.MyUtil;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "view_bill", schema = "public", catalog = "darschqvp4f5i8")
public class ViewBillEntity {
    private Double discountValue;
    private String discountCode;
    private Date invoiceDate;
    private String details;
    private String commune;
    private String district;
    private String province;
    private Integer phone;
    private String addressName;
    private String billId;

    @Basic

    @Column(name = "discount_value")
    public Double getDiscountValue() {
        if (discountValue == null){
            return 0.0;
        }else {
            return discountValue;
        }

    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    @Basic
    @Column(name = "discount_code")
    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    @Basic
    @Column(name = "invoice_date")
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @Basic
    @Column(name = "details")
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Basic
    @Column(name = "commune")
    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    @Basic
    @Column(name = "district")
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Basic
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "phone")
    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "address_name")
    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    @Basic
    @Id
    @Column(name = "bill_id")
    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewBillEntity bill = (ViewBillEntity) o;

        if (discountValue != null ? !discountValue.equals(bill.discountValue) : bill.discountValue != null)
            return false;
        if (discountCode != null ? !discountCode.equals(bill.discountCode) : bill.discountCode != null) return false;
        if (invoiceDate != null ? !invoiceDate.equals(bill.invoiceDate) : bill.invoiceDate != null) return false;
        if (details != null ? !details.equals(bill.details) : bill.details != null) return false;
        if (commune != null ? !commune.equals(bill.commune) : bill.commune != null) return false;
        if (district != null ? !district.equals(bill.district) : bill.district != null) return false;
        if (province != null ? !province.equals(bill.province) : bill.province != null) return false;
        if (phone != null ? !phone.equals(bill.phone) : bill.phone != null) return false;
        if (addressName != null ? !addressName.equals(bill.addressName) : bill.addressName != null) return false;
        if (billId != null ? !billId.equals(bill.billId) : bill.billId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = discountValue != null ? discountValue.hashCode() : 0;
        result = 31 * result + (discountCode != null ? discountCode.hashCode() : 0);
        result = 31 * result + (invoiceDate != null ? invoiceDate.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (commune != null ? commune.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (addressName != null ? addressName.hashCode() : 0);
        result = 31 * result + (billId != null ? billId.hashCode() : 0);
        return result;
    }

    public String shortAddress() {
        return getDetails() + ", " + getCommune();
    }

    public String fullAddress() {
        return getDetails() + ", " + getCommune() + ", " + getDistrict() + ", " + getProvince();
    }
    public String discountValueFormat(){
        return MyUtil.format(getDiscountValue());
    }
}
