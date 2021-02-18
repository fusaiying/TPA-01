package com.paic.ehis.cs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 受理对象 AcceptDetailInfo
 * 
 * @author sino
 * @date 2021-01-14
 */
@Data
public class AcceptDetailInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String workOrderNo ;
    /** 受理渠道;信息需求受理渠道：电话中心；在线客服；邮箱；网站；柜面；寿险；微信；监管；媒体；
     预约受理渠道：电话；邮箱；网站；柜面；APP；寿险；微信；媒体；在线客服；监管部门；
     投诉受理渠道：电话；邮箱；APP；监管部门；媒体；信函；来访；官网；其他 */
    private String channelCode ;
    /** 服务项目 */
    private String itemCode ;
    /** 来电人编号;person_info中的编号 */
    private String callPersonId ;
    /** 来电人与被保人关系 */
    private String callRelationBy ;
    /** 优先级 */
    private String priorityLevel ;
    /** 联系人编号;person_info中的编号 */
    private String contactsPersonId ;
    /** 联系人与被保人关系 */
    private String contactsRelationBy ;
    /** 电话中心业务流水号 */
    private String callCenterId ;
    /** E-MAIL */
    private String email ;
    /** 出单机构 */
    private String organCode ;
    /** 业务内容 */
    private String content ;
    /** 投诉人编号;person_info中的编号 */
    private String complaintPersonId ;
    /** 状态 */
    private String status ;
    /** 创建人;原操作人，存放user_info中的流水号 */
    private String createBy ;
    /** 创建时间 */
    private Date createTime ;
    /** 更新人;每次修改人员，存放user_info中的流水号 */
    private String updateBy ;
    /** 更新时间 */
    private Date updateTime ;
    /** 字段属性0;见转意表 */
    private String prop0 ;
    /** 字段属性1;见转意表 */
    private String prop1 ;
    /** 字段属性2;见转意表 */
    private String prop2 ;
    /** 字段属性3;见转意表 */
    private String prop3 ;
    /** 字段属性4;见转意表 */
    private String prop4 ;
    /** 字段属性5;见转意表 */
    private String prop5 ;
    /** 字段属性6;见转意表 */
    private String prop6 ;
    /** 字段属性7;见转意表 */
    private String prop7 ;
    /** 字段属性8;见转意表 */
    private String prop8 ;
    /** 字段属性9;见转意表 */
    private String prop9 ;
    /** 字段属性10;见转意表 */
    private String prop10 ;
    /** 字段属性11;见转意表 */
    private String prop11 ;
    /** 字段属性12;见转意表 */
    private String prop12 ;
    /** 字段属性13;见转意表 */
    private String prop13 ;
    /** 字段属性14;见转意表 */
    private String prop14 ;
    /** 字段属性15;见转意表 */
    private String prop15 ;
    /** 字段属性16;见转意表 */
    private String prop16 ;
    /** 字段属性17;见转意表 */
    private String prop17 ;
    /** 字段属性18;见转意表 */
    private String prop18 ;
    /** 字段属性19;见转意表 */
    private String prop19 ;
    /** 字段属性20;见转意表 */
    private String prop20 ;
    /** 字段属性21;见转意表 */
    private String prop21 ;
    /** 字段属性22;见转意表 */
    private String prop22 ;
    /** 字段属性23;见转意表 */
    private String prop23 ;
    /** 字段属性24;见转意表 */
    private String prop24 ;
    /** 字段属性25;见转意表 */
    private String prop25 ;

}
