package com.ruoyi.studentInformation.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.studentInformation.mapper.PunishLevelsMapper;
import com.ruoyi.studentInformation.domain.PunishLevels;
import com.ruoyi.studentInformation.service.IPunishLevelsService;

/**
 * 处罚等级Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-08
 */
@Service
public class PunishLevelsServiceImpl implements IPunishLevelsService
{
    @Autowired
    private PunishLevelsMapper punishLevelsMapper;

    /**
     * 查询处罚等级代码
     *
     * @param id 处罚等级代码主键
     * @return 处罚等级代码
     */
    @Override
    public PunishLevels selectPunishLevelsById(Long id)
    {
        return punishLevelsMapper.selectPunishLevelsById(id);
    }

    /**
     * 查询处罚等级代码列表
     *
     * @param punishLevels 处罚等级代码
     * @return 处罚等级代码
     */
    @Override
    public List<PunishLevels> selectPunishLevelsList(PunishLevels punishLevels)
    {
        return punishLevelsMapper.selectPunishLevelsList(punishLevels);
    }

    /**
     * 新增处罚等级代码
     *
     * @param punishLevels 处罚等级代码
     * @return 结果
     */
    @Override
    public int insertPunishLevels(PunishLevels punishLevels)
    {
        return punishLevelsMapper.insertPunishLevels(punishLevels);
    }

    /**
     * 修改处罚等级代码
     *
     * @param punishLevels 处罚等级代码
     * @return 结果
     */
    @Override
    public int updatePunishLevels(PunishLevels punishLevels)
    {
        return punishLevelsMapper.updatePunishLevels(punishLevels);
    }

    /**
     * 批量删除处罚等级代码
     *
     * @param ids 需要删除的处罚等级代码主键
     * @return 结果
     */
    @Override
    public int deletePunishLevelsByIds(Long[] ids)
    {
        return punishLevelsMapper.deletePunishLevelsByIds(ids);
    }

    /**
     * 删除处罚等级代码信息
     *
     * @param id 处罚等级代码主键
     * @return 结果
     */
    @Override
    public int deletePunishLevelsById(Long id)
    {
        return punishLevelsMapper.deletePunishLevelsById(id);
    }
}
