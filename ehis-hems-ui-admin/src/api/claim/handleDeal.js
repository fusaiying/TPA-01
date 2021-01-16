import request from '@/utils/request'
//获取个人池信息
export function getList(data) {
  return request({
    url: 'claim/claim/case/getList',
    method: 'post',
    data
  })
}

//获取公共池信息
export function getPublicList(data) {
  return request({
    url: 'claim/claim/case/getPublicList',
    method: 'post',
    data
  })
}

//初审案件获取
export function getOneCase(data) {
  return request({
    url: 'claim/claim/case/receive',
    method: 'post',
    data
  })
}

//初审案件申请
export function apply(data) {
  return request({
    url: 'claim/claim/case/apply',
    method: 'post',
    data
  })
}

//案件回收
export function recovery(data) {
  return request({
    url: 'claim/claim/case/recovery',
    method: 'post',
    data
  })
}

// 获取申请人信息
export function getApplicantInfo(data) {
  return request({
    url: 'claim/claim/info/applicantInfo',
    method: 'post',
    data
  })
}

// 获取被保人信息
export function getInsuredInfo(data) {
  return request({
    url: 'claim/claim/info/insuredInfo',
    method: 'post',
    data
  })
}

// 获取赔付信息
export function getPayInfo(data) {
  return request({
    url: 'claim/claim/info/payInfo',
    method: 'post',
    data
  })
}

// 赔付移除按钮
export function delGetDutyResult(data) {
  return request({
    url: 'claim/claim/info/delGetDutyResult',
    method: 'post',
    data
  })
}

// 赔付计算按钮
export function payCalculation(data) {
  return request({
    url: 'claim/claim/info/payCalculation',
    method: 'post',
    data
  })
}

// 赔付信息保存按钮
export function savePayConclusion(data) {
  return request({
    url: 'claim/claim/info/savePayConclusion',
    method: 'post',
    data
  })
}

// 保存赔付信息
export function savePayInfo(data) {
  return request({
    url: 'claim/claim/info/savePayInfo',
    method: 'post',
    data
  })
}

// 获取领款人信息
export function getPayeeInfo(data) {
  return request({
    url: 'claim/claim/info/payeeInfo',
    method: 'post',
    data
  })
}

// 计算金额
export function calculationPayAmount(data) {
  return request({
    url: 'claim/claim/info/calculationPayAmount',
    method: 'post',
    data
  })
}

// 保存领款人信息
export function savePayeeInfo(data) {
  return request({
    url: 'claim/claim/info/savePayeeInfo',
    method: 'post',
    data
  })
}

// 保单信息
export function relationPolicy(data) {
  return request({
    url: 'claim/claim/info/relationPolicy',
    method: 'post',
    data
  })
}

// 获取风险信息
export function getDangerInfo(data) {
  return request({
    url: 'claim/claim/info/dangerInfo',
    method: 'post',
    data
  })
}

// 风险查询
export function getRiskInfo(data) {
  return request({
    url: 'claim/claim/info/getRiskInfo',
    method: 'post',
    data
  })
}

// 获取事件信息
export function getEventInfoList(data) {
  return request({
    url: 'claim/claim/info/eventInfo',
    method: 'post',
    data
  })
}

// 保存事件信息
export function saveEventInfo(data) {
  return request({
    url: 'claim/claim/info/saveEventInfo',
    method: 'post',
    data
  })
}

// 事件删除
export function deleteEvent(data) {
  return request({
    url: 'claim/claim/info/deleteEvent',
    method: 'post',
    data
  })
}

// 事件信息转移接口
export function saveEventRelationBills(data) {
  return request({
    url: 'claim/claim/info/saveEventRelationBills',
    method: 'post',
    data
  })
}

// 获取赔案备注信息
export function getCaseRemark(data) {
  return request({
    url: 'claim/claim/info/getCaseRemark',
    method: 'post',
    data
  })
}

