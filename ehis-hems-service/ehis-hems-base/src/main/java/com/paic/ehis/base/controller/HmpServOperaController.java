package com.paic.ehis.base.controller;

import com.paic.ehis.common.core.utils.PathUtil;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.utils.file.FileUtils;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.base.domain.*;
import com.paic.ehis.base.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * 供应商管理Controller
 *
 * @author sino
 * @date 2020-09-21
 */
@RestController
@RequestMapping("/provider")
public class HmpServOperaController extends BaseController {
    @Autowired
    private IHmpServOperaService hmpServOperaService;

    @Autowired
    private IHmpHospCharacterService hmpHospCharacterService;

    @Autowired
    private IHmpServContactsService hmpServContactsService;

    @Autowired
    private IHmpFileInfoService hmpFileInfoService;

    @Autowired
    private IHmpAddressService hmpAddressService;


    /**
     * 查询供应商管理1列表
     */
    @GetMapping("/list")
    public TableDataInfo list(HmpServOpera hmpServOpera) {
        startPage();
        List<HmpServOpera> list = hmpServOperaService.selectHmpServOperaList(hmpServOpera);
        return getDataTable(list);
    }

    /**
     * 导出供应商管理1列表
     */
    @Log(title = "供应商管理1", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HmpServOpera hmpServOpera) throws IOException {
        List<HmpServOpera> list = hmpServOperaService.selectHmpServOperaList(hmpServOpera);
        ExcelUtil<HmpServOpera> util = new ExcelUtil<HmpServOpera>(HmpServOpera.class);
        util.exportExcel(response, list, "provider");
    }

    /**
     * 获取供应商管理1详细信息
     */
    @GetMapping(value = "/{servcomno}")
    public AjaxResult getInfo(@PathVariable("servcomno") String servcomno) {
        return AjaxResult.success(hmpServOperaService.selectHmpServOperaById(servcomno));
    }

    /**
     * 新增操作
     */
    @Log(title = "供应商网络-供应商管理-基础信息维护-新增操作", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProviderInfo providerInfo) {
        // 获取供应商基础信息
        HmpServOpera hmpServOpera = providerInfo.getHmpServOpera();
        // 获取医疗特色信息
        HmpHospCharacter hmpHospCharacter = providerInfo.getHmpHospCharacter();
        // 获取联系人信息
        List<HmpServContacts> hmpServContactsList = providerInfo.getHmpServContactsList();
        // 新增 供应商中文名称校验
        if (checkRepeat("CHN", hmpServOpera)) {
            // AjaxResult.error 直接会在前台弹出错误
            return AjaxResult.error("当前新增的供应商中文名称在系统中已存在，请再次确认后重新录入！");
        }
        // 新增 供应商英文名称校验
        if (checkRepeat("EN", hmpServOpera)) {
            return AjaxResult.error("当前新增的机构英文名称在系统中已存在，请再次确认后重新录入！");
        }
        // 使用PubFun完成主键递增
        String servComNo = "H" + PubFun.createMySqlMaxNoUseCache("HmpServOpera", 0, 9);
        hmpServOpera.setServcomno(servComNo);
        // 获取登录人
        String username = getOperator();
        hmpServOpera.setOperator(username);
        // 设置创建日期 创建时间
        hmpServOpera.setMakedate(getCurrentDate());
        hmpServOpera.setMaketime(getCurrentTime());
        // 设置省 市 区县
        if (hmpServOpera.getSelectedOptions().length != 0) {
            hmpServOpera.setProvince(hmpServOpera.getSelectedOptions()[0]);
            hmpServOpera.setCity(hmpServOpera.getSelectedOptions()[1]);
            hmpServOpera.setDistrict(hmpServOpera.getSelectedOptions()[2]);
        }

        // 设置医疗特色信息
        hmpHospCharacter.setServcomno(servComNo);
        hmpHospCharacter.setOperator(username);
        hmpHospCharacter.setMakedate(getCurrentDate());
        hmpHospCharacter.setMaketime(getCurrentTime());

        // 设置联系人信息
        for (HmpServContacts hmpServContacts : hmpServContactsList) {
            String contactno = PubFun.createMySqlMaxNoUseCache("HmpServContacts", 0, 9);
            hmpServContacts.setContactno(contactno);
            hmpServContacts.setServcomno(servComNo);
            hmpServContacts.setOperator(username);
            hmpServContacts.setMakedate(getCurrentDate());
            hmpServContacts.setMaketime(getCurrentTime());
        }
        // 新增供应商基础信息
        int insertHmpServOpera = hmpServOperaService.insertHmpServOpera(hmpServOpera);
        // 新增医疗特色信息
        int insertHmpHospCharacter = hmpHospCharacterService.insertHmpHospCharacter(hmpHospCharacter);
        // 新增联系人信息
        int insertHmpServContacts = hmpServContactsService.insertHmpServContacts(hmpServContactsList);
        // 都新增成功 那么返回成功
        if (insertHmpHospCharacter == 1 && insertHmpServOpera == 1 && insertHmpServContacts >= 1) {
            return toAjax(1);
        } else {
            return toAjax(0);
        }
    }

