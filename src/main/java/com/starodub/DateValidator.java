package com.starodub;

import java.time.LocalDate;

public class DateValidator {

    private LocalDate localDate;
    private boolean mustBeFuture;
    private boolean exclusive;

    private DateValidator(LocalDate localDate) {
        this.localDate = localDate;
    }

    public static void validateFromToDates(DateValidator fromDate, DateValidator toDate) {
        sameDatesCondition(fromDate.getLocalDate(), toDate.getLocalDate());
        sameDatesExclusiveCondition(fromDate, fromDate.getLocalDate(), toDate.getLocalDate());
        toDateInTheFutureCondition(fromDate, toDate.getLocalDate());
        fromDateInTheFutureCondition(fromDate, toDate.getLocalDate());
    }

    private static void fromDateInTheFutureCondition(DateValidator conditionFromDate, LocalDate toDate) {
        if (conditionFromDate.isMustBeFuture() && !toDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date from must be in future");
        }
    }

    private static void toDateInTheFutureCondition(DateValidator conditionFromDate, LocalDate toDate) {
        if (conditionFromDate.isMustBeFuture() && !toDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date to must be in the future");
        }
    }

    private static void sameDatesExclusiveCondition(DateValidator conditionFromDate, LocalDate fromDate, LocalDate toDate) {
        if (conditionFromDate.isExclusive() && fromDate.equals(toDate)) {
            throw new IllegalArgumentException("Dates can't be the same");
        }
    }

    private static void sameDatesCondition(LocalDate fromDate, LocalDate toDate) {
        if (fromDate.isAfter(toDate)) {
            throw new IllegalArgumentException("To date cannot be later than from date");
        }
    }

    public static DateValidator of(LocalDate localDate) {
        return new DateValidator(localDate);
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public boolean isMustBeFuture() {
        this.mustBeFuture = true;
        return mustBeFuture;
    }

    public boolean isExclusive() {
        this.exclusive = true;
        return exclusive;
    }

    public static DateValidator canBePast(LocalDate localDate) {
        return DateValidator.of(localDate);
    }

    public static DateValidator mustBeFuture(LocalDate localDate) {
        return DateValidator.of(localDate);
    }

    public static DateValidator exclusive(DateValidator dateValidator) {
        return DateValidator.of(dateValidator.getLocalDate());
    }

    public static DateValidator inclusive(DateValidator dateValidator) {
        return DateValidator.of(dateValidator.getLocalDate());
    }
}
