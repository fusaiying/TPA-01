import request from '@/utils/request'


// 查询 供应商合约信息 列表
export function getSupplierContractList(query) {
  return request({
    url:  '/provider/supplier/list',
    method: 'get',
    params: query
  })
}

// 查询 供应商合约信息 详情
export function getSupplierContractDetail(contractNo) {
  return request({
    url:  '/provider/supplier/' + contractNo,
    method: 'get',
  })
}

//新增base_supplier_contract（供应商合约）
export function addSupplierContract(data) {
  return request({
    url: '/provider/supplier',
    method: 'post',
    data
  })
}
// 查询 供应商合约信息 详情
export function updateSupplierContract(data) {
  return request({
    url:  '/provider/supplier/',
    method: 'put',
     data

  })
}

// 查询 供应商服务项目 列表
export function getContractServerList(query) {
  return request({
    url:  '/provider/service/list',
    method: 'get',
    params: query
  })
}

// 删除 供应商服务项目
export function deleteContractServer(serialNo) {
  return request({
    url:  '/provider/service/once/'+ serialNo,
    method: 'delete',
  })
}

// 新增 供应商服务项目
export function addContractServer(listDto) {
  return request({
    url:  '/provider/service/addList',
    method: 'POST',
    data:listDto
  })
}

// 查询 （合约附件）列表
export function getFileList(query) {
  return request({
    url:  '/provider/annex/list',
    method: 'get',
    params: query
  })
}

// 所有供应商列表
export function getAllBaseSupplierInfo(query) {
  return request({
    url:  '/provider/info/allList',
    method: 'get',
    params: query
  })
}

// 所有服务机构列表
export function getAllBaseProviderInfo(query) {
  return request({
    url:  '/provider/org/allList',
    method: 'post',
    params: query
  })
}


// 所有服务项目列表
export function getAllBaseServiceInfo(query) {
  return request({
    url:  '/provider/projectInfo/allList',
    method: 'get',
    params: query
  })
}

// 查询 服务机构合约 列表
export function getSupplierContractBakDetail(query) {
  return request({
    url:  '/provider/bak/list',
    method: 'get',
    params: query
  })
}
//上传附件
export function uploadFile(data) {
  // return request({
  //   url: 'claim/file/claim/supplier/uploadContractAnnex',
  //   method: 'post',
  //   data
  // })
}
//下载附件
export function downloadFile(data) {
  // return request({
  //   url: 'file/file/claim/supplier/downloadContractAnnex',
  //   method: 'post',
  //   data,
  //   responseType: 'blob',
  //   timeout: 1000 * 60 * 60 * 3 ,
  // })
}
//删除附件
export function deleteFile(data) {
  // return request({
  //   url: 'claim/claim/supplier/attemDelete',
  //   method: 'post',
  //   data,
  // })
}
