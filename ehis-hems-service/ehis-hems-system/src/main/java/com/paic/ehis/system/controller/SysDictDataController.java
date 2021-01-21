package com.paic.ehis.system.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.dto.DictDetailDTO;
import com.paic.ehis.system.domain.vo.DictTypeVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.system.domain.SysDictData;
import com.paic.ehis.system.service.ISysDictDataService;
import com.paic.ehis.system.service.ISysDictTypeService;

/**
 * 数据字典信息
 *
 * @author admin
 */
@RestController
@RequestMapping("/dict/data")
public class SysDictDataController extends BaseController
{
    @Autowired
    private ISysDictDataService dictDataService;

    @Autowired
    private ISysDictTypeService dictTypeService;

    @PreAuthorize("@ss.hasPermi('system:dict:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysDictData dictData)
    {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:dict:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDictData dictData) throws IOException
    {
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        ExcelUtil<SysDictData> util = new ExcelUtil<SysDictData>(SysDictData.class);
        util.exportExcel(response, list, "字典数据");
    }

    /**
     * 查询字典数据详细
     */
    @PreAuthorize("@ss.hasPermi('system:dict:query')")
    @GetMapping(value = "/{dictCode}")
    public AjaxResult getInfo(@PathVariable Long dictCode)
    {
        return AjaxResult.success(dictDataService.selectDictDataById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType)
    {
        return AjaxResult.success(dictTypeService.selectDictDataByType(dictType));
    }

    @PostMapping(value = "/type/list")
    public AjaxResult dictTypeList(@Validated @RequestBody List<DictTypeVo> dictsList){
        return AjaxResult.success(dictTypeService.selectDictDataByTypeList(dictsList));
    }

    /**
     * 新增字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:add')")
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysDictData dict)
    {
        dict.setCreateBy(SecurityUtils.getUsername());
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改保存字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:edit')")
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysDictData dict)
    {
        dict.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(dictDataService.updateDictData(dict));
    }

    /**
     * 删除字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:remove')")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictCodes}")
    public AjaxResult remove(@PathVariable Long[] dictCodes)
    {
        return toAjax(dictDataService.deleteDictDataByIds(dictCodes));
    }

    @ApiOperation("查询字典详情不分页")
    @PostMapping(value = "/queryAllByName")
    public List<DictDetailDTO> getDictDetailsByDictName(@RequestBody String dictName){
        List<SysDictData> sysDictData = dictTypeService.selectDictDataByType(dictName);
        List<DictDetailDTO> dictDetailDTOS =  new ArrayList<>();
        for (SysDictData sysDictDatum : sysDictData) {
            DictDetailDTO dictDetailDTO = new DictDetailDTO();
            dictDetailDTO.setId(sysDictDatum.getDictCode());
            dictDetailDTO.setLabel(sysDictDatum.getDictLabel());
            dictDetailDTO.setValue(sysDictDatum.getDictValue());
            dictDetailDTO.setSort(sysDictDatum.getDictSort().toString());
            Date tCreateTime = sysDictDatum.getCreateTime();
            if(tCreateTime == null){
                dictDetailDTO.setCreateTime(null);
            }else{
                dictDetailDTO.setCreateTime(new Timestamp(sysDictDatum.getCreateTime().getTime()));
            }

            dictDetailDTOS.add(dictDetailDTO);
        }
        return dictDetailDTOS;
    }


}
