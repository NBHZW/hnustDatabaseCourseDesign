package com.ruoyi.web.controller.studentInformation;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.studentInformation.domain.RewardCode;
import com.ruoyi.studentInformation.service.IRewardCodeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 奖励等级代码Controller
 *
 * @author ruoyi
 * @date 2023-11-08
 */
@RestController
@RequestMapping("/system/code")
public class RewardCodeController extends BaseController
{
    @Autowired
    private IRewardCodeService rewardCodeService;

    /**
     * 查询奖励等级代码列表
     */
    @PreAuthorize("@ss.hasPermi('system:code:list')")
    @GetMapping("/list")
    public TableDataInfo list(RewardCode rewardCode)
    {
        startPage();
        List<RewardCode> list = rewardCodeService.selectRewardCodeList(rewardCode);
        return getDataTable(list);
    }

    /**
     * 导出奖励等级代码列表
     */
    @PreAuthorize("@ss.hasPermi('system:code:export')")
    @Log(title = "奖励等级代码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RewardCode rewardCode)
    {
        List<RewardCode> list = rewardCodeService.selectRewardCodeList(rewardCode);
        ExcelUtil<RewardCode> util = new ExcelUtil<RewardCode>(RewardCode.class);
        util.exportExcel(response, list, "奖励等级代码数据");
    }

    /**
     * 获取奖励等级代码详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:code:query')")
    @GetMapping(value = "/{code}")
    public AjaxResult getInfo(@PathVariable("code") String code)
    {
        return success(rewardCodeService.selectRewardCodeByCode(code));
    }

    /**
     * 新增奖励等级代码
     */
    @PreAuthorize("@ss.hasPermi('system:code:add')")
    @Log(title = "奖励等级代码", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RewardCode rewardCode)
    {
        return toAjax(rewardCodeService.insertRewardCode(rewardCode));
    }

    /**
     * 修改奖励等级代码
     */
    @PreAuthorize("@ss.hasPermi('system:code:edit')")
    @Log(title = "奖励等级代码", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RewardCode rewardCode)
    {
        return toAjax(rewardCodeService.updateRewardCode(rewardCode));
    }

    /**
     * 删除奖励等级代码
     */
    @PreAuthorize("@ss.hasPermi('system:code:remove')")
    @Log(title = "奖励等级代码", businessType = BusinessType.DELETE)
    @DeleteMapping("/{codes}")
    public AjaxResult remove(@PathVariable String[] codes)
    {
        return toAjax(rewardCodeService.deleteRewardCodeByCodes(codes));
    }
}
