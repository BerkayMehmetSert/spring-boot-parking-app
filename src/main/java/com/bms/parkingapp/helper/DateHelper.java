package com.bms.parkingapp.helper;

import com.bms.parkingapp.helper.message.BusinessMessage;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateHelper {
    private DateHelper() {
        throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
    }

    public static LocalDate getLocalDate() {
        return LocalDate.now();
    }

    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }
}