    /**
     * 修改供应商管理1
     */
    @Log(title = "供应商管理1", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProviderInfo providerInfo) {
        // 表单提交的地区为数组形式，需要封装到实体类中的  省 市 区
        HmpServOpera hmpServOpera = providerInfo.getHmpServOpera();
        String[] selectedOptions = hmpServOpera.getSelectedOptions();
        if (selectedOptions.length != 0) {
            hmpServOpera.setProvince(selectedOptions[0]);
            hmpServOpera.setCity(selectedOptions[1]);
            hmpServOpera.setDistrict(selectedOptions[2]);
        }
        // 修改供应商基本信息
        int updateHmpServOpera = hmpServOperaService.updateHmpServOpera(hmpServOpera);
        // 修改医疗特色信息
        int updateHmpHospCharacter = hmpHospCharacterService.updateHmpHospCharacter(providerInfo.getHmpHospCharacter());
        // 修改联系人信息
        List<HmpServContacts> hmpServContactsList = providerInfo.getHmpServContactsList();
        for (HmpServContacts hmpServContacts : hmpServContactsList) {
            hmpServContacts.setServcomno(providerInfo.getHmpServOpera().getServcomno());
            String contactno = PubFun.createMySqlMaxNoUseCache("HmpServContacts", 0, 9);
            hmpServContacts.setContactno(contactno);
            hmpServContacts.setOperator(getOperator());
            hmpServContacts.setMakedate(getCurrentDate());
            hmpServContacts.setMaketime(getCurrentTime());
        }
        int updateHmpServContacts = hmpServContactsService.updateHmpServContacts(hmpServContactsList);
        if (updateHmpServOpera != 0 && updateHmpHospCharacter != 0 && updateHmpServContacts != 0) {
            return toAjax(1);
        } else {
            return toAjax(0);
        }
    }

    /**
     * 删除供应商管理1
     */
    @Log(title = "供应商管理1", businessType = BusinessType.DELETE)
    @DeleteMapping("/{servcomnos}")
    public AjaxResult remove(@PathVariable String[] servcomnos) {
        return toAjax(hmpServOperaService.deleteHmpServOperaByIds(servcomnos));
    }

    /**
     * 根据供应商编码查询医疗特色信息
     */
    @GetMapping(value = "/character/{servcomno}")
    public AjaxResult character(@PathVariable("servcomno") String servcomno) {
        return AjaxResult.success(hmpHospCharacterService.selectHmpHospCharacterByNo(servcomno));
    }

    /**
     * 根据供应商编码查询联系人信息
     */
    @GetMapping(value = "/concats/{servcomno}")
    public AjaxResult concats(@PathVariable("servcomno") String servcomno) {
        AjaxResult ajaxResult = AjaxResult.success();
        if (StringUtils.isNotEmpty(servcomno)) {
            List<HmpServContacts> hmpServContactsList = hmpServContactsService.selectHmpServContacts(servcomno);
            ajaxResult.put("hmpServContactsList", hmpServContactsList);
        }
        return ajaxResult;
    }

