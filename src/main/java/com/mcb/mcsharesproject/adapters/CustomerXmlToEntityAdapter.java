package com.mcb.mcsharesproject.adapters;

import com.mcb.mcsharesproject.entities.customer.Customer;
import com.mcb.mcsharesproject.enums.CustomerTypeEnum;
import com.mcb.mcsharesproject.xml.bindings.customer.DataItemCustomer;
import org.apache.commons.lang3.StringUtils;

/**
 * This utility class has the responsibility to convert XML object to entity.
 */
public class CustomerXmlToEntityAdapter {

    private CustomerXmlToEntityAdapter() {
    }

    public static Customer convertToEntity(DataItemCustomer customer) {
        Customer customerEntity = new Customer();
        customerEntity.setCustomerId(customer.getCustomerId());
        customerEntity.setCustomerName(customer.getContactDetails().getContactName());
        customerEntity.setContactNumber(customer.getContactDetails().getContactNumber());
        customerEntity.setCustomerType(CustomerTypeEnum.valueOf(StringUtils.upperCase(customer.getCustomerType())));
        customerEntity.setDateOfBirthOrIncorporated(customer.getDateOfBirth() != null ? customer.getDateOfBirth()
                                                            : customer.getDateIncorporated());
        customerEntity.setRegistrationNumber(customer.getRegistrationNumber());
        addMailingAddressToCustomerEntity(customerEntity, customer.getMailingAddress());
        customerEntity.setNumberOfShares(customer.getSharesDetails().getNumShares());
        customerEntity.setSharePrice(customer.getSharesDetails().getSharePrice());

        return customerEntity;
    }

    private static void addMailingAddressToCustomerEntity(Customer customerEntity,
                                                          com.mcb.mcsharesproject.xml.bindings.customer.MailingAddress mailingAddress) {
        customerEntity.setAddressLine1(mailingAddress.getAddressLine1());
        customerEntity.setAddressLine2(mailingAddress.getAddressLine2());
        customerEntity.setTownCity(mailingAddress.getTownCity());
        customerEntity.setCountry(mailingAddress.getCountry());
    }

}
