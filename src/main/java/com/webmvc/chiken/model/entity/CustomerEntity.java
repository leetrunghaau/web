package com.webmvc.chiken.model.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "customer", schema = "public", catalog = "darschqvp4f5i8")
public class CustomerEntity {
    private int customerId;
    private String ggId;
    private String fbId;
    private String mail;
    private String passwd;
    private String name;

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy= AUTO)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "gg_id")
    public String getGgId() {
        return ggId;
    }

    public void setGgId(String ggId) {
        this.ggId = ggId;
    }

    @Basic
    @Column(name = "fb_id")
    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    @Basic
    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "passwd")
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEntity customer = (CustomerEntity) o;

        if (customerId != customer.customerId) return false;
        if (ggId != null ? !ggId.equals(customer.ggId) : customer.ggId != null) return false;
        if (fbId != null ? !fbId.equals(customer.fbId) : customer.fbId != null) return false;
        if (mail != null ? !mail.equals(customer.mail) : customer.mail != null) return false;
        if (passwd != null ? !passwd.equals(customer.passwd) : customer.passwd != null) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerId;
        result = 31 * result + (ggId != null ? ggId.hashCode() : 0);
        result = 31 * result + (fbId != null ? fbId.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (passwd != null ? passwd.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
