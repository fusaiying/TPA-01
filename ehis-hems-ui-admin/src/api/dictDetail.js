import request from '@/utils/request'

export function get(dictName) {
  return request({
    url: '/system/dict/data/type/' + dictName,
    method: 'get'
  })
}

// export function getDictMap(dictName) {
//   const params = {
//     dictName,
//     page: 0,
//     size: 9999
//   }
//   return request({
//     url: 'system/api/dictDetail/map',
//     method: 'get',
//     params
//   })
// }

export function add(data) {
  return request({
    url: '/system/dict/data',
    method: 'post',
    data: data
  })
}

export function del(id) {
  return request({
    url: '/system/dict/data/' + dictCode,
    method: 'delete'
  })
}

export function edit(data) {
  return request({
    url: '/system/dict/data',
    method: 'put',
    data: data
  })
}