// 保存赔案备注信息
export function saveCaseRemark(data) {
  return request({
    url: 'claim/claim/info/saveCaseRemark',
    method: 'post',
    data
  })
}

// 获取出险信息
export function getInDangerInfo(data) {
  return request({
    url: 'claim/claim/info/inDangerInfo',
    method: 'post',
    data
  })
}

// 保存信息（批量）
export function saveDangerInfo(data) {
  return request({
    url: 'claim/claim/info/saveDangerInfo',
    method: 'post',
    data
  })
}

// // 删除疾病信息
// export function deleteDiseaseInfo(data) {
//   return request({
//     url: 'claim/claim//info/deleteDiseaseInfo',
//     method: 'post',
//     data
//   })
// }

// // 删除伤残信息
// export function deleteDisabilityInfo(data) {
//   return request({
//     url: 'claim/claim//info/deleteDisabilityInfo',
//     method: 'post',
//     data
//   })
// }

// // 删除手术信息
// export function deleteOperationInfo(data) {
//   return request({
//     url: 'claim/claim//info/deleteOperationInfo',
//     method: 'post',
//     data
//   })
// }

// // 删除意外信息
// export function deleteAccidentInfo(data) {
//   return request({
//     url: 'claim/claim//info/deleteAccidentInfo',
//     method: 'post',
//     data
//   })
// }

// 删除出险信息
export function deleteDangerInfo(data) {
  return request({
    url: 'claim/claim/info/deleteDangerInfo',
    method: 'post',
    data
  })
}

// 获取既往信息 ⚠️待确定是否用到
export function getPastClaimsList(data) {
  return request({
    url: 'claim/claim/info/pastClaims',
    method: 'post',
    data
  })
}

// 获取退件列表
export function materiaRefundList(data) {
  return request({
    url: 'claim/claim/info/materiaRefundList',
    method: 'post',
    data
  })
}

//收件人信息
export function materiaRefundInfo(data) {
  return request({
    url: 'claim/claim/info/materiaRefundInfo',
    method: 'post',
    data
  })
}

// 保存退件信息
export function saveRefund(data) {
  return request({
    url: 'claim/claim/info/saveRefund',
    method: 'post',
    data
  })
}

// 赔付明细
export function getPayDetail(data) {
  return request({
    url: 'claim/claim/info/payDetail',
    method: 'post',
    data
  })
}

// 材料信息列表
export function getMateriaList(data) {
  return request({
    url: 'claim/claim/info/materiaList',
    method: 'post',
    data
  })
}

// 材料信息保存
export function saveMateriaList(data) {
  return request({
    url: 'claim/claim/info/saveMateriaList',
    method: 'post',
    data
  })
}

// 材料信息删除
export function delMaterial(data) {
  return request({
    url: 'claim/claim/info/delMaterial',
    method: 'post',
    data
  })
}

// 材料数量
export function getMaterialCount(data) {
  return request({
    url: 'claim/claim/info/getMaterialCount',
    method: 'post',
    data
  })
}

// 案件暂停接口
export function casesuspend(data) {
  return request({
    url: 'claim/claim/case/casesuspend',
    method: 'post',
    data
  })
}

// 结论处理接口
export function saveConclusion(data) {
  return request({
    url: 'claim/claim/info/saveConclusion',
    method: 'post',
    data
  })
}

//调处理结论之前需要调这个接口
export function saveConclusionCheck(data) {
  return request({
    url: 'claim/claim/info/saveConclusionCheck',
    method: 'post',
    data
  })
}

// 时间轴接口
export function caseSchedule(data) {
  return request({
    url: 'claim/claim/info/caseSchedule',
    method: 'post',
    data
  })
}

// 影像文件接口
export function getImageInfo(data) {
  return request({
    url: 'claim/claim/info/getImageInfo',
    method: 'post',
    data
  })
}

