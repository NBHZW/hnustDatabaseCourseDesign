package com.ruoyi.studentInformation.service;

import java.util.List;
import com.ruoyi.studentInformation.domain.Reward;

/**
 * 奖励信息记录Service接口
 *
 * @author ruoyi
 * @date 2023-11-05
 */
public interface IRewardService
{
    /**
     * 查询奖励信息记录
     *
     * @param id 奖励信息记录主键
     * @return 奖励信息记录
     */
    public Reward selectRewardById(Long id);

    /**
     * 查询奖励信息记录列表
     *
     * @param reward 奖励信息记录
     * @return 奖励信息记录集合
     */
    public List<Reward> selectRewardList(Reward reward);

    /**
     * 新增奖励信息记录
     *
     * @param reward 奖励信息记录
     * @return 结果
     */
    public int insertReward(Reward reward);

    /**
     * 修改奖励信息记录
     *
     * @param reward 奖励信息记录
     * @return 结果
     */
    public int updateReward(Reward reward);

    /**
     * 批量删除奖励信息记录
     *
     * @param ids 需要删除的奖励信息记录主键集合
     * @return 结果
     */
    public int deleteRewardByIds(Long[] ids);

    /**
     * 删除奖励信息记录信息
     *
     * @param id 奖励信息记录主键
     * @return 结果
     */
    public int deleteRewardById(Long id);
}
