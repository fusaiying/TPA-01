import request from '@/utils/request'
import fileRequest from '@/utils/fileRequest'

export function getInfo(data) {
  return request({
    url: 'investigate/invest/survey/result/getInfo',
    method: 'post',
    data
  })
}
export function saveResult(data) {
  return request({
    url: 'investigate/invest/survey/result/saveResult',
    method: 'post',
    data
  })
}
/**
 *
 * @param
 */
export function getCreatorType() {
  return request({
    url: 'investigate/invest/survey/getCreatorType',
    method: 'post',
  })
}
export function getSchedule(data) {
  return request({
    url: 'investigate/invest/survey/getSchedule',
    method: 'post',
    data
  })
}
export function getAllSchedule(data) {
  return request({
    url: 'investigate/invest/survey/getAllSchedule',
    method: 'post',
    data
  })
}
/** 调查处理查询页面 **/                       /**  调完 **/
export function getSurveyList(data) {
  return request({
    url: 'investigate/invest/survey/getSurveyHandleList',
    method: 'post',
    data
  })
}
/** 调查详情 **/                             /**  调完 **/
export function getSurveyDetail(taskno,source) {
  return request({
    url: 'investigate/invest/survey/getSurveyDetailInfo',
    method: 'get',
    params:{
      taskno:taskno,
      source:source
    }
  })
}
/** 被保人信息 **/                       /** 调完 **/
export function getInsuranceInfo(data) {
  return request({
    url: 'investigate/invest/survey/getInsuranceByIdNo',
    method: 'post',
    data
  })
}
/** 保单列表信息 **/                        /**  调完 **/
export function getContList(data) {
  return request({
    url: 'investigate/invest/survey/getContList',
    method: 'post',
    data
  })
}
/** 关联案件信息（理赔） **/               /**  调完 **/
export function getClaimCaseList(data) {
  return request({
    url: 'investigate/invest/survey/getCaseList/claim',
    method: 'post',
    data
  })
}
/** 关联案件信息（垫付） **/               /**  调完 **/
export function getAdvanceCaseList(data) {
  return request({
    url: 'investigate/invest/survey/getCaseList/advance',
    method: 'post',
    data
  })
}
/** 调查发起信息 **/                    /** 调完 **/
export function getSurveyInfo(taskno,source) {
  return request({
    url: 'investigate/invest/survey/getSurveyInfo',
    method: 'get',
    params:{
      taskno:taskno,
      source:source
    }
  })
}
/** 供应商分配信息 **/                /** 调完 **/
export function getReassignInfo(data) {
  return request({
    url: 'investigate/invest/survey/getReassignInfo',
    method: 'post',
    data
  })
}
//导出待处理，已处理列表
export function exportFile(data) {     /** 调完 **/
  return request({
    url: 'investigate/invest/survey/export/exportFile',
    method: 'post',
    data,
    responseType: 'blob'
  })
}
// 下载文件
export function exportPublicFile(data) {
  return request({
    url: 'investigate/invest/survey/export/exportPublicFile',
    method: 'post',
    data,
    responseType: 'blob',
    timeout: 1000 * 60 * 60 * 3 ,
  })
}
//上传调查报告
export function uploadSurveyReport(data) {     /** 调完 **/
  return request({
    url: 'investigate/invest/survey/uploadSurveyReport',
    method: 'post',
    data
  })
}
//上传调查附件
export function uploadSurveyAnnex(data) {     /** 调完 **/
  return request({
    url: 'investigate/invest/survey/uploadSurveyAnnex',
    method: 'post',
    data
  })
}
//获取医院
export function getHospitals(data) {     /** 调完 **/
  return request({
    url: 'investigate/invest/survey/base/getallhospital',
    method: 'post',
    data
  })
}
// 获取医院模糊查询
export function getHospitalLike(data) {
  return request({
    url: 'investigate/invest/survey/base/getHospitalLike',
    method: 'post',
    data
  })
}
// 获取医院
export function getHospital(data) {
  return request({
    url: 'investigate/invest/survey/base/getHospital',
    method: 'post',
    data
  })
}

// 获取返显的医院
export function getHospitalInCode(data) {
  return request({
    url: 'investigate/invest/survey/base/getHospitalInCode',
    method: 'post',
    data
  })
}
// 省市区查询医院
export function getHospitalByAddress(data) {
  return request({
    url: 'investigate/invest/survey/base/getHospitalByAddress',
    method: 'post',
    data
  })
}

// 下载文件
export function downloadFile(data) {
  return request({
    url: 'investigate/invest/survey/downloadFile',
    method: 'post',
    data,
    responseType: 'blob',
    timeout: 1000 * 60 * 60 * 3 ,
  })
}
// 下载文件
export function downloadFileRar(data) {
  return fileRequest({
    url: 'invest/survey/downloadFileRar',
    method: 'post',
    data,
    responseType: 'blob',
    timeout: 1000 * 60 * 60 * 3 ,
  })
}
// 删除
export function deleteInvestFile(data) {
  return request({
    url: 'investigate/invest/survey/deleteInvestFile',
    method: 'post',
    data
  })
}
// 删除
export function deleteInvestFileList(data) {
  return request({
    url: 'investigate/invest/survey/deleteInvestFileList',
    method: 'post',
    data
  })
}
// 删除案件点明细
export function deleteCasePointDet(data) {
  return request({
    url: 'investigate/invest/survey/deleteCasePointDet',
    method: 'post',
    data
  })
}
// 删除案件点
export function deleteCasePoint(data) {
  return request({
    url: 'investigate/invest/survey/deleteCasePoint',
    method: 'post',
    data
  })
}
// 删除附件
export function deleteAttachment(data) {
  return request({
    url: 'investigate/invest/survey/deleteAttachment',
    method: 'post',
    data
  })
}
// 获取当前任务信息
export function getTaskInfoById(data) {
  return request({
    url: 'investigate/invest/survey/getTaskInfoById',
    method: 'post',
    data
  })
}
