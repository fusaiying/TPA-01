import request from '@/utils/request'

//# 查询符合条件的保单列表信息
export function policyListDataPool(data) {
  return request({
    url: '/cs/csCommon/internal/selectPolicyList',
    method: 'post',
    data: data
  })
}
//# 根据条件查询保单信息数据
export function policyInfoData(data) {
  return request({
    url: '/cs/csCommon/internal/getPolicyInfo',
    method: 'post',
    data: data
  })
}





