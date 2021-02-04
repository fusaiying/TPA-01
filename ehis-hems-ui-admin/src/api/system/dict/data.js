import request from '@/utils/request'

// 查询字典数据列表
export function listData(query) {
  return request({
    url: '/system/dict/data/list',
    method: 'get',
    params: query
  })
}

// 查询字典数据详细
export function getData(dictCode) {
  return request({
    url: '/system/dict/data/' + dictCode,
    method: 'get'
  })
}

// 根据字典类型查询字典数据信息
export function getDicts(dictType) {
  return request({
    url: '/system/dict/data/type/' + dictType,
    method: 'get'
  })
}

// 批量查询字典数据
export function getDictsList(data) {
  return request({
    url: '/system/dict/data/type/list',
    method: 'post',
    data: data
  })
}

// 新增字典数据
export function addData(data) {
  return request({
    url: '/system/dict/data',
    method: 'post',
    data: data
  })
}

// 修改字典数据
export function updateData(data) {
  return request({
    url: '/system/dict/data',
    method: 'put',
    data: data
  })
}

// 删除字典数据
export function delData(dictCode) {
  return request({
    url: '/system/dict/data/' + dictCode,
    method: 'delete'
  })
}

// 查询 州
export function selectContinent() {
  return request({
    url: '/system/dict/data/continent',
    method: 'get',
  })
}

// 查询 国家
export function selectCountryByContinent(continent) {
  return request({
    url: '/system/dict/data/country/' + continent,
    method: 'get',
  });
}

// 根据地区名称查询地区编码
export function selectPlaceCodeByPlaceName(placename) {
  return request({
    url: '/system/dict/data/code/' + placename,
    method: 'get',
  });
}

