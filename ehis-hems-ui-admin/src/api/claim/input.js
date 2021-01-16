import request from '@/utils/request'

// 发票信息保存
export function billInfoSave(data) {
  return request({
    url: 'claim/claim/input/billInfoSave',
    method: 'post',
    data
  })
}

// 发票明细信息保存
export function costDetailSave(data) {
  return request({
    url: 'claim/claim/input/costDetailSave',
    method: 'post',
    data
  })
}

// 获取费用项明细列表
export function getCostDetailList(data) {
  return request({
    url: 'claim/claim/input/getCostDetailList',
    method: 'post',
    data
  })
}

// 获取账单列表
export function getBillInfos(data) {
  return request({
    url: 'claim/claim/input/getBillInfos',
    method: 'post',
    data
  })
}

// 查询医院信息
export function getHospitalLike(data) {
  return request({
    url: 'claim/claim/base/getHospitalLike',
    method: 'post',
    data
  })
}

// 查询影像信息
export function getImages(data) {
  return request({
    url: 'claim/claim/input/getImages',
    method: 'post',
    data
  })
}

// 影像分类
export function classification(data) {
  return request({
    url: 'claim/claim/input/imageClassification',
    method: 'post',
    data
  })
}

// 删除费用项明细
export function deleteDetails(data) {
  return request({
    url: 'claim/claim/input/deleteDetails',
    method: 'post',
    data
  })
}

// 更新材料信息表
export function updateMaterial(data) {
  return request({
    url: 'claim/claim/input/updateMaterialList',
    method: 'post',
    data
  })
}

// 查询银行信息
export function getBankLike(data) {
  return request({
    url: 'claim/claim/base/getBankList',
    method: 'post',
    data
  })
}


// 判断账单是否存在于其他赔案
export function judgeBills(data) {
  return request({
    url: 'claim/claim/input/judgeBills',
    method: 'post',
    data
  })
}

// 比对被保人
export function comparjsonInsuredInfo(data) {
  return request({
    url: 'claim/claim/comparjsonInsuredInfo',
    method: 'post',
    data
  })
}
// 保存被保人
export function updateInsured(data) {
  return request({
    url: 'claim/claim/updateInsured',
    method: 'post',
    data
  })
}
// 根据赔案号查询申请人信息
export function applicationInfoByClaimNo(data) {
  return request({
    url: 'claim/claim/applicationInfoByClaimNo',
    method: 'get',
    params:{
      claimNo: data
    }
  })
}

// 保存结算单
export function addAccount(data) {
  return request({
    url: 'claim/claim/statement/addAccount',
    method: 'post',
    data
  })
}

// 保存结算单
export function selectStatementBills(data) {
  return request({
    url: 'claim/claim/statement/selectStatementBills',
    method: 'post',
    data
  })
}

// 保存领款人
export function payeeSave(data) {
  return request({
    url: 'claim/claim/input/payeeSave',
    method: 'post',
    data
  })
}

// 查询已关联发票号
export function selectStatementInvoiceno(data) {
  return request({
    url: 'claim/claim/statement/selectStatementInvoiceno',
    method: 'post',
    data
  })
}

// 费用明细信息保存
export function feedDetailsSave(data) {
  return request({
    url: 'claim/claim/input/feedDetailsSave',
    method: 'post',
    data
  })
}

// 查询费用项明细信息
export function getFeedDetail(data) {
  return request({
    url: 'claim/claim/input/getFeedDetail',
    method: 'post',
    data
  })
}

// 删除费用项明细信息
export function deleteFeedDetail(data) {
  return request({
    url: 'claim/claim/input/deleteFeedDetail  ',
    method: 'post',
    data
  })
}

// 查询被保人信息
export function findInsuredInfoByClaimno(data) {
  return request({
    url: 'claim/ClaimComprehensive/findInsuredInfoByClaimno',
    method: 'post',
    data
  })
}

// 根据pageid查发票
export function getBillInfo(data) {
  return request({
    url: 'claim/claim/input/getBillInfo',
    method: 'post',
    data
  })
}

// 录入保存前校验
export function inputCheck(data) {
  return request({
    url: 'claim/claim/input/inputCheck',
    method: 'post',
    data
  })
}

// 查询领款人信息
export function getPayeeInfos(data) {
  return request({
    url: 'claim/claim/input/getPayeeInfos',
    method: 'post',
    data
  })
}

// 删除领款人信息
export function deletePayee(data) {
  return request({
    url: 'claim/claim/input/deletePayee',
    method: 'post',
    data
  })
}

