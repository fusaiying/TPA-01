import request from '@/utils/request'

//# 查询可以质检的工单信息
export function inspectionQueryBy(query) {
  return request({
    //后台接口调用地址
    url: '/cs/spotCheck/internal/inspectionSendData',
    method: 'get',
    params: query
  })
}

//# 查询未处理的质检数据
export function inspectionListAndPublicPool(query) {
  return request({
    url: '/cs/spotCheck/internal/inspectionPublicData',
    method: 'get',
    params: query
  })
}
//质检差错确认首页查询
export function selectQualityFlagVO(params){
  return request({
    url: '/cs/spotCheck/internal/selectQualityFlagVO',
    method: 'get',
    params: params
  })
}

//# 查询处理中的质检数据
export function inspectionListAndPersonalPool(query) {
  return request({
    url: '/cs/spotCheck/internal/inspectionPersonData',
    method: 'get',
    params: query
  })
}
export function selectItemInspectionById(query) {
  return request({
    url: '/cs/spotCheck/list',
    method: 'get',
    params: query
  })
}

export function selectHandleStatus(query) {
  return request({
    url: '/cs/spotCheck/selectHandleStatus',
    method: 'get',
    params: query
  })
}

//# 任务改派查询改派操作人接口
export function selectSysUser() {
  return request({
    url: '/system/product/selectSysUser',
    method: 'get',
  })
}

//# 查询投诉数据
export function selectConfirmationQuery(query) {
  return request({
    //后台接口调用地址
    url: '/cs/spotCheck/internal/inspectionPublicData',
    method: 'get',
    params: query
  })
}

//# 发起质检操作
  export function inspectionSendByIds(sendIds) {
    return request({
      url: '/cs/spotCheck/internal/sendIds',
      method: 'put',
      data: sendIds
    })
  }

//# 获取待质检操作
export function inspectionGetByIds(getIds) {
  return request({
    url: '/cs/spotCheck/internal/getIds',
    method: 'put',
    data: getIds
  })
}


//# 查询客户基本信息
export function getPersonInfoData(params) {
   // return request({
   //   url: '/cs/spotCheck/getIds',
   //   method: 'put',
   //   data: params
   // })
}

//# 通过工单号查询工单详情
export function getAcceptInfoByTypeOrId(params){
  return request({
    url: '/cs/spotCheck/internal/getAcceptInfo',
    method: 'get',
    params: params
  })
}
//通过工单和类型去获取投诉处理信息
export function getComplaintHandleInfo(params){
  return request({
    url: '/cs/info3/selectDealVo',
    method: 'get',
    params: params
  })
}
export function getHandleInfoList(query){
  return request({
    url: '/cs/spotCheck/internal/getHandleInfoList' ,
    method: 'get',
    params: query,
  })
}
export function insertItem(form){
  return request({
    url: '/cs/spotCheck/insertItem',
    method: 'post',
    data: form
  })
}
export function insertItem2(form){
  return request({
    url: '/cs/spotCheck/insertItem2',
    method: 'post',
    data: form
  })
}
export function insertHandleInfo(form){
  return request({
    url: '/cs/spotCheck/internal/insertHandleInfo',
    method: 'post',
    data: form
  })
}

//# 通过工单号查询附件信息
export function getAttachmentListById(params) {
  return request({
    url: '/cs/spotCheck/internal/getAttachmentList',
    method: 'get',
    params: params
  })
}
//质检查询
export function selectQualityVo(params){
  return request({
    url: '/cs/spotCheck/internal/selectQualityVo',
    method: 'get',
    params: params
  })
}

//根据条件获取质检
export function getQualityHandleInfo(data){
  return request({
    url: '/cs/spotCheck/internal/getQualityHandleInfo ',
    method: 'post',
    data: data
  })
}
//质检数据保存
export function insetQualityHandleInfo(data){
  return request({
    url: '/cs/spotCheck/internal/insetQualityHandleInfo ',
    method: 'post',
    data: data
  })
}
