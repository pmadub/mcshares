package com.mcb.mcsharesproject.xml.bindings.customer;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType
public class MailingAddress {
    private String addressLine1;
    private String addressLine2;
    private String townCity;
    private String country;

    public String getAddressLine1() {
        return addressLine1;
    }

    @XmlElement(name = "Address_Line1")
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    @XmlElement(name = "Address_Line2")
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getTownCity() {
        return townCity;
    }

    @XmlElement(name = "Town_City")
    public void setTownCity(String townCity) {
        this.townCity = townCity;
    }

    public String getCountry() {
        return country;
    }

    @XmlElement(name = "Country")
    public void setCountry(String country) {
        this.country = country;
    }
}
