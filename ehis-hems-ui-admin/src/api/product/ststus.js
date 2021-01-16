import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/sinoutils";

// 状态维护
export function audit(productId) {
  return request({
    url: '/system/user/' + praseStrEmpty(productId),
    method: 'get'
  })
}

// 产品上线状态转换
export function changeOnLineStatus(productno,productstate){
  const data = {
    productno:productno,
    productstate:productstate
  }
  return request({
    url: '/product/definition/changeOnLineStatus',
    method: 'put',
    data: data
  })
}
