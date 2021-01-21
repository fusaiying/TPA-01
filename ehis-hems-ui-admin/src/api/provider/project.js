import request from '@/utils/request'

// 查询服务项目类别
export function getCategoryType() {
  return request({
    url: '/provider/projectInfo/getCategoryType',
    method: 'get'
  })
}

// 查询类别下的种类
export function getServerType(query) {
  return request({
    url: '/provider/projectInfo/getServerType',
    method: 'get',
    params: {category: query}
  })
}

// 查询种类名称
export function getServerTypeName(query) {
  return request({
    url: '/provider/projectInfo/getServerTypeName',
    method: 'get',
    params: query,
  })
}

// 查询服务项目列表
export function listProjectInfo(query) {
  return request({
    url: '/provider/projectInfo/list',
    method: 'get',
    params: query
  })
}

// 删除服务项目
export function delProjectInfo(servicecode) {
  return request({
    url: '/provider/projectInfo/' + servicecode,
    method: 'delete'
  })
}

// 保存服务项目信息
export function saveProjectInfo(data) {
  return request({
    url: '/provider/projectInfo/add',
    method: 'post',
    data: data
  })
}

// 查询服务项目详细信息
export function getProjectById(servicecode) {
  return request({
    url: '/provider/projectInfo/' + servicecode,
    method: 'get'
  })
}

// 查询服务项目流程配置信息
export function getServiceProcess(servicecode) {
  return request({
    url: '/provider/projectInfo/getServiceProcess',
    method: 'get',
    params: {servicecode: servicecode}
  })
}

// 存储服务项目流程配置
export function saveServiceProcess(servicecode,status,processList,fieldList) {
  return request({
    url: '/provider/projectInfo/saveServiceProcess',
    method: 'post',
    data: {servicecode:servicecode,status:status,processList,fieldList}
  })
}
