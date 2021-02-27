import request from '@/utils/request'
//# 查询协办工作池
export function selectAssist(query) {
  return request({
    url: '/cs/demand/selectAssist',
    method: 'get',
    params: query
  })
}



//------------------------------------------------------------投诉处理页面
//投诉暂存
export function collaborativeTemporary(data) {
  return request({
    url: '/cs/from/assistInComplaint',
    method: 'put',
    data: data
  })
}

//# 投诉处理的池
export function  complainSearch(workOrderNo) {
  return request({
    url: '/cs/complaint/'+workOrderNo,
    method: 'get',

  })
}

//-------------------------------------------------------------------信息需求


export function  coCancel(query) {
  return request({
    url: '/cs/from/updateCollaborativeStatus',
    method: 'get',
    params: query
  })
}
export function  coSearch(query) {
  return request({
    url: '/cs/from/list',
    method: 'get',
    params: query

  })
}
