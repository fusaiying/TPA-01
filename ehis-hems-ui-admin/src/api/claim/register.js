import request from '@/utils/request'

// 报案列表数据查询
export function getRegisterList(data) {
  return request({
    url: 'claim/claim/getRegisterList',
    method: 'post',
    data
  })
}

// 申请信息落库接口
export function applyInfoSave(data) {
  return request({
    url: 'claim/claim/applyInfoSave',
    method: 'post',
    data
  })
}

// 申请人信息落库接口
export function reportSave(data) {
  return request({
    url: 'claim/claim/reportSave',
    method: 'post',
    data
  })
}

// 被保人信息落库接口
export function insuredInfoSave(data) {
  return request({
    url: 'claim/claim/insuredInfoSave',
    method: 'post',
    data
  })
}

// 理赔查询核心数据
export function getCoreInfo(data) {
  return request({
    url: 'claim/claim/core/insuredInfoQuery',
    method: 'post',
    data
  })
}

// 查询地址信息
export function getAddress(data) {
  return request({
    url: 'claim/claim/base/getAddress',
    method: 'post',
    data
  })
}

// 查询报案详情信息
export function getRegisterInfo(data) {
  return request({
    url: 'claim/claim/getRegisterDetailInfo',
    method: 'post',
    data
  })
}

// 报案确认接口
export function registerConfirm(data) {
  return request({
    url: 'claim/claim/reportConfirm',
    method: 'post',
    data
  })
}

// 获取该被保人未关联报案数据
export function getNotRelationReport(data) {
  return request({
    url: 'claim/claim/getNotRelationReport',
    method: 'post',
    data
  })
}

// 获取该赔案关联报案数据
export function getRelationReport(data) {
  return request({
    url: 'claim/claim/getRelationReport',
    method: 'post',
    data
  })
}

// 关联报案
export function claimRelatedReport(data) {
  return request({
    url: 'claim/claim/claimRelatedReport',
    method: 'post',
    data
  })
}
// 分类完成
export function classifyFinish(data) {
  return request({
    url: 'claim/claim/input/classifyFinish',
    method: 'post',
    data
  })
}

// 查询理赔详情信息
export function getClaimInfo(data) {
  return request({
    url: 'claim/claim/getAcceptDetailInfo',
    method: 'post',
    data
  })
}

// 受理确认
export function acceptConfirm(data) {
  return request({
    url: 'claim/claim/acceptConfirm',
    method: 'post',
    data
  })
}

// 查询最大保额
export function getAmount(data) {
  return request({
    url: 'claim/claim/getMAxAmount',
    method: 'post',
    data
  })
}

// 查询费用项信息
export function getFeeitems(data) {
  return request({
    url: 'claim/claim/base/getFeeitems',
    method: 'post',
    data
  })
}

// 查询总行
export function getHeadBank(data) {
  return request({
    url: 'claim/claim/base/getHeadBank',
    method: 'post',
    data
  })
}

// 查询省
export function getProvince(data) {
  return request({
    url: 'claim/claim/base/getProvince',
    method: 'post',
    data
  })
}
// 查询市
export function getCity(data) {
  return request({
    url: 'claim/claim/base/getCity',
    method: 'post',
    data
  })
}
// 查询银行
export function getCategoryBank(data) {
  return request({
    url: 'claim/claim/base/getCategoryBank',
    method: 'post',
    data
  })
}

// 查询银行中文名
export function getBankName(data) {
  return request({
    url: 'claim/claim/base/getBankName',
    method: 'post',
    data
  })
}

// 查询关联报案数量
export function getCounts(data) {
  return request({
    url: 'claim/claim/getRelationReports',
    method: 'post',
    data
  })
}

// 处理批量影像上传的问题
export function imageDeal(data) {
  return request({
    url: 'claim/claim/esdocmainDeal',
    method: 'post',
    data
  })
}


// 校验该被保人是否存在未结案的案件
export function judgeClaim(data) {
  return request({
    url: 'claim/claim/judgeClaim',
    method: 'post',
    data
  })
}


// 校验该被保人是否存在未结案的案件
export function getInfo(data) {
  return request({
    url: 'claim/claim/getInfo',
    method: 'post',
    data
  })
}

// 保单号加密
export function des3Encode(data) {
  return request({
    url: 'claim/claim/des3PrivateKey',
    method: 'post',
    data
  })
}

// 查询该赔案关联的所有保单号
export function getPolicy(data) {
  return request({
    url: 'claim/claim/getPolicy',
    method: 'post',
    data
  })
}

// 查询保单号对应的险种
export function getRisk(data) {
  return request({
    url: 'claim/claim/getRisk',
    method: 'post',
    data
  })
}
// 保存受益人信息
export function bnfInfoSave(data) {
  return request({
    url: 'claim/claim/bnfInfoSave',
    method: 'post',
    data
  })
}
// 查询受益人信息
export function getBnfInfo(data) {
  return request({
    url: 'claim/claim/getBnf',
    method: 'post',
    data
  })
}

// 申请类别选择身故，点击申请人信息保存和受理确认校验是否录入了受益人信息
export function judgeBnf(data) {
  return request({
    url: 'claim/claim/judgeBnf',
    method: 'post',
    data
  })
}

// 校验案件是否存在未关联案件
export function judgeRegister(data) {
  return request({
    url: 'claim/claim/judgeRegister',
    method: 'post',
    data
  })
}

// 被保人五要素中有3个及3个以上的信息与系统中客户信息存在相同时，点击【保存】时，应非阻断弹框提示
export function judgeCustomer(data) {
  return request({
    url: 'claim/claim/judgeCustomer',
    method: 'post',
    data
  })
}


// 打印受理回执
export function getReceipt(data) {
  return request({
    url: 'claim/claim/getReceipt',
    method: 'post',
    data
  })
}

// 查询客户标签
export function getCustomerLabel(data) {
  return request({
    url: 'claim/claim/getCustomerLabel',
    method: 'post',
    data
  })
}

//删除受益人
export function deleteBnf(data) {
  return request({
    url: 'claim/claim/deleteBnf',
    method: 'post',
    data
  })
}


//删除受益人
export function deploy(data) {
  return request({
    url: 'claim/claim/deploy',
    method: 'post',
    data
  })
}
