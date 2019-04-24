package com.accumulate.file.service;

import com.accumulate.entity.file.UploadFile;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description: GridFS 文件服务器
 * @Modified By:
 */
public interface UploadFileCridFsService {

    /**
     * 保存文件
     *
     * @return
     */
    UploadFile uploadFile(MultipartFile file) throws Exception;

    /**
     * 删除文件
     *
     * @return
     */
    void removeFile(String id);

    /**
     * 根据id获取文件列表信息
     *
     * @return
     */
    UploadFile getFileById(String id);

    /**
     * 获取文件内容
     *
     * @param id
     * @return
     */
    GridFSDBFile getFileContent(String id);

//    /**
//     *
//     * @param inputStream
//     * @param userId
//     * @param userName
//     * @param batchNum
//     */
//    void uploadCaseFileReduce(InputStream inputStream, String userId, String userName, String batchNum, String companyCode) throws Exception ;
}
