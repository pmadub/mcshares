package com.mcb.mcsharesproject.repositories.customer;

import com.mcb.mcsharesproject.dto.CustomerDetailsDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomerRepositoryImpl implements CustomizedCustomerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public CustomerDetailsDTO retrieveCustomerDetails(String customerId) {

        String  query = " SELECT customer_id AS id, name, date_of_birth_or_incorporated, type, number_of_shares, "
                        + " share_price, balance "
                        + " FROM CUSTOMER "
                        + " WHERE customer_id = ? ";

        return (CustomerDetailsDTO) em.createNativeQuery(query, "customerDetailsResultMapping")
                                      .setParameter(1, customerId)
                                      .getSingleResult();
    }
}
