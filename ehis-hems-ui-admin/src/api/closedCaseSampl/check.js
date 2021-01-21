import request from '@/utils/request'
// 已处理列表
export function getCheckCompleteList(data) {
  return request({
    url: 'claim/claim/spotCheck/getCheckCompleteList',
    method: 'post',
    data
  })
}

// 待处理列表
export function getCheckPendingList(data) {
  return request({
    url: 'claim/claim/spotCheck/getCheckPendingList',
    method: 'post',
    data
  })
}
// 待处理清单下载
export function exportPendingFile() {
  return request({
    url: '/claim/claim/spotCheck/exportPendingFile',
    method: 'get',
    responseType: 'blob'
  })
}
// 待处理清单下载
export function exportCompleteFile() {
  return request({
    url: '/claim/claim/spotCheck/exportCompleteFile',
    method: 'get',
    responseType: 'blob'
  })
}
