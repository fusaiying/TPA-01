import request from '@/utils/request'
let sapi = 'claim'
/** 作业组管理（首页）查询列表 **/
export function getGroupWorkList(data) {
  return request({
    url: `${sapi}/claim/claimGroup/queryOperationgroups`,
    method: 'post',
    data
  })
}
/** 作业组管理（首页）新增作业组 **/
export function addGroupWork(data) {
  return request({
    url: `${sapi}/claim/claimGroup/insertOperationgroups`,
    method: 'post',
    data
  })
}
/** 作业组管理（首页）获取作业组组号 **/
export function getGroupNo(data) {
  return request({
    url: `${sapi}/claim/claimGroup/getOperationgroupno`,
    method: 'get',
    params:{
      notype: data
    }
  })
}
/** 作业组管理（首页）获取所属机构下操作用户的上级机构 **/
export function getUsercode(data) {
  return request({
    url: `${sapi}/claim/claimGroup/getUpperUsercode`,
    method: 'get',
    data
  })
}
/** 作业组管理（人员维护页）获取人员维护列表 **/
export function getGroupUsers(data) {
  return request({
    url: `${sapi}/claim/claimGroup/queryOperationgroupUsers`,
    method: 'post',
    data
  })
}
/** 作业组管理（人员维护页）获取人员维护列表新增 **/
export function addGroupUsers(data) {
  return request({
    url: `${sapi}/claim/claimGroup/insertOperationgroupUsers`,
    method: 'post',
    data
  })
}
/** 作业组管理（流转权限页）获取权限列表 **/
export function getGroupLimitations(data) {
  return request({
    url: `${sapi}/claim/claimGroup/queryOperationgroupLimitations`,
    method: 'post',
    data
  })
}
/** 作业组管理（流转权限页）权限列表新增 **/
export function addGroupLimitations(data) {
  return request({
    url: `${sapi}/claim/claimGroup/insertOperationgroupLimitations`,
    method: 'post',
    data
  })
}
/** 作业组管理（案件分配页）获取列表 **/
export function getRouterulesss(data) {
  return request({
    url: `${sapi}/claim/claimGroup/queryOperationgroupRouteruless`,
    method: 'post',
    data
  })
}
/** 作业组管理（案件分配页）获取列表新增 **/
export function addRouterulesss(data) {
  return request({
    url: `${sapi}/claim/claimGroup/insertOperationgroupRouteruless`,
    method: 'post',
    data
  })
}
/** 作业组管理 查询当前操作机构下操作用户的上级用户 **/
export function queryUpperUsercode(data) {
  return request({
    url: `${sapi}/claim/claimGroup/queryUpperUsercode`,
    method: 'get',
    data
  })
}
/** 作业组管理 查询保险公司下的所属机构 **/
export function queryBSMangecom(data) {
  return request({
    url: `${sapi}/claim/claimGroup/queryBSManagecom`,
    method: 'post',
    data
  })
}
/** 作业组管理 查TPA下的所属机构 **/
export function queryTPAMangecom(data) {
  return request({
    url: `${sapi}/claim/claimGroup/queryTPAManagecom`,
    method: 'post',
    data
  })
}
/** 作业组管理 查询操作用户 **/
export function queryUser(data) {
  return request({
    url: `${sapi}/claim/claimGroup/queryComcodeUserDetail`,
    method: 'post',
    data
  })
}
/** 作业组管理 删除一条 **/
export function delateOne(data) {
  return request({
    url: `${sapi}/claim/claimGroup/deleteOperationgroupUser`,
    method: 'post',
    data
  })
}
/** 作业组管理 查询操作用户 **/
export function queryCoreRiskCodeDetaill(data) {
  return request({
    url: `${sapi}/claim/claimGroup/queryCoreRiskCodeDetail`,
    method: 'post',
    data
  })
}

/** 作业组管理 查询操作用户上级用户 **/
export function queryComcodeUserUpperDetail(data) {
  return request({
    url: `${sapi}/claim/claimGroup/queryComcodeUserUpperDetail`,
    method: 'post',
    data
  })
}
/** 作业组管理 查询任务轨迹 **/
export function queryUserTrajectory(data) {
  return request({
    url: `${sapi}/claim/claimGroup/queryUserTrajectory`,
    method: 'post',
    data
  })
}

/** 作业组时效阻断 **/
export function queryWorkGroupTrajectory(data) {
  return request({
    url: `${sapi}/claim/claimGroup/queryWorkGroupTrajectory`,
    method: 'post',
    data
  })
}

/** 作业组删除 **/
export function deleteOperationgroups(data) {
  return request({
    url: `${sapi}/claim/claimGroup/deleteOperationgroups`,
    method: 'post',
    data
  })
}


