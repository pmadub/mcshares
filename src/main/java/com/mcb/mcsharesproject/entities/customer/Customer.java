package com.mcb.mcsharesproject.entities.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mcb.mcsharesproject.dto.CustomerDetailsDTO;
import com.mcb.mcsharesproject.enums.CustomerTypeEnum;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "CUSTOMER")
@SqlResultSetMapping(
        name = "customerDetailsResultMapping",
        classes = {@ConstructorResult(targetClass = CustomerDetailsDTO.class,
                columns = {@ColumnResult(name = "id",type = String.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "date_of_birth_or_incorporated", type = LocalDate.class),
                        @ColumnResult(name = "type", type = String.class),
                        @ColumnResult(name = "number_of_shares", type = long.class),
                        @ColumnResult(name = "share_price", type = double.class),
                        @ColumnResult(name = "balance", type = double.class)})})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = -1879888447763478305L;

    @Id
    @Column(name = "CUSTOMER_ID")
    private String customerId;

    @Column(name = "NAME")
    private String customerName;

    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private CustomerTypeEnum customerType;

    @Column(name = "DATE_OF_BIRTH_OR_INCORPORATED")
    private LocalDate dateOfBirthOrIncorporated;

    @Column(name = "REGISTRATION_NUMBER")
    private String registrationNumber;

    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;

    @Column(name = "ADDRESS_LINE_2")
    private String addressLine2;

    @Column(name = "TOWNCITY")
    private String townCity;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "NUMBER_OF_SHARES")
    @Positive
    private int numberOfShares;

    @Column(name = "SHARE_PRICE")
    private BigDecimal sharePrice;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public CustomerTypeEnum getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerTypeEnum customerType) {
        this.customerType = customerType;
    }

    public LocalDate getDateOfBirthOrIncorporated() {
        return dateOfBirthOrIncorporated;
    }

    public void setDateOfBirthOrIncorporated(LocalDate dateOfBirthOrIncorporated) {
        this.dateOfBirthOrIncorporated = dateOfBirthOrIncorporated;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getTownCity() {
        return townCity;
    }

    public void setTownCity(String townCity) {
        this.townCity = townCity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
        computeBalance();
    }

    public BigDecimal getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(BigDecimal sharePrice) {
        this.sharePrice = sharePrice;
        computeBalance();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void computeBalance() {
        if(sharePrice != null && numberOfShares > 0) {
            this.balance = this.sharePrice.multiply(BigDecimal.valueOf(this.numberOfShares));
        }
    }
}
