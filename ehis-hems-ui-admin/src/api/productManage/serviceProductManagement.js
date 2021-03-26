import request from '@/utils/request'

/**
 * 服务产品定义删除
 */
export function deleteProductInfo(data) {
  return request({
    url: '/product/info/deleteProductInfo',
    method: 'post',
    data
  })
}

/**
 * 服务产品定义列表查询
 */
export function getList(data) {
  return request({
    url: '/product/info/list',
    method: 'get',
    params: data
  })
}

/**
 * 服务产品定义列表默认查询
 */
export function listNull(data) {
  return request({
    url: 'product/info/listNull',
    method: 'get',
    params:data
  })
}
/**
 * 服务产品管理列表默认查询
 */
export function mangerListNull(data) {
  return request({
    url: 'product/info/mangerListNull',
    method: 'get',
    params:data
  })
}




/**
 * 服务产品基本信息重复校验
 */
export function checkField(data) {
  return request({
    url: '/product/info/checkField',
    method: 'post',
    data
  })
}
/**
 * 服务项目名称下拉框
 */
export function getServiceInfo() {
  return request({
    url: '/product/info/getServiceInfo',
    method: 'post',

  })
}
/**
 * 产品定义总保存
 */
export function saveProductInfo(data) {
  return request({
    url: '/product/info/saveProductInfo',
    method: 'post',
    data

  })
}
/**
 * 产品定义信息保存
 */
export function insertProductInfo(data) {
  return request({
    url: '/product/info/insertProductInfo',
    method: 'post',
    data

  })
}
/**
 * 产品审核提交审核
 */
export function insertCheckInfo(data) {
  return request({
    url: '/product/info/insertCheckInfo',
    method: 'post',
    data

  })
}
/**
 *
 */
export function updateSupplierStatus(data) {
  return request({
    url: '/product/info/updateSupplierStatus',
    method: 'post',
    data

  })
}

/**
 * 产品基本信息查询
 */
export function getProductInfo(data) {
  return request({
    url: '/product/info/getProductInfo',
    method: 'post',
    data

  })
}
/**
 * 查询服务项目关联表
 */
export function getProductServiceList(data) {
  return request({
    url: '/product/info/getProductServiceList',
    method: 'post',
    data

  })
}
/**
 * 查询服务手册
 */
export function getManualInfoList(data) {
  return request({
    url: '/product/info/getManualInfoList',
    method: 'get',
    params: data

  })
}
/**
 * 重复校验
 */
export function checkList(data) {
  return request({
    url: 'product/info/checkList',
    method: 'get',
    params:data

  })
}
/**
 * 查询供应商
 */
export function selectSupplier(data) {
  return request({
    url: 'product/info/selectSupplier',
    method: 'post',
    data

  })
}
/**
 * 供应商保存
 */
export function insertSupplier(data) {
  return request({
    url: 'product/info/insertSupplier',
    method: 'post',
    data

  })
}
/**
 * 产品定义提交审核
 */
export function checkProductInfo(data) {
  return request({
    url: 'product/info/checkProductInfo',
    method: 'post',
    data

  })
}
/**
 * 产品管理查询
 */
export function mangerList(data) {
  return request({
    url: 'product/info/mangerList',
    method: 'get',
    params: data

  })
}
/**
 * 产品下线
 */
export function insertMangerInfo(data) {
  return request({
    url: 'product/info/insertMangerInfo',
    method: 'post',
    data

  })
}
/**
 * 审核供应商查询
 */
export function selectProductSupplier(data) {
  return request({
    url: 'product/info/selectProductSupplier',
    method: 'post',
    data

  })
}
/**
 * 审核日志查询
 */
export function selectLogList(data) {
  return request({
    url: 'product/info/selectLogList',
    method: 'post',
    data
  })
}







