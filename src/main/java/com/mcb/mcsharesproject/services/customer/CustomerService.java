package com.mcb.mcsharesproject.services.customer;

import com.mcb.mcsharesproject.dto.CustomerDetailsDTO;
import com.mcb.mcsharesproject.entities.customer.Customer;
import com.mcb.mcsharesproject.enums.CustomerTypeEnum;
import com.mcb.mcsharesproject.vo.CustomerVO;
import com.mcb.mcsharesproject.xml.bindings.RequestDoc;
import com.mcb.mcsharesproject.xml.bindings.customer.MailingAddress;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public interface CustomerService {

    List<Customer> findAllCustomers ();

    List<Customer> findAllCustomersByPage(Pageable page);

    void saveCustomer(Customer customer);

    Customer findCustomerById(String customerId);

    CustomerDetailsDTO retrieveCustomerDetails(String customerId);

    List<Customer> retrieveAllCustomersContainingCustomerNameIgnoreCase(String customerName);

    void saveAllValidCustomerRecords(RequestDoc requestDoc);

    void validateRequestDataForUpdate(CustomerVO customerVO);

    Customer updateCustomer(Customer persistedCustomer, CustomerVO customerVO);

    Customer updateCustomerName(Customer persistedCustomer, String name);

    Customer updateContactNumber(Customer persistedCustomer, String contactNumber);

    Customer updateCustomerType(Customer persistedCustomer, CustomerTypeEnum contactType);

    Customer updateDateOfBirthOrIncorporated(Customer persistedCustomer, LocalDate dateOrBirthOrIncorporated);

    Customer updateRegistrationNumber(Customer persistedCustomer, String registrationNumber);

    Customer updateMailingAddress(Customer persistedCustomer, MailingAddress mailingAddress);

    Customer updateSharePrice(Customer persistedCustomer, BigDecimal sharePrice);

    Customer updateNumberOfShares(Customer persistedCustomer, int numberOfShares);
}
