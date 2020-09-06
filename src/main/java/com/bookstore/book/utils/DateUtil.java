package com.bookstore.book.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static String getToday() {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return df.format(new Date());
    }
}
