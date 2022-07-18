package com.mcb.mcsharesproject.adapters;

import com.mcb.mcsharesproject.utils.LocalDateUtils;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * This adapter class is called during unmarshalling String of format date.
 * It converts the string into Local Date object.
 * It converts the Local Date object to String.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    /**
     * @param s
     * @return
     * @throws Exception
     *
     * This method perform conversion the string date to LocalDate object.
     */
    @Override
    public LocalDate unmarshal(String s) throws Exception {
        if(StringUtils.isBlank(s)) {
            return null;
        }
        return LocalDateUtils.convertToLocalDate(s);
    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return null;
    }
}
