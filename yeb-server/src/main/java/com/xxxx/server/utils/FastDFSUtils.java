package com.xxxx.server.utils;

import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author liyongkang
 * @Date 2021/12/16
 **/

public class FastDFSUtils {

    private static Logger logger = LoggerFactory.getLogger(FastDFSUtils.class);

    /**
     * 初始化客户端
     * ClientGlobal.init()：读取配置文件，并初始化对应的属性
     */
    static {
        try {
            String filepath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filepath);
        } catch (Exception e) {
            logger.error("初始化FastDFS失败",e);
        }
    }

    /**
     * 上传文件
     */
    public static String[] upload(MultipartFile file){
        String filename = file.getOriginalFilename();
        logger.info("File Name :" + filename);
        String[] uploadResults = null;
        StorageClient storageClient = null;
        try {
            //获取StorageClient客户端
            storageClient = getStorageClient();
            //上传
            uploadResults = storageClient.upload_file(file.getBytes(),filename.substring(filename.lastIndexOf(".")+1),null);
        } catch (Exception e) {
            logger.error("上传文件失败",e.getMessage());
        }
        if (null == uploadResults){
            logger.error("上传失败!",storageClient.getErrorCode());
        }
        return uploadResults;
    }

    /**
     * 获取文件信息
     */
    public static FileInfo getFileInfo(String groupName, String remoteFileName){

        StorageClient storageClient = null;
        try {
            storageClient = getStorageClient();
            return storageClient.get_file_info(groupName,remoteFileName);
        }catch (Exception e){
            logger.error("文件信息获取失败!",e.getMessage());
        }
        return null;
    }

    /**
     * 下载文件
     */
    public static InputStream downFile(String groupName,String remoteFileName){
        StorageClient storageClient = null;
        InputStream inputStream = null;
        try {
            storageClient = getStorageClient();
            byte[] bytes = storageClient.download_file(groupName, remoteFileName);
            inputStream = new ByteArrayInputStream(bytes);
            return inputStream;
        }catch (Exception e){
            logger.error("文件下载失败!",e.getMessage());
        }
        return null;
    }

    /**
     * 删除文件
     */
    public static void deleteFile(String groupName,String remoteFileName){
        StorageClient storageClient = null;
        try {
            storageClient = getStorageClient();
            storageClient.delete_file(groupName, remoteFileName);
        }catch (Exception e){
            logger.error("文件删除失败!");
        }
    }

    /**
     * 生成Storage客户端
     */
    private static StorageClient getStorageClient() throws IOException {
        //获取TrackerServer服务器
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }

    /**
     * 生成tracker服务器
     */
    private static TrackerServer getTrackerServer() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        return trackerServer;
    }

    /**
     * 获取文件路径
     */
    public static String getTrackerUrl(){
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        try {
            trackerServer = trackerClient.getTrackerServer();
            storageServer = trackerClient.getStoreStorage(trackerServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "http://" + storageServer.getInetSocketAddress().getHostString() + ":80/";
    }

}
