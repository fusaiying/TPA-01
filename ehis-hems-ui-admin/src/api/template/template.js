import request from '@/utils/request'
const sapi = 'claim'
/** 模板查询 **/
export function getTemplate(data) {
  return request({
    url: `${sapi}/claim/template/getTemplate`,
    method: 'post',
    data
  })
}
/** 增加结论模板 **/
export function addConclusionTemplate(data) {
  return request({
    url: `${sapi}/claim/template/addConclusionTemplate`,
    method: 'post',
    data
  })
}
/** 增加补材模板 **/
export function addSupplementTemplate(data) {
  return request({
    url: `${sapi}/claim/template/addSupplementTemplate`,
    method: 'post',
    data
  })
}

/** 增加撤案模板 **/
export function addWithdrawnTemplate(data) {
  return request({
    url: '/claim/claim/template/addWithdrawnTemplate',
    method: 'post',
    data
  })
}

/** 增加不予受理模板 **/
export function addNotAccepteTemplate(data) {
  return request({
    url: '/claim/claim/template/addNotAccepteTemplate',
    method: 'post',
    data
  })
}

/** 增加模板 **/
export function configTemplate(data) {
  return request({
    url: 'claim/claim/template/configTemplate',
    method: 'post',
    data
  })
}

/** 查询险种 **/
export function getRiskDutyDetail(data) {
  return request({
    url: '/claim/claim/template/getRiskDutyDetail',
    method: 'post',
    data
  })
}

/** 查询模板 **/
export function queryTemplate(data) {
  return request({
    url: '/claim/claim/template/queryTemplate',
    method: 'post',
    data
  })
}

/** 删除模板 **/
export function deleteTemplate(data) {
  return request({
    url: '/claim/claim/template/deleteTemplate',
    method: 'post',
    data
  })
}
