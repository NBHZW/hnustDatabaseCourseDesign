package com.ruoyi.studentInformation.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.studentInformation.mapper.PersonsituationMapper;
import com.ruoyi.studentInformation.domain.Personsituation;
import com.ruoyi.studentInformation.service.IPersonsituationService;

/**
 * 个人情况Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-08
 */
@Service
public class PersonsituationServiceImpl implements IPersonsituationService
{
    @Autowired
    private PersonsituationMapper personsituationMapper;

    /**
     * 查询个人情况
     *
     * @param id 个人情况主键
     * @return 个人情况
     */
    @Override
    public Personsituation selectPersonsituationById(Long id)
    {
        return personsituationMapper.selectPersonsituationById(id);
    }

    /**
     * 查询个人情况列表
     *
     * @param personsituation 个人情况
     * @return 个人情况
     */
    @Override
    public List<Personsituation> selectPersonsituationList(Personsituation personsituation)
    {
        return personsituationMapper.selectPersonsituationList(personsituation);
    }

    /**
     * 新增个人情况
     *
     * @param personsituation 个人情况
     * @return 结果
     */
    @Override
    public int insertPersonsituation(Personsituation personsituation)
    {
        return personsituationMapper.insertPersonsituation(personsituation);
    }

    /**
     * 修改个人情况
     *
     * @param personsituation 个人情况
     * @return 结果
     */
    @Override
    public int updatePersonsituation(Personsituation personsituation)
    {
        return personsituationMapper.updatePersonsituation(personsituation);
    }

    /**
     * 批量删除个人情况
     *
     * @param ids 需要删除的个人情况主键
     * @return 结果
     */
    @Override
    public int deletePersonsituationByIds(Long[] ids)
    {
        return personsituationMapper.deletePersonsituationByIds(ids);
    }

    /**
     * 删除个人情况信息
     *
     * @param id 个人情况主键
     * @return 结果
     */
    @Override
    public int deletePersonsituationById(Long id)
    {
        return personsituationMapper.deletePersonsituationById(id);
    }
}
