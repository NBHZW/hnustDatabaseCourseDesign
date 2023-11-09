package com.ruoyi.studentInformation.mapper;

import java.util.List;
import com.ruoyi.studentInformation.domain.Clazz;

/**
 * 班级信息Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-07
 */
public interface ClazzMapper
{
    /**
     * 查询班级信息
     *
     * @param id 班级信息主键
     * @return 班级信息
     */
    public Clazz selectClazzById(Long id);

    /**
     * 查询班级信息列表
     *
     * @param clazz 班级信息
     * @return 班级信息集合
     */
    public List<Clazz> selectClazzList(Clazz clazz);

    /**
     * 新增班级信息
     *
     * @param clazz 班级信息
     * @return 结果
     */
    public int insertClazz(Clazz clazz);

    /**
     * 修改班级信息
     *
     * @param clazz 班级信息
     * @return 结果
     */
    public int updateClazz(Clazz clazz);

    /**
     * 删除班级信息
     *
     * @param id 班级信息主键
     * @return 结果
     */
    public int deleteClazzById(Long id);

    /**
     * 批量删除班级信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClazzByIds(Long[] ids);
}
