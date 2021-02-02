package com.paic.ehis.common.core.utils;

import com.paic.ehis.common.core.maxno.MySQLSysMaxNoIncrementer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PubFun {
    private static Logger logger = LoggerFactory.getLogger(PubFun.class);
    private static SimpleDateFormat hhmmss = new SimpleDateFormat("HHmmss");

    //系统最大值表缓存自增集合
    private static Map<String,MySQLSysMaxNoIncrementer> sysMaxNoMap = new HashMap<>();

    //大数据量表编码表自增集合
    private static Map<String,MySQLMaxValueIncrementer> bigDataMaxNoMap = new HashMap<>();

    public PubFun() {
    }

    public static boolean isLeapYear(int nYear) {
        boolean ResultLeap = false;
        ResultLeap = nYear % 400 == 0 | nYear % 100 != 0 & nYear % 4 == 0;
        return ResultLeap;
    }

    public static Date calDate(Date baseDate, int interval, String unit, Date compareDate) {
        Date returnDate = null;
        GregorianCalendar tBaseCalendar = new GregorianCalendar();
        tBaseCalendar.setTime(baseDate);
        if (unit.equals("Y")) {
            tBaseCalendar.add(1, interval);
        }

        if (unit.equals("M")) {
            tBaseCalendar.add(2, interval);
        }

        if (unit.equals("D")) {
            tBaseCalendar.add(5, interval);
        }

        if (compareDate != null) {
            GregorianCalendar tCompCalendar = new GregorianCalendar();
            tCompCalendar.setTime(compareDate);
            int nBaseYears = tBaseCalendar.get(1);
            int nBaseMonths = tBaseCalendar.get(2);
            int nCompMonths = tCompCalendar.get(2);
            int nCompDays = tCompCalendar.get(5);
            int oldDays = tCompCalendar.get(5);
            if (unit.equals("Y")) {
                if (1 == nCompMonths && 29 == oldDays && !isLeapYear(nBaseYears)) {
                    nCompDays = 28;
                }

                tCompCalendar.set(nBaseYears, nCompMonths, nCompDays);
                if (tCompCalendar.before(tBaseCalendar)) {
                    if (1 == nCompMonths && 29 == oldDays && !isLeapYear(nBaseYears + 1)) {
                        nCompDays = 28;
                    }

                    if (1 == nCompMonths && 29 == oldDays && isLeapYear(nBaseYears + 1)) {
                        nCompDays = oldDays;
                    }

                    tBaseCalendar.set(nBaseYears + 1, nCompMonths, nCompDays);
                    returnDate = tBaseCalendar.getTime();
                } else {
                    returnDate = tCompCalendar.getTime();
                }
            }

            if (unit.equals("M")) {
                tCompCalendar.set(nBaseYears, nBaseMonths, nCompDays);
                if (tCompCalendar.before(tBaseCalendar)) {
                    tBaseCalendar.set(nBaseYears, nBaseMonths + 1, nCompDays);
                    returnDate = tBaseCalendar.getTime();
                } else {
                    returnDate = tCompCalendar.getTime();
                }
            }

            if (unit.equals("D")) {
                returnDate = tBaseCalendar.getTime();
            }

            tCompCalendar = null;
        } else {
            returnDate = tBaseCalendar.getTime();
        }

        tBaseCalendar = null;
        return returnDate;
    }

    public static Date calxbEndDate(Date baseDate, int interval, String unit, Date compareDate) {
        Date returnDate = null;
        GregorianCalendar tBaseCalendar = new GregorianCalendar();
        tBaseCalendar.setTime(baseDate);
        if (unit.equals("Y")) {
            tBaseCalendar.add(1, interval);
        }

        if (unit.equals("M")) {
            tBaseCalendar.add(2, interval);
        }

        if (unit.equals("D")) {
            tBaseCalendar.add(5, interval);
        }

        returnDate = tBaseCalendar.getTime();
        GregorianCalendar tLeapCalendar = new GregorianCalendar();
        tLeapCalendar.setTime(returnDate);
        int[] arrComnYearEndDate = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] arrLeapYearEndDate = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int nOldYear = 1900 + baseDate.getYear();
        int nOldMonth = baseDate.getMonth();
        int nOldDate = baseDate.getDate();
        int nNewYear = tLeapCalendar.get(1);
        int nNewMonth = tLeapCalendar.get(2);
        if ((isLeapYear(nOldYear) && nOldDate == arrLeapYearEndDate[nOldMonth] || !isLeapYear(nOldYear) && nOldDate == arrComnYearEndDate[nOldMonth]) && unit != null && (unit.equalsIgnoreCase("Y") || unit.equalsIgnoreCase("M"))) {
            if (isLeapYear(nNewYear)) {
                returnDate.setDate(arrLeapYearEndDate[nNewMonth]);
            } else {
                returnDate.setDate(arrComnYearEndDate[nNewMonth]);
            }
        }

        tLeapCalendar = null;
        tBaseCalendar = null;
        return returnDate;
    }

    public static int calInterval3(Date startDate, Date endDate, String unit) {
        int interval = 0;
        GregorianCalendar sCalendar = new GregorianCalendar();
        sCalendar.setTime(startDate);
        int sYears = sCalendar.get(1);
        int sMonths = sCalendar.get(2);
        int sDays = sCalendar.get(5);
        int sDaysOfYear = sCalendar.get(6);
        GregorianCalendar eCalendar = new GregorianCalendar();
        eCalendar.setTime(endDate);
        int eYears = eCalendar.get(1);
        int eMonths = eCalendar.get(2);
        int eDays = eCalendar.get(5);
        int eDaysOfYear = eCalendar.get(6);
        if (unit.equals("Y")) {
            interval = eYears - sYears;
            if (eMonths < sMonths) {
                --interval;
            } else if (eMonths == sMonths && eDays < sDays) {
                --interval;
            }
        }

        int n;
        if (unit.equals("M")) {
            interval = eYears - sYears;
            interval *= 12;
            interval += eMonths - sMonths;
            if (eDays < sDays) {
                --interval;
                n = eCalendar.getActualMaximum(5);
                if (eDays == n) {
                    ++interval;
                }
            }
        }

        if (unit.equals("D")) {
            interval = eYears - sYears;
            interval *= 365;
            interval += eDaysOfYear - sDaysOfYear;
            n = 0;
            --eYears;
            int i;
            if (eYears > sYears) {
                i = sYears % 4;
                if (i == 0) {
                    ++sYears;
                    ++n;
                }

                int j = eYears % 4;
                if (j == 0) {
                    --eYears;
                    ++n;
                }

                n += (eYears - sYears) / 4;
            }

            if (eYears == sYears) {
                i = sYears % 4;
                if (i == 0) {
                    ++n;
                }
            }

            interval += n;
        }

        return interval;
    }

    public static int getActualMaximum(int year, int month) {
        GregorianCalendar date = new GregorianCalendar(year, month - 1, 1);
        return date.getActualMaximum(5);
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("([\\+\\-])?([0-9])+(.[0-9])?([0-9])*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    public static String newCalDate(String baseDate, String unit, int aconunt) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = sdf.parse(baseDate);
        } catch (Exception var7) {
            logger.debug("得到指定日期后推多少天出现异常,异常信息:" + var7.toString());
        }

        c.setTime(date);
        if (unit.equals("Y")) {
            c.add(1, aconunt);
        } else if (unit.equals("M")) {
            c.add(2, aconunt);
        } else if (unit.equals("D")) {
            c.add(5, aconunt);
        } else {
            c.add(5, aconunt);
        }

        logger.debug(sdf.format(c.getTime()));
        return sdf.format(c.getTime());
    }

    public static boolean isDateBefore(String date1, String date2) throws ParseException {
        DateFormat df = DateFormat.getDateTimeInstance();
        return df.parse(date1).before(df.parse(date2));
    }

    public static int calInterval(Date startDate, Date endDate, String unit) {
        int interval = 0;
        GregorianCalendar sCalendar = new GregorianCalendar();
        sCalendar.setTime(startDate);
        int sYears = sCalendar.get(1);
        int sMonths = sCalendar.get(2);
        int sDays = sCalendar.get(5);
        GregorianCalendar eCalendar = new GregorianCalendar();
        eCalendar.setTime(endDate);
        int eYears = eCalendar.get(1);
        int eMonths = eCalendar.get(2);
        int eDays = eCalendar.get(5);
        if (unit.equals("Y")) {
            interval = eYears - sYears;
            if (eMonths < sMonths) {
                --interval;
            } else if (eMonths == sMonths && eDays < sDays) {
                --interval;
                if (eMonths == 1 && sYears % 4 == 0 && eYears % 4 != 0 && eDays == 28) {
                    ++interval;
                }
            }
        }

        if (unit.equals("M")) {
            interval = eYears - sYears;
            interval *= 12;
            interval += eMonths - sMonths;
            if (eDays < sDays) {
                --interval;
                int maxDate = eCalendar.getActualMaximum(5);
                if (eDays == maxDate) {
                    ++interval;
                }
            }
        }

        if (unit.equals("D")) {
            sCalendar.set(sYears, sMonths, sDays);
            eCalendar.set(eYears, eMonths, eDays);
            long lInterval = (eCalendar.getTime().getTime() - sCalendar.getTime().getTime()) / 86400000L;
            interval = (int)lInterval;
        }

        return interval;
    }

    public static String TransID(String tStrID) {
        String tStrIDtmp = "";
        String tStrIDnew = "";
        String tChkLetter = "10X98765432";
        int r1 = 0;
        int w1 = 1;
        tStrIDtmp = tStrID.substring(0, 6) + "19" + tStrID.substring(6, 15);

        for(int i = 17; i >= 1; --i) {
            int a = Integer.parseInt(tStrIDtmp.substring(i - 1, i));
            w1 = 2 * w1;
            int w = w1 % 11;
            r1 += w * a;
        }

        int j = r1 % 11;
        if (j >= 0 && j <= 10) {
            ++j;
            tStrIDnew = tStrIDtmp + tChkLetter.substring(j - 1, j);
        } else {
            tStrIDnew = tStrIDtmp + "E";
        }

        return tStrIDnew;
    }

    public static String RCh(String sourString, String cChar, int cLen) {
        int tLen = sourString.length();
        String tReturn = "";
        if (tLen >= cLen) {
            return sourString;
        } else {
            int iMax = cLen - tLen;

            for(int i = 0; i < iMax; ++i) {
                tReturn = tReturn + cChar;
            }

            tReturn = sourString.trim() + tReturn.trim();
            return tReturn;
        }
    }

    public static String LCh(String sourString, String cChar, int cLen) {
        int tLen = sourString.length();
        String tReturn = "";
        if (tLen >= cLen) {
            return sourString;
        } else {
            int iMax = cLen - tLen;

            for(int i = 0; i < iMax; ++i) {
                tReturn = tReturn + cChar;
            }

            tReturn = tReturn.trim() + sourString.trim();
            return tReturn;
        }
    }

    public static String getCurrentDate() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        String tString = df.format(today);
        return tString;
    }

    public static String getCurrentTime() {
        String pattern = "HH:mm:ss";
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        String tString = df.format(today);
        return tString;
    }

    public static String getNoLimit(String comCode) {
        comCode = comCode.trim();
        int tLen = comCode.length();
        if (tLen > 6) {
            comCode = comCode.substring(0, 6);
        }

        if (tLen < 6) {
            comCode = RCh(comCode, "0", 6);
        }

        String tString = "";
        tString = comCode + getCurrentDate().substring(0, 4);
        return tString;
    }

    public static String getPiccNoLimit(String comCode) {
        comCode = comCode.trim();
        logger.debug("comCode :" + comCode);
        int tLen = comCode.length();
        if (tLen == 8) {
            comCode = comCode.substring(2, 6);
        }

        if (tLen == 4) {
            comCode = comCode.substring(2, 4) + "00";
        }

        logger.debug("SubComCode :" + comCode);
        String tString = "";
        tString = comCode + getCurrentDate().substring(2, 4) + getCurrentDate().substring(5, 7) + getCurrentDate().substring(8, 10);
        logger.debug("PubFun getPiccNoLimit : " + tString);
        return tString;
    }

    public static String getTenLengNo(String tNo) {
        String tRetrunValue = "";
        int tLength = tNo.length();
        if (tLength < 12) {
            int t = 12 - tLength;
            String tempAdd = "";

            for(int i = 1; i <= t; ++i) {
                tempAdd = tempAdd + "0";
            }

            tRetrunValue = tempAdd + tNo;
        }

        return tRetrunValue;
    }

    public static String getChnMoney(double money) {
        String ChnMoney = "";
        String s0 = "";
        if (money == 0.0D) {
            ChnMoney = "人民币零元整";
            return ChnMoney;
        } else {
            if (money < 0.0D) {
                s0 = "负";
                money *= -1.0D;
            }

            String sMoney = (new DecimalFormat("0")).format(money * 100.0D);
            int nLen = sMoney.length();
            String sInteger;
            String sDot;
            if (nLen < 2) {
                sInteger = "";
                if (nLen == 1) {
                    sDot = "0" + sMoney.substring(nLen - 1, nLen);
                } else {
                    sDot = "0";
                }
            } else {
                sInteger = sMoney.substring(0, nLen - 2);
                sDot = sMoney.substring(nLen - 2, nLen);
            }

            String sFormatStr = formatStr(sInteger);
            String s1 = getChnM(sFormatStr.substring(0, 4), "亿");
            String s2 = getChnM(sFormatStr.substring(4, 8), "万");
            String s3 = getChnM(sFormatStr.substring(8, 12), "");
            String s4 = getDotM(sDot);
            if (s1.length() > 0 && s1.substring(0, 1).equals("0")) {
                s1 = s1.substring(1, s1.length());
            }

            if (s1.length() > 0 && s1.substring(s1.length() - 1, s1.length()).equals("0") && s2.length() > 0 && s2.substring(0, 1).equals("0")) {
                s1 = s1.substring(0, s1.length() - 1);
            }

            if (s2.length() > 0 && s2.substring(s2.length() - 1, s2.length()).equals("0") && s3.length() > 0 && s3.substring(0, 1).equals("0")) {
                s2 = s2.substring(0, s2.length() - 1);
            }

            if (s4.equals("00")) {
                s4 = "";
                if (s3.length() > 0 && s3.substring(s3.length() - 1, s3.length()).equals("0")) {
                    s3 = s3.substring(0, s3.length() - 1);
                }
            }

            if (s3.length() > 0 && s3.substring(s3.length() - 1, s3.length()).equals("0") && s4.length() > 0 && s4.substring(0, 1).equals("0")) {
                s3 = s3.substring(0, s3.length() - 1);
            }

            if (s4.length() > 0 && s4.substring(s4.length() - 1, s4.length()).equals("0")) {
                s4 = s4.substring(0, s4.length() - 1);
            }

            if (s3.equals("0")) {
                s3 = "";
                s4 = "0" + s4;
            }

            ChnMoney = s0 + s1 + s2 + s3 + "元" + s4;
            if (ChnMoney.substring(0, 1).equals("0")) {
                ChnMoney = ChnMoney.substring(1, ChnMoney.length());
            }

            for(int i = 0; i < ChnMoney.length(); ++i) {
                if (ChnMoney.substring(i, i + 1).equals("0")) {
                    ChnMoney = ChnMoney.substring(0, i) + "零" + ChnMoney.substring(i + 1, ChnMoney.length());
                }
            }

            if (sDot.substring(1, 2).equals("0")) {
                ChnMoney = ChnMoney + "整";
            }

            return "人民币" + ChnMoney;
        }
    }

    private static String getDotM(String sIn) {
        String sMoney = "";
        if (!sIn.substring(0, 1).equals("0")) {
            sMoney = sMoney + getNum(sIn.substring(0, 1)) + "角";
        } else {
            sMoney = sMoney + "0";
        }

        if (!sIn.substring(1, 2).equals("0")) {
            sMoney = sMoney + getNum(sIn.substring(1, 2)) + "分";
        } else {
            sMoney = sMoney + "0";
        }

        return sMoney;
    }

    public static String forMatDate(Date date) {
        String tFormatDate = "yyyy-MM-dd";
        SimpleDateFormat tSimpleDateFormat = new SimpleDateFormat(tFormatDate);
        return tSimpleDateFormat.format(date);
    }

    private static String getChnM(String strUnit, String digit) {
        String sMoney = "";
        boolean flag = false;
        if (strUnit.equals("0000")) {
            sMoney = sMoney + "0";
            return sMoney;
        } else {
            if (!strUnit.substring(0, 1).equals("0")) {
                sMoney = sMoney + getNum(strUnit.substring(0, 1)) + "仟";
            } else {
                sMoney = sMoney + "0";
                flag = true;
            }

            if (!strUnit.substring(1, 2).equals("0")) {
                sMoney = sMoney + getNum(strUnit.substring(1, 2)) + "佰";
                flag = false;
            } else if (!flag) {
                sMoney = sMoney + "0";
                flag = true;
            }

            if (!strUnit.substring(2, 3).equals("0")) {
                sMoney = sMoney + getNum(strUnit.substring(2, 3)) + "拾";
                flag = false;
            } else if (!flag) {
                sMoney = sMoney + "0";
                flag = true;
            }

            if (!strUnit.substring(3, 4).equals("0")) {
                sMoney = sMoney + getNum(strUnit.substring(3, 4));
            } else if (!flag) {
                sMoney = sMoney + "0";
                flag = true;
            }

            if (sMoney.substring(sMoney.length() - 1, sMoney.length()).equals("0")) {
                sMoney = sMoney.substring(0, sMoney.length() - 1) + digit.trim() + "0";
            } else {
                sMoney = sMoney + digit.trim();
            }

            return sMoney;
        }
    }

    private static String formatStr(String sIn) {
        int n = sIn.length();
        String sOut = sIn;

        for(int k = 1; k <= 12 - n; ++k) {
            sOut = "0" + sOut;
        }

        return sOut;
    }

    private static String getNum(String value) {
        String sNum = "";
        Integer I = new Integer(value);
        int iValue = I.intValue();
        switch(iValue) {
            case 0:
                sNum = "零";
                break;
            case 1:
                sNum = "壹";
                break;
            case 2:
                sNum = "贰";
                break;
            case 3:
                sNum = "叁";
                break;
            case 4:
                sNum = "肆";
                break;
            case 5:
                sNum = "伍";
                break;
            case 6:
                sNum = "陆";
                break;
            case 7:
                sNum = "柒";
                break;
            case 8:
                sNum = "捌";
                break;
            case 9:
                sNum = "玖";
        }

        return sNum;
    }

    public static double getApproximation(double aValue) {
        if (Math.abs(aValue) <= 0.01D) {
            aValue = 0.0D;
        }

        return aValue;
    }

    public static double setPrecision(float value, String precision) {
        return (double)Float.parseFloat((new DecimalFormat(precision)).format((double)value));
    }

    public static double setPrecision(double value, String precision) {
        return Double.parseDouble((new DecimalFormat(precision)).format(value));
    }

    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        } else {
            BigDecimal b = new BigDecimal(Double.toString(v));
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, scale, 4).doubleValue();
        }
    }

    public static Vector formUpdateSql(String[] tables, String condition, String wherepart) {
        Vector sqlVec = new Vector();

        for(int i = 0; i < tables.length; ++i) {
            sqlVec.add("update " + tables[i] + " set " + condition + " where " + wherepart);
        }

        return sqlVec;
    }

    public static String DeleteZero(String sIn) {
        int n = sIn.length();
        String sOut = sIn;

        while(sOut.substring(0, 1).equals("0") && n > 1) {
            sOut = sOut.substring(1, n);
            n = sOut.length();
            logger.debug(sOut);
        }

        return sOut.equals("0") ? "" : sOut;
    }

    public static String changForJavaScript(String s) {
        char[] arr = s.toCharArray();
        s = "";

        for(int i = 0; i < arr.length; ++i) {
            if (arr[i] == '"' || arr[i] == '\'') {
                s = s + "\\";
            }

            if (arr[i] == '\n') {
                s = s + "\\n";
            } else if (arr[i] != '\r') {
                if (arr[i] == '\\') {
                    s = s + "\\";
                }

                s = s + arr[i];
            }
        }

        return s;
    }

    public static String changForHTML(String s) {
        char[] arr = s.toCharArray();
        s = "";

        for(int i = 0; i < arr.length; ++i) {
            if (arr[i] == '"' || arr[i] == '\'') {
                s = s + "\\";
            }

            if (arr[i] == '\n') {
                s = s + "<br>";
            } else if (arr[i] != '\r') {
                s = s + arr[i];
            }
        }

        return s;
    }

    public static String getClassFileName(Object o) {
        String fileName = o.getClass().getName();
        fileName = fileName.substring(fileName.lastIndexOf(".") + 1);
        return fileName;
    }

    public static void out(Object o, String s) {
        logger.debug(getClassFileName(o) + " : " + s);
    }

    public static String DateTimeBetween(String ColDateName, String ColTimeName, String StartDate, String StartTime, String EndDate, String EndTime) {
        String tReturnSQL = "";
        if (StartDate != null && EndDate != null && !StartDate.equals("") && !EndDate.equals("")) {
            if (StartDate.equals(EndDate)) {
                tReturnSQL = " AND (" + ColDateName + "='" + StartDate + "' AND " + ColTimeName + ">='" + StartTime + "' AND " + ColTimeName + "<='" + EndTime + "') ";
            } else {
                tReturnSQL = " AND ((" + ColDateName + "='" + StartDate + "' AND " + ColTimeName + ">='" + StartTime + "') OR " + "(" + ColDateName + "='" + EndDate + "' AND " + ColTimeName + "<='" + EndTime + "') OR " + "(" + ColDateName + ">'" + StartDate + "' AND " + ColDateName + "<'" + EndDate + "')) ";
            }
        }

        return tReturnSQL;
    }

    public static Date calOFDate(Date baseDate, int interval, String unit, Date compareDate) {
        Date returnDate = null;
        GregorianCalendar mCalendar = new GregorianCalendar();
        mCalendar.setTime(baseDate);
        if (unit.equals("Y")) {
            mCalendar.add(1, interval);
        }

        if (unit.equals("M")) {
            mCalendar.add(2, interval);
        }

        if (unit.equals("D")) {
            mCalendar.add(5, interval);
        }

        if (compareDate != null) {
            GregorianCalendar cCalendar = new GregorianCalendar();
            cCalendar.setTime(compareDate);
            int mYears = mCalendar.get(1);
            int mMonths = mCalendar.get(2);
            int cMonths = cCalendar.get(2);
            int cDays = cCalendar.get(5);
            if (unit.equals("Y")) {
                cCalendar.set(mYears, cMonths, cDays);
                if (mMonths < cCalendar.get(2)) {
                    cCalendar.set(mYears, mMonths + 1, 0);
                }

                if (cCalendar.before(mCalendar)) {
                    mCalendar.set(mYears + 1, cMonths, cDays);
                    returnDate = mCalendar.getTime();
                } else {
                    returnDate = cCalendar.getTime();
                }
            }

            if (unit.equals("M")) {
                cCalendar.set(mYears, mMonths, cDays);
                if (mMonths < cCalendar.get(2)) {
                    cCalendar.set(mYears, mMonths + 1, 0);
                }

                if (cCalendar.before(mCalendar)) {
                    mCalendar.set(mYears, mMonths + 1, cDays);
                    returnDate = mCalendar.getTime();
                } else {
                    returnDate = cCalendar.getTime();
                }
            }

            if (unit.equals("D")) {
                returnDate = mCalendar.getTime();
            }
        } else {
            returnDate = mCalendar.getTime();
        }

        return returnDate;
    }

    public static String bigDoubleToCommonString(double value, String pattern) {
        try {
            DecimalFormat tDecimalFormat = new DecimalFormat(pattern);
            String valueString = tDecimalFormat.format(value);
            return valueString;
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static String getScripName(String Type, String env, String name) {
        if (Type != null && !"".equals(Type) && env != null && !"".equals(env) && name != null && !"".equals(name)) {
            int ran = (int)(Math.random() * 100.0D);
            String NAME = Type + "_" + env + "_" + name + "_" + hhmmss.format(new Date()) + ran + ".sql";
            return NAME;
        } else {
            return "";
        }
    }



    public static String getCurrentDate2() {
        String pattern = "yyyyMMdd";
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        String tString = df.format(today);
        return tString;
    }

    public static String getSimpleStackTrace(Throwable tThrowable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        String var5;
        try {
            tThrowable.printStackTrace(pw);
            String stackTrack = sw.toString();
            int index = stackTrack.indexOf("\n");
            if (index > 0) {
                var5 = stackTrack.substring(0, index);
                return var5;
            }

            var5 = stackTrack;
        } finally {
            pw.close();
        }

        return var5;
    }

    public static boolean isValidDate(String sDate) {
        String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
        String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        if (sDate != null) {
            Pattern pattern = Pattern.compile(datePattern1);
            Matcher match = pattern.matcher(sDate);
            if (match.matches()) {
                pattern = Pattern.compile(datePattern2);
                match = pattern.matcher(sDate);
                return match.matches();
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String getRemortIP(HttpServletRequest request) {
        return request.getHeader("x-forwarded-for") == null ? request.getRemoteAddr() : request.getHeader("x-forwarded-for");
    }

    public static boolean isValidFee(String feeString) {
        if ("".equals(feeString)) {
            return true;
        } else {
            try {
                new Double(feeString);
                return true;
            } catch (NumberFormatException var2) {
                return false;
            }
        }
    }

    public static String createMaxNo(String cNoType, int cNoLength) {
        if (cNoType != null && cNoType.trim().length() > 0 && cNoLength > 0) {
            cNoType = cNoType.toUpperCase();
            String tReturn;
            if (!cNoType.equals("DOCID") && !cNoType.equals("PAGEID") && !cNoType.equals("MISSIONID") && !cNoType.equals("MISSIONSERIELNO")) {
                tReturn = "";
                String cNoLimit = "SN";
                if (!cNoType.equals("COMMISIONSN") && !cNoType.equals("GRPNO") && !cNoType.equals("CUSTOMERNO") && !cNoType.equals("SUGDATAITEMCODE") && !cNoType.equals("SUGITEMCODE") && !cNoType.equals("SUGMODELCODE") && !cNoType.equals("SUGCODE")) {
                    BigInteger tMaxNo = CreateNo(cNoType, cNoLimit);
                    if (tMaxNo == null) {
                        return null;
                    } else {
                        String tStr = tMaxNo.toString();
                        tStr = PubFun.LCh(tStr, "0", cNoLength);
                        tReturn = tStr.trim();
                        return tReturn;
                    }
                } else {
                    logger.debug("该类型流水号，请采用CreateMaxNo('" + cNoType + "','SN')方式生成");
                    return null;
                }
            } else {
                logger.debug("编码类型注意填写正确!");
                return null;
            }
        } else {
            logger.debug("NoType长度错误或NoLength错误");
            return null;
        }
    }

    private static BigInteger CreateNo(String cNoType, String cNoLimit) {

        DataSource dataSource = (DataSource)SpringUtils.getBean("dataSource");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            logger.debug("CreateNo : fail to get db connection");
            return null;
        }
        if (conn == null) {
            logger.debug("CreateNo : fail to get db connection");
            return null;
        } else {
            new BigInteger("0");

            try {
                String DataType = conn.getMetaData().getDriverName().toUpperCase();
                conn.setAutoCommit(false);
                StringBuffer tSBql = new StringBuffer(256);
                if (!DataType.contains("ORACLE") && !DataType.contains("MYSQL")) {
                    if (DataType.contains("SQL") && DataType.contains("SERVER")) {
                        tSBql.append("select maxno from sys_maxno rowlock where notype='");
                        tSBql.append(cNoType);
                        tSBql.append("' and nolimit='");
                        tSBql.append(cNoLimit);
                        tSBql.append("'");
                    } else if (DataType.contains("DB2")) {
                        tSBql.append("select maxno from sys_maxno where notype='");
                        tSBql.append(cNoType);
                        tSBql.append("' and nolimit='");
                        tSBql.append(cNoLimit);
                        tSBql.append("' with ?? ");
                    }
                } else {
                    tSBql.append("select maxno from sys_maxno where notype='");
                    tSBql.append(cNoType);
                    tSBql.append("' and nolimit='");
                    tSBql.append(cNoLimit);
                    tSBql.append("' for update");
                }

                if (tSBql.length() == 0) {
                    logger.debug("SysConst.DBTYPE is error ...");
                    return null;
                } else {
                    String result = null;
                    result = getOneValue(conn,tSBql.toString());
                    if (result != null) {
                        BigInteger tMaxNo;
                        if (result.equals("")) {
                            tSBql = new StringBuffer(256);
                            tSBql.append("insert into sys_maxno(notype, nolimit, maxno) values('");
                            tSBql.append(cNoType);
                            tSBql.append("', '");
                            tSBql.append(cNoLimit);
                            tSBql.append("', 1)");
                            if (!execUpdateSQL(conn,tSBql.toString())) {
                                logger.debug("CreateNo 插入失败，请重试!");
                                conn.rollback();
                                conn.close();
                                return null;
                            }

                            tMaxNo = new BigInteger("1");
                        } else {
                            tSBql = new StringBuffer(256);
                            tSBql.append("update sys_maxno set maxno = maxno + 1 where notype = '");
                            tSBql.append(cNoType);
                            tSBql.append("' and nolimit = '");
                            tSBql.append(cNoLimit);
                            tSBql.append("'");
                            if (!execUpdateSQL(conn,tSBql.toString())) {
                                logger.debug("CreateNo 更新失败，请重试!");
                                conn.rollback();
                                conn.close();
                                return null;
                            }

                            tMaxNo = (new BigInteger(result)).add(new BigInteger("1"));
                        }

                        conn.commit();
                        conn.close();
                        return tMaxNo;
                    } else {
                        logger.debug("CreateNo 资源忙，请稍后!");
                        conn.rollback();
                        conn.close();
                        return null;
                    }
                }
            } catch (Exception var10) {
                var10.printStackTrace();

                try {
                    conn.rollback();
                    conn.close();
                    return null;
                } catch (Exception var9) {
                    var9.printStackTrace();
                    return null;
                }
            }
        }
    }

    public static String getOneValue(Connection conn, String sql) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String mValue = "";
        logger.debug("ExecSQL : " + sql);

        try {
            pstmt = conn.prepareStatement(GBKToUnicode(sql), 1003, 1007);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                mValue = rs.getString(1);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException var36) {
            logger.debug("### Error at OneValue: " + sql);
            mValue = "";
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (SQLException var33) {
                        var33.printStackTrace();
                    } finally {
                        try {
                            logger.debug("Sql's bug is very big: " + sql);
                            pstmt.close();
                        } catch (SQLException var32) {
                            logger.debug("Sql's bug is very big: " + sql);
                        }

                    }
                }
            } catch (SQLException var35) {
                var35.printStackTrace();
            }
        }
        return mValue;
    }

    public static boolean execUpdateSQL(Connection conn, String sql) {
        PreparedStatement pstmt = null;
        logger.debug("ExecSQL : " + sql);

        try {
            pstmt = conn.prepareStatement(GBKToUnicode(sql), 1003, 1008);
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException var34) {
            logger.debug("### Error at execUpdateSQL: " + sql);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException var32) {
                    var32.printStackTrace();
                } finally {
                    try {
                        logger.debug("Sql's bug is very big: " + sql);
                        pstmt.close();
                    } catch (SQLException var31) {
                        logger.debug("Sql's bug is very big: " + sql);
                    }

                }
            }
            return false;
        }
    }

    private static String GBKToUnicode(String strOriginal) {
        if (strOriginal != null) {
            try {
                return isGBKString(strOriginal) ? new String(strOriginal.getBytes("GBK"), "ISO8859_1") : strOriginal;
            } catch (Exception var2) {
                return strOriginal;
            }
        } else {
            return null;
        }
    }

    private static boolean isGBKString(String tStr) {
        int tenth = tStr.length();
        for(int i = 0; i < tenth; ++i) {
            int t1 = Integer.parseInt(Integer.toOctalString(tStr.charAt(i)));
            if (t1 > 511) {
                return true;
            }
        }
        return false;
    }

    /**
     * 创建MySQL编码最大值，使用缓存机制，表sys_maxno
     * @param cNoType 编码类型
     * @param cacheSize 缓存大小，默认10
     * @param cNoLength 编码返回长度
     * @return 结果
     */
    public static String createMySqlMaxNoUseCache(String cNoType, int cacheSize, int cNoLength){
        if (cNoType != null && cNoType.trim().length() > 0 && cNoLength > 0) {
            cNoType = cNoType.toUpperCase();
            String tReturn = "";

            //从集合中获取最大值缓存类
            MySQLSysMaxNoIncrementer maxNoIncrementer = sysMaxNoMap.get(cNoType);
            if(maxNoIncrementer == null){

                //获取数据源
                DataSource dataSource = (DataSource)SpringUtils.getBean("dataSource");

                //集合中没有则创建编码自增缓存序列类，并放入map稽核里面用于缓存使用
                maxNoIncrementer = new MySQLSysMaxNoIncrementer(dataSource,cNoType);
                if(cacheSize<=0){
                    cacheSize = 10;
                }
                maxNoIncrementer.setCacheSize(cacheSize);
                sysMaxNoMap.put(cNoType,maxNoIncrementer);
            }

            //转化为获取下一个值的数据类，并获取下一个值
            DataFieldMaxValueIncrementer maxNoData = maxNoIncrementer;
            String maxNo = maxNoData.nextStringValue();
            if (maxNo == null) {
                return null;
            } else {
                //拼接编码长度
                String tStr = maxNo;
                tStr = PubFun.LCh(tStr, "0", cNoLength);
                tReturn = tStr.trim();
                return tReturn;
            }
        } else {
            logger.debug("NoType长度错误或NoLength错误");
            return null;
        }
    }

    /**
     * 创建MySQL编码最大值，使用缓存机制，每个表有自己的主键自增表，需要手动去数据库创建
     * @param seq_table 自增表
     * @param columnName 自增列
     * @param cacheSize 缓存大小，默认100
     * @param cNoLength 编码返回长度
     * @return 结果
     */
    public static String createMySqlBigDataMaxNoUseCache(String seq_table, String columnName, int cacheSize, int cNoLength){
        if (seq_table != null && columnName != null && seq_table.trim().length() > 0 && columnName.trim().length() > 0 && cNoLength > 0) {
            seq_table = seq_table.toLowerCase();
            String tReturn = "";

            //从集合中获取最大值缓存类
            MySQLMaxValueIncrementer maxValueIncrementer = bigDataMaxNoMap.get(seq_table);
            if(maxValueIncrementer == null){

                //获取数据源
                DataSource dataSource = (DataSource)SpringUtils.getBean("dataSource");

                //集合中没有则创建编码自增缓存序列类，并放入map稽核里面用于缓存使用
                maxValueIncrementer = new MySQLMaxValueIncrementer(dataSource,seq_table,columnName);
                if(cacheSize<=0){
                    cacheSize = 100;
                }
                maxValueIncrementer.setCacheSize(cacheSize);
                bigDataMaxNoMap.put(seq_table,maxValueIncrementer);
            }

            //转化为获取下一个值的数据类，并获取下一个值
            DataFieldMaxValueIncrementer maxNoData = maxValueIncrementer;
            String maxNo = maxNoData.nextStringValue();
            if (maxNo == null) {
                return null;
            } else {
                //拼接编码长度
                String tStr = maxNo;
                tStr = PubFun.LCh(tStr, "0", cNoLength);
                tReturn = tStr.trim();
                return tReturn;
            }
        } else {
            logger.debug("NoType长度错误或NoLength错误");
            return null;
        }
    }
}
