package com.paic.ehis.base.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.base.domain.ContractVO;
import com.paic.ehis.base.domain.HmpContract;
import com.paic.ehis.base.domain.HmpFileInfo;
import com.paic.ehis.base.domain.HmpServConpro;
import com.paic.ehis.base.service.IHmpContractService;
import com.paic.ehis.base.service.IHmpFileInfoService;
import com.paic.ehis.base.service.IHmpServConproService;
import com.paic.ehis.base.service.IHmpServProjectService;
import com.paic.ehis.common.core.utils.PathUtil;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.utils.file.FileUtils;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.PubFun;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 供应商合约Controller
 *
 * @author sino
 * @date 2020-09-23
 */
@RestController
@RequestMapping("/contract")
public class HmpContractController extends BaseController {

    @Autowired
    private IHmpContractService hmpContractService;

    @Autowired
    private IHmpFileInfoService hmpFileInfoService;

    @Autowired
    private IHmpServConproService hmpServConproService;

    @Autowired
    private IHmpServProjectService hmpServProjectService;

    /**
     * 查询供应商合约列表
     */
    @PreAuthorize("@ss.hasPermi('provider:contract:list')")
    @GetMapping("/list")
    public TableDataInfo list(HmpContract hmpContract) throws ParseException {
        startPage();
        List<HmpContract> list = hmpContractService.selectHmpContractList(hmpContract);
        return getDataTable(list);
    }

    /**
     * 导出供应商合约列表
     */
    @PreAuthorize("@ss.hasPermi('provider:contract:export')")
    @Log(title = "供应商合约", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HmpContract hmpContract) throws IOException {
        List<HmpContract> list = hmpContractService.selectHmpContractList(hmpContract);
        ExcelUtil<HmpContract> util = new ExcelUtil<HmpContract>(HmpContract.class);
        util.exportExcel(response, list, "contract");
    }

    /**
     * 获取供应商合约详细信息
     */
    @PreAuthorize("@ss.hasPermi('provider:contract:query')")
    @GetMapping(value = "/{contractno}")
    public AjaxResult getInfo(@PathVariable("contractno") String contractno) {
        return AjaxResult.success(hmpContractService.selectHmpContractById(contractno));
    }

    /**
     * 新增供应商合约
     */
    @PreAuthorize("@ss.hasPermi('provider:contract:add')")
    @Log(title = "供应商合约 - 新增", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ContractVO contractVO) {
        HmpContract hmpContract = contractVO.getHmpContract();
        HmpContract contractByName = hmpContractService.getContractByName(hmpContract.getContractname());
        if (contractByName != null) {
            return AjaxResult.error("当前录入的[合约名称]在系统中已存在，请再次确认后重新录入！");
        }
        // 合约编码-主键
        String contractno = "C" + PubFun.createMySqlMaxNoUseCache("ContractNo", 0, 9);
        hmpContract.setContractno(contractno);
        // 状态01 可用
        hmpContract.setConstate("01");
        // 操作员
        hmpContract.setOperator(SecurityUtils.getUsername());

        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM);
        Date date = new Date();
        String format = dateFormat.format(date);
        // 创建日期
        hmpContract.setMakedate(date);
        // 创建时间
        hmpContract.setMaketime(format);
        // 修改日期
        hmpContract.setModifydate(date);
        // 修改时间
        hmpContract.setModifytime(format);

        Integer insertHmpServConpro = null;
        // 获取供应商服务项目
        List<HmpServConpro> hmpServConproList = contractVO.getHmpServConpro();
        // 存储project编码的set
        Set<String> projectSet = new HashSet<>();
        if (hmpServConproList.size() != 0) {
            for (HmpServConpro hmpServConpro : hmpServConproList) {
                // 设置合约编码
                if (hmpServConpro != null) {
                    hmpServConpro.setContactno(contractno);
                }
                // 设置操作员
                hmpServConpro.setOperator(getOperator());
                // 设置创建日期
                hmpServConpro.setMakedate(getCurrentDate());
                // 设置修改日期
                hmpServConpro.setModifieddate(getCurrentDate());
                // 设置创建时间
                hmpServConpro.setMaketime(getCurrentTime());
                // 设置修改时间
                hmpServConpro.setModifiedtime(getCurrentTime());

                // 获取projectcode
                String projectcode = hmpServConpro.getProjectcode();
                projectSet.add(projectcode);
            }
            // 如果项目数目 不等于名称数目 那么说明有重复的服务项目
            if (hmpServConproList.size() != projectSet.size()) {
                return AjaxResult.error("请检查该供应商是否有提供重复服务！");
            }
            // 新增服务项目
            insertHmpServConpro = hmpServConproService.insertHmpServConpro(hmpServConproList);
        }

        // 新增供应商合约
        int insertHmpContract = hmpContractService.insertHmpContract(hmpContract);

