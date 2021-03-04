import request from '@/utils/request'

//查询公共池 (规则审核)
export function listAndPublicPool(data) {
  return request({
    url: '/claimcal/product/listAndPublicPool',
    method: 'post',
    data: data
  })
}

//个人池：待处理 (规则审核)
export function pendingAndPersonPool(data) {
  return request({
    url: '/claimcal/product/listAndPersonalPoolUnfinished',
    method: 'post',
    data: data
  })
}

//个人池：已处理 (规则审核)
export function completedAndPersonPool(data) {
  return request({
    url: '/claimcal/product/listAndPersonalPoolFinish',
    method: 'post',
    data: data
  })
}

//点击获取  (规则审核)
export function acquire(data) {
  return request({
    url: '/claimcal/product/editUpdateBy',
    method: 'post',
    data: data
  })
}

// 查询产品信息公共池 列表 (规则定义)
export function searchPublicPool(query) {
  return request({
    url: '/claimcal/product/publicList',
    method: 'post',
    data: query
  })
}


// 查询已处理产品信息 列表 (规则定义)
export function searchProcessedList(query) {
  return request({
    url: '/claimcal/product/processedList',
    method: 'post',
    data: query
  })
}


// 查询待处理产品信息 列表 (规则定义)
export function searchUntreatedList(query) {
  return request({
    url: '/claimcal/product/untreatedList',
    method: 'post',
    data: query
  })
}

// 从直结复核公共池获取到待处理个人池 (规则定义)
export function acquireDefinition(riskCodes) {
  return request({
    url: '/claimcal/product/riskCodes',
    method: 'put',
    data: riskCodes
  })
}

// 查询产品信息详细
export function getProduct(riskCode) {
  return request({
    url: '/claimcal/product/' + riskCode,
    method: 'put'
  })
}

// 查询计划信息 列表
export function listPlan(query) {
  return request({
    url: '/claimcal/plan/list',
    method: 'get',
    params: query
  })
}

//产品查询工作池接口 (综合查询)
export function selectProductQuery(data) {
  return request({
    url: '/claimcal/product/selectProductQuery',
    method: 'post',
    data: data
  })
}
// 查询责任明细信息 列表 (责任明细)
export function listDetail(query) {
  return request({
    url: '/claimcal/detail/list',
    method: 'get',
    params: query
  })
}

//任务改派查询接口
export function selectClaimProductList(data) {
  return request({
    url: '/claimcal/product/selectClaimProductList',
    method: 'post',
    data: data
  })
}

//任务改派修改操作人接口
export function updateClaimProductList(data) {
  return request({
    url: '/claimcal/product/updateClaimProductList',
    method: 'post',
    data: data
  })
}
// 查询福利规则 列表
export function listRule(query) {
  return request({
    url: '/claimcal/productRule/list',
    method: 'get',
    params: query
  })
}
// 福利对象获取
export function getWelfare(rickCode) {
  return request({
    url: '/claimcal/product/'+rickCode,
    method: 'get',
  })
}

// 规则新增
export function addRule(data) {
  return request({
    url: '/claimcal/productRule',
    method: 'post',
    data:data
  })
}

// 规则删除
export function deleteRule(ruleNos) {
  return request({
    url: '/claimcal/productRule/ruleNos',
    method: 'post',
    data:ruleNos
  })
}

// 提交审核/审核完毕
export function submitReview(data) {
  return request({
    url: '/claimcal/log',
    method: 'post',
    data:data
  })
}

// 重设
export function resetInfo(data) {
  return request({
    url:'claimcal/product/riskCode',
    method: 'put',
    data:data
  })
}
// 重设
export function checkSave(riskCode) {
  return request({
    url:'claimcal/log/check/'+riskCode,
    method: 'get',
  })
}

export function getListNew(data) {
  return request({
    url:'provider/org/getListNew',
    method: 'post',
    data:data
  })
}

//任务改派查询改派操作人接口
export function selectSysUser(data) {
  return request({
    url: '/claimcal/product/selectSysUser',
    method: 'post',
    data:data
  })
}





