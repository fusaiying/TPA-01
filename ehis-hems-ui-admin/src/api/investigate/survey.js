import request from '@/utils/request'
/*公共池查询*/
export function getPublicList(data) {
  return request({
    url: 'investigate/invest/survey/getPublicList',
    method: 'post',
    data
  })
}
/*批量申请*/
export function apply(data) {
  return request({
    url: 'investigate/invest/survey/apply',
    method: 'post',
    data
  })

}
/*调查撤销*/
export function revoke(data) {
  return request({
    url: 'invest/survey/distribution/revoke',
    method: 'post',
    data
  })
}
/*调查接收公共池查询*/
export function getReceivePublicList(data) {
  return request({
    url: 'investigate/invest/survey/getReceptionPoolList',
    method: 'post',
    data
  })
}
/*调查接收待处理列表查询*/
export function getSurveyReceiveList(data) {
  return request({
    url: 'investigate/invest/survey/getSurveyReceiveList',
    method: 'post',
    data
  })
}

/**
 * 调查接收
 * @param data
 */
export function surveyReception(data) {
  return request({
    url: 'investigate/invest/survey/receive/addReceiver',
    method: 'post',
    data
  })
}
/**
 * 拒绝接收
 * @param data
 */
export function surveyRefuse(data) {
  return request({
    url: 'investigate/invest/survey/receive/addRefuse',
    method: 'post',
    data
  })
}
export function getSurveyList(data) {
  return request({
    url: 'investigate/invest/survey/getList',
    method: 'post',
    data
  })
}

export function getSurveyDetailInfo(taskno,source) {
  return request({
    url: 'investigate/invest/survey/getSurveyDetailInfo',
    method: 'get',
    params:{
      taskno:taskno,
      source:source
    }
  })
}
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

export function getContList(data) {
  return request({
    url: 'investigate/invest/survey/getContList',
    method: 'post',
    data
  })
}
export function getCaseListOfClaim(data) {
  return request({
    url: 'investigate/invest/survey/getCaseList/claim',
    method: 'post',
    data
  })
}
export function getCaseListOfRelevance(data) {
  return request({
    url: 'investigate/invest/survey/getCaseList/advance',
    method: 'post',
    data
  })
}
export function getClaimPastList(data) {
  return request({
    url: 'investigate/invest/survey/getClaimList/claim',
    method: 'post',
    data
  })
}

export function getAdvancePastList(data) {
  return request({
    url: 'investigate/invest/survey/getClaimeList/advance',
    method: 'post',
    data
  })
}
export function test(data) {
  return request({
    url: 'investigate/invest/survey/getSurveyDispatchList',
    method: 'post',
    data
  })
}
export function getInsuranceInfo(data) {
  return request({
    url: 'investigate/invest/survey/getReassignInfo',
    method: 'post',
    data
  })
}
export function getInsuranceByIdNo(data) {
  return request({
    url: 'investigate/invest/survey/getInsuranceByIdNo',
    method: 'post',
    data
  })
}
export function exportFile(data) {
  return request({
    url: 'investigate/invest/survey/export/exportSurveyTaskFile',
    method: 'post',
    data,
    responseType: 'blob'
  })
}
export function getSupplier() {
  return request({
    url: 'investigate/invest/survey/getSupplierName',
    method: 'get'
  })
}