        // 返回值
        if (insertHmpContract == 1) {
            return toAjax(1);
        } else {
            return toAjax(0);
        }
    }

    /**
     * 修改供应商合约
     */
    @PreAuthorize("@ss.hasPermi('provider:contract:edit')")
    @Log(title = "供应商合约", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ContractVO contractVO) {
        // 获取供应商合约信息
        HmpContract hmpContract = contractVO.getHmpContract();
        // 合约状态
        hmpContract.setConstate("01");
        // 操作员
        hmpContract.setOperator(getOperator());
        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM);
        Date date = new Date();
        String format = dateFormat.format(date);
        // 修改日期
        hmpContract.setModifydate(getCurrentDate());
        // 修改时间
        hmpContract.setModifytime(getCurrentTime());

        // 删除旧的服务项目列表
        hmpServConproService.deleteHmpServConproByNo(hmpContract.getContractno());
        // 获取新的服务项目列表
        List<HmpServConpro> hmpServConproList = contractVO.getHmpServConpro();
        // 创建Set集合 Set存储不重复数据 将projectcode存进来，如果不等于List的长度 那么说明有重复
        Set<String> projectSet = new HashSet<>();
        if (hmpServConproList.size() != 0) {
            for (HmpServConpro hmpServConpro : hmpServConproList) {
                // 设置contractno
                hmpServConpro.setContactno(hmpContract.getContractno());
                hmpServConpro.setOperator(getOperator());
                hmpServConpro.setMakedate(getCurrentDate());
                hmpServConpro.setMaketime(getCurrentTime());
                hmpServConpro.setModifiedtime(getCurrentTime());
                hmpServConpro.setModifieddate(getCurrentDate());
                // 如果项目编码传递过来是编码 不做处理，如果不是编码那么根据名称查询编码
                if (!hmpServConpro.getProjectcode().startsWith("PJ")) {
                    String projectcode = hmpServProjectService.selectHmpServProjectCodeByName(hmpServConpro.getProjectcode()).getProjectcode();
                    // 设置项目编码
                    if (projectcode != null) {
                        hmpServConpro.setProjectcode(projectcode);
                    }
                }
                // 存储projectcode
                String projectcode = hmpServConpro.getProjectcode();
                projectSet.add(projectcode);
            }
            // 循环遍历List集合查看是否有重复
            if (hmpServConproList.size() != projectSet.size()) {
                return AjaxResult.error("请检查该供应商是否有提供重复服务！");
            }
            // 插入服务项目
            hmpServConproService.insertHmpServConpro(hmpServConproList);
        }
        return toAjax(hmpContractService.updateHmpContract(hmpContract));
    }

    /**
     * 删除供应商合约
     */
    @PreAuthorize("@ss.hasPermi('provider:contract:remove')")
    @Log(title = "供应商合约", businessType = BusinessType.DELETE)
    @DeleteMapping("/{contractnos}")
    public AjaxResult remove(@PathVariable String[] contractnos) {
        return toAjax(hmpContractService.deleteHmpContractByIds(contractnos));
    }

    /**
     * 查询合约对象编码
     */
    @GetMapping("/getContractObj")
    public AjaxResult getContractObj() {
        return AjaxResult.success(hmpContractService.getContractObj());
    }

    @GetMapping("/getExpireContract")
    public AjaxResult getExpireContract() {
        return AjaxResult.success(hmpContractService.getExpireContract());
    }


    /**
     * 供应商合约文件上传
     *
     * @param file
     * @param contractno
     * @param filedesc
     * @return
     * @throws Exception
     */
    @PostMapping("/uploadFile")
    public AjaxResult importData(MultipartFile file, @RequestParam("contractno") String contractno, @RequestParam("filedesc") String filedesc) throws Exception {
        // 获取登录用户名称
        String username = SecurityUtils.getUsername();
        // 根据合约信息
        HmpContract hmpContract = hmpContractService.selectHmpContractById(contractno);
        // 文件信息
        HmpFileInfo hmpFileInfo = new HmpFileInfo();
        // 设置文件名称
        hmpFileInfo.setFilename(file.getOriginalFilename());
        // 设置文件来源
        hmpFileInfo.setFilesource(contractno);
        // 文件信息
        List<HmpFileInfo> hmpFileInfoList = hmpFileInfoService.selectHmpFileInfoList(hmpFileInfo);
        if (hmpFileInfoList.size() > 0) {
            return AjaxResult.error("操作失败-`" + hmpFileInfo.getFilename() + "`文件名已存在！");
        }
        //文件保存地址
        String url = PathUtil.projectPath.substring(6, PathUtil.projectPath.length()) + "sino_file/sino_provider/contract/" + contractno + "/";
        //保存文件
        boolean flag = FileUtils.saveFileByMultipartFile(file, url);
        if (!flag) {
            return AjaxResult.error("操作失败-请检查`" + hmpFileInfo.getFilename() + "`文件内容是否为空！");
        }
        //文件信息入库
        HmpFileInfo fileInput = new HmpFileInfo();
        fileInput.setFilecode("F" + PubFun.createMySqlMaxNoUseCache("FileInfo", 10, 9));
        fileInput.setFilename(file.getOriginalFilename());
        fileInput.setFilesource(hmpContract.getContractno());
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
     * 根据合约编码查询服务项目
     */
    @GetMapping(value = "/conpro/{contactno}")
    public AjaxResult getConpro(@PathVariable("contactno") String contactno) {
        AjaxResult ajaxResult = AjaxResult.success();
        if (StringUtils.isNotEmpty(contactno)) {
            List<HmpServConpro> hmpServConproList = hmpServConproService.selectHmpServConproByNo(contactno);
            // 如果是为了锁住 那么需要将projectcode设置为projectname 如果不锁住那么不需要
//            for (HmpServConpro hmpServConpro : hmpServConproList) {
//                HmpServProject hmpServProjectName = hmpServProjectService.selectHmpServProjectNameByCode(hmpServConpro.getProjectcode());
//                hmpServConpro.setProjectcode(hmpServProjectName.getProjectname());
//            }
            ajaxResult.put("hmpServConproList", hmpServConproList);
        }
        return ajaxResult;
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

}
