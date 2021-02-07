import request from '@/utils/request'

//查询已退回理赔批次 列表
export function getBackToList(query) {
  return request({
    url: 'claimapt/batch/backToList',
    method: 'post',
    data: query
  })
}

//查询已处理理赔批次 列表
export function getDealWithList(query) {
  return request({
    url: 'claimapt/batch/dealWithList',
    method: 'post',
    data: query
  })
}

//理赔类型-事后
// 理赔批次和报案台账信息
export function addBatchAndStanding(data) {
  return request({
    url: '/claimapt/record/addBatchAndStanding',
    method: 'post',
    data: data
  })
}
//理赔类型-事后
// 查询报案台账信息
export function getStanding(batchNo) {
  return request({
    url: '/system/case/postHocAccountingOfCases',
    method: 'get',
    params: batchNo
  })
}

//理赔类型-事后-提交
// 理赔批次和报案台账信息
export function addBatchAndStandingPresent(data) {
  return request({
    url: '/claimapt/record/addBatchAndStandingPresent',
    method: 'post',
    data: data
  })
}

//理赔类型-直结
// 理赔批次和报案台账信息
export function addBatch(data) {
  return request({
    url: '/claimapt/record/addBatch',
    method: 'post',
    data: data
  })
}

// 查询理赔批次 详细
export function getBatch(batchno) {
  return request({
    url: '/claimapt/batch/' + batchno,
    method: 'get'
  })
}

// 通过点击事件查询报案台账信息  列表
export function getMinData(batchNo) {
  return request({
    url: '/claimmgt/standing/'+batchNo,
    method: 'get',
  })
}

//  查询直结复核理赔批次公共池
export function getPublicList(batchno) {
  return request({
    url: '/claimapt/batch/publicList',
    method: 'post',
    data: batchno
  })
}

//  查询直结复核理赔批次待处理个人池
export function getUntreatedList(batchno) {
  return request({
    url: '/claimapt/batch/untreatedList',
    method: 'get',
    params: batchno
  })
}

//  从直结复核公共池获取到未处理个人池
export function getProcessedList(batchnoes) {
  return request({
    url: '/claimapt/batch/processedList',
    method: 'get',
    params: batchnoes
  })
}

//  从直结复核公共池获取到未处理个人池
export function changeStatus(batchnoes) {
  return request({
    url: '/claimapt/batch/batchnoes',
    method: 'put',
    data: batchnoes
  })
}

//  未处理个人池的案件无效化
export function invalid(batchno) {
  return request({
    url: '/claimapt/batch/' + batchno,
    method: 'put',
    data:batchno
  })
}

// 个更新复核处理页面，根据复核结论，修改批次状态，并更新sysClaimBatch表数据
export function updateClaimBatch(data) {
  return request({
    url: '/claimapt/batch/updateClaimBatch',
    method: 'post',
    data: data
  })
}

// 查询理赔批次流程记录表
export function selectRecordByBatchno(data) {
  return request({
    url: '/claimapt/record/selectRecordByBatchno',
    method: 'post',
    data: data
  })
}

// 导出交单工作池已退回理赔批次 列表
export function exportReturnedPool(query) {
  return request({
    url: '/claimapt/batch/exportReturnedPool',
    method: 'post',
    params: query	//(swagger上多字段下显示（query）)
  })
}

// 导出交单工作池已处理理赔批次 列表
export function exportProcessedPool(query) {
  return request({
    url: '/claimapt/batch/exportProcessedPool',
    method: 'post',
    params: query	//(swagger上多字段下显示（query）)
  })
}

// 导出交单复核已处理理赔批次 列表
export function exportPersonalProcessed() {
  return request({
    url: '/claimapt/batch/exportPersonalProcessed',
    method: 'post',
  })
}

// 导出交单复核待处理理赔批次 列表
export function exportPersonalUntreated() {
  return request({
    url: '/claimapt/batch/exportPersonalUntreated',
    method: 'post',
  })
}

//获取所有部门
export function getDeptList(data) {
  return request({
    url:'system/dept/deptList',
    method:'get'
  })
}
//获取当前部门
export function getThisDept(parentId) {
  return request({
    url: '/system/dept/getdepts',
    method: 'get',
    params:parentId
  })
}