// 影像打包下载
export function dowloadFileRar(data) {
  return request({
    url: 'file/file/claim/dowloadFileRar',
    method: 'post',
    data,
    responseType: 'blob'
  })
}
// 单个下载文件
export function dowloadFileOne(data) {
  return request({
    url: 'file/file/claim/dowloadFile',
    method: 'post',
    data,
    responseType: 'blob'
  })
}
// 批量下载文件
export function dowloadFileAll(data) {
  return request({
    url: 'file/file/claim/dowloadFileAll',
    method: 'post',
    data,
    responseType: 'blob'
  })
}
// 获取影像文件条数
export function getFileCount(data) {
  return request({
    url: 'claim/claim/file/getFileCount',
    method: 'post',
    data
  })
}
// 既往调查接口
export function pastSurvey(data) {
  return request({
    url: 'claim/claim/info/pastSurvey',
    method: 'post',
    data
  })
}

// 既往协谈接口
export function pastNegotiation(data) {
  return request({
    url: 'claim/claim/info/pastNegotiation',
    method: 'post',
    data
  })
}

// 结案抽检处理
export function updateCheck(data) {
  return request({
    url: 'claim/claim/spotCheck/updateCheck',
    method: 'post',
    data
  })
}
// 获取补充物理件接口
export function materialPendingQuery(data) {
  return request({
    url: 'claim/applicationPhysicalParts/materialPendingQuery',
    method: 'post',
    data
  })
}
// 补充物理件保存
export function addMentarydetail(data) {
  return request({
    url: 'claim/applicationPhysicalParts/addMentarydetail',
    method: 'post',
    data
  })
}
// 获取材料类型
export function getMaterialtype(data) {
  return request({
    url: 'claim/applicationPhysicalParts/getMaterialtype',
    method: 'post',
    data
  })
}
// 补充物理件处理完毕
export function handleSubmit(data) {
  return request({
    url: 'claim/applicationPhysicalParts/handleSubmit',
    method: 'post',
    data
  })
}
// 处理文件下载按钮列表
export function getFileList(data) {
  return request({
    url: 'claim/claim/file/getFileList',
    method: 'post',
    data
  })
}

// 协谈列表
export function getNegotiationInfo(data) {
  return request({
    url: 'claim/claim/info/getNegotiationInfo',
    method: 'post',
    data
  })
}

// 发起协谈
export function startNegotiation(data) {
  return request({
    url: 'claim/claim/info/startNegotiation',
    method: 'post',
    data
  })
}

// 协谈列表下载
export function dowloadImageFile(data) {
  return request({
    url: 'file/file/claim/dowloadImageFile',
    method: 'post',
    data,
    responseType: 'blob'
  })
}


// 处理页面初始化的时调用
export function basicInfo(data) {
  return request({
    url: 'claim/claim/info/basicInfo',
    method: 'post',
    data
  })
}

// 不予受理
export function revokeCase(data) {
  return request({
    url: 'claim/claim/info/revokeCase',
    method: 'post',
    data
  })
}

// 报案信息列表
export function registerInfo(data) {
  return request({
    url: 'claim/claim/info/registerInfo',
    method: 'post',
    data
  })
}

// 补材任务列表
export function pastSupplementaryList(data) {
  return request({
    url: 'claim/claim/info/pastSupplementaryList',
    method: 'post',
    data
  })
}

// 补材弹框材料列表
export function getAntMateriaList(data) {
  return request({
    url: 'claim/claim/info/getAntMateriaList',
    method: 'post',
    data
  })
}

// 发起补材
export function startStuff(data) {
  return request({
    url: 'claim/claim/info/startStuff',
    method: 'post',
    data
  })
}

// 判断是否退回
export function getCaseLastNodeInfo(data) {
  return request({
    url: 'claim/claim/info/getCaseLastNodeInfo',
    method: 'post',
    data
  })
}

// 判断是否暂停
export function isSuspend(data) {
  return request({
    url: 'claim/claim/case/isSuspend',
    method: 'post',
    data
  })
}