    /**
     * 供应商附件导入
     *
     * @param file      文件
     * @param servcomno 供应商编码
     * @param filedesc  文件描述
     * @return
     * @throws Exception
     */
    @Log(title = "供应商附件", businessType = BusinessType.IMPORT)
    @PostMapping("/uploadFile")
    public AjaxResult importData(MultipartFile file, @RequestParam("servcomno") String servcomno, @RequestParam("filedesc") String filedesc) throws Exception {
        // 获取登录用户名称
        String username = SecurityUtils.getUsername();
        // 根据供应商编码获取供应商信息
        HmpServOpera hmpServOpera = hmpServOperaService.selectHmpServOperaById(servcomno);
        // 文件信息
        HmpFileInfo hmpFileInfo = new HmpFileInfo();
        // 设置文件名称
        hmpFileInfo.setFilename(file.getOriginalFilename());
        // 设置文件来源
        hmpFileInfo.setFilesource(servcomno);
        // 文件信息
        List<HmpFileInfo> hmpFileInfoList = hmpFileInfoService.selectHmpFileInfoList(hmpFileInfo);
        if (hmpFileInfoList.size() > 0) {
            return AjaxResult.error("操作失败-`" + hmpFileInfo.getFilename() + "`文件名已存在！");
        }
        //文件保存地址
        String url = PathUtil.projectPath.substring(6, PathUtil.projectPath.length()) + "sino_file/sino_provider/" + servcomno + "/";
        //保存文件
        boolean flag = FileUtils.saveFileByMultipartFile(file, url);
        if (!flag) {
            return AjaxResult.error("操作失败-请检查`" + hmpFileInfo.getFilename() + "`文件内容是否为空！");
        }
        //文件信息入库
        HmpFileInfo fileInput = new HmpFileInfo();
        fileInput.setFilecode("F" + PubFun.createMySqlMaxNoUseCache("FileInfo", 10, 9));
        fileInput.setFilename(file.getOriginalFilename());
        fileInput.setFilesource(hmpServOpera.getServcomno());
        fileInput.setAlternatefield1(filedesc);
        fileInput.setFilepath(url + file.getOriginalFilename());
        fileInput.setOperator(username);
        fileInput.setMakedate(new Date());
        fileInput.setMaketime(PubFun.getCurrentTime());
        fileInput.setModifydate(new Date());
        fileInput.setModifytime(PubFun.getCurrentTime());
        int i = hmpFileInfoService.insertHmpFileInfo(fileInput);
        if (i > 0) {
            return AjaxResult.success("上传文件成功！");
        } else {
            return AjaxResult.error("上传文件失败！");
        }
    }


