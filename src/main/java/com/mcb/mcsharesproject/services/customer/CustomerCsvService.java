package com.mcb.mcsharesproject.services.customer;

import com.mcb.mcsharesproject.entities.customer.Customer;
import com.mcb.mcsharesproject.services.file.IFileStorageService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Service
public class CustomerCsvService {

    private static final Path CSV_FILE_PATH = Path.of("csv");

    @Autowired
    @Qualifier("csvFileStorageBean")
    private IFileStorageService fileStorageService;


    public Path createCsvFile(List<Customer> customers) throws IOException {
        var filePath = CSV_FILE_PATH.resolve("customers.csv");
        fileStorageService.create(filePath);
        FileWriter out = new FileWriter(filePath.toFile());
        try (CSVPrinter printer = new CSVPrinter(out,
                                                 CSVFormat.DEFAULT.withHeader("CUSTOMER_ID",
                                                                              "NAME",
                                                                              "DATE_OF_BIRTH/INCORPORATED",
                                                                              "CUSTOMER_TYPE",
                                                                              "NUMBER_OF_SHARES",
                                                                              "SHARE_PRICE_PER_UNIT",
                                                                              "BALANCE"))) {
            for (Customer c : customers) {
                printer.printRecord(c.getCustomerId(),
                                    c.getCustomerName(),
                                    c.getDateOfBirthOrIncorporated(),
                                    c.getCustomerType(),
                                    c.getNumberOfShares(),
                                    c.getSharePrice(),
                                    c.getBalance());
            }

            printer.flush();
        }

        return filePath;
    }
}
