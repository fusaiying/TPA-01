import request from '@/utils/request'

//服务机构列表查询
export function getList(data) {
  return request({
    url: '/provider/org/getList',
    method: 'get',
    params: data
  })
}

//基本信息查询
export function getInfo(data) {
  return request({
    url: '/provider/org/getInfo',
    method: 'post',
    data
  })
}

//新增基本信息
export function addInfo(data) {
  return request({
    url: '/provider/org/addInfo',
    method: 'post',
    data
  })
}

//基本信息修改
export function updateInfo(data) {
  return request({
    url: '/provider/org/updateInfo',
    method: 'post',
    data
  })
}

//科室信息查询
export function getdepInfo(data) {
  return request({
    url: '/provider/org/getdepInfo',
    method: 'post',
    data
  })
}

//科室信息新增与修改
export function adddepInfo(data) {
  return request({
    url: '/provider/org/adddepInfo',
    method: 'post',
    data
  })
}

//服务信息查询
export function getserviceInfo(data) {
  return request({
    url: '/provider/org/getserviceInfo',
    method: 'post',
    data
  })
}

//服务信息新增
export function addserviceInfo(data) {
  return request({
    url: '/provider/org/addserviceInfo',
    method: 'post',
    data
  })
}
//服务信息修改
export function updateserviceInfo(data) {
  return request({
    url: '/provider/org/updateserviceInfo',
    method: 'post',
    data
  })
}

//预约信息查询
export function getapplyInfo(data) {
  return request({
    url: '/provider/org/getapplyInfo',
    method: 'post',
    data
  })
}

//预约信息新增
export function addapplyInfo(data) {
  return request({
    url: '/provider/org/addapplyInfo',
    method: 'post',
    data
  })
}

//预约信息修改
export function updateapplyInfo(data) {
  return request({
    url: '/provider/org/updateapplyInfo',
    method: 'post',
    data
  })
}

//联系信息查询
export function getcontactsInfo(data) {
  return request({
    url: '/provider/org/getcontactsInfo',
    method: 'post',
    data
  })
}

//联系信息新增 修改
export function addcontactsInfo(data) {
  return request({
    url: '/provider/org/addcontactsInfo',
    method: 'post',
    data
  })
}

//结算信息查询
export function getbankInfo(data) {
  return request({
    url: '/provider/org/getbankInfo',
    method: 'post',
    data
  })
}

//结算信息新增修改
export function addbankInfo(data) {
  return request({
    url: '/provider/org/addbankInfo',
    method: 'post',
    data
  })
}

/**
 * 查询地址
 */
export function getAddress(data) {
  return request({
    url: 'provider/org/getAddress',
    method: 'post',
    data
  })
}

/**
 * 提交审核
 */
export function checkUp(data) {
  return request({
    url: 'provider/org/checkUp',
    method: 'post',
    data
  })
}
//验证重名
export function checkfield(data) {
  return request({
    url: 'provider/org/checkfield',
    method: 'post',
    data
  })
}
//查询医疗网络类型
export function getNewtworktypeList(data) {
  return request({
    url: 'provider/org/getNewtworktypeList',
    method: 'post',
    data: data
  })
}
//修改医疗网络类型
export function updateNewtworktypeList(data) {
  return request({
    url: 'provider/org/updateNewtworktypeList',
    method: 'post',
    data: data
  })
}

//查询医疗网络记录
export function getNewtworktypeLogList(data) {
  return request({
    url: 'provider/org/getNewtworktypeLogList',
    method: 'get',
    params: data
  })
}



//查询险种记录
export function getNewtworktypeRisklog(data) {
  return request({
    url: 'provider/org/getNewtworktypeRisklog',
    method: 'get',
    params: data
  })
}




//提交至审核
export function updateStatus(data) {
  return request({
    url: 'provider/org/updateStatus',
    method: 'post',
    data
  })
}
//查询待审核医院列表列表
export function getCheckUpList(data) {
  return request({
    url: 'provider/org/getCheckUpList',
    method: 'get',
    params:data
  })
}

//医院信息进行审核处理
export function insertCheckInfo(data) {
  return request({
    url: 'provider/org/insertCheckInfo',
    method: 'post',
    data
  })
}

//医院信息进行审核信息查询
export function selectCheckInfo(data) {
  return request({
    url: 'provider/org/selectCheckInfo',
    method: 'post',
    data
  })
}

//结算信息记录查询
export function selectsettleInfo(data) {
  return request({
    url: 'provider/org/selectsettleInfo',
    method: 'get',
    params: data
  })
}









//查询历史合约
export function getHistoryConlist(data) {
  return request({
    url: 'provider/supplier/code/'+data,
    method: 'get'
  })
}








export function addHospital(data) {
  return request({
    url: '/baseinfo/claim/hospital/insertOne',
    method: 'post',
    data
  })
}

export function updataHospital(data) {
  return request({
    url: '/baseinfo/claim/hospital/updateOne',
    method: 'post',
    data
  })
}


// 打开方式为编辑
export function getOne(data) {
  return request({
    url: '/baseinfo/claim/hospital/getOne',
    method: 'post',
    data
  })
}

export function deleteOne(data) {
  return request({
    url: '/baseinfo/claim/hospital/deleteOne',
    method: 'post',
    data
  })
}
