package com.webmvc.chiken.model.entity;

import javax.persistence.*;
import java.sql.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "bill", schema = "public", catalog = "darschqvp4f5i8")
public class BillEntity {
    private Date invoiceDate;
    private Byte processed;
    private String billId;
    private AddressEntity addressByAddressId;
    private CustomerEntity customer;
    private DiscountEntity discount;

    @Basic
    @Column(name = "invoice_date")
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @Basic
    @Column(name = "processed")
    public Byte getProcessed() {
        return processed;
    }

    public void setProcessed(Byte processed) {
        this.processed = processed;
    }

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

        BillEntity that = (BillEntity) o;

        if (billId != that.billId) return false;
        if (invoiceDate != null ? !invoiceDate.equals(that.invoiceDate) : that.invoiceDate != null) return false;
        if (processed != null ? !processed.equals(that.processed) : that.processed != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = invoiceDate != null ? invoiceDate.hashCode() : 0;
        result = 31 * result + (processed != null ? processed.hashCode() : 0);

        return result;
    }

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    public AddressEntity getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(AddressEntity addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    public CustomerEntity getCustomer(){return  customer;}

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
    @ManyToOne
    @JoinColumn(name = "discount_id", referencedColumnName = "discount_id")
    public DiscountEntity getDiscount(){return discount;}

    public void setDiscount(DiscountEntity discount) {
        this.discount = discount;
    }
}
