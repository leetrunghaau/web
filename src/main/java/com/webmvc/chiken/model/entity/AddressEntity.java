package com.webmvc.chiken.model.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "address", schema = "public", catalog = "darschqvp4f5i8")
public class AddressEntity {
    private String addressName;
    private Integer phone;
    private String province;
    private String district;
    private String commune;
    private String details;
    private int addressId;
    private CustomerEntity customerId;



    @Basic
    @Column(name = "address_name")

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
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
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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
    @Column(name = "commune")
    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    @Basic
    @Column(name = "details")
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy= AUTO)
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressEntity that = (AddressEntity) o;

        if (addressId != that.addressId) return false;
        if (addressName != null ? !addressName.equals(that.addressName) : that.addressName != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        if (commune != null ? !commune.equals(that.commune) : that.commune != null) return false;
        if (details != null ? !details.equals(that.details) : that.details != null) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = addressName != null ? addressName.hashCode() : 0;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (commune != null ? commune.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + addressId;

        return result;
    }
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    public CustomerEntity getCustomerId(){return customerId;}

    public void setCustomerId(CustomerEntity customerId) {
        this.customerId = customerId;
    }
    public String fullAddress() {
        return getDetails() + ", " + getCommune() + ", " + getDistrict() + ", " + getProvince();
    }
    public String phoneFormat(){
        return String.valueOf(phone).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "(+84)$1 $2 $3");
    }
}
