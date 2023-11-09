package com.ruoyi.studentInformation.mapper;

import java.util.List;
import com.ruoyi.studentInformation.domain.ChangeCode;

/**
 * 变更代码Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-08
 */
public interface ChangeCodeMapper
{
    /**
     * 查询变更代码
     *
     * @param id 变更代码主键
     * @return 变更代码
     */
    public ChangeCode selectChangeCodeById(Long id);

    /**
     * 查询变更代码列表
     *
     * @param changeCode 变更代码
     * @return 变更代码集合
     */
    public List<ChangeCode> selectChangeCodeList(ChangeCode changeCode);

    /**
     * 新增变更代码
     *
     * @param changeCode 变更代码
     * @return 结果
     */
    public int insertChangeCode(ChangeCode changeCode);

    /**
     * 修改变更代码
     *
     * @param changeCode 变更代码
     * @return 结果
     */
    public int updateChangeCode(ChangeCode changeCode);

    /**
     * 删除变更代码
     *
     * @param id 变更代码主键
     * @return 结果
     */
    public int deleteChangeCodeById(Long id);

    /**
     * 批量删除变更代码
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChangeCodeByIds(Long[] ids);
}