package com.ruoyi.studentInformation.mapper;

import java.util.List;
import com.ruoyi.studentInformation.domain.Department;
import org.apache.ibatis.annotations.Select;

/**
 * 院系信息Mapper接口
 *
 * @author ruoyi
 * @date 2023-11-07
 */
public interface DepartmentMapper
{
    /**
     * 查询院系信息
     *
     * @param id 院系信息主键
     * @return 院系信息
     */
    public Department selectDepartmentById(Long id);

    /**
     * 查询院系信息列表
     *
     * @param department 院系信息
     * @return 院系信息集合
     */
    public List<Department> selectDepartmentList(Department department);

    /**
     * 新增院系信息
     *
     * @param department 院系信息
     * @return 结果
     */
    public int insertDepartment(Department department);

    /**
     * 修改院系信息
     *
     * @param department 院系信息
     * @return 结果
     */
    public int updateDepartment(Department department);

    /**
     * 删除院系信息
     *
     * @param id 院系信息主键
     * @return 结果
     */
    public int deleteDepartmentById(Long id);

    /**
     * 批量删除院系信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDepartmentByIds(Long[] ids);

    @Select("select count(id) from department where id=#{departmentId}")
    Integer getDepartmentId(Long departmentId);
}
