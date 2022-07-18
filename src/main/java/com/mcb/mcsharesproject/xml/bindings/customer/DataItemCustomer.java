package com.mcb.mcsharesproject.xml.bindings.customer;

import com.mcb.mcsharesproject.adapters.DateOfBirthValidationAdapter;
import com.mcb.mcsharesproject.adapters.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlAccessorType
public class DataItemCustomer {
    private String customerId;
    private String customerType;
    private LocalDate dateOfBirth;
    private LocalDate dateIncorporated;
    private String registrationNumber;
    private MailingAddress mailingAddress;
    private ContactDetails contactDetails;
    private SharesDetails sharesDetails;

    public String getCustomerId() {
        return customerId;
    }

    @XmlElement(name = "customer_id")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerType() {
        return customerType;
    }

    @XmlElement(name = "Customer_Type")
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @XmlElement(name = "Date_Of_Birth")
    @XmlJavaTypeAdapter(DateOfBirthValidationAdapter.class)
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateIncorporated() {
        return dateIncorporated;
    }

    @XmlElement(name = "Date_Incorp")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public void setDateIncorporated(LocalDate dateIncorporated) {
        this.dateIncorporated = dateIncorporated;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @XmlElement(name = "REGISTRATION_NO")
    public void setRegistrationNumber(String registrationNumber) {
        setRegistrationNo(registrationNumber);
    }

    public MailingAddress getMailingAddress() {
        return mailingAddress;
    }

    @XmlElement(name = "Registration_No")
    public void setRegistrationNumberInLowerCase(String registrationNumber) {
        setRegistrationNo(registrationNumber);
    }

    private void setRegistrationNo(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @XmlElement(name = "Mailing_Address")
    public void setMailingAddress(MailingAddress mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    @XmlElement(name = "Contact_Details")
    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    @XmlElement(name = "Shares_Details")
    public SharesDetails getSharesDetails() {
        return sharesDetails;
    }

    public void setSharesDetails(SharesDetails sharesDetails) {
        this.sharesDetails = sharesDetails;
    }
}