// 豁免列表
export function queryExemptionDetail(data) {
  return request({
    url: 'claim/claim/exemptionDeal/queryExemptionDetail',
    method: 'post',
    data
  })
}
// 更新豁免
export function updateExemptionDetail(data) {
  return request({
    url: 'claim/claim/exemptionDeal/updateExemptionDetail',
    method: 'post',
    data
  })
}

// 既往信息 - 赔案信息
export function getPastClaims(data) {
  return request({
    url: 'claim/claim/info/pastClaims',
    method: 'post',
    data
  })
}
// 既往信息 - 赔案信息
export function getTempalte(data) {
  return request({
    url: 'claim/claim/info/tempalte',
    method: 'post',
    data
  })
}
// 医院等级
export function getFirstMedicalInstitution(data) {
  return request({
    url: 'claim/claim/thirdKnowledge/getFirstMedicalInstitution',
    method: 'post',
    data
  })
}
// 诊断查询
export function getLikeICD10(data) {
  return request({
    url: 'claim/claim/thirdKnowledge/getLikeICD10',
    method: 'post',
    data
  })
}
// 诊断查询-bycode
export function getICD10ByCode(data) {
  return request({
    url: 'claim/claim/thirdKnowledge/getICD10ByCode',
    method: 'post',
    data
  })
}
// 查询医院-bYcode
export function getHosptailByCode(data) {
  return request({
    url: 'claim/claim/thirdKnowledge/getMedicalByCode',
    method: 'post',
    data
  })
}

// 物理件材料信息
export function returnInformationQuery(data) {
  return request({
    url: 'claim/applicationPhysicalParts/returnInformationQuery',
    method: 'post',
    data
  })
}

// 保存退件信息
export function addreturnInformation(data) {
  return request({
    url: 'claim/supplementUpload/addreturnInformation',
    method: 'post',
    data
  })
}

// 查询话术模板
export function getScriptTemplate(data) {
  return request({
    url: 'claim/applicationPhysicalParts/getScriptTemplate',
    method: 'post',
    data
  })
}

// 查看当前节点结论
export function getLastOptlog(data) {
  return request({
    url: 'claim/claim/info/getLastOptlog',
    method: 'post',
    data
  })
}

// 工单解约保单信息查询
export function queryPolicyinfo(data) {
  return request({
    url: 'claim/claim/WorkTermination/queryPolicyinfo',
    method: 'post',
    data
  })
}

// 工单解约信息传送给客服
export function queryPolicyinfoSend(data) {
  return request({
    url: 'claim/claim/WorkTermination/queryPolicyinfoSend',
    method: 'post',
    data
  })
}

// 工单解约结果查询
export function queryNegotiationResults(data) {
  return request({
    url: 'claim/claim/WorkTermination/queryNegotiationResults',
    method: 'post',
    data
  })
}

// 查询出险信息名称
export function getInfoByName(data) {
  return request({
    url: 'claim/claim/accident/getInfoByName',
    method: 'post',
    data
  })
}
// 查询出险信息名称code查等级
export function getaccident(data) {
  return request({
    url: 'claim/claim/accident/getInfoByCode',
    method: 'post',
    data
  })
}
// 查依据
export function getProofNameByCode(data) {
  return request({
    url: 'claim/claim/accident/getProofNameByCode',
    method: 'post',
    data
  })
}
// 根据code查判断依据
export function getProofNameBySubCode(data) {
  return request({
    url: 'claim/claim/accident/getProofNameBySubCode',
    method: 'post',
    data
  })
}

// 审核人接口
export function getGroupUserList(data) {
  return request({
    url: 'claim/claim/info/getGroupUserList',
    method: 'post',
    data
  })
}

// 审物理件查看反显处理结论
export function getConclusion(data) {
  return request({
    url: 'claim/applicationPhysicalParts/getConclusion',
    method: 'post',
    data
  })
}

// 获取提调按钮数量
export function countInvestigaeNumber(data) {
  return request({
    url: 'claim/claim/investigate/countInvestigaeNumber',
    method: 'post',
    data
  })
}

