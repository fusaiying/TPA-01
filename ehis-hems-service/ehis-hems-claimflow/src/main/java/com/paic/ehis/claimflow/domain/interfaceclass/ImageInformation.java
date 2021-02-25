package com.paic.ehis.claimflow.domain.interfaceclass;

import lombok.Data;

/**
 * Created by IntelliJ IDEA
 * User: fusaiying-傅赛赢
 *
 * @author: 傅赛赢
 * Date: 2021/2/25
 * subject:影响信息
 */
@Data
public class ImageInformation {
    /**
     * 条码号
     */
    private String barCode;
    /**
     * 业务类型
     */
    private String businessTypes;
    /**
     * 条码类型
     */
    private String barCodeType;
    /**
     * 短条码类型
     */
    private String shortBarcodeType;
    /**
     * 扫描时间
     */
    private String sweepDate;
    /**
     * 扫描网点
     */
    private String scanNetwork;
    /**
     * 扫描人员
     */
    private String scanningPersonnel;
    /**
     * 扫描机构
     */
    private String scanningMechanism;
    /**
     * 扫描总页数
     */
    private String scanTotalPages;
    /**
     * 信息空
     */
    private String informationEmpty;
    /**
     * 区域码
     */
    private String areaCode;
    /**
     * 资料影像大小
     */
    private String dataImageSize;
    /**
     * 扫描序列号
     */
    private String scanSerialNo;
    /**
     * 文档文件类型
     */
    private String documentFileType;
    /**
     * 文件UEL地址
     */
    private String documentUelAddress;
    /**
     * 覆盖
     */
    private String cover;
    /**
     * 事前事后
     */
    private String beforeAndAfter;
}
