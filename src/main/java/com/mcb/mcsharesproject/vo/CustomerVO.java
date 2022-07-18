package com.mcb.mcsharesproject.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mcb.mcsharesproject.enums.CustomerTypeEnum;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -1449098925371804294L;
    private String id;
    private String name;
    private String contactNumber;
    private CustomerTypeEnum customerType;
    private LocalDate dateOfBirthOrIncorporated;
    private String registrationNumber;
    private String addressLine1;
    private String addressLine2;
    private String townCity;
    private String country;
    private int numberOfShares;
    private BigDecimal sharePrice;
    private BigDecimal balance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    }

    public BigDecimal getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(BigDecimal sharePrice) {
        this.sharePrice = sharePrice;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


}
