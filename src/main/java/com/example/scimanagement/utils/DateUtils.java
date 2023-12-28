package com.example.scimanagement.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateUtils {
    public static java.sql.Date formatString(String start_date) throws ParseException {
        if(start_date.length()<15){
            return java.sql.Date.valueOf(start_date);
        }
        String dateString=start_date.substring(4,15);
        String[] parts=dateString.split(" ");
        String datePart=parts[2]+"-"+parts[0]+"-"+parts[1];

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MMM-dd", Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");


        java.util.Date date = inputFormat.parse(datePart);
        String formattedDate = outputFormat.format(date);

        java.sql.Date sqlDate = java.sql.Date.valueOf(formattedDate);
        return sqlDate;
    }
}
