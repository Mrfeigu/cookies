package com.delicacy.cookies.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author linzhenghui
 * @date 2020/10/14
 */
public class DateTimeUtils {

    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /*****************************************************转换*************************************************************/

    /**
     * date 转 localDateTime
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date){
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * localDateTime 转 date
     * @param localDateTime
     * @return
     */
    public static Date toDate(LocalDateTime localDateTime){
        return new Date(localDateTime.atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli());
    }


    /**
     * time 转 localDateTime
     * @param timestamp
     * @return
     */
    public static LocalDateTime toLocalDateTime(long timestamp){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    /**
     * localDateTime 转 time
     * @param localDateTime
     * @return
     */
    public static long toTime(LocalDateTime localDateTime){
        return localDateTime.atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * timeStr 转 localDateTIme
     * @param timeStr
     * @param pattern
     * @return
     */
    public static LocalDateTime toLocalDateTime(String timeStr, String pattern){
        return LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * localDateTIme 转 timeStr
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String toTimeStr(LocalDateTime localDateTime, String pattern){
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }


    /**************************************************实用工具**********************************************************/

    /**
     * @param date 日期
     * @param day day 负数为减
     * @return
     */
    public static Date plusReduceDay(Date date, int day){
        if(day == 0){
            return date;
        }
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        if(day < 0){
            int localDay = Math.abs(day);
            return toDate(localDateTime.minusDays(localDay));
        }
        return toDate(localDateTime.plusDays(day));
    }






}
