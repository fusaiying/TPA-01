import request from '@/utils/request'

// 获取发起调查首页数据初始化
export function getTaskInfo(data) {
  return request({
    url: 'investigate/invest/survey/initiate/getInfo',
    method: 'post',
    data
  })
}
// 删除待处理中的数据
export function deleteTask(data) {
  return request({
    url: 'investigate/invest/survey/initiate/delete',
    method: 'post',
    data
  })
}

// 新增调查 发起信息数据落库
export function initiateInfo(data) {
  return request({
    url: 'investigate/invest/survey/initiate',
    method: 'post',
    data
  })
}

// 下载Excel模板
export function downloadExcel(data) {
  return request({
    url: 'investigate/invest/survey/downloadExcel',
    method: 'post',
    data,
    responseType: 'blob',
    timeout: 1000 * 60 * 60 * 3 ,
  })
}

// 客户信息落库
export function customerInfoInsert(data) {
  return request({
    url: 'investigate/invest/survey/customerInfoInsert',
    method: 'post',
    data
  })
}

// 关联赔案信息落库
export function relatedClaimInfoInsert(data) {
  return request({
    url: 'investigate/invest/survey/relatedClaimInfoInsert',
    method: 'post',
    data
  })
}
// 关联垫付信息落库
export function relatedPayForInsert(data) {
  return request({
    url: 'investigate/invest/survey/relatedPayForInsert',
    method: 'post',
    data
  })
}
// 既往赔案信息落库
export function pastClaimInfoInsert(data) {
  return request({
    url: 'investigate/invest/survey/pastClaimInfoInsert',
    method: 'post',
    data
  })
}
// 既往垫付信息落库
export function pastPayForInsert(data) {
  return request({
    url: 'investigate/invest/survey/pastPayForInsert',
    method: 'post',
    data
  })
}
// 获取待处理任务 当前节点
export function getRunStep(data) {
  return request({
    url: 'investigate/invest/survey/runStep',
    method: 'post',
    data
  })
}

// 上传测试
export function uploadFile(data) {
  return request({
    url: 'investigate/invest/survey/upload',
    method: 'post',
    data
  })
}
// 下载测试
export function downloadInvestFile(data) {
  return request({
    url: 'investigate/invest/survey/downloadFile',
    method: 'post',
    data,
    responseType: 'blob',
    timeout: 1000 * 60 * 60 * 3 ,
  })
}

// 查询核心数据
export function getCoreInfo(data) {
  return request({
    url: 'accesslis/core/queryPolicyInfo',
    method: 'post',
    data
  })
}

// 查询未处理任务信息
export function getPendingInfo(data) {
  return request({
    url: 'investigate/invest/survey/getPendingInfo',
    method: 'post',
    data
  })
}

// 删除上传的文件
export function deleteFile(data) {
  return request({
    url: 'investigate/invest/survey/deleteFile',
    method: 'post',
    data
  })
}
// 批量导入理赔案件
export function batchClaimInfo(data) {
  return request({
    url: 'investigate/invest/survey/batchClaimInfo',
    method: 'post',
    data
  })
}
// 批量导入垫付案件
export function batchPayForInfo(data) {
  return request({
    url: 'investigate/invest/survey/batchPayForInfo',
    method: 'post',
    data
  })
}
// 预览上传的文件
export function previewImg(data) {
  return request({
    url: 'investigate/invest/survey/previewImg',
    method: 'post',
    data
  })
}

// 保单号加密
export function des3Encode(data) {
  return request({
    url: 'investigate/invest/survey/des3PrivateKey',
    method: 'post',
    data
  })
}
