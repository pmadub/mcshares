package com.mcb.mcsharesproject.vo;

import com.mcb.mcsharesproject.xml.bindings.RequestDoc;

import java.util.List;

public class RequestDocUnmarshallingResult {

    private RequestDoc requestDoc;
    private List<XsdValidationErrorVO> validationErrors ;
    private boolean isSuccessful;

    public RequestDocUnmarshallingResult(RequestDoc requestDoc) {
        this.requestDoc = requestDoc;
        this.isSuccessful = true;
    }

    public RequestDocUnmarshallingResult(List<XsdValidationErrorVO> validationErrors) {
        this.validationErrors = validationErrors;
        this.isSuccessful = false;
    }

    public RequestDoc getRequestDoc() {
        return requestDoc;
    }

    public void setRequestDoc(RequestDoc requestDoc) {
        this.requestDoc = requestDoc;
    }

    public List<XsdValidationErrorVO> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<XsdValidationErrorVO> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}
