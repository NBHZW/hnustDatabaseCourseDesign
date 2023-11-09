package com.ruoyi.studentInformation.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.studentInformation.mapper.PunishmentMapper;
import com.ruoyi.studentInformation.domain.Punishment;
import com.ruoyi.studentInformation.service.IPunishmentService;

/**
 * 处罚表Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-06
 */
@Service
public class PunishmentServiceImpl implements IPunishmentService
{
    @Autowired
    private PunishmentMapper punishmentMapper;

    /**
     * 查询处罚表
     *
     * @param id 处罚表主键
     * @return 处罚表
     */
    @Override
    public Punishment selectPunishmentById(Long id)
    {
        return punishmentMapper.selectPunishmentById(id);
    }

    /**
     * 查询处罚表列表
     *
     * @param punishment 处罚表
     * @return 处罚表
     */
    @Override
    public List<Punishment> selectPunishmentList(Punishment punishment)
    {
        return punishmentMapper.selectPunishmentList(punishment);
    }

    /**
     * 新增处罚表
     *
     * @param punishment 处罚表
     * @return 结果
     */
    @Override
    public int insertPunishment(Punishment punishment)
    {
        return punishmentMapper.insertPunishment(punishment);
    }

    /**
     * 修改处罚表
     *
     * @param punishment 处罚表
     * @return 结果
     */
    @Override
    public int updatePunishment(Punishment punishment)
    {
        return punishmentMapper.updatePunishment(punishment);
    }

    /**
     * 批量删除处罚表
     *
     * @param ids 需要删除的处罚表主键
     * @return 结果
     */
    @Override
    public int deletePunishmentByIds(Long[] ids)
    {
        return punishmentMapper.deletePunishmentByIds(ids);
    }

    /**
     * 删除处罚表信息
     *
     * @param id 处罚表主键
     * @return 结果
     */
    @Override
    public int deletePunishmentById(Long id)
    {
        return punishmentMapper.deletePunishmentById(id);
    }
}
