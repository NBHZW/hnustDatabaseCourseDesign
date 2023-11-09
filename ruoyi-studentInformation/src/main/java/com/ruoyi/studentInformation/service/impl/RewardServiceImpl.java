package com.ruoyi.studentInformation.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.studentInformation.mapper.RewardMapper;
import com.ruoyi.studentInformation.domain.Reward;
import com.ruoyi.studentInformation.service.IRewardService;

/**
 * 奖励信息记录Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-05
 */
@Service
public class RewardServiceImpl implements IRewardService
{
    @Autowired
    private RewardMapper rewardMapper;

    /**
     * 查询奖励信息记录
     *
     * @param id 奖励信息记录主键
     * @return 奖励信息记录
     */
    @Override
    public Reward selectRewardById(Long id)
    {
        return rewardMapper.selectRewardById(id);
    }

    /**
     * 查询奖励信息记录列表
     *
     * @param reward 奖励信息记录
     * @return 奖励信息记录
     */
    @Override
    public List<Reward> selectRewardList(Reward reward)
    {
        return rewardMapper.selectRewardList(reward);
    }

    /**
     * 新增奖励信息记录
     *
     * @param reward 奖励信息记录
     * @return 结果
     */
    @Override
    public int insertReward(Reward reward)
    {
        return rewardMapper.insertReward(reward);
    }

    /**
     * 修改奖励信息记录
     *
     * @param reward 奖励信息记录
     * @return 结果
     */
    @Override
    public int updateReward(Reward reward)
    {
        return rewardMapper.updateReward(reward);
    }

    /**
     * 批量删除奖励信息记录
     *
     * @param ids 需要删除的奖励信息记录主键
     * @return 结果
     */
    @Override
    public int deleteRewardByIds(Long[] ids)
    {
        return rewardMapper.deleteRewardByIds(ids);
    }

    /**
     * 删除奖励信息记录信息
     *
     * @param id 奖励信息记录主键
     * @return 结果
     */
    @Override
    public int deleteRewardById(Long id)
    {
        return rewardMapper.deleteRewardById(id);
    }
}
