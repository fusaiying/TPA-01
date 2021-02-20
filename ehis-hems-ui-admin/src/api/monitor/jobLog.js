import request from '@/utils/request'

// 查询调度日志列表
export function listJobLog(query) {
  return request({
    url: '/system/job/log/list',
    method: 'get',
    params: query
  })
}

// 删除调度日志
export function delJobLog(jobLogId) {
  return request({
    url: '/system/job/log/' + jobLogId,
    method: 'delete'
  })
}

// 清空调度日志
export function cleanJobLog() {
  return request({
    url: '/system/job/log/clean',
    method: 'delete'
  })
}
