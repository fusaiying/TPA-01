import request from '@/utils/request'

/** 查询审核列表 **/
export function queryAuditListApprove(data) {
  return request({
    url: '/baseinfo/claim/SocialInsuranceCatalogue/querySocialInsuranceCatalogueApprove',
    method: 'post',
    data
  })
}

/** 退回审核 **/
export function rejecAudit(data) {
  return request({
    url: '/baseinfo/claim/SocialInsuranceCatalogue/updateSocialInsuranceConclusionFalse',
    method: 'post',
    data
  })
}

/** 通过审核 **/
export function Approved(data) {
  return request({
    url: '/baseinfo/claim/SocialInsuranceCatalogue/updateSocialInsuranceConclusionTrue',
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
