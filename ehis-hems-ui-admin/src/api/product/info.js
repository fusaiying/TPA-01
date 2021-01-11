import request from '@/utils/request'
import { selectDictLabel } from "@/utils/sinoutils";

// 查询服务类型
export function getServiceComNo(query) {
  return request({
    url: '/product/definition/getServiceComNo',
    method: 'get',
    params: query
  })
}

// 查询服务类型
export function getServiceNetWorkList(query) {
  return request({
    url: '/product/definition/getServiceNetWorkList',
    method: 'get',
    params: query
  })
}

// 查询所有省份
export function getProvinceAll(query) {
  return request({
    url: '/product/definition/getProvinceAll',
    method: 'get',
    params: query
  })
}

// 查询城市
export function getCityByProvince(query) {
  return request({
    url: '/product/definition/getCityByProvince',
    method: 'get',
    params: query
  })
}

// 查询地区
export function getDistrictByCity(query) {
  return request({
    url: '/product/definition/getDistrictByCity',
    method: 'get',
    params: query
  })
}

// 保存产品信息，服务机构，服务网点
export function saveProduct(productInfo,serviceProjectList,serviceOrgList,serviceNetWorkList) {
  return request({
    url: '/product/definition/addProduct',
    method: 'post',
    //params: {productInfo:productInfo,serviceOrgList:serviceOrgList,serviceNetWorkList:serviceNetWorkList}
    data: {prodInfo:productInfo,servProjects:serviceProjectList,servOperas:serviceOrgList,comWebSites:serviceNetWorkList}
  })
}

// 查询产品的服务机构，服务网点
export function queryServiceOrgAndNetWork(productno) {
  return request({
    url: '/product/definition/queryServiceOrgAndNetWork',
    method: 'post',
    data: productno
  })
}

// 查询本产品
export function getProductInfoById(productid) {
  return request({
    url: '/product/definition/getProductInfoById',
    method: 'post',
    data: productid
  })
}


// 保存产品服务地区
export function saveServiceCity(productInfo,serviceCityList) {
  return request({
    url: '/product/definition/saveServiceCity',
    method: 'post',
    //params: {productInfo:productInfo,serviceOrgList:serviceOrgList,serviceNetWorkList:serviceNetWorkList}
    data: {prodInfo:productInfo,prodSalComs:serviceCityList}
  })
}

// 查询产品的服务地区
export function queryServiceCity(productno) {
  return request({
    url: '/product/definition/queryServiceCity',
    method: 'post',
    data: productno
  })
}

// 产品审核提交
export function productAudit(productInfo) {
  return request({
    url: '/product/definition/productAudit',
    method: 'post',
    data: productInfo
  })
}

// 查询产品的审核轨迹
export function listAuditTrack(query) {
  return request({
    url: '/product/definition/queryAuditTrack',
    method: 'get',
    params: query
  })
}

// 产品审核完毕
export function productAudit2(productInfo,auditTrack) {
  return request({
    url: '/product/definition/productAudit2',
    method: 'post',
    data: {prodInfo:productInfo,audit:auditTrack}
  })
}

// 财务复核审核完毕
export function productAudit3(productInfo,auditTrack) {
  return request({
    url: '/product/definition/productAudit3',
    method: 'post',
    data: {prodInfo:productInfo,audit:auditTrack}
  })
}
//查询产品审核结果
export function getProductExamineResult(productno){
  return request({
    url: '/product/definition/getProductExamineResult',
    method: 'post',
    data: productno
  })
}

//产品上线
export function productPublish(productInfo){
  return request({
    url: '/product/definition/productPublish',
    method: 'post',
    data: productInfo
  })
}

//重新审核
export function productReAuditTrack(productInfo) {
  return request({
    url: '/product/definition/productReAuditTrack',
    method: 'post',
    data: productInfo
  })
}

//状态维护-产品更新
export function productUpdate(productInfo) {
  return request({
    url: '/product/definition/productUpdate',
    method: 'post',
    data: {prodInfo:productInfo}
  })
}

//保存产品服务项目、服务机构、服务网点
export function saveProductProject(product,serviceProjectList,serviceOrgList,serviceNetWorkList){
  return request({
    url: '/product/definition/updateProject',
    method: 'post',
    data: {prodInfo:product,servProjects:serviceProjectList,servOperas:serviceOrgList,comWebSites:serviceNetWorkList}
  })
}
