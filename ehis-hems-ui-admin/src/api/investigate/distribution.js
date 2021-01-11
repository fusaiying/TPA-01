import request from '@/utils/request'

/** 既往理赔信息（理赔） **/
export function getPastClaimCaseList(data) {
  return request({
    url: 'investigate/invest/survey/getClaimList/claim',
    method: 'post',
    data
  })
}
/** 既往垫付信息（垫付） **/
export function getPastAdvanceCaseList(data) {
  return request({
    url: 'investigate/invest/survey/getClaimeList/advance',
    method: 'post',
    data
  })
}
/** 关联调查信息 **/
export function getSurveyInfoList(data) {
  return request({
    url: 'investigate/invest/survey/getSurveyList',
    method: 'post',
    data
  })
}
/** 分配，再次分配 **/
export function submitDispatchInfo(data) {
  return request({
    url: 'investigate/invest/survey/reassign/submit',
    method: 'post',
    data
  })
}
/** 供应商分配信息 **/
export function getReassignInfo(data) {
  return request({
    url: 'investigate/invest/survey/getReassignInfo',
    method: 'post',
    data
  })
}
/** 再次分配查询信息 **/
export function getRedistributionInfo(data) {
  return request({
    url: 'investigate/invest/survey/redistribution/getReassignInfo',
    method: 'post',
    data
  })
}
/** 退回记录 **/
export function getDispatchInfo(data) {
  return request({
    url: 'investigate/invest/survey/reassign/getRecordList',
    method: 'post',
    data
  })
}

export function goSurveyRevoke(data) {
  return request({
    url: 'investigate/invest/survey/distribution/revoke',
    method: 'post',
    data
  })
}
/** 公共池查询 **/
export function getDisPublicList(data) {
  return request({
    url: 'investigate/invest/survey/getDisPublicList',
    method: 'post',
    data
  })
}
/** 分配页面查询 **/
export function getPending(data) {
  return request({
    url: 'investigate/invest/survey/getSurveyDispatchList',
    method: 'post',
    data
  })
}

/** 调查详情查询 **/
export function getSurveyDetail(taskno, source) {
  return request({
    url: 'investigate/invest/survey/getSurveyDetailInfo',
    method: 'post',
    params: {
      taskno: taskno,
      source: source
    }
  })
}

/** 被保人信息查询 **/
export function getInsuranceInfo(data) {
  return request({
    url: 'investigate/invest/survey/getInsuranceByIdNo',
    method: 'post',
    data
  })
}

/** 关联保单查询 **/
export function getContList(data) {
  return request({
    url: 'investigate/invest/survey/getContList',
    method: 'post',
    data
  })
}

/** 关联理赔案件信息查询 **/
export function getClaimCaseList(data) {
  return request({
    url: 'investigate/invest/survey/getCaseList/claim',
    method: 'post',
    data
  })
}

/** 关联垫付案件信息查询 **/
export function getAdvanceCaseList(data) {
  return request({
    url: 'investigate/invest/survey/getCaseList/advance',
    method: 'post',
    data
  })
}

/** 下载 **/
export function dowload(data) {
  return request({
    url: 'investigate/invest/survey/dowloadFile',
    method: 'post',
    data,
    responseType: 'blob',
    timeout: 1000 * 60 * 60 * 3 ,
  })
}

/** 获取供应商下拉选 **/
export function getServiceProvider(data) {
  return request({
    url: 'investigate/invest/survey/reassign/getSupplierList',
    method: 'post',
    data
  })
}

/** 获取全部供应商下拉选 **/
export function getAllServiceProvider(data) {
  return request({
    url: 'investigate/invest/aging/getServiceProvider',
    method: 'post',
    data
  })
}
export function exportDistributeFile(data) {     /** 调完 **/
  return request({
    url: 'investigate/invest/survey/export/exportDistributeFile',
    method: 'post',
    data,
    responseType: 'blob'
  })
}
