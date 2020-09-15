package org.jeecg.modules.wxCommon.util;
import java.io.IOException;
import java.io.InputStream;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
/**
* @describe 图片上传
* @author: LeoD
* @createTime 2018年3月27日 下午12:23:33
*/
public class QiniuUploadUtil {
    private static final Logger logger = LoggerFactory.getLogger(QiniuUploadUtil.class);
    private static final String ACCESS_KEY = "0mWSA5pbzjJbZBDsdbjtYQXI0srw1mWOESFH6_Uh";
    private static final String SECRET_KEY = "9LEAlVIWLFJjMLmIrXpyKQSB9LoV6dL6wnjqu5f_";
    private static final String BUCKET = "wanxin-sinao";
    public static final String IMG_SUFFIX_LIST = "jpg,jpeg,gif,png,ico,bmp,jpeg";
    public static final String URL = "http://img.sinoeducate.com/";
/**
* 上传图片
* @param file
* @return
* @throws IOException
*/
public static String upload(@RequestParam MultipartFile file) {
    if (file == null) {
    logger.warn("上传文件为空!");return null;
    }
    InputStream inputStream = null;
    try {
    /** 获取文件的后缀* */
        /*if (!checkFile(file.getOriginalFilename())) {
             logger.warn("上传文件类型不支持");
            return null;
        }*/
        inputStream = file.getInputStream();
        DefaultPutRet ret = qiNiuUpload(inputStream);
        if (null == ret) {
            logger.error("上传文件服务器返回为null");
            return null;
        }
        logger.info("上传图片名称:" + file.getOriginalFilename());
        logger.info("上传后hash:" + ret.hash);
        logger.info("上传后key:" + ret.key);
        return URL+ret.hash;
    } catch (QiniuException ex) {
        logger.error("上传七牛服务器异常", ex);
    } catch (Exception e) {
        logger.error("上传异常", e);
    } finally {
    try {
        inputStream.close();
    } catch (IOException e) {
        logger.error("上传文件，关闭输入流异常", e);
    }
   }
    return null;
}
/**
* 检测图片文件类型
* @param fileName
* @return
*/
public static boolean checkFile(String fileName) {
    boolean flag = false;
    String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
    logger.info("上传文件后缀:" + suffix);
    if (IMG_SUFFIX_LIST.contains(suffix.trim().toLowerCase())) {
        flag = true;
    }
    return flag;
}
/**
* 上传七牛服务器
* @param is
* @return
*/
private static DefaultPutRet qiNiuUpload(InputStream is) throws Exception {
    Configuration cfg = new Configuration(Zone.zone2());
    UploadManager uploadManager = new UploadManager(cfg);
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    String upToken = auth.uploadToken(BUCKET);
    com.qiniu.http.Response response = uploadManager.put(is, null, upToken, null, null);
    //解析上传成功的结果
    DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
    logger.info(putRet.toString());
    logger.info(putRet.key);
    logger.info(putRet.hash);
     return putRet;
    }
public static String getToken() {
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    return auth.uploadToken(BUCKET);
    }
}