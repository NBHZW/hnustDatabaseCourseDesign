package com.ruoyi.web.controller.studentInformation;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
import com.ruoyi.studentInformation.domain.ChangeCode;
import com.ruoyi.studentInformation.service.IChangeCodeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
/**
 * 变更代码Controller
 *
 * @author ruoyi
 * @date 2023-11-08
 */
@RestController
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/system/changeCode")
public class ChangeCodeController extends BaseController
{
    @Autowired
    private IChangeCodeService changeCodeService;

    /**
     * 查询变更代码列表
     */
    @PreAuthorize("@ss.hasPermi('system:changeCode:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChangeCode changeCode)
    {
        startPage();
        List<ChangeCode> list = changeCodeService.selectChangeCodeList(changeCode);
        return getDataTable(list);
    }

    /**
     * 导出变更代码列表
     */
    @PreAuthorize("@ss.hasPermi('system:changeCode:export')")
    @Log(title = "变更代码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChangeCode changeCode)
    {
        List<ChangeCode> list = changeCodeService.selectChangeCodeList(changeCode);
        ExcelUtil<ChangeCode> util = new ExcelUtil<ChangeCode>(ChangeCode.class);
        util.exportExcel(response, list, "变更代码数据");
    }

    /**
     * 获取变更代码详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:changeCode:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(changeCodeService.selectChangeCodeById(id));
    }

    /**
     * 新增变更代码
     */
    @PreAuthorize("@ss.hasPermi('system:changeCode:add')")
    @Log(title = "变更代码", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChangeCode changeCode)
    {
        return toAjax(changeCodeService.insertChangeCode(changeCode));
    }

    /**
     * 修改变更代码
     */
    @PreAuthorize("@ss.hasPermi('system:changeCode:edit')")
    @Log(title = "变更代码", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChangeCode changeCode)
    {
        return toAjax(changeCodeService.updateChangeCode(changeCode));
    }

    /**
     * 删除变更代码
     */
    @PreAuthorize("@ss.hasPermi('system:changeCode:remove')")
    @Log(title = "变更代码", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(changeCodeService.deleteChangeCodeByIds(ids));
    }
}