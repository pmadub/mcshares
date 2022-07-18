package com.mcb.mcsharesproject.controllers;

import com.mcb.mcsharesproject.entities.customer.Customer;
import com.mcb.mcsharesproject.enums.CustomerTypeEnum;
import com.mcb.mcsharesproject.services.customer.CustomerCsvService;
import com.mcb.mcsharesproject.services.customer.CustomerService;
import com.mcb.mcsharesproject.services.file.IFileStorageService;
import com.mcb.mcsharesproject.services.log.LogService;
import com.mcb.mcsharesproject.services.xml.XmlIntegrationService;
import com.mcb.mcsharesproject.utils.MessageUtils;
import com.mcb.mcsharesproject.vo.*;
import com.mcb.mcsharesproject.xml.bindings.customer.MailingAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.bind.JAXBException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class CustomerController {

    @Autowired
    @Qualifier("uploadFileStorageBean")
    private IFileStorageService fileStorageService;

    @Autowired
    private XmlIntegrationService xmlIntegrationService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerCsvService customerCsvService;

    @Autowired
    private LogService logService;

    @PostMapping("/customers/upload")
    public ResponseEntity<EntityResponseVO> processUploadedFile(@RequestParam("file") MultipartFile file) throws IOException {
        fileStorageService.save(file);
        Resource resource = fileStorageService.load(file.getOriginalFilename());
        RequestDocUnmarshallingResult unmarshallResult;
        try {
            unmarshallResult = xmlIntegrationService.unmarshall(resource.getFile());
        } catch (JAXBException | SAXException e) {
            return ResponseEntity.internalServerError().body(new EntityResponseVO(null, e.getMessage(),
                                                                                  null));
        }

        if (!unmarshallResult.isSuccessful()) {
            unmarshallResult.getValidationErrors().forEach(error -> {
                log.error(error.getErrorMessage());
                logService.logError(new LogInfoVO(LocalDateTime.now(),
                                                  file.getOriginalFilename(),
                                                  error.getErrorMessage()));
            });
            return ResponseEntity.badRequest()
                                 .body(new EntityResponseVO(unmarshallResult.getValidationErrors(),
                                                            MessageUtils.createXmlFileContainValidationErrorMessage(file.getOriginalFilename()),
                                                            null));
        }

        customerService.saveAllValidCustomerRecords(unmarshallResult.getRequestDoc());

        return ResponseEntity.ok(new EntityResponseVO(MessageUtils.createXmlFileUploadSuccessful(file.getOriginalFilename())));
    }

    @GetMapping("/customers")
    public List<Customer> retrieveAllCustomers() {
        return customerService.findAllCustomers();
    }

    @GetMapping("/customers/{customer_id}")
    public Customer retrieveCustomer(@PathVariable(name = "customer_id") String customerId) {
        return customerService.findCustomerById(customerId);
    }

    @PutMapping("/customers/{customer_id}/update")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(name = "customer_id") String customerId,
                                                   @Valid @RequestBody CustomerVO customer) {
        var persistedCustomer = customerService.findCustomerById(customerId);

        customerService.validateRequestDataForUpdate(customer);

        Customer updatedCustomer = customerService.updateCustomer(persistedCustomer, customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PutMapping("/customers/{customer_id}/name/update")
    public ResponseEntity<Customer> updateCustomerName(@PathVariable(name = "customer_id") String customerId,
                                                       @RequestBody String name) {
        var persistedCustomer = customerService.findCustomerById(customerId);
        Customer updatedCustomer = customerService.updateCustomerName(persistedCustomer, name);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PutMapping("/customers/{customer_id}/contact_number/update")
    public ResponseEntity<Customer> updateConsumerContactNumber(@PathVariable(name = "customer_id") String customerId,
                                                       @RequestBody String contactNumber) {
        var persistedCustomer = customerService.findCustomerById(customerId);
        Customer updatedCustomer = customerService.updateContactNumber(persistedCustomer, contactNumber);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PutMapping("/customers/{customer_id}/customer_type/update")
    public ResponseEntity<Customer> updateConsumerType(@PathVariable(name = "customer_id") String customerId,
                                                                @RequestBody CustomerTypeEnum customerType) {
        var persistedCustomer = customerService.findCustomerById(customerId);
        Customer updatedCustomer = customerService.updateCustomerType(persistedCustomer, customerType);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PutMapping("/customers/{customer_id}/date_of_birth_or_incorporated/update")
    public ResponseEntity<Customer> updateCustomerDateOfBirthOrIncorporated(@PathVariable(name = "customer_id") String customerId,
                                                                @RequestBody LocalDate dateOfBirthOrIncorporated) {
        var persistedCustomer = customerService.findCustomerById(customerId);
        Customer updatedCustomer = customerService.updateDateOfBirthOrIncorporated(persistedCustomer, dateOfBirthOrIncorporated);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PutMapping("/customers/{customer_id}/registration_number/update")
    public ResponseEntity<Customer> updateCustomerRegistrationNumber(@PathVariable(name = "customer_id") String customerId,
                                                                    @RequestBody String registrationNumber) {
        var persistedCustomer = customerService.findCustomerById(customerId);
        Customer updatedCustomer = customerService.updateRegistrationNumber(persistedCustomer, registrationNumber);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PutMapping("/customers/{customer_id}/mailing_address/update")
    public ResponseEntity<Customer> updateCustomerMailingAddress(@PathVariable(name = "customer_id") String customerId,
                                                             @RequestBody MailingAddress mailingAddress) {
        var persistedCustomer = customerService.findCustomerById(customerId);
        Customer updatedCustomer = customerService.updateMailingAddress(persistedCustomer, mailingAddress);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PutMapping("/customers/{customer_id}/share_price/update")
    public ResponseEntity<Customer> updateCustomerSharePrice(@PathVariable(name = "customer_id") String customerId,
                                                             @RequestBody BigDecimal sharePrice) {
        var persistedCustomer = customerService.findCustomerById(customerId);
        Customer updatedCustomer = customerService.updateSharePrice(persistedCustomer, sharePrice);
        return ResponseEntity.ok(updatedCustomer);
    }

    @PutMapping("/customers/{customer_id}/number_of_shares/update")
    public ResponseEntity<Customer> updateCustomerNumberOfShares(@PathVariable(name = "customer_id") String customerId,
                                                             @RequestBody int numberOfShares) {
        var persistedCustomer = customerService.findCustomerById(customerId);
        Customer updatedCustomer = customerService.updateNumberOfShares(persistedCustomer, numberOfShares);
        return ResponseEntity.ok(updatedCustomer);
    }


    @GetMapping("/customers/{customer_name}/search")
    public List<Customer> searchCustomer(@PathVariable(name = "customer_name") String customerName) {
        return customerService.retrieveAllCustomersContainingCustomerNameIgnoreCase(customerName);
    }

    @GetMapping("/customers/download_csv")
    public ResponseEntity<CustomersFileDownloadResponseEntity> downloadListOfCustomer(HttpServletResponse response,
                                                                                      @RequestParam(defaultValue = "0") int page,
                                                                                      @RequestParam(defaultValue = "3") int size) {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=customers.csv");

        var subSetOfCustomers = customerService.findAllCustomersByPage(PageRequest.of(page, size));
        String fileName;
        try {
            Path csvPath = customerCsvService.createCsvFile(subSetOfCustomers);
            copyToResponse(response, csvPath);
            fileName = csvPath.getFileName().toString();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(new CustomersFileDownloadResponseEntity(fileName, LocalDateTime.now(),subSetOfCustomers.size()));
    }

    private void copyToResponse(HttpServletResponse response, Path csvPath) throws IOException {
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(csvPath.toFile()))) {
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }

}
