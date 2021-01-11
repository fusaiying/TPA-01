import request from '@/utils/request'

// 协谈页面查询（处理中）
export function queryNegotiationinfo01(data) {
  return request({
    url: 'claim/claim/Negotiation/queryNegotiationinfo01',
    method: 'post',
    data
  })
}

// 协谈页面查询（已处理）
export function queryNegotiationinfo02(data) {
  return request({
    url: 'claim/claim/Negotiation/queryNegotiationinfo02',
    method: 'post',
    data
  })
}

// 协谈处理页面查询
export function negotiationBack(data) {
  return request({
    url: 'claim/claim/Negotiation/negotiationBack',
    method: 'post',
    data
  })
}

// 协谈处理页面保存
export function updateNegotiation(data) {
  return request({
    url: 'claim/claim/Negotiation/updateNegotiation',
    method: 'post',
    data
  })
}

// 协谈处理完毕
export function finishNegotiation(data) {
  return request({
    url: 'claim/claim/Negotiation/finishNegotiation',
    method: 'post',
    data
  })
}

// 上传文件反显
export function getImageInfo(data) {
  return request({
    url: 'file/file/claim/queryImage',
    method: 'post',
    data
  })
}

// 已上传文件删除
export function deleteImage(data) {
  return request({
    url: 'file/file/claim/deleteImage',
    method: 'post',
    data
  })
}

// 下载接口
export function dowloadFile(data) {
  return request({
    url: 'file/file/claim/dowloadImageFile',
    method: 'post',
    data,
    responseType: 'blob'
  })
}
