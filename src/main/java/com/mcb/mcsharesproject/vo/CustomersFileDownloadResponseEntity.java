package com.mcb.mcsharesproject.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomersFileDownloadResponseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -5031116196481217148L;

    private String fileName;

    private LocalDateTime timestamp;

    private long numberOfCustomerRecords;

    public CustomersFileDownloadResponseEntity(String fileName, LocalDateTime timestamp, long numberOfCustomerRecords) {
        this.fileName = fileName;
        this.timestamp = timestamp;
        this.numberOfCustomerRecords = numberOfCustomerRecords;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public long getNumberOfCustomerRecords() {
        return numberOfCustomerRecords;
    }

    public void setNumberOfCustomerRecords(long numberOfCustomerRecords) {
        this.numberOfCustomerRecords = numberOfCustomerRecords;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CustomersFileDownloadResponseEntity.class.getSimpleName() + "[", "]").add(
                "fileName='" + fileName + "'")
                                                                                                           .add("timestamp='"
                                                                                                                + timestamp
                                                                                                                + "'")
                                                                                                           .add("numberOfCustomerRecords="
                                                                                                                + numberOfCustomerRecords)
                                                                                                           .toString();
    }
}
