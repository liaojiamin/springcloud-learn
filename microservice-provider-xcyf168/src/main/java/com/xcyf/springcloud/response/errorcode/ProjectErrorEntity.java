package com.xcyf.springcloud.response.errorcode;


import com.xcyf.springcloud.util.ErrorEntity;

/**
 * Created by zhenghualong on 2017/5/2.
 */
public abstract class ProjectErrorEntity extends ErrorEntity {

    protected ProjectErrorEntity(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    @Override
    public String getHeadCode() {
        return HeadCode.EMOTION_HEAD_CODE + getModuleCode();
    }

    public abstract String getModuleCode();
}
