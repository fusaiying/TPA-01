import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/sinoutils";

// 查询文件列表
export function listFile(query) {
  return request({
    url: '/system/fileInfo/list',
    method: 'get',
    params: query
  })
}

// 删除文件
export function delFile(fileids) {
  return request({
    url: '/system/fileInfo/'+fileids,
    method: 'delete'
  })
}

// 下载文件
export function downloadFile(file) {
  return request({
    url: '/system/fileInfo/download',
    method: 'post',
    data: file
  })
}
