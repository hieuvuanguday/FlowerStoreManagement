/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Formatter {

    /**
     * Formats the given date object to a string representation using the
     * specified date format.
     *
     * @param date The date object to be formatted.
     * @param format The format string representing the desired date format.
     * @return The formatted date string.
     */
    public static String toDateString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * Parses the given date string according to the specified date format and
     * returns a Date object.
     *
     * @param date The date string to be parsed.
     * @param format The format string representing the expected date format.
     * @return The parsed Date object, or the current date if parsing fails.
     */
    public static Date toDate(String date, String format) {
        Date ret = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            ret = sdf.parse(date);
        } catch (ParseException e) {
        }
        return ret;
    }

    /**
     * Checks if the given date string is valid according to the specified date
     * format.
     *
     * @param date The date string to be validated.
     * @param format The format string representing the expected date format.
     * @return {@code true} if the date string is valid, {@code false}
     * otherwise.
     */
    public static boolean isValidDateFormat(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date ret = sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Formats the provided arguments into a formatted information line string.
     *
     * @param arg1 The first argument as an integer.
     * @param arg2 The second argument as a string.
     * @param arg3 The third argument as a string.
     * @param arg4 The fourth argument as a string.
     * @param arg5 The fifth argument as an integer.
     * @param arg6 The sixth argument as a double.
     * @return The formatted information line string.
     */
    public static String toInfoLine(int arg1, String arg2, String arg3, String arg4, int arg5, Double arg6) {
        String total = String.format("$ %.0f", arg6);
        String ret = String.format("|%4d| %-8s | %-10s | %-18s | %12d | %11s |",
                arg1, arg2, arg3, arg4, arg5, total);
        return ret;
    }

    /**
     * Formats the provided count and total amount into a formatted total line
     * string.
     *
     * @param count The count as an integer.
     * @param total The total amount as a double.
     * @return The formatted total line string.
     */
    public static String toTotalLine(int count, double total) {
        String _total = String.format("$ %.0f", total);
        String ret = String.format("|    | Total    |                              | %12d | %11s |", count, _total);
        return ret;
    }
}
