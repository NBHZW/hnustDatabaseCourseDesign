package com.ruoyi.studentInformation.service;

import java.util.List;
import com.ruoyi.studentInformation.domain.Personsituation;

/**
 * 个人情况Service接口
 *
 * @author ruoyi
 * @date 2023-11-08
 */
public interface IPersonsituationService
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
     * 批量删除个人情况
     *
     * @param ids 需要删除的个人情况主键集合
     * @return 结果
     */
    public int deletePersonsituationByIds(Long[] ids);

    /**
     * 删除个人情况信息
     *
     * @param id 个人情况主键
     * @return 结果
     */
    public int deletePersonsituationById(Long id);
}
