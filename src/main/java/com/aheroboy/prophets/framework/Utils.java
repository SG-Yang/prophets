package com.aheroboy.prophets.framework;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sgyang on 2/13/17.
 */
public class Utils {
    public static final ThreadLocal<SimpleDateFormat> df = new ThreadLocal<SimpleDateFormat>(){

        @Override protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("YYYY-MM-dd");
        }
    };

    public static final ThreadLocal<SimpleDateFormat> tf = new ThreadLocal<SimpleDateFormat>(){
        protected  SimpleDateFormat initialValue(){
            return new SimpleDateFormat("HH:mm:ss");
        }
    };

    public static final ThreadLocal<SimpleDateFormat> YYYY_MM_DD_HHMMSS = new ThreadLocal<SimpleDateFormat>(){
        @Override protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        }
    };

    public static final Date parseHHMMSSTime(String timeStr){
        try {
            return tf.get().parse(timeStr);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public static final Date parseYYYY_MM_DD_Date(String dateStr){
        try {
            return df.get().parse(dateStr);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
