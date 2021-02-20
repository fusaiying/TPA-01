import request from '@/utils/request'


// 不参与理算
export function revokeAdjustment(data) {
  return request({
    url: '/system/calRule/editAdjustmentSubsidiaryNo',
    method: 'post',
    data
  })
}


// 撤销
export function cancelAdjustment(data) {
  return request({
    url: '/system/calRule/editAdjustmentSubsidiaryYes',
    method: 'post',
    data
  })
}


//查询 理算
export function adIsData(query) {
  return request({
    url: '/system/calRule/listByRptNo',
    method: 'get',
    params: query
  })
}

//查询 不理算
export function adNoData(query) {
  return request({
    url: '/system/calRule/listByRptNoTwo',
    method: 'get',
    params: query
  })
}

export function claimInformation(query) {
  return request({
    url: '/claimflow/case/selectClaimInformation',
    method: 'get',
    params: query
  })
}

// 新增案件协谈信息  保存按钮
export function addDiscussion(data) {
  return request({
    url: '/claimflow/discussion',
    method: 'post',
    data: data
  })
}

// 新增案件赔付信息 （保存）
export function updateCal(data) {
  return request({
    url: '/claimflow/cal',
    method: 'put',
    data: data
  })
}

// 理算审核案件退回 按钮 (主要需要rptNo)
export function backToClaimCase(query){
  return request({
    url: '/claimflow/case/backToCase',
    method: 'get',
    params: query
  })
}


// 理算审核  审核完毕 按钮
export function finishClaimCase(query){
  return request({
    url: '/claimflow/case/ReviewCompleted',
    method: 'get',
    params: query
  })
}

// 理算审核提调 按钮 (主参：rptNo)
export function inQuireConfirm(query){
  return request({
    url: '/claimflow/case/raiseCase',
    method: 'get',
    params: query
  })
}

// 新增案件调查任务
export function inQuireSave(data) {
  return request({
    url: '/claimflow/case/saveInvestigation',
    method: 'post',
    data: data
  })
}


// 查询案件赔付信息详细	（tab页）
export function calInfo(rptNo) {
  return request({
    url: '/claimflow/cal/' + rptNo,
    method: 'get'
  })
}


// 理算审核  审核完毕险种产品重设验证  (主要需要rptNo)
export function verifyProReset(query){
  return request({
    url: '/claimflow/case/verifyReset',
    method: 'get',
    params: query
  })
}

// 理算审核  审核完毕账单币种是否一致   批次号 batchNo; 账单币种  billCurrency

export function verifyCurrency(query) {
  return request({
    url: '/claimflow/case/verifyBillCurrency',
    method: 'get',
    params: query
  })
}


//（审核完毕按钮前，验证 追讨金额大于0生成追讨记录并显示在追讨工作池）
  export function addRecoveryInfo (data) {
    return request({
      url: '/claimflow/debt',
      method: 'post',
      data: data
    })
  }
