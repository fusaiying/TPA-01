package com.paic.ehis.common.core.web.page;

import com.paic.ehis.common.core.utils.ServletUtils;
import com.paic.ehis.common.core.web.domain.BaseEntity;

import java.util.Map;

/**
 * 表格数据处理
 * 
 * @author admin
 */
public class TableSupport
{
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    private static String orderByColumn;

    private static String sort;

    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain()
    {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(IS_ASC));
        return pageDomain;
    }

    public static PageDomain getPageDomain(BaseEntity baseEntity)
    {
        PageDomain pageDomain = new PageDomain();
        Map<String,Object> tMap = baseEntity.getParams();
        pageDomain.setPageNum(Integer.parseInt(tMap.get(PAGE_NUM).toString()));
        pageDomain.setPageSize(Integer.parseInt(tMap.get(PAGE_SIZE).toString()));
        pageDomain.setOrderByColumn(tMap.get(ORDER_BY_COLUMN) == null ? null : tMap.get(ORDER_BY_COLUMN).toString());
        pageDomain.setIsAsc(tMap.get(IS_ASC) == null ? null : tMap.get(IS_ASC).toString());
        return pageDomain;
    }

    public static PageDomain buildPageRequest()
    {
        return getPageDomain();
    }


    public static PageDomain buildPageRequest(BaseEntity baseEntity)
    {
        return getPageDomain(baseEntity);
    }

    /**
     * 自定义封装分页对象
     */
    public static PageDomain getSortPageDomain()
    {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(PAGE_SIZE));
        pageDomain.setOrderByColumn(orderByColumn);
        pageDomain.setIsAsc(sort);
        return pageDomain;
    }

    public static PageDomain buildSortPageRequest()
    {
        return getSortPageDomain();
    }

    public static String getOrderByColumn() {
        return orderByColumn;
    }

    public static void setOrderByColumn(String orderByColumn) {
        TableSupport.orderByColumn = orderByColumn;
    }

    public static String getSort() {
        return sort;
    }

    public static void setSort(String sort) {
        TableSupport.sort = sort;
    }
}
