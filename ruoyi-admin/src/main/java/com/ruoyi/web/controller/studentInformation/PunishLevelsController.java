package com.ruoyi.web.controller.studentInformation;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.studentInformation.domain.PunishLevels;
import com.ruoyi.studentInformation.service.IPunishLevelsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 处罚等级Controller
 *
 * @author ruoyi
 * @date 2023-11-08
 */
@RestController
@RequestMapping("/system/levels")
public class PunishLevelsController extends BaseController
{
    @Autowired
    private IPunishLevelsService punishLevelsService;

    /**
     * 查询处罚等级代码列表
     */
    @PreAuthorize("@ss.hasPermi('system:levels:list')")
    @GetMapping("/list")
    public TableDataInfo list(PunishLevels punishLevels)
    {
        startPage();
        List<PunishLevels> list = punishLevelsService.selectPunishLevelsList(punishLevels);
        return getDataTable(list);
    }

    /**
     * 导出处罚等级代码列表
     */
    @PreAuthorize("@ss.hasPermi('system:levels:export')")
    @Log(title = "处罚等级代码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PunishLevels punishLevels)
    {
        List<PunishLevels> list = punishLevelsService.selectPunishLevelsList(punishLevels);
        ExcelUtil<PunishLevels> util = new ExcelUtil<PunishLevels>(PunishLevels.class);
        util.exportExcel(response, list, "处罚等级代码数据");
    }

    /**
     * 获取处罚等级代码详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:levels:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(punishLevelsService.selectPunishLevelsById(id));
    }

    /**
     * 新增处罚等级代码
     */
    @PreAuthorize("@ss.hasPermi('system:levels:add')")
    @Log(title = "处罚等级代码", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PunishLevels punishLevels)
    {
        return toAjax(punishLevelsService.insertPunishLevels(punishLevels));
    }

    /**
     * 修改处罚等级代码
     */
    @PreAuthorize("@ss.hasPermi('system:levels:edit')")
    @Log(title = "处罚等级代码", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PunishLevels punishLevels)
    {
        return toAjax(punishLevelsService.updatePunishLevels(punishLevels));
    }

    /**
     * 删除处罚等级代码
     */
    @PreAuthorize("@ss.hasPermi('system:levels:remove')")
    @Log(title = "处罚等级代码", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(punishLevelsService.deletePunishLevelsByIds(ids));
    }
}