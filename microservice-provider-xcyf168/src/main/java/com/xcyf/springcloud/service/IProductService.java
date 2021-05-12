package com.xcyf.springcloud.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xcyf.springcloud.entity.XcyfProduct;
import com.xcyf.springcloud.request.AddProductRequest;
import com.xcyf.springcloud.request.PageListProductRequest;
import com.xcyf.springcloud.response.BaseResponse;
import com.xcyf.springcloud.response.ProductDetailResponse;

/**
 * @author liaojiamin
 * @Date:Created in 16:53 2021/5/11
 */
public interface IProductService extends IService<XcyfProduct> {
    BaseResponse addproduct(AddProductRequest request);

    BaseResponse delProduct(Long productID);

    BaseResponse updateProduct(AddProductRequest request);

    IPage<XcyfProduct> pageList(PageListProductRequest request);

    ProductDetailResponse productDetail(Long id, Long userID);
}
