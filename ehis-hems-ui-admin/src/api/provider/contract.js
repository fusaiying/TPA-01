import request from '@/utils/request'

// 查询供应商合约列表
export function listContract(query) {
  return request({
    url: '/provider/contract/list',
    method: 'get',
    params: query
  })
}

// 查询供应商合约详细
export function getContract(contractno) {
  return request({
    url: '/provider/contract/' + contractno,
    method: 'get'
  })
}

// 新增供应商合约
export function addContract(form, serviceInfo) {
  return request({
    url: '/provider/contract',
    method: 'post',
    data: {hmpContract: form, hmpServConpro: serviceInfo}
  })
}

// 修改供应商合约
export function updateContract(form, serviceInfo) {
  return request({
    url: '/provider/contract',
    method: 'put',
    data: {hmpContract: form, hmpServConpro: serviceInfo},
  })
}

// 删除供应商合约
export function delContract(contractno) {
  return request({
    url: '/provider/contract/' + contractno,
    method: 'delete'
  })
}

//查询合约对象编码
export function getContractObj() {
  return request({
    url: '/provider/contract/getContractObj',
    method: 'get'
  })
}
// 查询即将到期合约
export function getExpireContract() {
  return request({
    url: '/provider/contract/getExpireContract',
    method: 'get'
  })
}
// 根据合约名称查询合约对象
export function getContractByName(contractname) {
  return request({
    url: '/provider/contract/getContractByName/' + contractname,
    method: 'get'
  })
}

// 查询服务项目下拉框  调用Product产品微服务的接口
export function selectHmpServProjectOptions() {
  return request({
    url: '/product/project/selectOptions',
    method: 'get'
  })
}

// 根据合约编码查询服务项目
export function getConPro(contactno) {
  return request({
    url: '/provider/contract/conpro/' + contactno,
    method: 'get'
  })
}

// 根据合约编码查询服务项目
export function getFileByContractNo(contactno) {
  return request({
    url: '/provider/contract/conpro/' + contactno,
    method: 'get'
  })
}


