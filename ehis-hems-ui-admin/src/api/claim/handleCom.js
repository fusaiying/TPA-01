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

//# 查询理算页面查询案件备注 列表（参数  rptNo）
export function adjustRemarkList(query) {
  return request({
    url: '/system/remark/adjustRemarkList',
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

//受理确认按钮保存功能
export function editCaseAndRecordInfoSuspend(data) {
  return request({
    url: '/system/case/editCaseAndRecordInfoSuspend',
    method: 'post',
    data: data
  })
}

// 查询案件申请人信息详细
export function getRegister(rptNo) {
  return request({
    url: '/system/register/' + rptNo,
    method: 'get'
  })
}

// 查询案件受理信息 详细
export function getAccept(rptNo) {
  return request({
    url: '/system/accept/' + rptNo,
    method: 'get'
  })
}

//查询案件领款人 详细（可能有多条）
export function listRemarkRptNo(rptNo) {
  return request({
    url: '/system/payee/s/' + rptNo,
    method: 'get',
  })
}

//查询案件领款人 详细（可能有多条）
export function getHospital(rptNo) {
  return request({
    url: '/system/payee/getInfohospitalCode',
    method: 'post',
    params: rptNo
  })
}


// 查询案件备注详细 详细（可能有多条）
export function getRemarkRptNo(rptNo) {
  return request({
    url: '/system/remark/s/' + rptNo,
    method: 'get'
  })
}

// 查询案件问题件详细
export function getProblemRptNo(rptNo) {
  return request({
    url: '/system/problem/s/' + rptNo,
    method: 'get'
  })
}
// 新增问题件
export function addProblem(data) {
  return request({
    url: '/system/problem',
    method: 'post',
    data: data
  })
}

//被保人信息查询接口
// 查询被保人信息详细
export function getInsured(rptNo) {
  return request({
    url: '/system/insured/' + rptNo,
    method: 'get'
  })
}

//撤件体检按钮
export function removeCase(data) {
  return request({
    url: '/system/case/editCaseCheckFrom',
    method: 'post',
    data: data
  })
}

//查询账单明细列表
export function getBillList(rptNo) {
  return request({
    url: '/system/bill/list',
    method: 'get',
    params: rptNo
  })
}

//保存账单明细列表
export function saveBill(data) {
  return request({
    url: '/system/bill',
    method: 'post',
    data: data
  })
}

//编辑查询账单明细列表
export function editBill(billId) {
  return request({
    url: '/system/bill/' + billId,
    method: 'get',
  })
}

//获取费用项
export function getFee(data) {
  return request({
    url: '/system/feeitem/list',
    method: 'post',
    data:data
  })
}

//查询医院
export function getHospitalInfo(data) {
  return request({
    url: '/provider/org/selectHospitalInfoNew',
    method: 'post',
    data: data
  })
}

//理算
//个人池处理中
// 查询案件信息 详细
export function listConditionsForTheAdjustmentUnder(data) {
  return request({
    url: '/system/case/listConditionsForTheAdjustmentUnder',
    method: 'get',
    params: data
  })
}

//个人池已处理
// 查询案件信息 详细
export function listConditionsForTheAdjustmentOver(data) {
  return request({
    url: '/system/case/listConditionsForTheAdjustmentOver',
    method: 'get',
    params: data
  })
}

//个人池悬挂中
// 查询案件信息 详细
export function listConditionsForTheAdjustmentHang(data) {
  return request({
    url: '/system/case/listConditionsForTheAdjustmentHang',
    method: 'get',
    params: data
  })
}

//删除账单明细
export function deleteBill(billId) {
  return request({
    url: '/system/bill/' + billId,
    method: 'delete',
  })
}

//录入完毕删除账单明细
export function changeBillStatus(rptNo) {
  return request({
    url: '/system/bill/changeStatus',
    method: 'get',
    params: rptNo
  })
}


//# 默认页面账单载入查询  (主参：rptNo)
export function infoList(data) {
  return request({
    url: '/system/calBill/infoList',
    method: 'get',
    params: data
  })
}

//# 下拉查询页面账单费用项详情 (主参：rptNo、billId)
export function itemList(data) {
  return request({
    url: '/system/item/itemList',
    method: 'get',
    params: data
  })
}

//# 一键展开页面账单查询 按钮	(主参：rptNo、billId)
export function detailsList(data) {
  return request({
    url: '/system/calBill/detailsList',
    method: 'get',
    params: data
  })
}

//# 费用项账单保单号及险种查询 下拉框	(主参：rptNo)
export function insurancePolicyList(data) {
  return request({
    url: '/system/item/InsurancePolicyList',
    method: 'post',
    data: data
    // params: data
  })
}

//# 费用项账单责任明细查询 下拉框	(主参：riskCode、feeItemCode)
export function responsibilityDetailsList(data) {
  return request({
    url: '/system/item/responsibilityDetailsList',
    method: 'get',
    params: data
  })
}

//#费用项账单   保存  按钮
export function billDetailsSave(data) {
  return request({
    url: '/system/calBill',
    method: 'put',
    data: data
  })
}

//理算审核 案件汇总信息  (主参：rptNo)
export function calSummary(data) {
  return request({
    url: 'system/cal/calSummary',
    method: 'get',
    params: data
  })
}
