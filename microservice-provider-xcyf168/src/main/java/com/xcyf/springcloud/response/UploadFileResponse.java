package com.xcyf.springcloud.response;

import java.util.List;

/**
 * @author liaojiamin
 * @Date:Created in 16:14 2019/8/19
 */
public class UploadFileResponse extends BaseResponse {
    private Integer saveCount;
    private Integer failCount;
    private List<String> fileNames;

    public UploadFileResponse(Integer saveCount, Integer failCount, List<String> fileNames){
        this.saveCount = saveCount;
        this.failCount = failCount;
        this.fileNames = fileNames;
    }


    public Integer getSaveCount() {
        return saveCount;
    }

    public void setSaveCount(Integer saveCount) {
        this.saveCount = saveCount;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }
}
