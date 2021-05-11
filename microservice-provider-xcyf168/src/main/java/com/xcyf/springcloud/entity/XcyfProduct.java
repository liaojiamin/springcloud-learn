package com.xcyf.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户产品信息表
 * </p>
 *
 * @author wolf
 * @since 2021-05-11
 */
@TableName("XcyfProduct")
public class XcyfProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 归属用户ID
     */
    @TableField("userID")
    private Long userID;
    /**
     * 产品图片
     */
    private String pic;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品描述信息
     */
    private String describe;
    /**
     * 产品参数
     */
    private String parameter;
    /**
     * 状态：1正常，0限制，-1删除
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField("createTime")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("updateTime")
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "XcyfProduct{" +
        ", id=" + id +
        ", userID=" + userID +
        ", pic=" + pic +
        ", name=" + name +
        ", describe=" + describe +
        ", parameter=" + parameter +
        ", status=" + status +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
