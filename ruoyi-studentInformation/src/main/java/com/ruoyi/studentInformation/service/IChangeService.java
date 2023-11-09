package com.ruoyi.studentInformation.service;

import java.util.List;
import com.ruoyi.studentInformation.domain.Change;

/**
 * 学籍信息变更Service接口
 *
 * @author ruoyi
 * @date 2023-11-05
 */
public interface IChangeService
{
    /**
     * 查询学籍信息变更
     *
     * @param id 学籍信息变更主键
     * @return 学籍信息变更
     */
    public Change selectChangeById(Long id);

    /**
     * 查询学籍信息变更列表
     *
     * @param change 学籍信息变更
     * @return 学籍信息变更集合
     */
    public List<Change> selectChangeList(Change change);

    /**
     * 新增学籍信息变更
     *
     * @param change 学籍信息变更
     * @return 结果
     */
    public int insertChange(Change change);

    /**
     * 修改学籍信息变更
     *
     * @param change 学籍信息变更
     * @return 结果
     */
    public int updateChange(Change change);

    /**
     * 批量删除学籍信息变更
     *
     * @param ids 需要删除的学籍信息变更主键集合
     * @return 结果
     */
    public int deleteChangeByIds(Long[] ids);

    /**
     * 删除学籍信息变更信息
     *
     * @param id 学籍信息变更主键
     * @return 结果
     */
    public int deleteChangeById(Long id);
}
