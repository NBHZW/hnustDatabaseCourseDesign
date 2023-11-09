package com.ruoyi.web.controller.studentInformation;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.studentInformation.mapper.ChangeMapper;
import com.ruoyi.studentInformation.mapper.StudentMapper;
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
import com.ruoyi.studentInformation.domain.Change;
import com.ruoyi.studentInformation.service.IChangeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学籍信息变更Controller
 *
 * @author ruoyi
 * @date 2023-11-05
 */
@RestController
@RequestMapping("/system/change")
public class ChangeController extends BaseController
{
    @Autowired
    private IChangeService changeService;

    @Autowired
    private StudentMapper studentMapper;


    // TODO 更改学籍信息的代码是否存在于学籍更改表中的检测功能

    /**
     * 查询学籍信息变更列表
     */
    @PreAuthorize("@ss.hasPermi('system:change:list')")
    @GetMapping("/list")
    public TableDataInfo list(Change change)
    {
        startPage();
        List<Change> list = changeService.selectChangeList(change);
        return getDataTable(list);
    }

    /**
     * 导出学籍信息变更列表
     */
    @PreAuthorize("@ss.hasPermi('system:change:export')")
    @Log(title = "学籍信息变更", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Change change)
    {
        List<Change> list = changeService.selectChangeList(change);
        ExcelUtil<Change> util = new ExcelUtil<Change>(Change.class);
        util.exportExcel(response, list, "学籍信息变更数据");
    }

    /**
     * 获取学籍信息变更详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:change:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(changeService.selectChangeById(id));
    }

    /**
     * 新增学籍信息变更
     */
    @PreAuthorize("@ss.hasPermi('system:change:add')")
    @Log(title = "学籍信息变更", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Change change)
    {
        Integer amount = studentMapper.getStudentId(change.getStudentId());
        if(amount==0) return AjaxResult.error("添加失败,检查学号是否有误");

        return toAjax(changeService.insertChange(change));
    }

    /**
     * 修改学籍信息变更
     */
    @PreAuthorize("@ss.hasPermi('system:change:edit')")
    @Log(title = "学籍信息变更", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Change change)
    {
        Integer amount = studentMapper.getStudentId(change.getStudentId());
        if(amount==0) return AjaxResult.error("修改失败,检查学号是否有误");
        return toAjax(changeService.updateChange(change));
    }

    /**
     * 删除学籍信息变更
     */
    @PreAuthorize("@ss.hasPermi('system:change:remove')")
    @Log(title = "学籍信息变更", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(changeService.deleteChangeByIds(ids));
    }
}
