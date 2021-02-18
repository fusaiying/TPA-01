import request from '@/utils/request'




export function selectInformation(data) {
  return request({
    url: '/cs/info/list1',
    method: 'get',
    params: data
  })
}


