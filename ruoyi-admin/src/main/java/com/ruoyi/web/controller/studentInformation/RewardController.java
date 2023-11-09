package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.studentInformation.mapper.RewardCodeMapper;
import com.ruoyi.studentInformation.mapper.RewardMapper;
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
public class RewardController extends BaseController
{
    @Autowired
    private IRewardService rewardService;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RewardCodeMapper rewardCodeMapper;

    /**
     * 查询奖励信息记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:reward:list')")
    @GetMapping("/list")
    public TableDataInfo list(Reward reward)
    {
        startPage();
        List<Reward> list = rewardService.selectRewardList(reward);
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
        if(amount==0) return AjaxResult.error("添加失败，检查级别代码是否有误");

        return toAjax(rewardService.insertReward(reward));
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
        if(amount==0) return AjaxResult.error("添修改失败，检查级别代码是否有误");
        return toAjax(rewardService.updateReward(reward));
    }

    /**
     * 删除奖励信息记录
     */
    @PreAuthorize("@ss.hasPermi('system:reward:remove')")
    @Log(title = "奖励信息记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(rewardService.deleteRewardByIds(ids));
    }
}
