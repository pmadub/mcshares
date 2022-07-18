package com.mcb.mcsharesproject.dto;

import com.mcb.mcsharesproject.enums.CustomerTypeEnum;

import java.time.LocalDate;

public class CustomerDetailsDTO {
    private String id;
    private String customerName;
    private LocalDate dateOfBirthOrIncorporated;
    private CustomerTypeEnum customerType;
    private long numberOfShares;
    private double sharePrice;
    private double balance;

    public CustomerDetailsDTO() {
    }

    public CustomerDetailsDTO(String id,
                              String customerName,
                              LocalDate dateOfBirthOrIncorporated,
                              String customerType,
                              long numberOfShares,
                              double sharePrice,
                              double balance) {
        this.id = id;
        this.customerName = customerName;
        this.dateOfBirthOrIncorporated = dateOfBirthOrIncorporated;
        this.customerType = CustomerTypeEnum.valueOf(customerType);
        this.numberOfShares = numberOfShares;
        this.sharePrice = sharePrice;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getDateOfBirthOrIncorporated() {
        return dateOfBirthOrIncorporated;
    }

    public void setDateOfBirthOrIncorporated(LocalDate dateOfBirthOrIncorporated) {
        this.dateOfBirthOrIncorporated = dateOfBirthOrIncorporated;
    }

    public CustomerTypeEnum getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerTypeEnum customerType) {
        this.customerType = customerType;
    }

    public long getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(long numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public double getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(double sharePrice) {
        this.sharePrice = sharePrice;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
