package com.ruoyi.web.controller.studentInformation;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.studentInformation.mapper.StudentMapper;
import com.ruoyi.studentInformation.service.impl.StudentServiceImpl;
import org.apache.poi.hssf.record.DVALRecord;
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
import com.ruoyi.studentInformation.domain.Clazz;
import com.ruoyi.studentInformation.service.IClazzService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 班级信息Controller
 *
 * @author ruoyi
 * @date 2023-11-07
 */
@RestController
@RequestMapping("/system/clazz")
@Transactional(rollbackFor = Exception.class)
public class ClazzController extends BaseController
{
    @Autowired
    private IClazzService clazzService;

    @Autowired
    private StudentMapper studentMapper;



    /**
     * 查询班级信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:clazz:list')")
    @GetMapping("/list")
    public TableDataInfo list(Clazz clazz)
    {
        startPage();
        List<Clazz> list = clazzService.selectClazzList(clazz);
        for (Clazz c : list) {
            if(studentMapper.getStudentId(c.getMonitor())==0){
                c.setMonitor(c.getMonitor()+"(该学号已经不存在)");
            }else{
                c.setMonitor(c.getMonitor()+studentMapper.getStudentNameByStudentId(c.getMonitor()));
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出班级信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:clazz:export')")
    @Log(title = "班级信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Clazz clazz)
    {
        List<Clazz> list = clazzService.selectClazzList(clazz);
        ExcelUtil<Clazz> util = new ExcelUtil<Clazz>(Clazz.class);
        util.exportExcel(response, list, "班级信息数据");
    }

    /**
     * 获取班级信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:clazz:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(clazzService.selectClazzById(id));
    }

    /**
     * 新增班级信息
     */
    @PreAuthorize("@ss.hasPermi('system:clazz:add')")
    @Log(title = "班级信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Clazz clazz)
    {
        Integer amount = studentMapper.getStudentId(clazz.getMonitor());
        if(amount==0)  return AjaxResult.error("添加失败，请检查班长学号是否有误");
        return toAjax(clazzService.insertClazz(clazz));
    }

    /**
     * 修改班级信息
     */
    @PreAuthorize("@ss.hasPermi('system:clazz:edit')")
    @Log(title = "班级信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Clazz clazz)
    {
        Integer amount = studentMapper.getStudentId(clazz.getMonitor());
        if(amount==0)  return AjaxResult.error("修改失败，请检查班长学号是否有误");
        return toAjax(clazzService.updateClazz(clazz));
    }

    /**
     * 删除班级信息
     */
    @PreAuthorize("@ss.hasPermi('system:clazz:remove')")
    @Log(title = "班级信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(clazzService.deleteClazzByIds(ids));
    }
}
