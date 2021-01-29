import request from '@/utils/request'


//查询案件归档主页面
export function caseFilingList(query) {
  return request({
    url: '/system/casefiling/caseFilingList',
    method: 'get',
    params: query
  })
}


//改变是否销毁状态
export function editDestroy(data) {
  return request({
    url: '/system/casefiling/editDestroy',
    method: 'post',
    data
  })
}


//新增案件归档
export function addInfo(data) {
  return request({
    url: '/system/casefiling/add',
    method: 'post',
    data: data
  })
}


//编辑按钮
export function editCaseFiling(data) {
  return request({
    url: '/system/casefiling/editCaseFiling',
    method: 'post',
    data: data
  })
}

//查询案件归档详细信息
export function getInfoCaseFilingList(query) {
  return request({
    url: '/system/casefiling/getInfoCaseFilingList',
    method: 'get',
    params: query
  })
}

//保存案件归档详细信息
export function updateCaseFilingInfo(data) {
  return request({
    url: '/system/casefiling/updateCaseFilingInfo',
    method: 'post',
    data
  })
}




/*

export function listInfo(query) {
  return request({
    url: '/system/whitelist/list',
    method: 'get',
    params: query
  })
}

// 新增/修改
export function editData(data) {
  return request({
    url: '/system/whitelist/add',
    method: 'post',
    data
  })
}


// 删除
export function debtWhiteInfo(debtWhitelistId) {
  return request({
    url:  '/system/whitelist/remove/'+ debtWhitelistId,
    method: 'delete',
  })
}

//是否有欠款
export function checkMoney(query) {
  return request({
    url:  '/system/whitelist/residualList',
    method: 'get',
    params: query
  })
}
*/
