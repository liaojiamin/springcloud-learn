package com.xcyf.springcloud.response;

import com.xcyf.springcloud.entity.XcyfProduct;

import java.io.Serializable;

/**
 * @author liaojiamin
 * @Date:Created in 12:18 2021/5/12
 */
public class ProductDetailResponse implements Serializable {
    private XcyfProduct xcyfProduct;

    public XcyfProduct getXcyfProduct() {
        return xcyfProduct;
    }

    public void setXcyfProduct(XcyfProduct xcyfProduct) {
        this.xcyfProduct = xcyfProduct;
    }
}
