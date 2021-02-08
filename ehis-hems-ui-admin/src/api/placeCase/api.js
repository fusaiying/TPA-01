import request from '@/utils/request'


//查询案件归档主页面
export function caseFilingList(data) {
  return request({
    url: '/claimflow/casefiling/caseFilingList',
    method: 'post',
    data: data
  })
}


//改变是否销毁状态
export function editDestroy(data) {
  return request({
    url: '/claimflow/casefiling/editDestroy',
    method: 'post',
    data
  })
}


//新增案件归档
export function addInfo(data) {
  return request({
    url: '/claimflow/casefiling/add',
    method: 'post',
    data: data
  })
}


//编辑按钮
export function editCaseFiling(data) {
  return request({
    url: '/claimflow/casefiling/editCaseFiling',
    method: 'post',
    data: data
  })
}

//查询案件归档详细信息
export function getInfoCaseFilingList(query) {
  return request({
    url: '/claimflow/casefiling/getInfoCaseFilingList',
    method: 'post',
    data: query
  })
}

//保存案件归档详细信息
export function updateCaseFilingInfo(data) {
  return request({
    url: '/claimflow/casefiling/updateCaseFilingInfo',
    method: 'post',
    data
  })
}


//模糊查询部门
export function getDepts(data) {
  return request({
    url:'system/dept/list',
    method:'get',
    data:data
  })
}
