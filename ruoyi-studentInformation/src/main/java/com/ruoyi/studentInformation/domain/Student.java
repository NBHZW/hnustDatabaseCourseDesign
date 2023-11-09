package com.ruoyi.studentInformation.domain;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 student
 *
 * @author ruoyi
 * @date 2023-11-03
 */
public class Student extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 学号 */
    @Excel(name = "学号")
    private String studentId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 班级编号 */
    @Excel(name = "班级编号")
    private Long clazz;

    /** 院系编号 */
    @Excel(name = "院系编号")
    private Long department;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 籍贯 */
    @Excel(name = "籍贯")
    private String nativePlace;

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
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getSex()
    {
        return sex;
    }
    public void setClazz(Long clazz)
    {
        this.clazz = clazz;
    }

    public Long getClazz()
    {
        return clazz;
    }
    public void setDepartment(Long department)
    {
        this.department = department;
    }

    public Long getDepartment()
    {
        return department;
    }
    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public Date getBirthday()
    {
        return birthday;
    }
    public void setNativePlace(String nativePlace)
    {
        this.nativePlace = nativePlace;
    }

    public String getNativePlace()
    {
        return nativePlace;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("studentId", getStudentId())
                .append("name", getName())
                .append("sex", getSex())
                .append("clazz", getClazz())
                .append("department", getDepartment())
                .append("birthday", getBirthday())
                .append("nativePlace", getNativePlace())
                .toString();
    }
}