// 获取模板
export function getContents(data) {
  return request({
    url: 'claim/claim/template/getContents',
    method: 'post',
    data
  })
}
// 结案抽检时处理结论查询
export function getCheckOptLog(data) {
  return request({
    url: 'claim/claim/info/check/getCheckOptLog',
    method: 'post',
    data
  })
}
//是否需要豁免
export function checkExemption(data) {
  return request({
    url: 'claim/claim/info/checkExemption',
    method: 'post',
    data
  })
}
//确认豁免
export function saveExemption(data) {
  return request({
    url: 'claim/claim/info/saveExemption',
    method: 'post',
    data
  })
}

// 影像删除接口
export function claimDelete(data) {
  return request({
    url: 'file/file/claim/claimDelete',
    method: 'post',
    data
  })
}
// 判断当前节点的状态
export function checkCaseDeal(data) {
  return request({
    url: 'claim/claim/info/checkCaseDeal',
    method: 'post',
    data
  })
}
// 判断撤案重开当前节点的状态
export function judgeReopen(data) {
  return request({
    url: 'claim/claim/reopen/judgeReopen',
    method: 'post',
    data
  })
}
// 判断补财，物理件等当前节点的状态
export function blockCheck(data) {
  return request({
    url: 'claim/claim/blockCheck',
    method: 'post',
    data
  })
}
// 判断报案节点的状态
export function judgeStatus(data) {
  return request({
    url: 'claim/claim/judgeStatus',
    method: 'post',
    data
  })
}

// 判断是否申诉纠错
export function isAppealCorrection(data) {
  return request({
    url: 'claim/claim/info/isAppealCorrection',
    method: 'post',
    data
  })
}

