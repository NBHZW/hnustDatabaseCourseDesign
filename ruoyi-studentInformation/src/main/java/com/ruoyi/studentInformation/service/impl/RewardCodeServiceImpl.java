package com.ruoyi.studentInformation.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.studentInformation.mapper.RewardCodeMapper;
import com.ruoyi.studentInformation.domain.RewardCode;
import com.ruoyi.studentInformation.service.IRewardCodeService;

/**
 * 奖励等级代码Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-08
 */
@Service
public class RewardCodeServiceImpl implements IRewardCodeService
{
    @Autowired
    private RewardCodeMapper rewardCodeMapper;

    /**
     * 查询奖励等级代码
     *
     * @param code 奖励等级代码主键
     * @return 奖励等级代码
     */
    @Override
    public RewardCode selectRewardCodeByCode(String code)
    {
        return rewardCodeMapper.selectRewardCodeByCode(code);
    }

    /**
     * 查询奖励等级代码列表
     *
     * @param rewardCode 奖励等级代码
     * @return 奖励等级代码
     */
    @Override
    public List<RewardCode> selectRewardCodeList(RewardCode rewardCode)
    {
        return rewardCodeMapper.selectRewardCodeList(rewardCode);
    }

    /**
     * 新增奖励等级代码
     *
     * @param rewardCode 奖励等级代码
     * @return 结果
     */
    @Override
    public int insertRewardCode(RewardCode rewardCode)
    {
        return rewardCodeMapper.insertRewardCode(rewardCode);
    }

    /**
     * 修改奖励等级代码
     *
     * @param rewardCode 奖励等级代码
     * @return 结果
     */
    @Override
    public int updateRewardCode(RewardCode rewardCode)
    {
        return rewardCodeMapper.updateRewardCode(rewardCode);
    }

    /**
     * 批量删除奖励等级代码
     *
     * @param codes 需要删除的奖励等级代码主键
     * @return 结果
     */
    @Override
    public int deleteRewardCodeByCodes(String[] codes)
    {
        return rewardCodeMapper.deleteRewardCodeByCodes(codes);
    }

    /**
     * 删除奖励等级代码信息
     *
     * @param code 奖励等级代码主键
     * @return 结果
     */
    @Override
    public int deleteRewardCodeByCode(String code)
    {
        return rewardCodeMapper.deleteRewardCodeByCode(code);
    }
}
