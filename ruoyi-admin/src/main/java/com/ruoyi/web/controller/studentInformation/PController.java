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
import com.ruoyi.studentInformation.domain.P;
import com.ruoyi.studentInformation.service.IPService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 测试Controller
 *
 * @author ruoyi
 * @date 2023-11-11
 */
@RestController
@RequestMapping("/system/p")
@Transactional(rollbackFor = Exception.class)
public class PController extends BaseController
{
    @Autowired
    private IPService pService;

    /**
     * 查询测试列表
     */
    @PreAuthorize("@ss.hasPermi('system:p:list')")
    @GetMapping("/list")
    public TableDataInfo list(P p)
    {
        startPage();
        List<P> list = pService.selectPList(p);
        return getDataTable(list);
    }

    /**
     * 导出测试列表
     */
    @PreAuthorize("@ss.hasPermi('system:p:export')")
    @Log(title = "测试", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, P p)
    {
        List<P> list = pService.selectPList(p);
        ExcelUtil<P> util = new ExcelUtil<P>(P.class);
        util.exportExcel(response, list, "测试数据");
    }

    /**
     * 获取测试详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:p:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pService.selectPById(id));
    }

    /**
     * 新增测试
     */
    @PreAuthorize("@ss.hasPermi('system:p:add')")
    @Log(title = "测试", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody P p)
    {
        return toAjax(pService.insertP(p));
    }

    /**
     * 修改测试
     */
    @PreAuthorize("@ss.hasPermi('system:p:edit')")
    @Log(title = "测试", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody P p)
    {
        return toAjax(pService.updateP(p));
    }

    /**
     * 删除测试
     */
    @PreAuthorize("@ss.hasPermi('system:p:remove')")
    @Log(title = "测试", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pService.deletePByIds(ids));
    }
}
