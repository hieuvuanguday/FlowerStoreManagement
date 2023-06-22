/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Administrator
 */
public class Inputter {

    private static final Scanner sc = new Scanner(System.in);

    /**
     * Prompts the user for an integer input and returns the entered value.
     *
     * @param inputMsg The message to display as a prompt for the user.
     * @return The integer value entered by the user.
     */
    public static int getInt(String inputMsg) {
        boolean flag = true;
        int num = 0;
        while (flag) {
            try {
                System.out.print(inputMsg);
                num = Integer.parseInt(sc.nextLine());
                flag = false;
            } catch (NumberFormatException e) {
                System.out.println("Must be an integer number! ");
            }
        }
        return num;
    }

    /**
     * Prompts the user for a real number input and returns the entered value.
     *
     * @param inputMsg The message to display as a prompt for the user.
     * @return The real number value entered by the user.
     */
    public static double getReal(String inputMsg) {
        boolean flag = true;
        double num = 0;
        while (flag) {
            try {
                System.out.print(inputMsg);
                num = Double.parseDouble(sc.nextLine());
                flag = false;
            } catch (NumberFormatException e) {
                System.out.println("Must be a real number! ");
            }
        }
        return num;
    }

    /**
     * Prompts the user for a positive integer input and returns the entered
     * value.
     *
     * @param inputMsg The message to display as a prompt for the user.
     * @return The positive integer value entered by the user.
     */
    public static int getPositiveInt(String inputMsg) {
        boolean flag = true;
        int num = 0;
        while (flag) {
            try {
                System.out.print(inputMsg);
                num = Integer.parseInt(sc.nextLine());
                if (num < 0) {
                    throw new Exception();
                }
                flag = false;
            } catch (NumberFormatException e) {
                System.out.println("Must be an integer number! ");
            } catch (Exception e) {
                System.out.println("Must be higher than 0! ");
            }
        }
        return num;
    }

    /**
     * Prompts the user for a positive real number input and returns the entered
     * value.
     *
     * @param inputMsg The message to display as a prompt for the user.
     * @return The positive real number value entered by the user.
     */
    public static double getPositiveReal(String inputMsg) {
        boolean flag = true;
        double num = 0;
        while (flag) {
            try {
                System.out.print(inputMsg);
                num = Double.parseDouble(sc.nextLine());
                if (num < 0) {
                    throw new Exception();
                }
                flag = false;
            } catch (NumberFormatException e) {
                System.out.println("Must be an integer number! ");
            } catch (Exception e) {
                System.out.println("Must be higher than 0! ");
            }
        }
        return num;
    }

    /**
     * Prompts the user for a string input and returns the entered value.
     *
     * @param inputMsg The message to display as a prompt for the user.
     * @return The string value entered by the user.
     */
    public static String getString(String inputMsg) {
        System.out.print(inputMsg);
        return sc.nextLine();
    }

    /**
     * Prompts the user for a non-blank string input and returns the entered
     * value.
     *
     * @param inputMsg The message to display as a prompt for the user.
     * @return The non-blank string value entered by the user.
     */
    public static String getStringNonBlank(String inputMsg) {
        boolean flag = true;
        String str = null;
        while (flag) {
            try {
                System.out.print(inputMsg);
                str = sc.nextLine();
                if (str == null || str.isEmpty()) {
                    throw new Exception();
                }
                flag = false;
            } catch (Exception e) {
                System.out.println("This field cannot be empty.");
            }
        }
        return str;
    }

