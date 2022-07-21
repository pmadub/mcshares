package com.mcb.mcsharesproject.utils;

import java.text.MessageFormat;

/**
 * This class is provide common message creation.
 */
public class MessageUtils {
    private static final String CUSTOMER_NOT_FOUND_MESSAGE = "customer with id = {0} has not being found";
    private static final String XML_FILE_CONTAIN_VALIDATION_ERROR = "{0} contains validation errors";
    private static final String FILE_HAS_BEEN_UPLOADED_SUCCESSFULLY = "{0} has been uploaded successfully, "
                                                                      + " customers records has been stored";
    private static final String PAGE_SIZE_BREACHED_MAXIMUM_LIMIT = " {0} is above the maximum limit authorised for the"
                                                                   + " page size ";

    private MessageUtils() {
    }


    public static String createXmlFileUploadSuccessful(String fileName) {
        return MessageFormat.format(FILE_HAS_BEEN_UPLOADED_SUCCESSFULLY, fileName);
    }

    public static String createCustomerNotFoundMessage(String customerId) {
        return MessageFormat.format(CUSTOMER_NOT_FOUND_MESSAGE, customerId);
    }

    public static String createXmlFileContainValidationErrorMessage(String fileName) {
        return MessageFormat.format(XML_FILE_CONTAIN_VALIDATION_ERROR, fileName);
    }

    public static String createMaximumPageSizeLimitBreachedMessages(int size) {
        return MessageFormat.format(PAGE_SIZE_BREACHED_MAXIMUM_LIMIT, size);
    }
}
