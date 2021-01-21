import request from '@/utils/request'

/** 1.关联调查/既往调查 查询 **/
export function relationSurvey(data) {
  return request({
    url: 'claim/claim/investigate/relationSurvey',
    method: 'post',
    data
  })
}

/** 2.调查信息  案件点**/
export function surveyInfo(data) {
  return request({
    url: 'claim/claim/investigate/surveyInfo',
    method: 'post',
    data
  })
}

/** 3.发起调查次数**/
export function investigateCount(data) {
  return request({
    url: 'claim/claim/investigate/investigateCount',
    method: 'post',
    data
  })
}

/** 4.发起提调**/
export function investigateTask(data) {
  return request({
    url: 'claim/claim/investigate/investigateTask',
    method: 'post',
    data
  })
}

/** 5.下载附件**/
export function claimDownloadFile(data) {
  return request({
    url: 'claim/claim/investigate/downloadFile',
    method: 'post',
    data,
    responseType: 'blob'
  })
}

/** 6.查询涉案金额**/
export function getFinalamount(data) {
  return request({
    url: 'claim/claim/investigate/getFinalamount',
    method: 'post',
    data,
  })
}

/** 7.查询出险地区**/
export function getHospitalArea(data) {
  return request({
    url: 'claim/claim/investigate/getHospitalArea',
    method: 'post',
    data,
  })
}



