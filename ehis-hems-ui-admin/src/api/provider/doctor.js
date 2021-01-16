import request from '@/utils/request'

// 查询base_org_doc(机构医生信息）列表
export function listDoc(query) {
  return request({
    url: '/provider/docinfo/list',
    method: 'get',
    params: query
  })
}

// 删除多个医生信息
export function delDocList(docCodes) {
  return request({
    url: '/provider/docinfo/' + docCodes,
    method: 'delete'
  })
}

// 单个删除医生信息
export function delDoc(docCode) {
  return request({
    url: '/provider/docinfo/oneDoc/' + docCode,
    method: 'delete'
  })
}

// 根据医生编码获取医生信息
export function getDoctorInfo(docCode) {
  return request({
    url: '/provider/docinfo/' + docCode,
    method: 'get'
  })
}

// 查询所属服务机构全部
export function getSupplierOptions() {
  return request({
    url: '/provider/docinfo/dataList',
    method: 'get'
  })
}

// 保存医生信息
export function saveDoctor(data) {
  return request({
    url: '/provider/docinfo/add',
    method: 'post',
    data: data
  })
}

// 查询近30天医生信息
export function nearDoc(query) {
  return request({
    url: '/provider/docinfo/month',
    method: 'get',
    params: query
  })
}
