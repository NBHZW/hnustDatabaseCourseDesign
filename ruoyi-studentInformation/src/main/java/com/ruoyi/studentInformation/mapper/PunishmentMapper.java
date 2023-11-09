package com.ruoyi.studentInformation.mapper;

import java.util.List;
import com.ruoyi.studentInformation.domain.Punishment;

/**
 * 处罚表Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-06
 */
public interface PunishmentMapper
{
    /**
     * 查询处罚表
     *
     * @param id 处罚表主键
     * @return 处罚表
     */
    public Punishment selectPunishmentById(Long id);

    /**
     * 查询处罚表列表
     *
     * @param punishment 处罚表
     * @return 处罚表集合
     */
    public List<Punishment> selectPunishmentList(Punishment punishment);

    /**
     * 新增处罚表
     *
     * @param punishment 处罚表
     * @return 结果
     */
    public int insertPunishment(Punishment punishment);

    /**
     * 修改处罚表
     *
     * @param punishment 处罚表
     * @return 结果
     */
    public int updatePunishment(Punishment punishment);

    /**
     * 删除处罚表
     *
     * @param id 处罚表主键
     * @return 结果
     */
    public int deletePunishmentById(Long id);

    /**
     * 批量删除处罚表
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePunishmentByIds(Long[] ids);
}
