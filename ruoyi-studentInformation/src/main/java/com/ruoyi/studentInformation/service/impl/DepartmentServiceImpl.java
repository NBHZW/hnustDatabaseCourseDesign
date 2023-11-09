package com.ruoyi.studentInformation.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.studentInformation.mapper.DepartmentMapper;
import com.ruoyi.studentInformation.domain.Department;
import com.ruoyi.studentInformation.service.IDepartmentService;

/**
 * 院系信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-07
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService
{
    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 查询院系信息
     *
     * @param id 院系信息主键
     * @return 院系信息
     */
    @Override
    public Department selectDepartmentById(Long id)
    {
        return departmentMapper.selectDepartmentById(id);
    }

    /**
     * 查询院系信息列表
     *
     * @param department 院系信息
     * @return 院系信息
     */
    @Override
    public List<Department> selectDepartmentList(Department department)
    {
        return departmentMapper.selectDepartmentList(department);
    }

    /**
     * 新增院系信息
     *
     * @param department 院系信息
     * @return 结果
     */
    @Override
    public int insertDepartment(Department department)
    {
        return departmentMapper.insertDepartment(department);
    }

    /**
     * 修改院系信息
     *
     * @param department 院系信息
     * @return 结果
     */
    @Override
    public int updateDepartment(Department department)
    {
        return departmentMapper.updateDepartment(department);
    }

    /**
     * 批量删除院系信息
     *
     * @param ids 需要删除的院系信息主键
     * @return 结果
     */
    @Override
    public int deleteDepartmentByIds(Long[] ids)
    {
        return departmentMapper.deleteDepartmentByIds(ids);
    }

    /**
     * 删除院系信息信息
     *
     * @param id 院系信息主键
     * @return 结果
     */
    @Override
    public int deleteDepartmentById(Long id)
    {
        return departmentMapper.deleteDepartmentById(id);
    }
}
