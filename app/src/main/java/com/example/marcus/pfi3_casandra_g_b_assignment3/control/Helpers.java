package com.example.marcus.pfi3_casandra_g_b_assignment3.control;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Helpers{

    public static String formatTime(String dateTimeString){
        String formattedTime= "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date =null;
        try {
            date = dateFormat.parse(dateTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String hour;
        if (cal.get(Calendar.HOUR_OF_DAY)<10){
            hour = "0"+ String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
        }else {
            hour = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
        }
        String minute;
        if (cal.get(Calendar.MINUTE)<10){
            minute = "0"+ String.valueOf(cal.get(Calendar.MINUTE));
        }else {
            minute = String.valueOf(cal.get(Calendar.MINUTE));
        }

        formattedTime = formattedTime + " " + hour +":"+minute;
        return hour +":"+minute;
    }

    public static String formatDate(String dateTimeString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date =null;
        try {
            date = dateFormat.parse(dateTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(cal.get(Calendar.MONTH)+1);
        return day +"/"+month;
    }

    public static String getDate(String dateTimeString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date =null;
        try {
            date = dateFormat.parse(dateTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(cal.get(Calendar.MONTH)+1);
        return year+"-"+month +"-"+day;
    }


    public static String getTravelTimeinMinutes(String startTime, String endTime){
        int diffMinutes= 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date startDate = null, endDate = null;
        try {
            startDate = dateFormat.parse(startTime);
            endDate = dateFormat.parse(endTime);
            Calendar calStart = Calendar.getInstance();
            calStart.setTime(startDate);
            Calendar calEnd = Calendar.getInstance();
            calEnd.setTime(endDate);
            long millisTravel = calEnd.getTimeInMillis() - calStart.getTimeInMillis();
            diffMinutes = ((int)millisTravel/(60*1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(diffMinutes);
    }

    public static String timeToDeparture(String startTime){
        int diffMinutes=-1;
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date =null;
        try {
            date = dateFormat.parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        long millisDiff = cal.getTimeInMillis() - now.getTimeInMillis();
        if (millisDiff>0){
            diffMinutes = ((int)millisDiff/(60*1000));
        }
        return String.valueOf(diffMinutes);
    }


    public static Calendar parseCalendarString(String dateTimeString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date =null;
        try {
            date = dateFormat.parse(dateTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("Europe/Stockholm"));
        cal.setTime(date);
        return cal;
    }


    public static Calendar getTimeNow(){
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        return now;
    }


    public static boolean isToday(String depDateTime) {
        boolean isToday = false;
        Calendar depDate = parseCalendarString(depDateTime);
        int depDay = depDate.get(Calendar.DAY_OF_MONTH);
        Calendar now = getTimeNow();
        int nowDay = now.get(Calendar.DAY_OF_MONTH);
        if (depDay==nowDay){
            isToday = true;
        }
        return isToday;
    }


    public static boolean isTomorrow(String depDateTime) {
        boolean isTomorrow = false;
        Calendar depDate = parseCalendarString(depDateTime);
        int depDay = depDate.get(Calendar.DAY_OF_YEAR);
        Calendar now = getTimeNow();
        int nowDay = now.get(Calendar.DAY_OF_YEAR);
        if ((depDay-nowDay)==1){
            isTomorrow = true;
        }
        return isTomorrow;
    }


    public static String isAfterTomorrow(String dateTime) {
        Calendar depDate = parseCalendarString(dateTime);
        int month = depDate.get(Calendar.MONTH)+1;
        int day = depDate.get(Calendar.DAY_OF_MONTH);
        return day+"/"+month;
    }
}