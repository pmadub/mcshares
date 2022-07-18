package com.mcb.mcsharesproject.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.StringJoiner;

public class XsdValidationErrorVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -3632280283398229188L;

    private int lineNumber;
    private int columnNumber;
    private String errorMessage;

    public XsdValidationErrorVO(int lineNumber, int columnNumber, String errorMessage) {
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
        this.errorMessage = errorMessage;
    }

    public XsdValidationErrorVO(int lineNumber, int columnNumber) {
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    public XsdValidationErrorVO(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", XsdValidationErrorVO.class.getSimpleName() + "[", "]").add("lineNumber="
                                                                                                 + lineNumber)
                                                                                            .add("columnNumber="
                                                                                              + columnNumber)
                                                                                            .add("errorMessage='"
                                                                                              + errorMessage
                                                                                              + "'")
                                                                                            .toString();
    }
}
