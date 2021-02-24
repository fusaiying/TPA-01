import request from '@/utils/request'

// 查询base_supplier_info（供应商基础信息）列表30天
export function listInfo(query) {
  return request({
    url: '/provider/info/list',
    method: 'post',
    data: query
  })
}
/*// 查询
export function getSupplierInfo(query) {
  return request({
    url: '/provider/info/list2',
    method: 'get',
    params: query
  })
}*/

// 查询base_supplier_info（供应商基础信息）列表
export function getListInfo(query) {
  return request({
    url: '/provider/info/list1',
    method: 'post',
    data: query
  })
}

/**
 * 新增供应商 查询地址
 */
export function getAddress() {
  return request({
    url: 'provider/org/getAddress',
    method: 'post',
  })
}

// 查询base_supplier_info（供应商基础信息）详细
export function getInfo(serialNo) {
  return request({
    url: '/provider/info/' + serialNo,
    method: 'get'
  })
}

// 查询base_supplier_receipt(供应商开票信息)详细
export function getReceip(supplierCode) {
  return request({
    url: '/provider/receip/' + supplierCode,
    method: 'get'
  })
}

// 查询base_contacts（联系人信息）列表
export function listContacts(query) {
  return request({
    url: '/provider/contacts/list',
    method: 'get',
    params: query
  })
}

// 查询base_bank（银行信息）列表
export function listBank(query) {
  return request({
    url: '/provider/bank/list1',
    method: 'get',
    params: query
  })
}

// 查询base_provider_info（息服务商基本信）列表
export function listProviderInfo(query) {
  return request({
    url: '/provider/outlets/list1',
    method: 'get',
    params: query
  })
}

// 保存所有
export function saveAll(data) {
  return request({
    url: '/provider/provider1/add',
    method: 'post',
    data: data
  })
}

// 保存联系人
export function saveContacts(data) {
  return request({
    url: '/provider/contacts/save',
    method: 'post',
    data: data
  })
}
// 保存账户
export function saveBank(data) {
  return request({
    url: '/provider/bank/save',
    method: 'post',
    data: data
  })
}
// 服务机构查询
export function getService(data) {
  return request({
    url: '/provider/org/getlist1',
    method: 'post',
    data: data
  })
}

// 服务机构确认
export function saveService(data) {
  return request({
    url: '/provider/outlets',
    method: 'post',
    data: data
  })
}

// 服务机构删除
export function deleteService(data) {
  return request({
    url: '/provider/outlets/updatestatus',
    method: 'put',
    data: data
  })
}
// 服务机构删除
export function addInfo(data) {
  return request({
    url: '/provider/info/add',
    method: 'post',
    data: data
  })
}

// 联系人删除
export function deleteContacts(data) {
  return request({
    url: '/provider/contacts/delete',
    method: 'post',
    data: data
  })
}
