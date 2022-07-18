package com.mcb.mcsharesproject.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

/**
 * This class holds common validation so to provide a centralized approach.
 */
public class ValidationUtils {

    private ValidationUtils() {
    }

    /**
     * @param dateOfBirth
     * @return
     *
     * This method perform date of birth validation. It verifies
     * if the date of birth is greater than 18 yrs.
     */
    public static boolean isDateOfBirthNotValid(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears() < 18;
    }

    /**
     * @param sharePrice
     * @return
     *
     * This method determined if the share price is valid.
     * In the business logic, the share price is invalid when :
     * It is less than or equal 0
     * It has more than 2 decimal places
     */
    public static boolean isSharePriceValid(BigDecimal sharePrice) {
        return !(sharePrice != null
                 && sharePrice.compareTo(BigDecimal.ZERO) <= 0
                 && getNumberOfDecimalPlaces(sharePrice) > 2);
    }

    /**
     * @param bigDecimal
     * @return
     *
     * This method determines the number of decimal places found in the
     * BigDecimal object.
     */
    public static int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
        String string = bigDecimal.stripTrailingZeros().toPlainString();
        int index = string.indexOf(".");
        return index < 0 ? 0 : string.length() - index - 1;
    }
}
