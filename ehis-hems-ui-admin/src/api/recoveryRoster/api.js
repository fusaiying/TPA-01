import request from '@/utils/request'



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
