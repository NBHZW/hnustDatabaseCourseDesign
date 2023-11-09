package com.ruoyi.studentInformation.mapper;

import java.util.List;
import com.ruoyi.studentInformation.domain.Personsituation;

/**
 * 个人情况Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-08
 */
public interface PersonsituationMapper
{
    /**
     * 查询个人情况
     *
     * @param id 个人情况主键
     * @return 个人情况
     */
    public Personsituation selectPersonsituationById(Long id);

    /**
     * 查询个人情况列表
     *
     * @param personsituation 个人情况
     * @return 个人情况集合
     */
    public List<Personsituation> selectPersonsituationList(Personsituation personsituation);

    /**
     * 新增个人情况
     *
     * @param personsituation 个人情况
     * @return 结果
     */
    public int insertPersonsituation(Personsituation personsituation);

    /**
     * 修改个人情况
     *
     * @param personsituation 个人情况
     * @return 结果
     */
    public int updatePersonsituation(Personsituation personsituation);

    /**
     * 删除个人情况
     *
     * @param id 个人情况主键
     * @return 结果
     */
    public int deletePersonsituationById(Long id);

    /**
     * 批量删除个人情况
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePersonsituationByIds(Long[] ids);
}
