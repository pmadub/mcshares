package com.mcb.mcsharesproject.services.customer;

import com.mcb.mcsharesproject.entities.customer.Customer;
import com.mcb.mcsharesproject.enums.CustomerTypeEnum;
import com.mcb.mcsharesproject.exceptions.CustomerRequestDataFailedValidationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testUpdateNumberOfSharesValid() {
        Customer dummyIndividualCustomer = createDummyIndividualCustomer();
        customerService.saveCustomer(dummyIndividualCustomer);
        Customer customerById = customerService.findCustomerById(dummyIndividualCustomer.getCustomerId());
        customerService.updateNumberOfShares(customerById,5);
        Customer updatedCustomer = customerService.findCustomerById(dummyIndividualCustomer.getCustomerId());
        Assertions.assertEquals(5, updatedCustomer.getNumberOfShares());
        Assertions.assertEquals(updatedCustomer.getSharePrice().multiply(BigDecimal.valueOf(5)),
                                updatedCustomer.getBalance());
    }

    @Test
    void testUpdateNumberOfSharesWithZero() {
        Customer dummyIndividualCustomer = createDummyIndividualCustomer();
        customerService.saveCustomer(dummyIndividualCustomer);
        Customer customerById = customerService.findCustomerById(dummyIndividualCustomer.getCustomerId());
        Assertions.assertThrowsExactly(CustomerRequestDataFailedValidationException.class,
                                       () -> customerService.updateNumberOfShares(customerById, 0));
    }

    @Test
    void testUpdateNumberOfSharesWithCorporate () {
        Customer dummyCorporateCustomer = createDummyCorporateCustomer();
        customerService.saveCustomer(dummyCorporateCustomer);
        Customer customerById = customerService.findCustomerById(dummyCorporateCustomer.getCustomerId());
        Assertions.assertThrowsExactly(CustomerRequestDataFailedValidationException.class,
                                       () -> customerService.updateNumberOfShares(customerById, 5));
    }

    @Test
    void testUpdateSharePriceWithBalanceUpdate() {
        Customer dummyIndividualCustomer = createDummyIndividualCustomer();
        customerService.saveCustomer(dummyIndividualCustomer);
        Customer customerById = customerService.findCustomerById(dummyIndividualCustomer.getCustomerId());
        customerService.updateNumberOfShares(customerById,50);
        Customer updatedCustomer = customerService.findCustomerById(dummyIndividualCustomer.getCustomerId());
        Assertions.assertEquals(updatedCustomer.getSharePrice().multiply(BigDecimal.valueOf(50)),
                                updatedCustomer.getBalance());
    }

    private Customer createDummyIndividualCustomer() {
        Customer customer = new Customer();
        customer.setCustomerId("001");
        customer.setCustomerName("CustomerName");
        customer.setCustomerType(CustomerTypeEnum.INDIVIDUAL);
        customer.setSharePrice(BigDecimal.valueOf(10.50));
        customer.setNumberOfShares(10);

        return customer;
    }

    private Customer createDummyCorporateCustomer() {
        Customer customer = new Customer();
        customer.setCustomerId("001");
        customer.setCustomerName("CustomerName");
        customer.setCustomerType(CustomerTypeEnum.CORPORATE);
        customer.setSharePrice(BigDecimal.valueOf(10.50));
        customer.setNumberOfShares(10);

        return customer;
    }

}