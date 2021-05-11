package com.xcyf.springcloud.request;

import java.io.Serializable;

/**
 * @author liaojiamin
 * @Date:Created in 16:55 2021/5/11
 */
public class AddProductRequest implements Serializable {
    private Long id;
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
}
