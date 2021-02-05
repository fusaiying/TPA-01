package com.paic.ehis.claimflow.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * 判断对象实体的属性是否为空
 */
public class ObjectNullUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(com.paic.ehis.claimflow.utils.ObjectNullUtil.class);

    public static boolean objectIsNull(Object obj) {
        if (obj == null) {
            return true;
        }
        //获取所有属性
        Field[] fields = obj.getClass().getDeclaredFields();
        if (fields == null || fields.length == 0) {
            return true;
        }
        boolean flag = true;
        for (Field field : fields) {
            //不检查，设置可访问
            field.setAccessible(true);
            try {
                if (field.get(obj) instanceof Collection) {
                    if (isNotNull((Collection) field.get(obj))) {
                        flag = false;
                        break;
                    }
                } else {
                    if (isNotNull(field.get(obj))) {
                        flag = false;
                        break;
                    }
                }
            } catch (Exception e) {
                LOGGER.error("ObjectNullUtil.objectIsNull校验异常", e);
                flag = false;
            }
        }
        return flag;
    }

    public final static boolean isNull(Object obj) {
        if (obj == null || isNull(obj.toString())) {
            return true;
        }
        return false;
    }

    public final static boolean isNull(Collection collection) {
        if (collection == null || collection.size() == 0)
            return true;
        return false;
    }

    public final static boolean isNull(String str) {
        return str == null || "".equals(str.trim())
                || "null".equals(str.toLowerCase());
    }

    public final static boolean isNotNull(Collection collection) {
        return !isNull(collection);
    }

    public final static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }


}