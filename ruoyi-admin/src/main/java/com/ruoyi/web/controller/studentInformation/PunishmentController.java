package com.ruoyi.web.controller.studentInformation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.studentInformation.domain.Personsituation;
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
import com.ruoyi.studentInformation.domain.Punishment;
import com.ruoyi.studentInformation.service.IPunishmentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 处罚表Controller
 *
 * @author ruoyi
 * @date 2023-11-06
 */
@RestController
@RequestMapping("/system/punishment")
@Transactional(rollbackFor = Exception.class)
public class PunishmentController extends BaseController
{
    @Autowired
    private IPunishmentService punishmentService;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private IPersonsituationService personsituationService;

    /**
     * 查询处罚表列表
     */
    @PreAuthorize("@ss.hasPermi('system:punishment:list')")
    @GetMapping("/list")
    public TableDataInfo list(Punishment punishment)
    {
        startPage();
        List<Punishment> list = punishmentService.selectPunishmentList(punishment);
        return getDataTable(list);
    }

    /**
     * 导出处罚表列表
     */
    @PreAuthorize("@ss.hasPermi('system:punishment:export')")
    @Log(title = "处罚表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Punishment punishment)
    {
        List<Punishment> list = punishmentService.selectPunishmentList(punishment);
        ExcelUtil<Punishment> util = new ExcelUtil<Punishment>(Punishment.class);
        util.exportExcel(response, list, "处罚表数据");
    }

    /**
     * 获取处罚表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:punishment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(punishmentService.selectPunishmentById(id));
    }

    /**
     * 新增处罚表
     */
    @PreAuthorize("@ss.hasPermi('system:punishment:add')")
    @Log(title = "处罚表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Punishment punishment)
    {
        Integer amount = studentMapper.getStudentId(punishment.getStudentId());
        if(amount==0) return AjaxResult.error("添加失败，检查学号是否有误");

        // TODO 处罚级别代码是否存在判断功能
        List<String> ids=punishmentService.getIds();
        List<String> names=punishmentService.getNames();
        for (int i = 0; i < ids.size(); i++) {
            if(ids.get(i).equals(punishment.getLevels())){
                // 是处罚代码
                punishment.setLevels(names.get(i));

                // 同步个人信息的添加
                int n = punishmentService.insertPunishment(punishment);
                String recordNumber=punishmentService.getMostNewPunish();
                Personsituation p=new Personsituation();
                p.setStudentId(punishment.getStudentId());
                p.setNumber(recordNumber);
                p.setPersonPunishment(punishmentService.getLevelsCode(punishment.getLevels()));
                p.setName(studentMapper.getStudentNameByStudentId(punishment.getStudentId()));
                personsituationService.insertPersonsituation(p);
                return toAjax(n);
            } else if (names.get(i).equals(punishment.getLevels())) {
                int n = punishmentService.insertPunishment(punishment);
                String recordNumber=punishmentService.getMostNewPunish();
                Personsituation p=new Personsituation();
                p.setStudentId(punishment.getStudentId());
                p.setNumber(recordNumber);
                p.setPersonPunishment(punishmentService.getLevelsCode(punishment.getLevels()));
                p.setName(studentMapper.getStudentNameByStudentId(punishment.getStudentId()));
                personsituationService.insertPersonsituation(p);
                return toAjax(i);
            }
        }
        return AjaxResult.error("处罚名称或者处罚代码有误");
    }

    /**
     * 修改处罚表
     */
    @PreAuthorize("@ss.hasPermi('system:punishment:edit')")
    @Log(title = "处罚表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Punishment punishment)
    {

        Integer amount = studentMapper.getStudentId(punishment.getStudentId());
        if(amount==0) return AjaxResult.error("修改失败，检查学号是否有误");

        // TODO 处罚级别代码是否存在判断功能
        List<String> ids=punishmentService.getIds();
        List<String> names=punishmentService.getNames();
        int n;
        for (n = 0; n < ids.size(); n++) {
            if(ids.get(n).equals(punishment.getLevels())){
                // 是处罚代码
                punishment.setLevels(names.get(n));

                String oldPunishment=punishmentService.getLevelsCode(punishmentService.getLevelsById(punishment.getId()));
                String recordNumber= String.valueOf(punishment.getId());
                Personsituation p=new Personsituation();
                p.setStudentId(punishment.getStudentId());
                p.setNumber(recordNumber);
                p.setPersonPunishment(punishmentService.getLevelsCode(punishment.getLevels()));// ids.get(n)
                p.setName(studentMapper.getStudentNameByStudentId(punishment.getStudentId()));
                Map map=new HashMap<>();
                map.put("studentId",punishmentService.getStudentIdByid(punishment.getId()));
                map.put("number",punishment.getId());
                map.put("personPunishment",oldPunishment);
                p.setId(personsituationService.getPersonSituationIdPunishment(map));

                int i = punishmentService.updatePunishment(punishment);

                personsituationService.updatePersonsituation(p);

                return toAjax(i);

            } else if (names.get(n).equals(punishment.getLevels())) {
                //是处罚名称
                String oldPunishment=punishmentService.getLevelsCode(punishmentService.getLevelsById(punishment.getId()));
                String recordNumber= String.valueOf(punishment.getId());
                Personsituation p=new Personsituation();
                p.setStudentId(punishment.getStudentId());
                p.setNumber(recordNumber);
                p.setPersonPunishment(punishmentService.getLevelsCode(punishment.getLevels()));// ids.get(n)
                p.setName(studentMapper.getStudentNameByStudentId(punishment.getStudentId()));
                Map map=new HashMap<>();
                map.put("studentId",punishmentService.getStudentIdByid(punishment.getId()));
                map.put("number",punishment.getId());
                map.put("personPunishment",oldPunishment);
                p.setId(personsituationService.getPersonSituationIdPunishment(map));
                int i = punishmentService.updatePunishment(punishment);
                personsituationService.updatePersonsituation(p);
                return toAjax(i);
            }
        }
        return AjaxResult.error("处罚名称或者处罚代码有误");
    }

    /**
     * 删除处罚表
     */
    @PreAuthorize("@ss.hasPermi('system:punishment:remove')")
    @Log(title = "处罚表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        // 同步删除
        for (int i = 0; i < ids.length; i++) {
            String studentId=punishmentService.getStudentIdByid(ids[i]);
            String levels=punishmentService.getLevelsCode(punishmentService.getLevelsById(ids[i]));
            Map map=new HashMap<>();
            map.put("studentId",studentId);
            map.put("levels",levels);
            map.put("id",ids[i]);
            personsituationService.deletePersonsituationPunish(map);
        }

        return toAjax(punishmentService.deletePunishmentByIds(ids));
    }
}
