package com.bookstore.book.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static String getToday() {
        return sdf.format(new Date());
    }

    public static Date getDateFromString(String dateString){
        Date date = new Date();
        try {
            date = sdf.parse(dateString);
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return date;
    }
}
