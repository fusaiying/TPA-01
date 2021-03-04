import request from '@/utils/request'


// 本次协谈处理
export function updateDiss(data) {
  return request({
    url:  '/claimflow/discussion/updateClaimCaseDiscussion',
    method: 'put',
    data:data
  })
}

//查询 问题件工作池  (待处理)
export function PendingData(query) {
  return request({
    url: '/claimflow/discussion/selectCaseDisListUnder',
    method: 'get',
    params: query
  })
}

//查询 问题件工作池  (已处理)
export function processedData(query) {
  return request({
    url: '/claimflow/discussion/selectCaseDisListOver',
    method: 'get',
    params: query
  })
}

// 查询 案件基本信息  /discussion/{rptNo} 非历史
export function baseInfo(rptNo) {
  return request({
    url:  '/claimflow/discussion/caseInfo/' + rptNo,
    method: 'get',
  })
}
// 查询 案件基本信息  /discussion/{rptNo} 历史协谈信息
export function historyBaseInfo(rptNo) {
  return request({
    url:  '/claimflow/discussion/caseInfo/history/' + rptNo,
    method: 'get',
  })
}

// 查询 历史协谈信息
export function historyDisInfo(rptNo) {
  return request({
    url:  '/claimflow/discussion/history/' + rptNo,
    method: 'get',
  })
}


// 查询 历史调查信息
export function historySurInfo(query) {
  return request({
    url:  '/claimflow/detail/listByRptNo/' ,
    method: 'get',
    params: query
  })
}


