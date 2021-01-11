import request from '@/utils/request'
// 退案列表
export function getList(data) {
  return request({
    url: 'claim/returnProcessingEntry/getList',
    method: 'post',
    data
  })
}
// 退案处理列表
export function returnProcessingDisplay(data) {
  return request({
    url: 'claim/returnProcessingEntry/returnProcessingDisplay',
    method: 'post',
    data
  })
}
// 处理完毕
export function returnCompleted(data) {
  return request({
    url: 'claim/returnProcessingEntry/returnCompleted',
    method: 'post',
    data
  })
}
// 退件保存
export function returnInformationSave(data) {
  return request({
    url: 'claim/returnProcessingEntry/returnInformationSave',
    method: 'post',
    data
  })
}
// 发送短信
export function sendSMS(data) {
  return request({
    url: 'claim//returnProcessingEntry/sendSMS',
    method: 'post',
    data
  })
}
