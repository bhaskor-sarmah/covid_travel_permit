package com.bohniman.travelpermit.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateUtil
 */
public class DateUtil {

    public static Date addHoursToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        calendar.add(Calendar.MINUTE, 59);
        calendar.add(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static String getFormattedDateTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        return df.format(date);
    }

    public static String getDefaultDateTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    public static Date getDateFromString(String date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.parse(date);
    }

    public static String getFormattedDateTimeWithMilliseconds(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
        return df.format(date);
    }

    public static String getFormattedDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(date);
    }

    public static Date getDateFromDateTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(df.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String getFormattedTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss a");
        return df.format(date);
    }

    public static String getFormattedTimeNotSecond(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
        return df.format(date);
    }

    public static Date getFirstDateOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static String friendlyTimeDiff(long timeDifferenceMilliseconds) {
        long diffSeconds = timeDifferenceMilliseconds / 1000;
        long diffMinutes = timeDifferenceMilliseconds / (60 * 1000);
        long diffMinutesSeconds = (long) (timeDifferenceMilliseconds % (60 * 1000));
        diffMinutesSeconds = (long) (diffMinutesSeconds / 1000);

        long diffHours = timeDifferenceMilliseconds / (60 * 60 * 1000);
        long diffHoursMinutes = (long) (timeDifferenceMilliseconds % (60 * 60 * 1000));
        diffHoursMinutes = (long) (diffHoursMinutes / (60 * 1000));

        long diffDays = timeDifferenceMilliseconds / (60 * 60 * 1000 * 24);
        long diffDaysHours = (long) (timeDifferenceMilliseconds % (60 * 60 * 1000 * 24));
        diffDaysHours = (long) (diffDaysHours / (60 * 60 * 1000));

        long diffWeeks = timeDifferenceMilliseconds / (60 * 60 * 1000 * 24 * 7);
        long diffWeekDays = (long) (timeDifferenceMilliseconds % (60 * 60 * 1000 * 24 * 7));
        diffWeekDays = (long) (diffWeekDays / (60 * 60 * 1000 * 24));

        long diffMonths = (long) (timeDifferenceMilliseconds / (60 * 60 * 1000 * 24 * 30.41666666));
        long diffMonthsWeeks = (long) (timeDifferenceMilliseconds % (60 * 60 * 1000 * 24 * 30.41666666));
        diffMonthsWeeks = (long) (diffMonthsWeeks / (60 * 60 * 1000 * 24 * 7));

        long diffYears = timeDifferenceMilliseconds / ((long) 60 * 60 * 1000 * 24 * 365);
        long diffYearsMonths = (long) (timeDifferenceMilliseconds % ((long) 60 * 60 * 1000 * 24 * 365));
        diffYearsMonths = (long) (diffYearsMonths / (60 * 60 * 1000 * 24 * 30.41666666));

        if (diffSeconds < 1) {
            return "less than a second";
        } else if (diffMinutes < 1) {
            return diffSeconds + " seconds";
        } else if (diffHours < 1) {
            return diffMinutes + " minutes " + diffMinutesSeconds + " Seconds";
        } else if (diffDays < 1) {
            return diffHours + " hours " + diffHoursMinutes + " Minutes";
        } else if (diffWeeks < 1) {
            return diffDays + " days " + diffDaysHours + " Hours";
        } else if (diffMonths < 1) {
            return diffWeeks + " weeks " + diffWeekDays + " Days";
        } else if (diffYears < 1) {
            return diffMonths + " months " + diffMonthsWeeks + " Weeks";
        } else {
            return diffYears + " years " + diffYearsMonths + " Months";
        }
    }
}