package com.xcyf.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xcyf.springcloud.Enums.UserStatusEnum;
import com.xcyf.springcloud.entity.XcyfProduct;
import com.xcyf.springcloud.entity.XcyfUser;
import com.xcyf.springcloud.mapper.XcyfProductMapper;
import com.xcyf.springcloud.mapper.XcyfUserMapper;
import com.xcyf.springcloud.request.AddProductRequest;
import com.xcyf.springcloud.request.PageListProductRequest;
import com.xcyf.springcloud.response.BaseResponse;
import com.xcyf.springcloud.response.errorcode.HeadCode;
import com.xcyf.springcloud.response.errorcode.ProductError;
import com.xcyf.springcloud.service.IProductService;
import com.xcyf.springcloud.util.BusinessAssert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author liaojiamin
 * @Date:Created in 16:58 2021/5/11
 */
@Service
public class ProductServiceImpl extends ServiceImpl<XcyfProductMapper, XcyfProduct> implements IProductService {
    @Autowired
    private XcyfProductMapper productMapper;
    @Autowired
    private XcyfUserMapper userMapper;
    @Override
    public BaseResponse addproduct(AddProductRequest request) {
        if(request == null || request.getUserID() == null ){
            BusinessAssert.error(ProductError.ADDPRODUCT_PARAM_ERROR);
        }
        Date date = new Date();
        XcyfProduct xcyfProduct = new XcyfProduct();
        BeanUtils.copyProperties(request, xcyfProduct);
        xcyfProduct.setCreateTime(date);
        xcyfProduct.setStatus(1);
        xcyfProduct.setUpdateTime(date);
        boolean insert = productMapper.insert(xcyfProduct) > 0;
        if(insert){
            BusinessAssert.error(ProductError.ADDPRODUCT_ADD_FAIL);
        }
        BaseResponse response = new BaseResponse();
        response.setCode(HeadCode.SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }

    @Override
    public BaseResponse delProduct(Long productID) {
        if(productID == null){
            BusinessAssert.error(ProductError.DEL_ADD_FAIL);
        }
        productMapper.deleteById(productID);
        BaseResponse response = new BaseResponse();
        response.setCode(HeadCode.SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }

    @Override
    public BaseResponse updateProduct(AddProductRequest request) {
        if(request == null || request.getId() == null){
            BusinessAssert.error(ProductError.UPDATE_ADD_FAIL);
        }
        Date date = new Date();
        XcyfProduct xcyfProduct = new XcyfProduct();
        BeanUtils.copyProperties(request, xcyfProduct);
        xcyfProduct.setUpdateTime(date);
        productMapper.updateById(xcyfProduct);
        BaseResponse response = new BaseResponse();
        response.setCode(HeadCode.SUCCESS);
        response.setMessage("SUCCESS");
        return response;
    }

    @Override
    public IPage<XcyfProduct> pageList(PageListProductRequest request) {
        if(request == null || request.getUserID() == null){
            BusinessAssert.error(ProductError.PARAM_ERROR);
        }
        Integer page = request.getPage() > 0 ? request.getPage() : 1;
        Integer pageSize = request.getPageSize() > 0 && request.getPageSize() < 500 ? request.getPageSize() : 20;
        XcyfUser user = userMapper.selectById(request.getUserID());
        QueryWrapper<XcyfProduct> wrapper = new QueryWrapper<>();
        if(UserStatusEnum.ORDINARY.getValue() == user.getUserType()){
            wrapper.eq("userID", user.getUserID());
        }
        IPage<XcyfProduct>  pageLIst = productMapper.selectPage(new Page<XcyfProduct>(page, pageSize), wrapper );
        return pageLIst;
    }
}

