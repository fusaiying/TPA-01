package com.paic.ehis.cs.utils;

import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
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
                Field otherfield=t.getClass().getDeclaredField(key);
                otherfield.setAccessible(true);
                otherfield.set(t,value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.out.println("没有对应属性");
                e.printStackTrace();
            }
        }
        return t;
    }

}
