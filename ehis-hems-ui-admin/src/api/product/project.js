import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/sinoutils";

// 查询服务项目列表
export function listProjectInfo(query) {
  return request({
    url: '/product/project/list',
    method: 'get',
    params: query
  })
}

// 删除服务项目
export function delProjectInfo(projectcodes) {
  return request({
    url: '/product/project/'+projectcodes,
    method: 'delete'
  })
}

// 保存服务项目
export function saveProject(projectNodeList) {
  return request({
    url: '/product/project/saveProject',
    method: 'post',
    data: projectNodeList
  })
}

// 初始化服务项目
export function queryProjectData(projectcode) {
  return request({
    url: '/product/project/queryProjectData',
    method: 'post',
    data: projectcode
  })
}

// 查询服务项目下的服务机构
export function getServiceOrgList(query){
  return request({
    url: '/product/project/getServiceOrgList',
    method: 'get',
    params: query,
  })
}
