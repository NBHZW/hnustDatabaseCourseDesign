package com.ruoyi.studentInformation.service;

import java.util.List;
import com.ruoyi.studentInformation.domain.PunishLevels;

/**
 * 处罚等级Service接口
 *
 * @author ruoyi
 * @date 2023-11-08
 */
public interface IPunishLevelsService
{
    /**
     * 查询处罚等级代码
     *
     * @param id 处罚等级代码主键
     * @return 处罚等级代码
     */
    public PunishLevels selectPunishLevelsById(Long id);

    /**
     * 查询处罚等级代码列表
     *
     * @param punishLevels 处罚等级代码
     * @return 处罚等级代码集合
     */
    public List<PunishLevels> selectPunishLevelsList(PunishLevels punishLevels);

    /**
     * 新增处罚等级代码
     *
     * @param punishLevels 处罚等级代码
     * @return 结果
     */
    public int insertPunishLevels(PunishLevels punishLevels);

    /**
     * 修改处罚等级代码
     *
     * @param punishLevels 处罚等级代码
     * @return 结果
     */
    public int updatePunishLevels(PunishLevels punishLevels);

    /**
     * 批量删除处罚等级代码
     *
     * @param ids 需要删除的处罚等级代码主键集合
     * @return 结果
     */
    public int deletePunishLevelsByIds(Long[] ids);

    /**
     * 删除处罚等级代码信息
     *
     * @param id 处罚等级代码主键
     * @return 结果
     */
    public int deletePunishLevelsById(Long id);
}