    /**
     * Prompts the user for a string input, validates it using a regular
     * expression, and returns the entered value.
     *
     * @param inputMsg The message to display as a prompt for the user.
     * @param errorMsg The error message to display if the entered string does
     * not match the specified regex pattern.
     * @param regex The regular expression pattern to validate the entered
     * string.
     * @return The validated string value entered by the user.
     */
    public static String getString(String inputMsg, String errorMsg, String regex) {
        boolean flag = true;
        String str = null;
        while (flag) {
            try {
                System.out.print(inputMsg);
                str = sc.nextLine();
                if (str == null || str.isEmpty()) {
                    throw new Exception("This feild cannot be empty.");
                }
                if (!str.matches(regex)) {
                    throw new Exception(errorMsg);
                }
                flag = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return str;
    }

    /**
     * Prompts the user for a string input and validates its length to be within
     * the specified range, then returns the entered value.
     *
     * @param inputMsg The message to display as a prompt for the user.
     * @param min The minimum allowed length for the entered string.
     * @param max The maximum allowed length for the entered string.
     * @return The validated string value entered by the user.
     */
    public static String getString(String inputMsg, int min, int max) {
        boolean flag = true;
        String str = null;
        while (flag) {
            try {
                System.out.print(inputMsg);
                str = sc.nextLine();
                int len = str.length();
                if (len < min || len > max) {
                    throw new Exception();
                }
                flag = false;
            } catch (Exception e) {
                System.out.println(String.format("String length must be in [%d,%d].", min, max));
            }
        }
        return str;
    }

    /**
     * Prompts the user for a date input and validates it against the specified
     * date format, then returns the entered value.
     *
     * @param inputMsg The message to display as a prompt for the user.
     * @param dateFormat The format string representing the expected date
     * format.
     * @return The validated date string entered by the user.
     */
    public static String getDate(String inputMsg, String dateFormat) {
        boolean flag = true;
        String inputStr = "";
        while (flag) {
            try {
                System.out.print(inputMsg);
                inputStr = sc.nextLine();
                if (checkValidDate(inputStr, dateFormat)) {
                    flag = false;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid date: " + dateFormat);
            }
        }
        return inputStr;
    }

    /**
     * Checks if the given date string is valid according to the specified date
     * format.
     *
     * @param date The date string to be validated.
     * @param dateFormat The format string representing the expected date
     * format.
     * @return {@code true} if the date is valid, {@code false} otherwise.
     */
    private static boolean checkValidDate(String date, String dateFormat) {
        try {
            if (!isValidDateFormat(date, dateFormat)) {
                throw new IllegalArgumentException("Invalid date format");
            }
            int day, month, year;
            String[] dateParts = date.split("[- /.]");
            if (dateFormat.equals("dd/mm/yyyy")) {
                day = Integer.parseInt(dateParts[0]);
                month = Integer.parseInt(dateParts[1]);
            } else {
                day = Integer.parseInt(dateParts[1]);
                month = Integer.parseInt(dateParts[0]);
            }
            year = Integer.parseInt(dateParts[2]);

            //check năm nhuận
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (day > 30) {
                    throw new IllegalArgumentException("Invalid date value");
                } else {
                    return true;
                }
            } else if (month == 2) {
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    if (day > 29) {
                        throw new IllegalArgumentException("Invalid date value");
                    } else {
                        return true;
                    }
                } else {
                    if (day > 28) {
                        throw new IllegalArgumentException("Invalid date value");
                    } else {
                        return true;
                    }
                }
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
    //regex for Date
    private static final String DDMMYYYY_REGEX = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";
    private static final String MMDDYYYY_REGEX = "^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d$";

    /**
     * Checks if the given date string matches the specified date format.
     *
     * @param date The date string to be checked.
     * @param dateFormat The format string representing the expected date
     * format.
     * @return {@code true} if the date string matches the format, {@code false}
     * otherwise.
     */
    private static boolean isValidDateFormat(String date, String dateFormat) {
        String regex = (dateFormat.equals("dd/mm/yyyy")) ? DDMMYYYY_REGEX : MMDDYYYY_REGEX;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    /**
     * Displays a menu with "Yes" and "No" options and prompts the user to
     * choose one. Returns true if the user chooses "Yes", and false if the user
     * chooses "No".
     *
     * @param msg The message to display as a prompt for the user.
     * @return {@code true} if the user chooses "Yes", {@code false} if the user
     * chooses "No".
     */
    public static boolean getYesOrNo(String msg) {
        Menu yesno = new Menu(msg);
        yesno.addOption("Yes");
        yesno.addOption("No");
        yesno.print();
        return yesno.getChoice() == 1;
    }
}
