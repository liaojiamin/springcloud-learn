package com.xcyf.springcloud.controller;

import com.xcyf.springcloud.response.BaseResponse;
import com.xcyf.springcloud.response.UploadFileResponse;
import com.xcyf.springcloud.service.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author liaojiamin
 * @Date:Created in 10:42 2021/5/12
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {
    @Autowired
    private IFileUploadService fileUploadService;

    @RequestMapping("/testFile")
    public String testFile(MultipartFile multipartFile) {
        return multipartFile.getOriginalFilename();
    }

    @RequestMapping("/uploadImage")
    public UploadFileResponse saveMultiImage(List<MultipartFile> multipartFile, Long userID) {
        return fileUploadService.saveMultiImage(multipartFile, userID);
    }

    @RequestMapping("/upload")
    public BaseResponse upload(String fileName, Long userID, MultipartFile multipartFile) {
        return fileUploadService.upload(fileName, userID, multipartFile);
    }
}
