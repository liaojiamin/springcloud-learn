package com.xcyf.springcloud.response.errorcode;

import com.xcyf.springcloud.util.ErrorEntity;

/**
 * @author liaojiamin
 * @Date:Created in 15:47 2021/5/11
 */
public class UserError extends ProjectErrorEntity{
    @Override
    public String getModuleCode() {
        return ModuleCode.USER_MODULE_CODE;
    }

    public UserError(String errorCode, String errorMessage){
        super(errorCode, errorMessage);
    }
    public static final ErrorEntity PARAM_ERROR = new UserError("001", "参数错误");
    public static final ErrorEntity LOGIN_PARAM_ERROR = new UserError("002", "登录用户信息错误");
    public static final ErrorEntity REGISTER_PARAM_ERROR = new UserError("003", "注册用户信息错误");
    public static final ErrorEntity ADDUSER_PARAM_ERROR = new UserError("002", "产品信息添加参数错误");
    public static final ErrorEntity ADDUSER_ADD_FAIL = new UserError("003", "添加失败");
    public static final ErrorEntity DEL_FAIL = new UserError("004", "删除失败");
    public static final ErrorEntity UPDATE_FAIL = new UserError("005", "修改失败");
}
