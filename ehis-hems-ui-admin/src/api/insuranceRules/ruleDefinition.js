import request from '@/utils/request'

/** 获取公共池列表 **/
export function getPublicList(data) {
  return request({
    url: '/calculation/claim/rule/getPublicList',
    method: 'post',
    data
  })
}

/** 获取全部险种编码 **/
export function getAllRiskList(data) {
  return request({
    url: '/calculation/claim/rule/getAllRiskCode',
    method: 'post',
    data
  })
}

/** 公共池申请 **/
export function bacthApply(data) {
  return request({
    url: '/calculation/claim/rule/apply',
    method: 'post',
    data
  })
}

/** 获取个人池列表 **/
export function getPersonList(data) {
  return request({
    url: '/calculation/claim/rule/getPersonList',
    method: 'post',
    data
  })
}

/** 获取险种信息 **/
export function getInsuranceInfo(data) {
  return request({
    url: '/calculation/claim/rule/getInsuranceInfo',
    method: 'post',
    data
  })
}

/** 获取规则信息 **/
export function getRules(data) {
  return request({
    url: '/calculation/claim/rule/getRule',
    method: 'post',
    data
  })
}

/** 修改责任信息 **/
export function updateDutyList(data) {
  return request({
    url: '/calculation/claim/rule/updateDuty',
    method: 'post',
    data
  })
}

/** 查询有效费用项 **/
export function getAllFeeitem(data) {
  return request({
    url: '/calculation/claim/rule/getAllFeeitem',
    method: 'post',
    data
  })
}

/** 修改规则信息 **/
export function updateRule(data) {
  return request({
    url: '/calculation/claim/rule/updateRule',
    method: 'post',
    data
  })
}

/** 插入规则信息 **/
export function insertRule(data) {
  return request({
    url: '/calculation/claim/rule/insertRule',
    method: 'post',
    data
  })
}

/** 审核环节批量申请 **/
export function updatePublicRisk(data) {
  return request({
    url: '/calculation/claim/riskApproval/updatePublicRisk',
    method: 'post',
    data
  })
}

/** 修改规则信息 **/
export function updatePublicRiskConclusion(data) {
  return request({
    url: '/calculation//claim/riskApproval/updatePublicRiskConclusion',
    method: 'post',
    data
  })
}

/** 规则重定 **/
export function updatePublicRiskReturn(data) {
  return request({
    url: '/calculation//claim/riskApproval/updatePublicRiskReturn',
    method: 'post',
    data
  })
}

/** 发起审核 **/
export function updateRiskSubmit(data) {
  return request({
    url: '/calculation//claim/riskApproval/updateRiskSubmit',
    method: 'post',
    data
  })
}
/** 删除规则信息 **/
export function deleteRule(data) {
  return request({
    url: '/calculation/claim/rule/deleteRule',
    method: 'post',
    data
  })
}
/** 查询特殊医院 **/
export function getMedicalInstitutionByName(data) {
  return request({
    url: '/calculation/claim/rule/getMedicalInstitutionByName',
    method: 'post',
    data
  })
}

/** 查询免责规则 **/
export function getExemption(data) {
  return request({
    url: '/calculation/claim/rule/exemption/getExemption',
    method: 'post',
    data
  })
}

/** 插入免责规则 **/
export function addExemption(data) {
  return request({
    url: '/calculation/claim/rule/exemption/addExemption',
    method: 'post',
    data
  })
}

/** 删除免责规则 **/
export function deleteExemption(data) {
  return request({
    url: '/calculation/claim/rule/exemption/deleteExemption',
    method: 'post',
    data
  })
}

/** 疾病类型查询免责名称 **/
export function getExemptionInfoByName(data) {
  return request({
    url: '/calculation/claim/rule/exemption/accident/getInfoByName',
    method: 'post',
    data
  })
}
/** 诊断类型查询免责名称 **/
export function getLikeICD10(data) {
  return request({
    url: '/calculation/claim/rule/exemption/getLikeICD10',
    method: 'post',
    data
  })
}

