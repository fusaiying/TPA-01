package com.paic.ehis.order.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Dateutils {
    //获取当前时间
    public static Map getCurrontTime() {
        Map map =new HashMap<String,Object>();
        Date dNow = new Date();   //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.MONTH, -3);  //设置为前3月
        dBefore = calendar.getTime();   //得到前3月的时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        String defaultStartDate = sdf.format(dBefore);    //格式化前3月的时间
        String defaultEndDate = sdf.format(dNow); //格式化当前时间
        map.put("defaultStartDate",defaultStartDate);
        map.put("defaultEndDate",defaultEndDate);
        return map;
}
    public static Map getCurrontTime1() {
        Map map1 = new HashMap<String, Object>();
        Date dNow1 = new Date();   //当前时间
        Date dBefore1 = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow1);//把当前时间赋给日历
        calendar.add(Calendar.MONTH, -1);  //设置为前1月
        dBefore1 = calendar.getTime();   //得到前1月的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
        String defaultStartDate1 = sdf.format(dBefore1);    //格式化前1月的时间
        String defaultEndDate1 = sdf.format(dNow1); //格式化当前时间
        map1.put("defaultStartDate", defaultStartDate1);
        map1.put("defaultEndDate", defaultEndDate1);
        return map1;
    }
    public static Map getCurrontTime2() {
        Map map2 = new HashMap<String, Object>();
        Date dNow2 = new Date();   //当前时间
        Date dBefore2 = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow2);//把当前时间赋给日历
        calendar.add(Calendar.DATE, -30);  //设置为前30天
        dBefore2 = calendar.getTime();   //得到前1月的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
        String defaultStartDate2 = sdf.format(dBefore2);    //格式化前1月的时间
        String defaultEndDate2 = sdf.format(dNow2); //格式化当前时间
        map2.put("defaultStartDate", defaultStartDate2);
        map2.put("defaultEndDate", defaultEndDate2);
        return map2;
    }

    public static Map getCurrontTime3() {
        Map map3 = new HashMap<String, Object>();
        Date dNow3 = new Date();   //当前时间
        Date dBefore3 = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow3);//把当前时间赋给日历
        calendar.add(Calendar.MONTH, -3);  //设置为前3月
        dBefore3 = calendar.getTime();   //得到前3月的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
        String defaultStartDate1 = sdf.format(dBefore3);    //格式化前3月的时间
        String defaultEndDate1 = sdf.format(dNow3); //格式化当前时间
        map3.put("defaultStartDate", defaultStartDate1);
        map3.put("defaultEndDate", defaultEndDate1);
        return map3;
    }

}
