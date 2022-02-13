package com.playtomic.tests.wallet.util;

import com.playtomic.tests.wallet.exception.DateFormatException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateFormatter {

    public LocalDate fromStringToLocalDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            throw new DateFormatException(e.getMessage());
        }
    }

    public LocalDateTime startTimeOfDay(String date) {
        return LocalDateTime.of(this.fromStringToLocalDate(date), LocalTime.MIDNIGHT);
    }

    public LocalDateTime endTimeOfDay(String date) {
        return LocalTime.MAX.atDate(this.fromStringToLocalDate(date));
    }

}
