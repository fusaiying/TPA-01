import request from '@/utils/request'
/** 案件拆分查询 **/
export function getSplitIncident(data) {
  return request({
    url: 'claim/claim/reopen/getSplitIncident',
    method: 'post',
    data
  })
}

/** 案件拆分操作 **/
export function splitCase(data) {
  return request({
    url: 'claim/claim/reopen/splitCase',
    method: 'post',
    data
  })
}
