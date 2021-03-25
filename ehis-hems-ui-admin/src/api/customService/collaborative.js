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


export function  coCancel(data) {
  return request({
    url: '/cs/from/updateCollaborativeStatus',
    method: 'put',
    data: data
  })
}
export function  coSearch(query) {
  return request({
    url: '/cs/from/list',
    method: 'get',
    params: query

  })
}

/*获取协办信息 详细信息*/
export function  getCollaborativeInfo(collaborativeId) {
  return request({
    url: '/cs/from/'+collaborativeId,
    method: 'get'

  })
}

/*获取协办信息 UMCode*/
export function  getUMCode(workOrderNo) {
  return request({
    url: '/cs/from/UMCode?workOrderNo='+workOrderNo,
    method: 'get'
  })
}

//# 查询信息需求页面数据
export function demandAccept(workOrderNo) {
  return request({
    url: '/cs/demand/accept?workOrderNo=' + workOrderNo,
    method: 'get'
  })
}