    /**
     * 重复性校验
     *
     * @return true 重复 false不重复
     */
    public Boolean checkRepeat(String name, HmpServOpera hmpServOpera) {
        HmpServOpera entity = new HmpServOpera();
        if ("CHN".equals(name)) {
            // 如果中文名称不为空，才会查询数据库。
            String chname = hmpServOpera.getChname();
            if (StringUtils.isNotEmpty(chname)) {
                entity.setChname(chname);
                List<HmpServOpera> hmpServOperas = hmpServOperaService.selectHmpServOperaList(entity);
                if (hmpServOperas.size() != 0) {
                    return true;
                }
            }
        }
        if ("EN".equals(name)) {
            // 如果英文名称不为空，那么才会查询数据库
            String enname = hmpServOpera.getEnname();
            if (StringUtils.isNotEmpty(enname)) {
                entity.setEnname(hmpServOpera.getEnname());
                List<HmpServOpera> hmpServOperas = hmpServOperaService.selectHmpServOperaList(entity);
                if (hmpServOperas.size() != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取当前登录人员
     *
     * @return
     */
    public String getOperator() {
        return SecurityUtils.getUsername();
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public Date getCurrentDate() {
        // 设置创建日期 创建时间
        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM);
        Date makeDate = new Date();
        return makeDate;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public String getCurrentTime() {
        // 设置创建日期 创建时间
        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM);
        Date makeDate = new Date();
        String makeTime = dateFormat.format(makeDate);
        return makeTime;
    }

    // =============== 供应商网络 ===================

    /**
     * 查询供应商网络列表
     */
    @GetMapping("/network/list")
    public TableDataInfo networkList(HmpServOpera hmpServOpera) {
        startPage();
        List<NetworkVO> networkVOList = hmpServOperaService.selectProviderNetwork(hmpServOpera);
        return getDataTable(networkVOList);
    }

    /**
     * 根据供应商编码查询服务网点配置对象
     */
    @GetMapping("/network/list/no")
    public TableDataInfo networkListByNo(HmpComWebSite hmpComWebSite) {
        startPage();
        List<HmpComWebSite> hmpComWebSites = hmpServOperaService.selectHmpComWebSiteByNo(hmpComWebSite);
        return getDataTable(hmpComWebSites);
    }

    /**
     * 根据服务网点编码批量删除服务网点配置对象
     */
    @Log(title = "服务网点删除", businessType = BusinessType.DELETE)
    @DeleteMapping("/website/{websitecode}")
    public AjaxResult removeWebSite(@PathVariable String[] websitecode) {
        return toAjax(hmpServOperaService.deleteHmpComWebSiteByIds(websitecode));
    }

    /**
     * 根据服务网点编码修改服务网点配置对象
     */
    @Log(title = "修改服务网点配置对象", businessType = BusinessType.UPDATE)
    @PutMapping("/website")
    public AjaxResult editWebSite(@RequestBody HmpComWebSite hmpComWebSite) {
        // 获取服务网点配置对象编码
        String websitecode = hmpComWebSite.getWebsitecode();
        if (StringUtils.isEmpty(websitecode)) {
            return null;
        } else {
            // 设置 省市地区
            String[] selectedOptions = hmpComWebSite.getSelectedOptions();
            if (selectedOptions.length != 0) {
                hmpComWebSite.setProvince(selectedOptions[0]);
                hmpComWebSite.setCity(selectedOptions[1]);
                hmpComWebSite.setDistrict(selectedOptions[2]);
            }
            // 设置操作员
            hmpComWebSite.setOperator(getOperator());
            // 设置当前日期
            hmpComWebSite.setMakedate(getCurrentDate());
            // 设置当前时间
            hmpComWebSite.setMaketime(getCurrentTime());
            return toAjax(hmpServOperaService.updateHmpComWebSiteById(hmpComWebSite));
        }
    }

    /**
     * 新增服务网点配置对象
     */
    @Log(title = "服务网点-新增操作", businessType = BusinessType.INSERT)
    @PostMapping("/website")
    public AjaxResult add(@RequestBody HmpComWebSite hmpComWebSite) {
        // 设置服务网点编码
        String webSiteCode = "D" + PubFun.createMySqlMaxNoUseCache("HmpComWebSite", 0, 9);
        hmpComWebSite.setWebsitecode(webSiteCode);
        // 设置网点所在地区
        String[] selectedWebSiteOptions = hmpComWebSite.getSelectedOptions();
        if (selectedWebSiteOptions.length != 0) {
            hmpComWebSite.setProvince(selectedWebSiteOptions[0]);
            hmpComWebSite.setCity(selectedWebSiteOptions[1]);
            hmpComWebSite.setDistrict(selectedWebSiteOptions[2]);
        }
        // 设置操作员
        hmpComWebSite.setOperator(getOperator());
        // 设置创建日期
        hmpComWebSite.setMakedate(getCurrentDate());
        // 设置创建时间
        hmpComWebSite.setMaketime(getCurrentTime());
        // 新增操作
        int insertHmpComWebSite = hmpServOperaService.insertHmpComWebSite(hmpComWebSite);

        if (insertHmpComWebSite == 1) {
            return toAjax(1);
        } else {
            return toAjax(0);
        }
    }

    // ========   网点导入  ===========

    /**
     * 根据供应商编码查询服务网点配置对象
     */
    @GetMapping("/network/list/no/trans")
    public TableDataInfo networkListByNoTrans(HmpComWebSite hmpComWebSite) {
        startPage();
        List<HmpComWebSite> hmpComWebSites = hmpServOperaService.selectHmpComWebSiteByNoTrans(hmpComWebSite);
        return getDataTable(hmpComWebSites);
    }

    /**
     * 服务网点配置对象模板下载
     *
     * @param response
     * @throws IOException
     */
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) throws IOException {
        ExcelUtil<HmpComWebSite> util = new ExcelUtil<>(HmpComWebSite.class);
        util.importTemplateExcel(response, "服务网点配置对象");
    }

    /**
     * 服务网点配置对象导入
     *
     * @param file
     * @param updateSupport
     * @return
     * @throws Exception
     */
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, @RequestParam("updateSupport") boolean updateSupport, @RequestParam("servcomno") String servcomno) {
        ExcelUtil<HmpComWebSite> util = new ExcelUtil<>(HmpComWebSite.class);
        List<HmpComWebSite> hmpComWebSiteList = null;
        try {
            hmpComWebSiteList = util.importExcel(file.getInputStream());
        } catch (Exception ex) {
            return AjaxResult.error("网点数据解析失败，请先下载模板，填写数据，再进行上传！");
        }
        if (hmpComWebSiteList.size() == 0) {
            return AjaxResult.error("上传失败，Excel中网点数据为空");
        }
        for (HmpComWebSite hmpComWebSite : hmpComWebSiteList) {
            // 设置供应商编码
            hmpComWebSite.setServcomno(servcomno);
            // 设置服务网点配置对象编码
            String webSiteCode = "D" + PubFun.createMySqlMaxNoUseCache("HmpComWebSite", 0, 9);
            hmpComWebSite.setWebsitecode(webSiteCode);
            // 设置操作员
            hmpComWebSite.setOperator(getOperator());
            // 设置修改日期
            hmpComWebSite.setMakedate(getCurrentDate());
            // 设置修改时间
            hmpComWebSite.setMaketime(getCurrentTime());
            // 根据州的名称获取到地址List
            List<HmpAddress> hmpAddressContinent = hmpAddressService.selectHmpAddressByName(hmpComWebSite.getContinent());
            // 如果查询出一个那么直接赋值
            try {
                if (hmpAddressContinent.size() == 0) {
                    return AjaxResult.error("数据导入失败，州格式不匹配");
                }
            } catch (Exception ex) {
                return AjaxResult.error("数据导入失败，州格式不匹配");
            }
            if (hmpAddressContinent.size() == 1) {
                hmpComWebSite.setContinent(hmpAddressContinent.get(0).getPlacecode());
            }

            // 根据国家名称获取到地址List
            List<HmpAddress> hmpAddressCountry = hmpAddressService.selectHmpAddressByName(hmpComWebSite.getCountry());
            // 如果查询不到国家 那么.size 会报空指针异常
            try {
                if (hmpAddressCountry.size() == 0) {
                    return AjaxResult.error("数据导入失败，国家格式不匹配");
                }
            } catch (Exception ex) {
                return AjaxResult.error("数据导入失败，国家格式不匹配");
            }
            if (hmpAddressCountry.size() == 1) {
                hmpComWebSite.setCountry(hmpAddressCountry.get(0).getPlacecode());
            }
            // 根据省名称获取到地址List
            List<HmpAddress> hmpAddressProvince = hmpAddressService.selectHmpAddressByName(hmpComWebSite.getProvince());
            try {
                if (hmpAddressProvince.size() == 1) {
                    hmpComWebSite.setProvince(hmpAddressProvince.get(0).getPlacecode());
                } else {
                    if (hmpAddressProvince.size() == 0) {
                        return AjaxResult.error("数据导入失败，省份格式不匹配");
                    }
                    for (HmpAddress hmpAddress : hmpAddressProvince) {
                        if (hmpAddress.getPlacetype().equals("03")) {
                            hmpComWebSite.setProvince(hmpAddress.getPlacecode());
                        }
                    }
                }
            } catch (Exception ex) {
                return AjaxResult.error("数据导入失败，省份格式不匹配");
            }


            // 根据市名获取到地址List
            List<HmpAddress> hmpAddressCity = hmpAddressService.selectHmpAddressByName(hmpComWebSite.getCity());
            try {
                if (hmpAddressCity.size() == 1) {
                    hmpComWebSite.setCity(hmpAddressCity.get(0).getPlacecode());
                } else {
                    if (hmpAddressCity.size() == 0) {
                        return AjaxResult.error("数据导入失败，市格式不匹配");
                    }
                    for (HmpAddress hmpAddress : hmpAddressCity) {
                        if (hmpAddress.getPlacetype().equals("04")) {
                            hmpComWebSite.setCity(hmpAddress.getPlacecode());
                        }
                    }
                }
            } catch (Exception ex) {
                return AjaxResult.error("数据导入失败，市格式不匹配");
            }

            // 根据地区名获取到地址List
            List<HmpAddress> hmpAddressDistrict = hmpAddressService.selectHmpAddressByName(hmpComWebSite.getDistrict());
            try {
                if (hmpAddressDistrict.size() == 1) {
                    hmpComWebSite.setDistrict(hmpAddressDistrict.get(0).getPlacecode());
                } else {
                    if (hmpAddressDistrict.size() == 0) {
                        return AjaxResult.error("数据导入失败，地区格式不匹配");
                    }
                    for (HmpAddress hmpAddress : hmpAddressCity) {
                        if (hmpAddress.getPlacetype().equals("05")) {
                            hmpComWebSite.setDistrict(hmpAddress.getPlacecode());
                        }
                    }
                }
            } catch (Exception ex) {
                return AjaxResult.error("数据导入失败，地区格式不匹配");
            }

        }
        // 插入数据
        int inserHmpWebSiteList = hmpServOperaService.insertHmpComWebSiteList(hmpComWebSiteList);
        return toAjax(inserHmpWebSiteList);
    }
    // ======== 医生网络-首页  ===========


    // ========= 服务实施-查询及处理 ============

    @GetMapping("/websitename/{websitecode}")
    public AjaxResult selectHmpComWebSiteByCode(@PathVariable("websitecode") String websitecode) {
        HmpComWebSite hmpComWebSite = hmpServOperaService.selectHmpComWebSiteByCode(websitecode);
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("hmpComWebSite", hmpComWebSite);
        return ajaxResult;
    }

}
