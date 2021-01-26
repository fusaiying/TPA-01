import request from '@/utils/request'


//案件抽检工作池-处理中
export function listCaseCheck(data) {
  return request({
    url: '/system/case/caseCheck',
    method: 'get',
    data: data
  })
}
//案件抽检工作池-已处理
export function listConditionsForTheAdjustmentOverNew(data) {
  return request({
    url: '/system/case/listConditionsForTheAdjustmentOverNew',
    method: 'get',
    data: data
  })
}

//退回审核按钮，退回后流转至该案件理算审核环节操作人处理中工作池
export function editCaseCheckBack(data) {
  return request({
    url: '/system/case/editCaseCheckBack',
    method: 'post',
    data: data
  })
}



//抽检完毕按钮，修改案件状态
export function editCaseCheck(data) {
  return request({
    url: '/system/case/editCaseCheck',
    method: 'post',
    data: data
  })
}
