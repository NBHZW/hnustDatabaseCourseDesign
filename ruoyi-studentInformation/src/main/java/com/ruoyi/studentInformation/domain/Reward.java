package com.ruoyi.studentInformation.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 奖励信息记录对象 reward
 *
 * @author ruoyi
 * @date 2023-11-05
 */
public class Reward extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录号 */
    private Long id;

    /** 学号 */
    @Excel(name = "学号")
    private String studentId;

    /** 级别 */
    @Excel(name = "级别")
    private Long levels;

    /** 记录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recTime;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setStudentId(String studentId)
    {
        this.studentId = studentId;
    }

    public String getStudentId()
    {
        return studentId;
    }
    public void setLevels(Long levels)
    {
        this.levels = levels;
    }

    public Long getLevels()
    {
        return levels;
    }
    public void setRecTime(Date recTime)
    {
        this.recTime = recTime;
    }

    public Date getRecTime()
    {
        return recTime;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("studentId", getStudentId())
                .append("levels", getLevels())
                .append("recTime", getRecTime())
                .append("description", getDescription())
                .toString();
    }
}