// 受理回执
export function downloadPdf(data) {
  return request({
    url: 'claim/claim/downloadPdf',
    method: 'post',
    data,
    responseType: 'blob'
  })
}
// 获取影像文件所有条数
export function getAllFileCount(data) {
  return request({
    url: 'claim/claim/file/getAllFileCount',
    method: 'post',
    data
  })
}
// 印象分类时缩略图preViewThumbnail
export function preViewThumbnail(data) {
  return request({
    url: 'file/file/claim/preViewThumbnail',
    method: 'post',
    data
  })
}
// 判断豁免是否可点击
export function queryAccordWithExemption(data) {
  return request({
    url: 'claim/claim/exemptionDeal/queryAccordWithExemption',
    method: 'post',
    data
  })
}
// 查询豁免条数
export function queryExemptionCount(data) {
  return request({
    url: 'claim/claim/exemptionDeal/queryExemptionCount',
    method: 'post',
    data
  })
}
// 更新豁免信息
export function deleteExemptionDetail(data) {
  return request({
    url: 'claim/claim/exemptionDeal/deleteExemptionDetail',
    method: 'post',
    data
  })
}
// 自动豁免问题
export function queryWaitExemptionCount(data) {
  return request({
    url: 'claim/claim/exemptionDeal/queryWaitExemptionCount',
    method: 'post',
    data
  })
}
// 自动豁免问题
export function queryPolicyExemptionDetail(data) {
  return request({
    url: 'claim/claim/exemptionDeal/queryPolicyExemptionDetail',
    method: 'post',
    data
  })
}
// export function queryPolicyExemptionDetail(data) {
//   return request({
//     url: 'claim/claim/exemptionDeal/queryPolicyExemptionDetail',
//     method: 'post',
//     data
//   })
// }
export function queryExemptionStartDate(data) {
  return request({
    url: 'claim/claim/exemptionDeal/queryExemptionStartDate',
    method: 'post',
    data
  })
}
// 权限流转确认
export function processCheck(data) {
  return request({
    url: 'claim/claim/info/processCheck',
    method: 'post',
    data
  })
}
// 查询扫描案件的历史轨迹
export function serchScanLog(data) {
  return request({
    url: 'claim/claim/scan',
    method: 'post',
    data
  })
}
// 查询是否是退回案件
export function isReturn(data) {
  return request({
    url: 'claim/claim/isReturn',
    method: 'post',
    data
  })
}
// 退回确认
export function returnOpinion(data) {
  return request({
    url: 'claim/claim/returnOpinion',
    method: 'post',
    data
  })
}
// 扫描提交
export function scanSubmit(data) {
  return request({
    url: 'claim/claim/scanSubmit',
    method: 'post',
    data
  })
}
// 查询发票列表  未处理
export function getInvoicelistInfo(data) {
  return request({
    url: 'claim/claim/event/invoiceInfo',
    method: 'post',
    data
  })
}
// 查询案件标签列表
export function getCasePublicList(data) {
  return request({
    url: 'claim/caseLabels/case/getPublicList',
    method: 'post',
    data
  })
}
// 添加案件标签
export function addCaseLabels(data) {
  return request({
    url: 'claim/caseLabels/case/addCaseLabels',
    method: 'post',
    data
  })
}
// 发票修改 未处理
export function saveInvoiceInfo(data) {
  return request({
    url: 'claim/claim/event/saveInvoiceInfo',
    method: 'post',
    data
  })
}
// 费用修改
export function saveInvoiceDetailInfo(data) {
  return request({
    url: 'claim/claim/event/saveInvoiceDetailInfo',
    method: 'post',
    data
  })
}
// 事件列表查询
export function getEventInfo(data) {
  return request({
    url: 'claim/claim/event/eventInfo',
    method: 'post',
    data
  })
}
// 事件删除
export function newEventDelete(data) {
  return request({
    url: 'claim/claim/event/eventDelete',
    method: 'post',
    data
  })
}
// 事件保存
export function newSaveEventInfo(data) {
  return request({
    url: 'claim/claim/event/saveEventInfo',
    method: 'post',
    data
  })
}
// 出险信息查询
export function newAccidentInfo(data) {
  return request({
    url: 'claim/claim/event/accidentInfo',
    method: 'post',
    data
  })
}
// 发票关联事件
export function relationEventInvoice(data) {
  return request({
    url: 'claim/claim/event/relationEventInvoice',
    method: 'post',
    data
  })
}
// 查询保单信息
export function getNewpolicyInfo(data) {
  return request({
    url: 'claim/claim/calculate/policyInfo',
    method: 'post',
    data
  })
}
// 赔付计算
export function newCalculatePay(data) {
  return request({
    url: 'claim/claim/calculate/calculatePay',
    method: 'post',
    data
  })
}
// 案件标签删除
export function delCaseLabels(data) {
  return request({
    url: 'claim/caseLabels/case/delCaseLabels',
    method: 'post',
    data
  })
}
// 保单信息保存
export function savePolicyInfo(data) {
  return request({
    url: 'claim/claim/calculate/savePolicyInfo',
    method: 'post',
    data
  })
}
// 修改保存赔付信息
export function updateCalculate(data) {
  return request({
    url: 'claim/claim/calculate/updateCalculate',
    method: 'post',
    data
  })
}
// 删除赔案备注
export function deleteRemark(data) {
  return request({
    url: 'claim/claim/info/deleteRemark',
    method: 'post',
    data
  })
}
// 查询新版赔案备注
export function caseRemarkList(data) {
  return request({
    url: 'claim/claim/event/caseRemarkList',
    method: 'post',
    data
  })
}
// 查询新版案件轨迹
export function caseProcessLog(data) {
  return request({
    url: 'claim/claim/event/caseProcessLog',
    method: 'post',
    data
  })
}
// 保存赔付明细
export function savePayeeInfos(data) {
  return request({
    url: 'claim/claim/event/savePayeeInfo',
    method: 'post',
    data
  })
}
// 保存调查回退
export function investFallback(data) {
  return request({
    url: 'claim/claim/investigate/investFallback',
    method: 'post',
    data
  })
}

