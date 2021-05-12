package com.xcyf.springcloud.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.collect.Lists;
import com.xcyf.springcloud.constants.PhotoConstants;
import com.xcyf.springcloud.response.BaseResponse;
import com.xcyf.springcloud.response.UploadFileResponse;
import com.xcyf.springcloud.response.errorcode.HeadCode;
import com.xcyf.springcloud.service.IFileUploadService;
import com.xcyf.springcloud.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author liaojiamin
 * @Date:Created in 16:01 2019/8/19
 */
@Service
public class FileUploadServiceImpl implements IFileUploadService {
    private Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    @Override
    public UploadFileResponse saveMultiImage(List<MultipartFile> multipartFile, Long userID) {
        Integer saveCount = multipartFile.size();
        List<Future<String>> tasks = Lists.newArrayListWithCapacity(saveCount);
        for (MultipartFile file : multipartFile) {
            uploadFile(file, userID);
        }
        List<String> results = fetchAsyncResults(tasks);
        return new UploadFileResponse(saveCount, saveCount - results.size(), results);
    }

    /**
     * 异步文件上传
     * */
    public Object uploadFile(MultipartFile multipartFile, Long userID) {
        return saveLocal(multipartFile.getOriginalFilename(), userID, multipartFile);
    }

    public String saveLocal(String fileName, Long memberId, MultipartFile multipartFile){
        String filePath = PhotoConstants.getPicPath() + File.separatorChar +
                UUID.randomUUID().toString() + String.valueOf(memberId) +"."+ FileUtil.getFileSuffix(fileName);
        logger.debug("upload filePath:{}, fileByte:{}", filePath, multipartFile);
        File newFile = new File(filePath);
        try {
            FileUtils.writeByteArrayToFile(newFile, multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("ProfileAsync upload error:{}", e.getMessage());
            return null;
        }
        return newFile.getName();
    }

    private List<String> fetchAsyncResults(List<Future<String>> tasks) {
        List<String> rets = Lists.newArrayListWithCapacity(tasks.size());
        for (Future<String> task : tasks) {
            try {
                String result = task.get(5, TimeUnit.SECONDS);
                if (StringUtils.isNotBlank(result)) {
                    rets.add(result);
                }
            } catch (Exception e) {
                task.cancel(true);
                logger.error("query task fail, the error is ", e);
            }
        }
        return rets;
    }

    @Override
    public BaseResponse upload(String fileName, Long memberId, MultipartFile multipartFile) {
        String filePath = PhotoConstants.getPicPath() + File.separatorChar +
                UUID.randomUUID().toString() + String.valueOf(memberId) +"."+ FileUtil.getFileSuffix(fileName);
        logger.debug("upload filePath:{}, fileByte:{}", filePath, multipartFile);
        File newFile = new File(filePath);
        try {
            FileUtils.writeByteArrayToFile(newFile, multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("FileUploadServiceImpl upload error:{}", e.getMessage());
            return null;
        }
        BaseResponse response = new BaseResponse();
        response.setCode(HeadCode.SUCCESS);
        response.setMessage(newFile.getName());
        return response;
    }
}
