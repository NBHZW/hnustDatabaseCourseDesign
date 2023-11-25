package com.ruoyi.web.controller.studentInformation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.studentInformation.domain.Personsituation;
import com.ruoyi.studentInformation.mapper.RewardCodeMapper;
import com.ruoyi.studentInformation.mapper.RewardMapper;
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
import com.ruoyi.studentInformation.domain.Reward;
import com.ruoyi.studentInformation.service.IRewardService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 奖励信息记录Controller
 *
 * @author ruoyi
 * @date 2023-11-05
 */
@RestController
@RequestMapping("/system/reward")
@Transactional(rollbackFor = Exception.class)
public class RewardController extends BaseController
{
    @Autowired
    private IRewardService rewardService;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RewardCodeMapper rewardCodeMapper;

    @Autowired
    private IPersonsituationService personsituationService;

    /**
     * 查询奖励信息记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:reward:list')")
    @GetMapping("/list")
    public TableDataInfo list(Reward reward)
    {
        startPage();
        List<Reward> list = rewardService.selectRewardList(reward);
        for (Reward reward1 : list) {
            String oldDescription = reward1.getDescription();
            if(oldDescription==null) oldDescription="";
            reward1.setDescription(oldDescription +" ( "+rewardCodeMapper.getDescription(reward1.getLevels())+" ) ");
        }
        return getDataTable(list);
    }

    /**
     * 导出奖励信息记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:reward:export')")
    @Log(title = "奖励信息记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Reward reward)
    {
        List<Reward> list = rewardService.selectRewardList(reward);
        ExcelUtil<Reward> util = new ExcelUtil<Reward>(Reward.class);
        util.exportExcel(response, list, "奖励信息记录数据");
    }

    /**
     * 获取奖励信息记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:reward:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(rewardService.selectRewardById(id));
    }

    /**
     * 新增奖励信息记录
     */
    @PreAuthorize("@ss.hasPermi('system:reward:add')")
    @Log(title = "奖励信息记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Reward reward)
    {
        Integer amount = studentMapper.getStudentId(reward.getStudentId());
        if(amount==0) return AjaxResult.error("添加失败,检查学号是否有误");

        amount=rewardCodeMapper.getCode(reward.getLevels());
        if(amount==0) return AjaxResult.error("添加失败，检查奖励级别代码是否有误");

        // 对应到个人情况数据的新增
        int i = rewardService.insertReward(reward);
        String recordNumber=rewardService.getMostNewRecord();
        Personsituation p=new Personsituation();
        p.setStudentId(reward.getStudentId());
        p.setNumber(recordNumber);
        p.setPersonReward(String.valueOf(reward.getLevels()));
        p.setName(studentMapper.getStudentNameByStudentId(reward.getStudentId()));
        personsituationService.insertPersonsituation(p);


        return toAjax(i);
    }

    /**
     * 修改奖励信息记录
     */
    @PreAuthorize("@ss.hasPermi('system:reward:edit')")
    @Log(title = "奖励信息记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Reward reward)
    {
        Integer amount = studentMapper.getStudentId(reward.getStudentId());
        if(amount==0) return AjaxResult.error("修改失败,检查学号是否有误");

        amount=rewardCodeMapper.getCode(reward.getLevels());
        if(amount==0) return AjaxResult.error("修改失败，检查奖励级别代码是否有误");


        // 同步个人信息修改
        String oldLevels=rewardService.getLevelsByid(reward.getId());
        Map map=new HashMap<>();// 旧数据存储
        map.put("studentId",rewardService.getStudentIdByid(reward.getId()));
        map.put("number",reward.getId());
        map.put("personReward",rewardService.getLevelsByid(reward.getId()));

        int i = rewardService.updateReward(reward);
        String recordNumber= String.valueOf(reward.getId());
        Personsituation p=new Personsituation();
        p.setStudentId(reward.getStudentId());
        p.setNumber(recordNumber);
        p.setPersonReward(String.valueOf(reward.getLevels()));
        p.setName(studentMapper.getStudentNameByStudentId(reward.getStudentId()));

        p.setId(personsituationService.getPersonSituationIdReward(map));
        System.out.println(p);
        personsituationService.updatePersonsituation(p);


        return toAjax(i);
    }

    /**
     * 删除奖励信息记录
     */
    @PreAuthorize("@ss.hasPermi('system:reward:remove')")
    @Log(title = "奖励信息记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        // 同步删除个人信息里面 根据学号 记录号 以及奖励等级
        for (int i = 0; i < ids.length; i++) {
            String studentId=rewardService.getStudentIdByid(ids[i]);
            String levels=rewardService.getLevelsByid(ids[i]);
            Map map=new HashMap<>();
            map.put("studentId",studentId);
            map.put("levels",levels);
            map.put("id",ids[i]);
            personsituationService.deletePersonsituationReward(map);
        }


        return toAjax(rewardService.deleteRewardByIds(ids));
    }
}
