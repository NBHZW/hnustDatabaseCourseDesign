package com.ruoyi.studentInformation.mapper;

import java.util.List;
import com.ruoyi.studentInformation.domain.Change;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 学籍信息变更Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-05
 */
@Mapper
public interface ChangeMapper
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
     * 删除学籍信息变更
     *
     * @param id 学籍信息变更主键
     * @return 结果
     */
    public int deleteChangeById(Long id);

    /**
     * 批量删除学籍信息变更
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChangeByIds(Long[] ids);

}
