package com.ruoyi.studentInformation.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.studentInformation.mapper.ChangeMapper;
import com.ruoyi.studentInformation.domain.Change;
import com.ruoyi.studentInformation.service.IChangeService;

import java.util.List;
@Service
public class ChangeServiceImpl implements IChangeService
{
    @Autowired
    private ChangeMapper changeMapper;

    /**
     * 查询学籍信息变更
     *
     * @param id 学籍信息变更主键
     * @return 学籍信息变更
     */
    @Override
    public Change selectChangeById(Long id)
    {
        return changeMapper.selectChangeById(id);
    }

    /**
     * 查询学籍信息变更列表
     *
     * @param change 学籍信息变更
     * @return 学籍信息变更
     */
    @Override
    public List<Change> selectChangeList(Change change)
    {
        return changeMapper.selectChangeList(change);
    }

    /**
     * 新增学籍信息变更
     *
     * @param change 学籍信息变更
     * @return 结果
     */
    @Override
    public int insertChange(Change change)
    {
        return changeMapper.insertChange(change);
    }

    /**
     * 修改学籍信息变更
     *
     * @param change 学籍信息变更
     * @return 结果
     */
    @Override
    public int updateChange(Change change)
    {
        return changeMapper.updateChange(change);
    }

    /**
     * 批量删除学籍信息变更
     *
     * @param ids 需要删除的学籍信息变更主键
     * @return 结果
     */
    @Override
    public int deleteChangeByIds(Long[] ids)
    {
        return changeMapper.deleteChangeByIds(ids);
    }

    /**
     * 删除学籍信息变更信息
     *
     * @param id 学籍信息变更主键
     * @return 结果
     */
    @Override
    public int deleteChangeById(Long id)
    {
        return changeMapper.deleteChangeById(id);
    }
}