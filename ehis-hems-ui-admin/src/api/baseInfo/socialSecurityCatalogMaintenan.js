import request from '@/utils/request'

/** 查询补码列表 **/
export function queryAuditList(data) {
  return request({
    url: '/baseinfo/claim/SocialInsuranceCatalogue/querySocialInsuranceCatalogue',
    method: 'post',
    data
  })
}
/** 查询审核列表 **/
export function queryAuditListApprove(data) {
  return request({
    url: '/baseinfo/claim/SocialInsuranceCatalogue/querySocialInsuranceCatalogueApprove',
    method: 'post',
    data
  })
}

/** 新增社保信息 **/
export function insertSocialInsurance(data) {
  return request({
    url: '/baseinfo//claim/SocialInsuranceCatalogue/insertSocialInsuranceCatalogue',
    method: 'post',
    data
  })
}

/** 修改社保信息 **/
export function updateSocialInsurance(data) {
  return request({
    url: '/baseinfo//claim/SocialInsuranceCatalogue/updateSocialInsuranceCatalogue',
    method: 'post',
    data
  })
}

/** 删除社保信息 **/
export function deleteSocialInsurance(data) {
  return request({
    url: '/baseinfo//claim/SocialInsuranceCatalogue/deleteSocialInsuranceCatalogue',
    method: 'post',
    data
  })
}

/** 查询社保明细 **/
export function querySocialInsuranceCatalogueDetailed(data) {
  return request({
    url: '/baseinfo/claim/SocialInsuranceCatalogue/querySocialInsuranceCatalogueDetailed',
    method: 'post',
    data
  })
}

/** 查询省份列表 **/
export function queryProvince(data) {
  return request({
    url: '/baseinfo/claim/address/getProvince',
    method: 'post',
    data
  })
}

