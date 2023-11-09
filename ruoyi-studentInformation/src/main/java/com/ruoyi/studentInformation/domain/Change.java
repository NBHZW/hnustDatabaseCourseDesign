package com.ruoyi.studentInformation.domain;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学籍信息变更对象 change
 *
 * @author ruoyi
 * @date 2023-11-05
 */
public class Change extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录号 */
    private Long id;

    /** 学号 */
    @Excel(name = "学号")
    private String studentId;

    /** 变更代码 */
    @Excel(name = "变更代码")
    private Long changes;

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
    public void setChanges(Long changes)
    {
        this.changes = changes;
    }

    public Long getChanges()
    {
        return changes;
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
                .append("changes", getChanges())
                .append("recTime", getRecTime())
                .append("description", getDescription())
                .toString();
    }
}
