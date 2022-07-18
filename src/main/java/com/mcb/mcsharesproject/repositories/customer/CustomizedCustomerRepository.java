package com.mcb.mcsharesproject.repositories.customer;

import com.mcb.mcsharesproject.dto.CustomerDetailsDTO;


public interface CustomizedCustomerRepository {

    CustomerDetailsDTO retrieveCustomerDetails(String customerId);
}
