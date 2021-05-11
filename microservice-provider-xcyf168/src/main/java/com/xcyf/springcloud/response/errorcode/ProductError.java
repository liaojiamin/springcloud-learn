package com.xcyf.springcloud.response.errorcode;

import com.xcyf.springcloud.util.ErrorEntity;

/**
 * @author liaojiamin
 * @Date:Created in 15:47 2021/5/11
 */
public class ProductError extends ProjectErrorEntity{
    @Override
    public String getModuleCode() {
        return ModuleCode.PRODUCT_MODULE_CODE;
    }

    public ProductError(String errorCode, String errorMessage){
        super(errorCode, errorMessage);
    }
    public static final ErrorEntity PARAM_ERROR = new ProductError("001", "参数错误");
    public static final ErrorEntity ADDPRODUCT_PARAM_ERROR = new ProductError("002", "产品信息添加参数错误");
    public static final ErrorEntity ADDPRODUCT_ADD_FAIL = new ProductError("003", "添加失败");
    public static final ErrorEntity DEL_ADD_FAIL = new ProductError("004", "删除失败");
    public static final ErrorEntity UPDATE_ADD_FAIL = new ProductError("005", "修改失败");
}
