package com.ruoyi.studentInformation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 个人情况对象 personsituation
 *
 * @author ruoyi
 * @date 2023-11-08
 */
public class Personsituation extends BaseEntity
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

    /** 学籍变更情况 */
    @Excel(name = "学籍变更情况")
    private String personChange;

    /** 个人奖励 */
    @Excel(name = "个人奖励")
    private String personReward;

    /** 个人惩处 */
    @Excel(name = "个人惩处")
    private String personPunishment;

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
    public void setPersonChange(String personChange)
    {
        this.personChange = personChange;
    }

    public String getPersonChange()
    {
        return personChange;
    }
    public void setPersonReward(String personReward)
    {
        this.personReward = personReward;
    }

    public String getPersonReward()
    {
        return personReward;
    }
    public void setPersonPunishment(String personPunishment)
    {
        this.personPunishment = personPunishment;
    }

    public String getPersonPunishment()
    {
        return personPunishment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("studentId", getStudentId())
                .append("name", getName())
                .append("personChange", getPersonChange())
                .append("personReward", getPersonReward())
                .append("personPunishment", getPersonPunishment())
                .toString();
    }
}
