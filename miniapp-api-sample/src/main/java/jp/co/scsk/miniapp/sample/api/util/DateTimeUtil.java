package jp.co.scsk.miniapp.sample.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
    
    /**
     * 日付→文字列を変換
     * @param date 日付
     * @param pattern フォマード文字列
     * @return 変換した文字列
     */
    public static String convertDateToString(Date date, String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 文字列→日付を変換
     * @param date 日付
     * @param pattern フォマード文字列
     * @return 変換した文字列
     */
    public static Date convertStringToDate(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(date);
    }
    
    public static Long getCurrentSecondStamp(){
        return new Date().getTime() / 1000;
    }
}
