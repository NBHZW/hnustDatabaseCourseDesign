package com.ruoyi.studentInformation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 奖励等级代码对象 reward_code
 *
 * @author ruoyi
 * @date 2023-11-08
 */

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 奖励等级代码对象 reward_code
 *
 * @author ruoyi
 * @date 2023-11-08
 */
public class RewardCode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 代码 */
    private String code;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
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
                .append("code", getCode())
                .append("description", getDescription())
                .toString();
    }
}