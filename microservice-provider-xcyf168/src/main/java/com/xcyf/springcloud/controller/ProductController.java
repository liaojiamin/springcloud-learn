package com.xcyf.springcloud.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xcyf.springcloud.entity.XcyfProduct;
import com.xcyf.springcloud.request.AddProductRequest;
import com.xcyf.springcloud.request.PageListProductRequest;
import com.xcyf.springcloud.response.BaseResponse;
import com.xcyf.springcloud.response.ProductDetailResponse;
import com.xcyf.springcloud.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaojiamin
 * @Date:Created in 17:26 2021/5/11
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @RequestMapping("/addProduct")
    public BaseResponse addProduct(AddProductRequest request){
        return productService.addproduct(request);
    }

    @RequestMapping("/delProduct")
    public BaseResponse delProduct(Long productID){
        return productService.delProduct(productID);
    }

    @RequestMapping("/updateProduct")
    public BaseResponse updateProduct(AddProductRequest request){
        return productService.updateProduct(request);
    }

    @RequestMapping("/pageList")
    public IPage<XcyfProduct> pageList(PageListProductRequest request){
        return productService.pageList(request);
    }

    @RequestMapping("/productDetail")
    public ProductDetailResponse productDetail(Long id, Long userID){
        return productService.productDetail(id, userID);
    }
}
