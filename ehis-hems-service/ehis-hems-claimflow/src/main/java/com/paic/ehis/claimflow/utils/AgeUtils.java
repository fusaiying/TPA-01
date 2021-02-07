package com.paic.ehis.claimflow.utils;

import java.util.Calendar;
import java.util.Date;

//
public class AgeUtils {
    public static int getAge(Date birthday) {
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(1);
        int monthNow = cal.get(2);
        int dayOfMonthNow = cal.get(5);
        cal.setTime(birthday);
        int yearBirth = cal.get(1);
        int monthBirth = cal.get(2);
        int dayOfMonthBirth = cal.get(5);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    --age;
                }
            } else {
                --age;
            }
        }
        return age;
    }
}
