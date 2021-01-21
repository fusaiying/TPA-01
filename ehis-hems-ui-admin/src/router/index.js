import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: 路由配置项
 *
 * hidden: true                   // 当设置 true 的时候该路由不会再侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1
 * alwaysShow: true               // 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
 *                                // 只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
 *                                // 若你想不管路由下面的 children 声明的个数都显示你的根路由
 *                                // 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由
 * redirect: noRedirect           // 当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
 * name:'router-name'             // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * meta : {
    roles: ['admin','editor']    // 设置该路由进入的权限，支持多个权限叠加
    title: 'title'               // 设置该路由在侧边栏和面包屑中展示的名字
    icon: 'svg-name'             // 设置该路由的图标，对应路径src/assets/icons/svg
    breadcrumb: false            // 如果设置为false，则不会在breadcrumb面包屑中显示
  }
 */

// 公共路由
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: (resolve) => require(['@/views/redirect'], resolve)
      }
    ]
  },
  {
    path: '/login',
    component: (resolve) => require(['@/views/login'], resolve),
    hidden: true
  },
  {
    path: '/404',
    component: (resolve) => require(['@/views/error/404'], resolve),
    hidden: true
  },
  {
    path: '/401',
    component: (resolve) => require(['@/views/error/401'], resolve),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: 'index',
    children: [
      {
        path: 'index',
        component: (resolve) => require(['@/views/index'], resolve),
        name: '首页',
        meta: { title: '首页', icon: 'dashboard', noCache: true, affix: true }
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'profile',
        component: (resolve) => require(['@/views/system/user/profile/index'], resolve),
        name: 'Profile',
        meta: { title: '个人中心', icon: 'user' }
      }
    ]
  },
  {
    path: '/dict',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'type/data/:dictId(\\d+)',
        component: (resolve) => require(['@/views/system/dict/data'], resolve),
        name: 'Data',
        meta: { title: '字典数据', icon: '' }
      }
    ]
  },
  {
    path: '/job',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'log',
        component: (resolve) => require(['@/views/monitor/job/log'], resolve),
        name: 'JobLog',
        meta: { title: '调度日志' }
      }
    ]
  },
  {
    path: '/gen',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'edit/:tableId(\\d+)',
        component: (resolve) => require(['@/views/tool/gen/editTable'], resolve),
        name: 'GenEdit',
        meta: { title: '修改生成配置' }
      }
    ]
  },
  // 报案处理
  {
    path: '/reportdetail',
    component: () => import('../views/claim/claimsHandle/common/com-deal.vue'),
    hidden: true,
    name: 'reportdetail',
    meta: { title: '报案处理', noCache: true },
  },
  // 受理处理
  {
    path: '/acceptdetail',
    component: () => import('../views/claim/claimsHandle/common/com-deal.vue'),
    hidden: true,
    name: 'acceptdetail',
    meta: { title: '受理处理', noCache: true },
  },
  // 初审
  {
    path: '/detail',
    component: () => import('../views/claim/claimsHandle/common/com-deal.vue'),
    hidden: true,
    name: 'detail',
    meta: { title: '初审处理', noCache: true },
  },
  // 新版本初审处理
  {
    path: '/newdetail',
    component: () => import('../views/claim/claimsHandle/firstCommon/comdeal.vue'),
    hidden: true,
    name: 'newdetail',
    meta: { title: '初审处理', noCache: true },
  },
  // 审核
  {
    path: '/reviewdetail',
    component: () => import('../views/claim/claimsHandle/common/com-deal.vue'),
    hidden: true,
    name: 'reviewdetail',
    meta: { title: '审核处理', noCache: true },
  },
  // 复核
  {
    path: '/complexdetail',
    component: () => import('../views/claim/claimsHandle/common/com-deal.vue'),
    hidden: true,
    name: 'complexdetail',
    meta: { title: '复核处理', noCache: true },
  },
  // 抽检
  {
    path: '/checkdetail',
    component: () => import('../views/claim/claimsHandle/common/com-deal.vue'),
    hidden: true,
    name: 'checkdetail',
    meta: { title: '抽检处理', noCache: true },
  },
  // 结案抽检
  {
    path: '/sportcheckdetail',
    component: () => import('../views/claim/claimsHandle/common/com-deal.vue'),
    hidden: true,
    name: 'sportcheckdetail',
    meta: { title: '抽检处理', noCache: true },
  },
  // 审批
  {
    path: '/correctdetail',
    component: () => import('../views/claim/claimsHandle/common/com-deal.vue'),
    hidden: true,
    name: 'correctdetail',
    meta: { title: '审批处理', noCache: true },
  },
  // 补材处理
  {
    path: '/materialesdetail',
    component: () => import('../views/claim/claimsHandle/common/materialsHandle.vue'),
    hidden: true,
    name: 'materialesdetail',
    meta: { title: '补材处理', noCache: true },
  },
  // 审物理件处理
  {
    path: '/physicaldetail',
    component: () => import('../views/claim/claimsHandle/common/com-deal.vue'),
    hidden: true,
    name: 'physicaldetail',
    meta: { title: '审物理件处理', noCache: true },
  },
  // 退件处理
  {
    path: '/returndetail',
    component: () => import('../views/claim/claimsHandle/return/returnEdit.vue'),
    hidden: true,
    name: 'returndetail',
    meta: { title: '退件处理', noCache: true },
  },
  // 录入处理
  {
    path: '/inputdetail',
    component: () => import('../views/claim/claimsHandle/common/com-deal.vue'),
    hidden: true,
    name: 'inputdetail',
    meta: { title: '录入处理', noCache: true },
  },
  // 录入处理
  {
    path: '/newinputdetail',
    component: () => import('../views/claim/claimsHandle/inputCommon/inputDeal.vue'),
    hidden: true,
    name: 'newinputdetail',
    meta: { title: '录入处理', noCache: true },
  },
  // 撤案重开处理
  {
    path: '/removecasedetail',
    component: () => import('../views/claim/claimsHandle/common/materialsHandle.vue'),
    hidden: true,
    name: 'removecasedetail',
    meta: { title: '撤案重开处理', noCache: true },
  },
  // 影像分类处理
  {
    path: '/ificatdetail',
    component: () => import('../views/claim/claimsHandle/common/imgClassificat.vue'),
    hidden: true,
    name: 'ificatdetail',
    meta: { title: '影像分类处理', noCache: true },
  },
  // 影像件查看
  {
    path: '/imageshow',
    component: () => import('../views/claim/claimsHandle/common/components/imageContainer.vue'),
    hidden: true,
    name: 'imageshow',
    meta: { title: '影像件处理/查看', noCache: true },
  },
  // 初审查看
  {
    path: '/imageshows',
    component: () => import('../views/claim/claimsHandle/firstCommon/firstImage.vue'),
    hidden: true,
    name: 'imageshows',
    meta: { title: '影像件处理/查看', noCache: true },
  },
  // 影像查看
  {
    path: '/classificatViewshow',
    component: () => import('../views/claim/claimsHandle/classificat/imageVIEW.vue'),
    hidden: true,
    name: 'classificatViewshow',
    meta: { title: '影像查看', noCache: true },
  },
  // 发票录入
  {
    path: '/invoiceHandle',
    component: () => import('../views/claim/claimsHandle/common/imgClassificat.vue'),
    hidden: true,
    name: 'invoiceHandle',
    meta: { title: '发票录入', noCache: true },
  },
  // 案件详情
  {
    path: '/casedetail',
    component: () => import('../views/claim/claimsHandle/common/com-deal.vue'),
    hidden: true,
    name: 'casedetail',
    meta: { title: '案件详情', noCache: true },
  },
  // 调查详情
  {
    path: '/serchDetail',
    component: () => import('../../src/views/claim/claimsOperatManage/search/claimserch.vue'),
    hidden: true,
    name: 'serchDetail',
    meta: { title: '调查详情', noCache: true },
  },
  // 调查详情
  {
    path: '/policyDetail',
    component: () => import('../../src/views/claim/claimsOperatManage/search/policy-detail.vue'),
    hidden: true,
    name: 'policyDetail',
    meta: { title: '保单详情', noCache: true },
  },
  // 服务项目列表-新增/编辑
  {
    path: '/basicInfo',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'projectManagement/add/:projectno(\\w+)',
        component: (resolve) => require(['@/views/claim/basicInfoManage/projectManagement/info'], resolve),
        name: 'ProjectInfo',
        meta: { title: '服务项目信息', icon: '' }
      }
    ]
  },
  // 服务项目列表-服务流程配置
  {
    path: '/basicInfo',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'projectManagement/workflow/:projectno(\\w+)',
        component: (resolve) => require(['@/views/claim/basicInfoManage/projectManagement/workflow'], resolve),
        name: 'ProjectWorkFlow',
        meta: { title: '流程配置', icon: '' }
      }
    ]
  },
  // 服务项目列表-服务流程配置-消息模板
  {
    path: '/basicInfo',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'projectManagement/messageTemp/',
        component: () => import('@/views/claim/basicInfoManage/projectManagement/messageTemplate'),
        name: 'messageTemplate',
        meta: { title: '消息模板', noCache: true }
      }
    ]
  },
  // 医生管理-新增  /basicInfo/doctor/add/0
  {
    path: '/basicInfo',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'doctor/add/:docno(\\w+)',
        component: () => import('@/views/claim/basicInfoManage/doctor/info'),
        name: 'doctorInfo',
        meta: { title: '医生信息新增/编辑', noCache: true }
      }
    ]
  },

]

export default new Router({
  mode: 'history', // 去掉url中的#
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})
