package cn.meeler.test.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {

    public static SimpleDateFormat getInstance(String format){
        return InstanceHolder.toCreateInstance(format);
    }

    public static String format(Date date,String format){
        SimpleDateFormat instance = DateFormatUtils.getInstance(format);
        return instance.format(date);
    }

    public static Date parse(String timeStr, String format) {
        SimpleDateFormat instance = DateFormatUtils.getInstance(format);
        try {
            Date parse = instance.parse(timeStr);
            return parse;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    private static class InstanceHolder {
        public static SimpleDateFormat toCreateInstance(String format){
            return new SimpleDateFormat(format);
        }
    }
}
