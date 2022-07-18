package com.mcb.mcsharesproject.adapters;

import com.mcb.mcsharesproject.utils.LocalDateUtils;
import com.mcb.mcsharesproject.utils.ValidationUtils;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.SAXParseException;

import java.time.LocalDate;

/**
 * This adapter class extends LocalDateAdapter class to provide
 * a business logic implementation.
 */
public class DateOfBirthValidationAdapter extends LocalDateAdapter {

    /**
     * @param s
     * @return
     * @throws Exception
     *
     * This method convert the string date into local date object.
     * Additionally, it performs also some business logic to verify
     * if the date has a period of greater or equal 18 yrs.
     *
     * In case, the validation fails, an exception is thrown.
     */
    @Override
    public LocalDate unmarshal(String s) throws Exception {
        if(StringUtils.isBlank(s)) {
            return null;
        }
        LocalDate dateOfBirth = LocalDateUtils.convertToLocalDate(s);
        if (ValidationUtils.isDateOfBirthNotValid(dateOfBirth)) {
            throw new SAXParseException(" Age should be greater or equal than 18  ", null);
        }
        return dateOfBirth;
    }

}
