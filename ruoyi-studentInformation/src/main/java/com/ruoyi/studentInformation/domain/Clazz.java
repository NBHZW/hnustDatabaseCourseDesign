package com.ruoyi.studentInformation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 班级信息对象 clazz
 *
 * @author ruoyi
 * @date 2023-11-07
 */
public class Clazz extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 班级全称 */
    @Excel(name = "班级全称")
    private String name;

    /** 班长学号 */
    @Excel(name = "班长学号")
    private String monitor;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setMonitor(String monitor)
    {
        this.monitor = monitor;
    }

    public String getMonitor()
    {
        return monitor;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("monitor", getMonitor())
                .toString();
    }
}
