import request from '@/utils/request'

//# 查询处理中受理案件信息 列表
export function getBackList(query) {
  return request({
    url: '/system/case/processingList',
    method: 'get',
    params: query
  })
}

//# 查询已处理受理案件信息 列表
export function getFinishList(query) {
  return request({
    url: '/system/case/processedList',
    method: 'get',
    params: query
  })
}

//# 查询悬挂中受理案件信息 列表
export function getHangUpList(query) {
  return request({
    url: '/system/case/suspensionList',
    method: 'get',
    params: query
  })
}
// 查询案件信息 详细
// 传报案号和批次号
export function getCase(data) {
  return request({
    url: '/system/case/getInfoClaimAndBatch',
    method: 'get',
    params: data
  })
}

// 查询理赔批次 详细
export function getBatch(batchNo) {
  return request({
    url: '/system/batch/' + batchNo,
    method: 'get'
  })
}

//保存申请人信息
//需要将前端的报案号，也发给我
// 新增案件申请人信息
export function addRegister(data) {
  return request({
    url: '/system/register',
    method: 'post',
    data: data
  })
}


//问题件接口
export function selectHistoricalProblem(data) {
  return request({
    url: '/system/problem/selectHistoricalProblem',
    method: 'get',
    data: data
  })
}

//被保人信息保存
export function addInsuredAndPolicy(data) {
  return request({
    url: '/system/insured/addInsuredAndPolicy',
    method: 'post',
    data: data
  })
}

//被保人查询页面
export function selectRecognizee(data) {
  return request({
    url: '/system/policyInsured/selectRecognizee',
    method: 'get',
    params: data
  })
}

//被保人保单信息页面
export function selectInsuredList(data) {
  return request({
    url: '/system/info/selectInsured',
    method: 'get',
    params: data
  })
}

//案件领款人
//默认查询当前被保人上一笔案件的账户信息
//报案号传过来
//赔案备注
//查询
// 查询案件备注列表
export function listRemark(query) {
  return request({
    url: '/system/payee/selectOne',
    method: 'get',
    params: query
  })
}

//需要将前端的报案号，也发给我
// 新增案件受理信息
export function addAccept(data) {
  return request({
    url: '/system/accept',
    method: 'post',
    data: data
  })
}
//问题件确认按钮功能（子任务生成未完成）
// 修改案件信息
export function editCaseInfoSuspend(data) {
  return request({
    url: '/system/case/editCaseInfoSuspend',
    method: 'post',
    data: data
  })
}

//赔案备注
//查询
// 查询案件备注列表
export function getListRemark(query) {
  return request({
    url: '/system/remark/list',
    method: 'get',
    params: query
  })
}
//赔案备注
//删除
// 删除案件备注
export function delRemark(remarkId) {
  return request({
    url: '/system/remark/' + remarkId,
    method: 'delete'
  })
}

// 修改案件备注
export function updateRemark(data) {
  return request({
    url: '/system/remark',
    method: 'put',
    data: data
  })
}
// 新增案件备注
export function addRemark(data) {
  return request({
    url: '/system/remark',
    method: 'post',
    data: data
  })
}

//# 新增案件领款人信息
export function addPayee(data) {
  return request({
    url: '/system/payee',
    method: 'post',
    data: data
  })
}
//# 修改案件领款人信息
export function editPayee(data) {
  return request({
    url: '/system/payee',
    method: 'put',
    data: data
  })
}
//# 删除案件领款人信息
export function delPayee(data) {
  return request({
    url: '/system/payee/rptNo',
    method: 'put',
    data: data
  })
}

//撤件弹框确认按钮
//修改案件信息
export function editCaseAndRecordInfoCancel(data) {
  return request({
    url: '/system/case/editCaseAndRecordInfoCancel ',
    method: 'post',
    data: data
  })
}
