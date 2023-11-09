package com.ruoyi.studentInformation.mapper;

import java.util.List;
import com.ruoyi.studentInformation.domain.RewardCode;
import org.apache.ibatis.annotations.Select;

/**
 * 奖励等级代码Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-08
 */
public interface RewardCodeMapper
{
    /**
     * 查询奖励等级代码
     *
     * @param code 奖励等级代码主键
     * @return 奖励等级代码
     */
    public RewardCode selectRewardCodeByCode(String code);

    /**
     * 查询奖励等级代码列表
     *
     * @param rewardCode 奖励等级代码
     * @return 奖励等级代码集合
     */
    public List<RewardCode> selectRewardCodeList(RewardCode rewardCode);

    /**
     * 新增奖励等级代码
     *
     * @param rewardCode 奖励等级代码
     * @return 结果
     */
    public int insertRewardCode(RewardCode rewardCode);

    /**
     * 修改奖励等级代码
     *
     * @param rewardCode 奖励等级代码
     * @return 结果
     */
    public int updateRewardCode(RewardCode rewardCode);

    /**
     * 删除奖励等级代码
     *
     * @param code 奖励等级代码主键
     * @return 结果
     */
    public int deleteRewardCodeByCode(String code);

    /**
     * 批量删除奖励等级代码
     *
     * @param codes 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRewardCodeByCodes(String[] codes);

    @Select("select count(code) from reward_code where code=#{levels}")
    Integer getCode(Long levels);
}