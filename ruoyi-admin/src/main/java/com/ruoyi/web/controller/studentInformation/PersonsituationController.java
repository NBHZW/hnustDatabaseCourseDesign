package com.ruoyi.web.controller.studentInformation;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.studentInformation.service.*;
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
import com.ruoyi.studentInformation.domain.Personsituation;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 个人情况Controller
 *
 * @author ruoyi
 * @date 2023-11-08
 */
@RestController
@RequestMapping("/system/personsituation")
@Transactional(rollbackFor = Exception.class)
public class PersonsituationController extends BaseController
{
    @Autowired
    private IPersonsituationService personsituationService;

    @Autowired
    private IChangeCodeService changeCodeService;

    @Autowired
    private IPunishmentService punishmentService;

    @Autowired
    private IRewardService rewardService;

    /**
     * 查询个人情况列表
     */
    @PreAuthorize("@ss.hasPermi('system:personsituation:list')")
    @GetMapping("/list")
    public TableDataInfo list(Personsituation personsituation)
    {
        startPage();
        List<Personsituation> list = personsituationService.selectPersonsituationList(personsituation);
        for (Personsituation p : list) {
            if(p.getPersonChange()!=null){
                p.setPersonChange(p.getPersonChange()+"("+changeCodeService.getChangeDescription(p.getPersonChange())+")");
            }

            if(p.getPersonPunishment()!=null){
                p.setPersonPunishment(p.getPersonPunishment()+"("+punishmentService.getDiscrption(p.getPersonPunishment())+")");
                if(punishmentService.getStatus(p.getNumber()).equals("N")){
                    p.setPersonPunishment(p.getPersonPunishment()+"(未生效)");
                }
            }

            if(p.getPersonReward()!=null){
                p.setPersonReward(p.getPersonReward()+"("+rewardService.getDiscription(p.getPersonReward())+")");
            }

        }
        return getDataTable(list);
    }

    /**
     * 导出个人情况列表
     */
    @PreAuthorize("@ss.hasPermi('system:personsituation:export')")
    @Log(title = "个人情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Personsituation personsituation)
    {
        List<Personsituation> list = personsituationService.selectPersonsituationList(personsituation);
        ExcelUtil<Personsituation> util = new ExcelUtil<Personsituation>(Personsituation.class);
        util.exportExcel(response, list, "个人情况数据");
    }

    /**
     * 获取个人情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:personsituation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(personsituationService.selectPersonsituationById(id));
    }

    /**
     * 新增个人情况
     */
    @PreAuthorize("@ss.hasPermi('system:personsituation:add')")
    @Log(title = "个人情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Personsituation personsituation)
    {
        return toAjax(personsituationService.insertPersonsituation(personsituation));
    }

    /**
     * 修改个人情况
     */
    @PreAuthorize("@ss.hasPermi('system:personsituation:edit')")
    @Log(title = "个人情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Personsituation personsituation)
    {
        return toAjax(personsituationService.updatePersonsituation(personsituation));
    }

    /**
     * 删除个人情况
     */
    @PreAuthorize("@ss.hasPermi('system:personsituation:remove')")
    @Log(title = "个人情况", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(personsituationService.deletePersonsituationByIds(ids));
    }
}