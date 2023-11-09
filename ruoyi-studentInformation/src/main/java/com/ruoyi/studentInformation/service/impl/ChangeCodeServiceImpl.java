package com.ruoyi.studentInformation.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.studentInformation.mapper.ChangeCodeMapper;
import com.ruoyi.studentInformation.domain.ChangeCode;
import com.ruoyi.studentInformation.service.IChangeCodeService;

/**
 * 变更代码Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-08
 */
@Service
public class ChangeCodeServiceImpl implements IChangeCodeService
{
    @Autowired
    private ChangeCodeMapper changeCodeMapper;

    /**
     * 查询变更代码
     *
     * @param id 变更代码主键
     * @return 变更代码
     */
    @Override
    public ChangeCode selectChangeCodeById(Long id)
    {
        return changeCodeMapper.selectChangeCodeById(id);
    }

    /**
     * 查询变更代码列表
     *
     * @param changeCode 变更代码
     * @return 变更代码
     */
    @Override
    public List<ChangeCode> selectChangeCodeList(ChangeCode changeCode)
    {
        return changeCodeMapper.selectChangeCodeList(changeCode);
    }

    /**
     * 新增变更代码
     *
     * @param changeCode 变更代码
     * @return 结果
     */
    @Override
    public int insertChangeCode(ChangeCode changeCode)
    {
        return changeCodeMapper.insertChangeCode(changeCode);
    }

    /**
     * 修改变更代码
     *
     * @param changeCode 变更代码
     * @return 结果
     */
    @Override
    public int updateChangeCode(ChangeCode changeCode)
    {
        return changeCodeMapper.updateChangeCode(changeCode);
    }

    /**
     * 批量删除变更代码
     *
     * @param ids 需要删除的变更代码主键
     * @return 结果
     */
    @Override
    public int deleteChangeCodeByIds(Long[] ids)
    {
        return changeCodeMapper.deleteChangeCodeByIds(ids);
    }

    /**
     * 删除变更代码信息
     *
     * @param id 变更代码主键
     * @return 结果
     */
    @Override
    public int deleteChangeCodeById(Long id)
    {
        return changeCodeMapper.deleteChangeCodeById(id);
    }
}