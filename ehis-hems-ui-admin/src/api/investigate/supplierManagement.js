import request from '@/utils/request'

export function getList(data) {
  return request({
    url: 'investigate/invest/supplier/getList',
    method: 'post',
    data
  })
}
/**
 * 新增供应商保存
 */
export function addSupplier(data) {
  return request({
    url: 'investigate/invest/supplier/insertOne',
    method: 'post',
    data
  })
}
/**
 * 新增供应商 查询地址
 */
export function getAddress(data) {
  return request({
    url: 'investigate/invest/supplier/getAddress',
    method: 'post',
    data
  })
}
/**
 * 获取 总行 省 市 级联信息
 */
export function getHeadBankProvinceCity(data) {
  return request({
    url: 'investigate/invest/supplier/getProvinceCity',
    method: 'post',
    data
  })
}
/**
 * 获取 总行
 */
export function getHeadBank(data) {
  return request({
    url: 'investigate/invest/supplier/getHeadBank',
    method: 'post',
    data
  })
}
/**
 * 获取 省
 */
export function getProvince(data) {
  return request({
    url: 'investigate/invest/supplier/getProvince',
    method: 'post',
    data
  })
}
/**
 * 获取 市
 */
export function getCity(data) {
  return request({
    url: 'investigate/invest/supplier/getCity',
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
    url: 'investigate/invest/supplier/updateOne',
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
    url: 'investigate/invest/supplier/deleteContact',
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
    url: 'investigate/invest/supplier/deleteAccount',
    method: 'post',
    data
  })
}
export function getCategory(data) {
  return request({
    url: 'investigate/invest/supplier/getCategory',
    method: 'post',
    data
  })
}

export function getCategorySon(data) {
  return request({
    url: 'investigate/invest/supplier/getCategorySon',
    method: 'post',
    data
  })
}

export function getCategoryBank(data) {
  return request({
    url: 'investigate/invest/supplier/getCategoryBank',
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
    url: 'investigate/invest/contract/getList',
    method: 'post',
    data
  })
}


export function removeBySupplierCode(data) {
  return request({
    url: 'investigate/invest/supplier/removeBySupplierCode',
    method: 'post',
    data
  })
}
/*根据供应商信息查询合同信息*/
export function getBySupplierCodeInfo(data) {
  return request({
    url: 'investigate/invest/update/getBySypplierCode',
    method: 'post',
    data
  })

}
/*根据合同信息查询合同附件等信息*/
export function getByConstractCodeInfo(data) {
  return request({
    url: 'investigate/invest/update/getByConstractCode',
    method: 'post',
    data
  })
}
export function submitContractInfo(data) {
  return request({
    url: 'investigate/invest/contract/saveContract',
    method: 'post',
    data
  })
}
export function submitRuleInfo(data) {
  return request({
    url: 'investigate/invest/contract/saveRule',
    method: 'post',
    data
  })
}
export function getContractAndSupplierInfo(data) {
  return request({
    url: 'investigate/invest/contract/getContractAndSupplierInfo',
    method: 'post',
    data
    // params:{
    //   suppliercode:suppliercode
    // }
  })
}
/*修改合同信息*/
export function updateConstract(data) {
  return request({
    url: 'investigate/invest/contract/update',
    method: 'post',
    data
  })

}
/*修改规则*/
export function updateBusiness(data) {
  return request({
    url: 'investigate/invest/business/update',
    method: 'post',
    data
  })

}
/*增加时效*/
export function limitationAdd(data) {
  return request({
    url: 'investigate/invest/update/limitationAdd',
    method: 'post',
    data
  })
}
/*删除时效*/
export function deletelimition(data) {
  return request({
    url: 'investigate/invest/update/limitationDelete',
    method: 'post',
    data
  })

}

/*供应商编辑查询*/
export function getSupplierContactAccountInfo(data) {
  return request({
    url: 'investigate/invest/supplier/getBySupplierCode',
    method: 'post',
    data
  })
}
/*合同附件上传*/
export function uploadContractAnnex(data) {
  return request({
    url: 'investigate/invest/contract/uploadContractAnnex',
    method: 'post',
    data
  })
}
// 下载文件
export function downloadFile(data) {
  return request({
    url: 'investigate/invest/contract/downloadContractAnnex',
    method: 'post',
    data,
    responseType: 'blob',
    timeout: 1000 * 60 * 60 * 3 ,
  })
}
// 合同编辑删除附件
export function deleteAttamentFile(data) {
  return request({
    url: 'investigate/invest/update/attemDelete',
    method: 'post',
    data,
  })
}
export function deleteContractAnnex(data) {
  return request({
    url: 'investigate/invest/contract/deleteContractAnnex',
    method: 'post',
    data,
  })
}
export function getSurveyTaskno(data) {
  return request({
    url: 'investigate/invest/contract/getSurveyTaskno',
    method: 'post',
    data
  })
}
