package com.paic.ehis.cs.utils;

import java.util.HashMap;
import java.util.Map;

public class TestDemo {
    public static void main(String[] args) {
//        Map<String,String> map=new HashMap<>();
//        map.put("name1","name2");
//        map.put("age1","age2");
//        map.put("sex1","sex2");
//        V2 v2=new V2();
//        v2.setName2("zhangsan");
//        v2.setAge2("12");
//        v2.setSex2("nan");
//        V1 v1=new V1();
//
//        VoUtils voUtils=new VoUtils<V1>();
//        v1= (V1) voUtils.fromVoToVo(v1,map,v2);
//        System.out.println(v1.toString());
//
//        V3 v3=new V3();
//        Map<String,String> map2=new HashMap<>();
//        map2.put("bb1","name2");
//        map2.put("aa1","age2");
//        map2.put("cc1","sex2");
//        VoUtils voUtils1=new VoUtils<V3>();
//        v3= (V3) voUtils1.fromVoToVo(v3,map2,v2);
//        System.out.println(v3.toString());

        // 数据库对象
        V2 v22=new V2();
        // 信息需求对象
        V1 v11=new V1();
        v11.setName1("zhangsan1111");
        v11.setAge1("12111");
        v11.setSex1("nan111");
        //反向关系
        Map<String,String> map12=new HashMap<>();
        map12.put("name2","name1");
        map12.put("age2","age1");
        map12.put("sex2","sex1");
        VoUtils voUtils2=new VoUtils<V2>();
        v22= (V2) voUtils2.fromVoToVo(v22,map12,v11);
        System.out.println(v22.toString());
    }
}
