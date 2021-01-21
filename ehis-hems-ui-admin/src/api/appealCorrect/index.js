import request from '@/utils/request'

// 查询list
export function getList(data) {
  return request({
    url: 'claim/GrievanceCorrection/getGrievanceCorrectionList',
    method: 'post',
    data
  })
}

// 改派
export function taskReassign(data) {
  return request({
    url: 'claim/',
    method: 'post',
    data
  })
}

// 申诉纠错处理
export function complaintCorrectionHandle(data) {
  return request({
    url: 'claim/GrievanceCorrection/complaintCorrectionHandle',
    method: 'post',
    data
  })
}
// 申诉纠错处理信息
export function complaintCorrectionHandleFind(data) {
  return request({
    url: 'claim/GrievanceCorrection/complaintCorrectionHandleFind',
    method: 'post',
    data
  })
}