import request from '@/utils/request'


// 查询字典数据详细
export function getData(dictCode) {
  return request({
    url: '/cs/enum/data/' + dictCode,
    method: 'get'
  })
}

// 根据字典类型查询字典数据信息
export function getDicts(dictType) {
  return request({
    url: '/cs/enum/data/type/' + dictType,
    method: 'get'
  })
}

// 批量查询字典数据
export function getCodeDictsList(data) {
  return request({
    url: '/cs/enum/data/type/list',
    method: 'post',
    data: data
  })
}
