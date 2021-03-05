package com.paic.ehis.cs.utils;

import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 *
 * @param <T> 输出对象
 */
public class VoUtils<T> {
    /**
     *
     * @param t 转成后的对象
     * @param fieldMap 对应关系
     * @param obj 被转对象
     * @return
     */
    public T fromVoToVo(T t,Map<String,String> fieldMap, Object obj){
        for(Map.Entry<String, String> entry:fieldMap.entrySet()){
            String key=entry.getKey();
            String otherKey=entry.getValue();
            System.out.println(key+"--->"+otherKey);
            try {
                Field field=obj.getClass().getDeclaredField(otherKey);
                field.setAccessible(true);
                Object value= field.get(obj);
                Class valueType=field.getType();
                System.out.println(value+"--->"+valueType);
                Field otherfield=t.getClass().getDeclaredField(key);
                Class fieldType=otherfield.getType();
                System.out.println("--->"+fieldType);
                otherfield.setAccessible(true);
                //目标是日期类型
                if(Date.class.isAssignableFrom(fieldType)){
                    Date date=null;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if(value instanceof String){
                        date = simpleDateFormat.parse(String.valueOf(value));
                    }else if(value instanceof Date){
                        date=(Date) value;
                    }else{
                        String valueStr=String.valueOf(value);
                        Long valueLong=Long.valueOf(valueStr);
                        date = new Date(valueLong);
                    }
                    otherfield.set(t,date);
                }else{
                    if(value instanceof Date){
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Object dateStr = simpleDateFormat.format(value);
                        otherfield.set(t,dateStr);
//                    }else if(value instanceof Integer){
//                        String valueStr=String.valueOf(value);
//                        otherfield.set(t,Integer.parseInt(valueStr));
                    }else{
                        otherfield.set(t,value);
                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.out.println("没有对应属性");
                e.printStackTrace();
            } catch (ParseException e) {
                System.out.println("属性内容转换异常");
                e.printStackTrace();
            }
        }
        return t;
    }

}
