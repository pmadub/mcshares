package com.mcb.mcsharesproject.services.customer;

import com.mcb.mcsharesproject.adapters.CustomerXmlToEntityAdapter;
import com.mcb.mcsharesproject.dto.CustomerDetailsDTO;
import com.mcb.mcsharesproject.entities.customer.Customer;
import com.mcb.mcsharesproject.enums.CustomerTypeEnum;
import com.mcb.mcsharesproject.exceptions.CustomerNotFoundException;
import com.mcb.mcsharesproject.exceptions.CustomerRequestDataFailedValidationException;
import com.mcb.mcsharesproject.repositories.customer.CustomerRepository;
import com.mcb.mcsharesproject.utils.MessageUtils;
import com.mcb.mcsharesproject.utils.ValidationUtils;
import com.mcb.mcsharesproject.vo.CustomerVO;
import com.mcb.mcsharesproject.xml.bindings.RequestDoc;
import com.mcb.mcsharesproject.xml.bindings.customer.MailingAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerRepository customerRepository;

    public List<Customer> findAllCustomers () {
        return customerRepository.findAll();
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAllCustomersByPage(Pageable page) {
        Page<Customer> pageOfCustomers = customerRepository.findAll(page);
        return pageOfCustomers.getContent();
    }

    @Override
    public Customer findCustomerById(String customerId) {
       return customerRepository.findById(customerId)
                       .orElseThrow(() -> new CustomerNotFoundException(MessageUtils.createCustomerNotFoundMessage(
                               customerId)));
    }

    @Override
    public CustomerDetailsDTO retrieveCustomerDetails(String customerId) {
        return customerRepository.retrieveCustomerDetails(customerId);
    }

    @Override
    public List<Customer> retrieveAllCustomersContainingCustomerNameIgnoreCase(String customerName) {
        return customerRepository.findByCustomerNameContainingIgnoreCase(customerName);
    }

    @Override
    public void saveAllValidCustomerRecords(RequestDoc requestDoc) {
        List<Customer> customerEntities = requestDoc.getCustomers()
                                                    .stream()
                                                    .map(CustomerXmlToEntityAdapter::convertToEntity)
                                                    .toList();
        customerRepository.saveAll(customerEntities);
        log.info(" All entries in requestDoc has been converted in customer entities and saved");
    }

    @Override
    public void validateRequestDataForUpdate(CustomerVO customerVO) {

        if (customerVO.getDateOfBirthOrIncorporated() != null
            && customerVO.getCustomerType() == CustomerTypeEnum.INDIVIDUAL
            && ValidationUtils.isDateOfBirthNotValid(customerVO.getDateOfBirthOrIncorporated())) {
            throw new CustomerRequestDataFailedValidationException(" Birth of birth should be atleast 18 yrs ");
        }

        validationSharePrice(customerVO.getSharePrice());

        validateNumberOfShares(customerVO.getNumberOfShares());
    }


    @Override
    public Customer updateCustomer(Customer persistedCustomer, CustomerVO customerVO) {
        persistedCustomer.setCustomerName(customerVO.getName());
        persistedCustomer.setContactNumber(customerVO.getContactNumber());
        persistedCustomer.setDateOfBirthOrIncorporated(customerVO.getDateOfBirthOrIncorporated());
        persistedCustomer.setRegistrationNumber(customerVO.getRegistrationNumber());
        updateMailingAddress(persistedCustomer, customerVO);
        updateShareDetails(persistedCustomer, customerVO);

        customerRepository.save(persistedCustomer);
        log.info(" customer with id = {} has been updated ",persistedCustomer.getCustomerId());
        return persistedCustomer;
    }

    @Override
    public Customer updateCustomerName(Customer persistedCustomer, String name) {
        persistedCustomer.setCustomerName(name);
        return customerRepository.save(persistedCustomer);
    }

    @Override
    public Customer updateContactNumber(Customer persistedCustomer, String contactNumber) {
        persistedCustomer.setCustomerName(contactNumber);
        return customerRepository.save(persistedCustomer);
    }

    @Override
    public Customer updateCustomerType(Customer persistedCustomer, CustomerTypeEnum contactType) {
        persistedCustomer.setCustomerType(contactType);
        return customerRepository.save(persistedCustomer);
    }

    @Override
    public Customer updateDateOfBirthOrIncorporated(Customer persistedCustomer,
                                                    LocalDate dateOrBirthOrIncorporated) {
        if(persistedCustomer.getCustomerType() == CustomerTypeEnum.INDIVIDUAL
           && ValidationUtils.isDateOfBirthNotValid(dateOrBirthOrIncorporated)) {
            throw new CustomerRequestDataFailedValidationException(" Birth of birth should be atleast 18 yrs ");
        }
        persistedCustomer.setDateOfBirthOrIncorporated(dateOrBirthOrIncorporated);
        return customerRepository.save(persistedCustomer);
    }

    @Override
    public Customer updateRegistrationNumber(Customer persistedCustomer, String registrationNumber) {
        persistedCustomer.setRegistrationNumber(registrationNumber);
        return customerRepository.save(persistedCustomer);
    }

    @Override
    public Customer updateMailingAddress(Customer persistedCustomer, MailingAddress mailingAddress) {
        persistedCustomer.setAddressLine1(mailingAddress.getAddressLine1());
        persistedCustomer.setAddressLine2(mailingAddress.getAddressLine2());
        persistedCustomer.setTownCity(mailingAddress.getTownCity());
        persistedCustomer.setCountry(mailingAddress.getCountry());
        return customerRepository.save(persistedCustomer);
    }

    @Override
    public Customer updateSharePrice(Customer persistedCustomer, BigDecimal sharePrice) {
        validationSharePrice(sharePrice);
        persistedCustomer.setSharePrice(sharePrice);
        return customerRepository.save(persistedCustomer);
    }

    @Override
    public Customer updateNumberOfShares(Customer persistedCustomer, int numberOfShares) {
        if (persistedCustomer.getCustomerType() == CustomerTypeEnum.CORPORATE) {
            throw new CustomerRequestDataFailedValidationException(" Number of shares update is not allowed for corporate ");
        }
        validateNumberOfShares(numberOfShares);
        persistedCustomer.setNumberOfShares(numberOfShares);
        return customerRepository.save(persistedCustomer);
    }

    private void updateShareDetails(Customer customer, CustomerVO customerVO) {
        if (customerVO.getCustomerType() != CustomerTypeEnum.CORPORATE && customerVO.getNumberOfShares() != 0) {
            customer.setNumberOfShares(customerVO.getNumberOfShares());
        }
        if (customerVO.getSharePrice() !=null) {
            customer.setSharePrice(customerVO.getSharePrice());
        }
    }

    private void updateMailingAddress(Customer customer, CustomerVO customerVO) {
        customer.setAddressLine1(customerVO.getAddressLine1());
        customer.setAddressLine2(customerVO.getAddressLine2());
        customer.setTownCity(customerVO.getTownCity());
        customer.setCountry(customerVO.getCountry());
    }

    private void validateNumberOfShares(int numberOfShares) {
        if (numberOfShares <= 0) {
            log.info(" Number of shares is not valid ");
            throw new CustomerRequestDataFailedValidationException(" Number of shares is not valid ");
        }
    }

    private void validationSharePrice(BigDecimal sharePrice) {
        if(!ValidationUtils.isSharePriceValid(sharePrice)){
            log.info(" Share price is not valid ");
            throw new CustomerRequestDataFailedValidationException(" Share price is not valid ");
        }
    }

}
