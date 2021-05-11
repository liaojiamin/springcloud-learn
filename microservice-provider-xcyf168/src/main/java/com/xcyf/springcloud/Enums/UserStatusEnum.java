package com.xcyf.springcloud.Enums;

/**
 * @author: liaojiamin
 * @description:
 * @date: 17:21 2021/5/11
 * @return 
 */
public enum UserStatusEnum {

    SYSTEM(0, "系统"), ORDINARY(1, "用户");

    private Integer value;

    private String desc;

    UserStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static UserStatusEnum of(Integer val) {
        for (UserStatusEnum target : UserStatusEnum.values()) {
            if (target.value == val) {
                return target;
            }
        }
        return null;
    }
}
