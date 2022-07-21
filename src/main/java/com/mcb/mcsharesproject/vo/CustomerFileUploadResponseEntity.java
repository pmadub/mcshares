package com.mcb.mcsharesproject.vo;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerFileUploadResponseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -1193105238210086610L;

    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private List<XsdValidationErrorVO> xsdValidationErrorList ;
    private String detailedMessage;

    public CustomerFileUploadResponseEntity(String message) {
        this.message = message;
    }

    public CustomerFileUploadResponseEntity(List<XsdValidationErrorVO> xsdValidationErrorList,
                                            String message,
                                            String detailedMessage) {
        this.message = message;
        this.xsdValidationErrorList = xsdValidationErrorList;
        this.detailedMessage = detailedMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @JsonGetter(value = "error list")
    public List<XsdValidationErrorVO> getXsdValidationErrorList() {
        return xsdValidationErrorList;
    }

    public void setXsdValidationErrorList(List<XsdValidationErrorVO> xsdValidationErrorList) {
        this.xsdValidationErrorList = xsdValidationErrorList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

    public void setDetailedMessage(String detailedMessage) {
        this.detailedMessage = detailedMessage;
    }
}
