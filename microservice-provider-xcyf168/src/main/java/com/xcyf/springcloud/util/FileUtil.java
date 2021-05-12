package com.xcyf.springcloud.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

/**
 * @author: liaojiamin
 * @description:
 * @date: 16:13 2019/8/23
 * @return 
 */
public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private static final int DEFAULT_SIZE = 1024;

    private static final int DEFAULT_BUFFER_SIZE = DEFAULT_SIZE * 4;

    private static final char PARTING = '/';

    /**
     * 获取文件大小
     *
     * @param filePath 文件路径
     * @return 当文件为空或不存在或是一个目录时，返回:-1
     */
    public static long getFileSizes(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException("file path is blank");
        }
        return getFileSizes(new File(filePath));
    }

    /**
     * 获取文件名字,如果fileurl为空或文件不存在返回:null
     *
     * @param: fileUrl
     * @return
     */
    public static String getFileName(String fileUrl) {
        if (StringUtils.isBlank(fileUrl)) {
            throw new IllegalArgumentException("file url is blank");
        }
        if (fileUrl.indexOf(PARTING) != -1){
            return fileUrl.substring(fileUrl.lastIndexOf(PARTING) + 1, fileUrl.length());
        }
        return null;
    }

    /**
     * 获取文件大小,如果file为空或文件不存在或文件是目录返回:-1
     *
     * @param file
     * @return
     */
    public static long getFileSizes(File file) {
        long s = -1L;
        if (file != null && file.exists() && file.isFile()) {
            return file.length();
        }
        return s;
    }


    /**
     * MultipartFile 转换成File
     *
     * @param multipartFile 原文件类型
     * @return File
     * @throws IOException
     */
    public static File multipartToFile(MultipartFile multipartFile) {
        File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") +
                multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(tmpFile);
        } catch (IOException e) {
            logger.error("multipartToFile error:{}", e.getMessage());
            return null;
        }
        return tmpFile;
    }

    /**
     * 将网络文件保存到本地指定路径
     *
     * @param url    网络文件地址
     * @param target 本地全路径
     * @return
     * @throws MalformedURLException
     */
    public static long downloadFile(String url, String target) throws MalformedURLException {
        if (StringUtils.isBlank(url) || StringUtils.isBlank(target)) {
            throw new IllegalArgumentException("url is null or ");
        }
        return downloadFile(new URL(url), new File(target));
    }

    /**
     * 将网络文件保存到本地指定路径
     *
     * @param url
     * @param target
     * @return
     */
    public static long downloadFile(URL url, File target) {
        if (url == null || target == null) {
            throw new IllegalArgumentException("url is null or target file is null!");
        }
        try (InputStream inputStream = url.openStream();) {
            return saveFile(inputStream, target);
        } catch (Exception e) {
            logger.error("Copy file to path[" + target + "] error!", e);
            return -1L;
        }
    }

    /**
     * @param is         输入流
     * @param targetFile
     * @return
     */
    public static long saveFile(InputStream is, File targetFile) {
        long result = -1L;
        if (is == null || targetFile == null || targetFile.isDirectory()) {
            logger.info("source inputstream or target file is null or target file is a directory!");
            return result;
        }
        if (!targetFile.exists()) {
            try {
                File directory = targetFile.getParentFile();
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                targetFile.createNewFile();
            } catch (IOException e) {
                logger.error("Create new file on path[" + targetFile.getAbsolutePath() + "] error!", e);
                return result;
            }
        }
        try (OutputStream os = new BufferedOutputStream(new FileOutputStream(targetFile));) {
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            long count = 0;
            int n = 0;
            while (-1 != (n = is.read(buffer))) {
                os.write(buffer, 0, n);
                count += n;
            }
            return count;
        } catch (IOException e) {
            logger.error("Copy file to path[" + targetFile.getAbsolutePath() + "] error!", e);
            return result;
        }
    }

    /**
     * 复制文件
     *
     * @param source 源文件路径
     * @param target 目标文件路径
     * @return
     */
    public static long copyFile(String source, String target) {
        if (StringUtils.isBlank(source) || StringUtils.isBlank(target)) {
            throw new IllegalArgumentException("source is null or target is null!");
        }

        return copyFile(new File(source), new File(target));
    }

    /**
     * 复制文件
     *
     * @param source 源文件
     * @param target 目标文件
     * @return 文件大小，当复制失败时，返回-1
     */
    public static long copyFile(File source, File target) {
        if (source == null || target == null) {
            logger.info("source file or target file is null");
            return -1L;
        }
        if (!source.exists()) {
            logger.info("source file[" + source.getAbsolutePath() + "] does not exist");
            return -1L;
        }

        try (InputStream is = new BufferedInputStream(new FileInputStream(source));) {
            return saveFile(is, target);
        } catch (Exception e) {
            logger.error("Copy file [" + source.getAbsolutePath() + "] to path[" + target + "] error!", e);
            return -1L;
        }
    }

    /**
     * 匹配文件是否是在某些后缀里
     *
     * @param file
     * @param allowSuffix
     * @return
     */
    public static boolean isSuffixMatch(File file, String allowSuffix) {
        if (file == null || file.isDirectory() || StringUtils.isBlank(allowSuffix)) {
            throw new IllegalArgumentException("File is null or file is a directory or allow suffies is blank!");
        }
        String fileName = file.getName();
        String suffix = getFileSuffix(fileName);

        return isSuffixMatch(suffix, allowSuffix);
    }

    /**
     * @param suffix      文件后缀
     * @param allowSuffix 需要匹配的后缀，英文状态分号";"分割
     * @return
     */
    public static boolean isSuffixMatch(String suffix, String allowSuffix) {
        if (StringUtils.isBlank(suffix) || StringUtils.isBlank(allowSuffix)) {
            throw new IllegalArgumentException("The special suffix is blank or allow suffies is blank!");
        }
        String[] allowArr = allowSuffix.split(";");
        for (String unit : allowArr) {
            if (suffix.toLowerCase().equals(unit)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件后缀名
     *
     * @return
     * @param: filePath文件路径
     */
    public static String getFileSuffix(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException("File path is blank!");
        }
        return filePath.substring(filePath.lastIndexOf(".") + 1);
    }


    public static byte[] file2byte(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static void byte2File(byte[] buf, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String buildFileName(File file) {
        String fileId = UUID.randomUUID().toString().replace("-", "");
        String fileName = String.format("%s-%s", fileId, file.getName());
        return fileName;
    }
}
