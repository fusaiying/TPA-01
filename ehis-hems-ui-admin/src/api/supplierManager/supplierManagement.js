import request from '@/utils/request'

export function getList(data) {
  return request({
    url: 'claim/claim/supplier/getList',
    method: 'post',
    data
  })
}
/**
 * 新增供应商保存
 */
export function addSupplier(data) {
  return request({
    url: 'claim/claim/supplier/insertOne',
    method: 'post',
    data
  })
}
/**
 * 新增供应商 查询地址
 */
export function getAddress(data) {
  return request({
    url: 'claim/claim/supplier/getAddress',
    method: 'post',
    data
  })
}
/**
 * 获取 总行 省 市 级联信息
 */
export function getHeadBankProvinceCity(data) {
  return request({
    url: 'claim/claim/supplier/getProvinceCity',
    method: 'post',
    data
  })
}
/**
 * 获取 总行
 */
export function getHeadBank(data) {
  return request({
    url: 'claim/claim/supplier/getHeadBank',
    method: 'post',
    data
  })
}
/**
 * 获取 省
 */
export function getProvince(data) {
  return request({
    url: 'claim/claim/supplier/getProvince',
    method: 'post',
    data
  })
}
/**
 * 获取 市
 */
export function getCity(data) {
  return request({
    url: 'claim/claim/supplier/getCity',
    method: 'post',
    data
  })
}
/**
 * 供应商修改
 * @param data
 */
export function updateOne(data) {
  return request({
    url: 'claim/claim/supplier/updateOne',
    method: 'post',
    data
  })
}

/**
 * 供应商修改-删除联系人
 * @param data
 */
export function deleteContact(data) {
  return request({
    url: 'claim/claim/supplier/deleteContact',
    method: 'post',
    data
  })
}
/**
 * 供应商修改-删除银行账户
 * @param data
 */
export function deleteAccount(data) {
  return request({
    url: 'claim/claim/supplier/deleteAccount',
    method: 'post',
    data
  })
}
export function getCategory(data) {
  return request({
    url: 'claim/claim/supplier/getCategory',
    method: 'post',
    data
  })
}

export function getCategorySon(data) {
  return request({
    url: 'claim/claim/supplier/getCategorySon',
    method: 'post',
    data
  })
}

export function getCategoryBank(data) {
  return request({
    url: 'claim/claim/supplier/getCategoryBank',
    method: 'post',
    data
  })
}

/**
 * 合同列表查询
 * @param data
 */
export function getContractTableInfo(data) {
  return request({
    url: 'claim/claim/supplier/contract/getList',
    method: 'post',
    data
  })
}


export function removeBySupplierCode(data) {
  return request({
    url: 'claim/claim/supplier/removeBySupplierCode',
    method: 'post',
    data
  })
}
/*根据供应商信息查询合同信息*/
export function getBySupplierCodeInfo(data) {
  return request({
    url: 'claim/claim/supplier/getBySypplierCode',
    method: 'post',
    data
  })

}
/*根据合同信息查询合同附件等信息*/
export function getByConstractCodeInfo(data) {
  return request({
    url: 'claim/claim/supplier/contract/getByConstractCode',
    method: 'post',
    data
  })
}
export function submitContractInfo(data) {
  return request({
    url: 'claim/claim/supplier/contract/saveContract',
    method: 'post',
    data
  })
}
export function getContractAndSupplierInfo(data) {
  return request({
    url: 'claim/claim/supplier/contract/getContractAndSupplierInfo',
    method: 'post',
    data
  })
}
/*修改合同信息*/
export function updateConstract(data) {
  return request({
    url: 'claim/claim/supplier/contract/update',
    method: 'post',
    data
  })

}
/*修改规则*/
export function updateBusiness(data) {
  return request({
    url: 'claim/claim/supplier/update',
    method: 'post',
    data
  })

}
/*增加时效*/
export function limitationAdd(data) {
  return request({
    url: 'claim/claim/supplier/limitationAdd',
    method: 'post',
    data
  })
}
/*删除时效*/
export function deletelimition(data) {
  return request({
    url: 'claim/claim/supplier/limitationDelete',
    method: 'post',
    data
  })

}

/*供应商编辑查询*/
export function getSupplierContactAccountInfo(data) {
  return request({
    url: 'claim/claim/supplier/getBySupplierCode',
    method: 'post',
    data
  })
}
/*合同附件上传*/
export function uploadContractAnnex(data) {
  return request({
    url: 'claim/file/claim/supplier/uploadContractAnnex',
    method: 'post',
    data
  })
}
// 下载文件
export function downloadFile(data) {
  return request({
    url: 'file/file/claim/supplier/downloadContractAnnex',
    method: 'post',
    data,
    responseType: 'blob',
    timeout: 1000 * 60 * 60 * 3 ,
  })
}
// 合同编辑删除附件
export function deleteAttamentFile(data) {
  return request({
    url: 'claim/claim/supplier/attemDelete',
    method: 'post',
    data,
  })
}
export function deleteContractAnnex(data) {
  return request({
    url: 'file/file/claim/supplier/deleteContractAnnex',
    method: 'post',
    data,
  })
}
export function getSurveyTaskno(data) {
  return request({
    url: 'claim/claim/supplier/getSurveyTaskno',
    method: 'post',
    data
  })
}
// 查询base_supplier_info（供应商基础信息）列表
export function listInfo(query) {
  return request({
    url: '/provider/info/list',
    method: 'get',
    params: query
  })
}
