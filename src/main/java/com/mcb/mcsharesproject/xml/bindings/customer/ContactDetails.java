package com.mcb.mcsharesproject.xml.bindings.customer;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType
public class ContactDetails {
    private String contactName;
    private String contactNumber;

    public String getContactName() {
        return contactName;
    }

    @XmlElement(name = "Contact_Name")
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    @XmlElement(name = "Contact_Number")
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
