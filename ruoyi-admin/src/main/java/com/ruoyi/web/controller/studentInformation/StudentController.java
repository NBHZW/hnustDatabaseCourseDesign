package com.ruoyi.web.controller.studentInformation;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.studentInformation.domain.Reward;
import com.ruoyi.studentInformation.mapper.DepartmentMapper;
import com.ruoyi.studentInformation.service.*;
import com.ruoyi.studentInformation.service.impl.ChangeServiceImpl;
import org.apache.poi.hssf.extractor.OldExcelExtractor;
import org.aspectj.weaver.loadtime.Aj;
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
import com.ruoyi.studentInformation.domain.Student;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2023-11-03
 */
@RestController
@RequestMapping("/system/student")
@Transactional(rollbackFor = Exception.class)
public class StudentController extends BaseController
{
    @Autowired
    private IStudentService studentService;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private IRewardService rewardService;

    @Autowired
    private IPunishmentService punishmentService;

    @Autowired
    private IChangeService changeService;

    @Autowired
    private IPersonsituationService personsituationService;

    @Autowired
    private IClazzService clazzService;

    /**
     * 查询学生信息表列表
     */
    @PreAuthorize("@ss.hasPermi('system:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(Student student)
    {
        startPage();
        List<Student> list = studentService.selectStudentList(student);
        return getDataTable(list);
    }

    /**
     * 导出学生信息表列表
     */
    @PreAuthorize("@ss.hasPermi('system:student:export')")
    @Log(title = "学生信息表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Student student)
    {
        List<Student> list = studentService.selectStudentList(student);
        ExcelUtil<Student> util = new ExcelUtil<Student>(Student.class);
        util.exportExcel(response, list, "学生信息表数据");
    }

    /**
     * 获取学生信息表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:student:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(studentService.selectStudentById(id));
    }

    /**
     * 新增学生信息表
     */
    @PreAuthorize("@ss.hasPermi('system:student:add')")
    @Log(title = "学生信息表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Student student)
    {
        // 性别输入格式检测
        String sex = student.getSex();
        if(sex.equals("M")){
            student.setSex("男");
        } else if (sex.equals("F")) {
            student.setSex("女");
        } else if (!sex.equals("男") && !sex.equals("女")) {
            return AjaxResult.error("性别输入有误");
        }

        // 学号唯一性检测
        String studentId = student.getStudentId();
        Boolean only = studentService.checkOnlyId(studentId);
        if(only==false) return AjaxResult.error("学号已存在");


        // 学院名称/编号是否存在检测
        List<String> departmentList=departmentMapper.getDepartment();
        List<String> departmentIdList=departmentMapper.getIds();
        int i=0;
        for (i = 0; i < departmentList.size(); i++) {
            if(student.getDepartment().equals(departmentList.get(i))){
                i=-1;
                break;
            } // 直接写的学院名称
            else if (student.getDepartment().equals(departmentIdList.get(i))) {
                // 写的是学院编号 那么改为对应的学院名称
                student.setDepartment(departmentList.get(i));
                i=-1;
            }
        }
        if(i>=0) return AjaxResult.error("学院名称/编号有误");

        return toAjax(studentService.insertStudent(student));
    }

    /**
     * 修改学生信息表
     */
    @PreAuthorize("@ss.hasPermi('system:student:edit')")
    @Log(title = "学生信息表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Student student)
    {
        // 性别输入格式检测
        String sex = student.getSex();
        if(sex.equals("M")){
            student.setSex("男");
        } else if (sex.equals("F")) {
            student.setSex("女");
        } else if (!sex.equals("男") && !sex.equals("女")) {
            return AjaxResult.error("性别输入有误");
        }

        // 学号唯一性检测
        String NewStudentId = student.getStudentId();
        String OldStudentId = studentService.getStudentIdById(student.getId());


        List<String> departmentList=departmentMapper.getDepartment();
        List<String> departmentIdList=departmentMapper.getIds();
        int i=0;
        for (i = 0; i < departmentList.size(); i++) {
            if(student.getDepartment().equals(departmentList.get(i))){
                i=-1;
                break;
            } // 直接写的学院名称
            else if (student.getDepartment().equals(departmentIdList.get(i))) {
                // 写的是学院编号 那么改为对应的学院名称
                student.setDepartment(departmentList.get(i));
                i=-1;
            }
        }
        if(i>=0) return AjaxResult.error("学院名称/编号有误");

        // 检测修改前和修改后的学号是否相同  如果不同 则需要查看新学号是否唯一  反之则不需要
        if(!OldStudentId.equals(NewStudentId)){
            Boolean only = studentService.checkOnlyId(NewStudentId);
            if(only==false) return AjaxResult.error("学号已存在");
        }
        /*Integer amount=departmentMapper.getDepartmentId(student.getDepartment());
        if(amount==0) return AjaxResult.error("修改失败,检查学院编号是否有误");*/

        //级联学号修改
        changeService.updateStudentId(OldStudentId,NewStudentId);
        punishmentService.updateStudentId(OldStudentId,NewStudentId);
        rewardService.updateStudentId(OldStudentId,NewStudentId);
        personsituationService.updateStudentId(OldStudentId,NewStudentId);
        clazzService.updateStudentId(OldStudentId,NewStudentId);


        return toAjax(studentService.updateStudent(student));
    }

    /**
     * 删除学生信息表
     */
    @PreAuthorize("@ss.hasPermi('system:student:remove')")
    @Log(title = "学生信息表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        // 级联删除
        for (Long id : ids) {
            String studentId=studentService.getStudentIdById(id);
            rewardService.deleteRewardByStudentId(studentId);
            punishmentService.deletePunishmentByStudentId(studentId);
            changeService.deleteChangeByStudentId(studentId);
            personsituationService.deletePersonsituationByStudentId(studentId);
        }



        return toAjax(studentService.deleteStudentByIds(ids));
    }
}