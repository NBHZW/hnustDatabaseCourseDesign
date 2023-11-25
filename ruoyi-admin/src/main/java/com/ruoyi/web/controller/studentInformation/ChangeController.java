package com.ruoyi.web.controller.studentInformation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.studentInformation.domain.Personsituation;
import com.ruoyi.studentInformation.mapper.ChangeCodeMapper;
import com.ruoyi.studentInformation.mapper.ChangeMapper;
import com.ruoyi.studentInformation.mapper.StudentMapper;
import com.ruoyi.studentInformation.service.IPersonsituationService;
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
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/system/change")
public class ChangeController extends BaseController
{
    @Autowired
    private IChangeService changeService;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ChangeCodeMapper changeCodeMapper;

    @Autowired
    private IPersonsituationService personsituationService;


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

        for (Change c : list) {
            if(c.getDescription()==null){
                c.setDescription(changeCodeMapper.getChangeDescription(c.getChanges()));
            }else{
                c.setDescription(c.getDescription()+"("+changeCodeMapper.getChangeDescription(c.getChanges())+")");
            }
        }

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
        // 学号存在性检测
        Integer amount = studentMapper.getStudentId(change.getStudentId());
        if(amount==0) return AjaxResult.error("添加失败,检查学号是否有误");

        // 学籍更改代码是否存在检测
        Long changeCode = change.getChanges();
        Integer i = changeCodeMapper.checkChangeCode(changeCode);
        if(i==0){
            return AjaxResult.error("添加失败,学籍变更代码不存在");
        }

        // 同步个人信息的新增
        int i1 = changeService.insertChange(change);
        String recordNumber=changeService.getMostNewChange();
        Personsituation p=new Personsituation();
        p.setStudentId(change.getStudentId());
        p.setNumber(recordNumber);
        p.setPersonChange(String.valueOf(change.getChanges()));
        p.setName(studentMapper.getStudentNameByStudentId(change.getStudentId()));
        personsituationService.insertPersonsituation(p);

        return toAjax(i1);
    }

    /**
     * 修改学籍信息变更
     */
    @PreAuthorize("@ss.hasPermi('system:change:edit')")
    @Log(title = "学籍信息变更", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Change change)
    {
        System.out.println(change);

        Integer amount = studentMapper.getStudentId(change.getStudentId());
        if(amount==0) return AjaxResult.error("修改失败,检查学号是否有误");

        // 学籍更改代码是否存在检测
        Long changeCode = change.getChanges();
        Integer i = changeCodeMapper.checkChangeCode(changeCode);
        if(i==0){
            return AjaxResult.error("修改失败,学籍变更代码不存在");
        }

        // 同步个人信息的修改
        Long oldChanges=changeService.getChangesByid(change.getId()); // 获得修改前的学籍变更代码 用于锁定数据

        Map map=new HashMap<>();// 旧数据存储
        map.put("studentId",changeService.getStudentIdByid(change.getId()));
        map.put("number",change.getId());
        map.put("personChange",changeService.getLevelsByid(change.getId()));



        int i1 = changeService.updateChange(change);
        String recordNumber= String.valueOf(change.getId());
        Personsituation p=new Personsituation();
        p.setStudentId(change.getStudentId());
        p.setNumber(recordNumber);
        p.setPersonChange(String.valueOf(change.getChanges()));
        p.setName(studentMapper.getStudentNameByStudentId(change.getStudentId()));
        p.setId(personsituationService.getPersonSituationIdChange(map));

        System.out.println(p);
        personsituationService.updatePersonsituation(p);

        System.out.println(change);

        return toAjax(i1);
    }

    /**
     * 删除学籍信息变更
     */
    @PreAuthorize("@ss.hasPermi('system:change:remove')")
    @Log(title = "学籍信息变更", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {

        for (int i = 0; i < ids.length; i++) {
            String studentId=changeService.getStudentIdByid(ids[i]);
            String levels=changeService.getLevelsByid(ids[i]);
            Map map=new HashMap<>();
            map.put("studentId",studentId);
            map.put("levels",levels);
            map.put("id",ids[i]);
            personsituationService.deletePersonsituationChange(map);
        }


        return toAjax(changeService.deleteChangeByIds(ids));
    }
}
