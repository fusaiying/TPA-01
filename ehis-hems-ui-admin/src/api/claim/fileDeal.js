import request from '@/utils/request'

// 文件预览
export function preView(data) {
  return request({
    url: 'file/file/claim/preView',
    method: 'post',
    data
  })
}

// 缩略图处理
export function deal(data) {
  return request({
    url: 'file/file/claim/dealThumbnail',
    method: 'post',
    data
  })
}
