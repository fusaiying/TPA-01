import request from '@/utils/request'

// 查询供应商管理1列表
export function listProvider(query) {
  return request({
    url: '/provider/provider/list',
    method: 'get',
    params: query
  })
}

// 查询供应商管理1详细
export function getProvider(servcomno) {
  return request({
    url: '/provider/provider/' + servcomno,
    method: 'get'
  })
}

// 新增供应商管理1
export function addProvider(form, characterForm, concatInfo) {
  return request({
    url: '/provider/provider',
    method: 'post',
    data: {hmpServOpera: form, hmpHospCharacter: characterForm, hmpServContactsList: concatInfo},
  })
}

// 修改供应商管理1
export function updateProvider(form, characterForm, concatInfo) {
  return request({
    url: '/provider/provider',
    method: 'put',
    data: {hmpServOpera: form, hmpHospCharacter: characterForm, hmpServContactsList: concatInfo},
  })
}

// 删除供应商管理1
export function delProvider(servcomno) {
  return request({
    url: '/provider/provider/' + servcomno,
    method: 'delete'
  })
}


// 根据供应商编码查询医疗机构特色信息
export function getCharacterByNo(servcomno) {
  return request({
    url: '/provider/provider/character/' + servcomno,
    method: 'get'
  })
}

// 根据供应商编码查询联系人信息
export function getContactsByNo(servcomno) {
  return request({
    url: '/provider/provider/concats/' + servcomno,
    method: 'get'
  });
}

// 查询供应商网络列表
export function listProviderNetwork(query) {
  return request({
    url: '/provider/provider/network/list',
    method: 'get',
    params: query
  })
}

// 根据供应商编码查询服务网点配置对象
export function listProviderNetworkByNo(query) {
  return request({
    url: '/provider/provider/network/list/no',
    method: 'get',
    params: query,
  });
}

// 根据服务网点编码删除服务网点配置对象
export function deleteHmpComWebSiteByIds(website) {
  return request({
    url: '/provider/provider/website/' + website,
    method: 'delete'
  })
}


// 根据服务网点编码  修改  服务网点配置对象
export function updateWebSite(form) {
  return request({
    url: '/provider/provider/website',
    method: 'put',
    data: form,
  })
}


// 新增服务网点配置对象
export function addWebSite(form) {
  return request({
    url: '/provider/provider/website',
    method: 'post',
    data: form,
  })
}

// ======== 网点导入  ===========

// 根据供应商编码查询服务网点配置对象
export function listProviderNetworkByNoTrans(query) {
  return request({
    url: '/provider/provider/network/list/no/trans',
    method: 'get',
    params: query,
  });
}

// ========医生网络-首页=============
export function listProviderDoctor(query) {
  return request({
    url: '/provider/provider/doctor/list',
    method: 'get',
    data: query,
  });
}

// ========= 服务实施-查询及处理 ============
export function selectComwebsitebyCode(websitecode) {
  return request({
    url: '/provider/provider/websitename/' + websitecode,
    method: 'get',
  })
}

