import request from '@/utils/request'

//交接任务列表
export function listInfo(query) {
  return request({
    url: '/finance/settleDetail/FinanceAdvanceSettleVOList',
    method: 'post',
    data: query
  })
}
// * 根据结算任务号查询代垫费案件结算明细*/
export function getInfo(query) {
  return request({
    url: '/finance/settleDetail/getInfoList',
    method: 'post',
    data: query
  })
}

/*删除按钮修改状态为无效*/
export function deleteFinanceInfo(settleTaskNo) {
  return request({
    url: '/finance/settleDetail/deleteFinanceInfo/'+settleTaskNo,
    method: 'delete',
  })
}

/**任务确认环节确认按钮，将结算状态由待确认改为待核销(待结算)*/
export function updateConfirm(settleTaskNo) {
  return request({
    url: '/finance/settleDetail/updateSettleStatus1/'+settleTaskNo,
    method: 'PUT',
    data:settleTaskNo
  })
}

/**核销按钮将结算状态由待核销改为已结算*/
export function updateSettleStatus2(settleTaskNos) {
  return request({
    url: '/finance/settleDetail/updateSettleStatus2'+settleTaskNos,
    method: 'put',
})
}

/**发起垫付款任务*/
export function initiateTask(query) {
  return request({
    url: '/finance/settleDetail/InitiateAdvancePaymentTask',
    method: 'get',
    query: query
  })
}
