package com.xcyf.springcloud.service;

import com.xcyf.springcloud.response.BaseResponse;
import com.xcyf.springcloud.response.UploadFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author liaojiamin
 * @Date:Created in 15:58 2019/8/19
 */
public interface IFileUploadService {
    /**
     * 保存图片
     * */
    UploadFileResponse saveMultiImage(List<MultipartFile> multipartFile, Long userID);

    /**
     * 用于解决分布式文件服务的接口
     * */
    BaseResponse upload(String fileName, Long userID, MultipartFile multipartFile);
}
