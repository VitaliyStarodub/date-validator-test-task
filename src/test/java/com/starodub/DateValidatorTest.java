package com.starodub;

import org.junit.Test;

import java.time.LocalDate;

import static com.starodub.DateValidator.*;

public class DateValidatorTest {

    private LocalDate now = LocalDate.now();

    @Test
    public void validateFromToDates_PastDates_Ok() {
        LocalDate dateFrom = LocalDate.now().minusDays(1);
        LocalDate dateTo = LocalDate.now().plusDays(1);
        validateFromToDates(canBePast(dateFrom), inclusive(canBePast(dateTo)));
    }

    @Test
    public void validateFromToDates_FutureDates_Ok() {
        LocalDate dateFrom = now.plusDays(1);
        LocalDate dateTo = now.plusDays(10);
        validateFromToDates(mustBeFuture(dateFrom), inclusive(mustBeFuture(dateTo)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateFromToDates_FromLargerThanTo_Exception() {
        LocalDate dateFrom = now.minusDays(1);
        LocalDate dateTo = now.plusDays(1);
        validateFromToDates(canBePast(dateTo), inclusive(canBePast(dateFrom)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateFromToDates_ExclusiveWithEqualDates_Exception() {
        LocalDate date = now;
        validateFromToDates(canBePast(date), exclusive(canBePast(date)));
    }
}