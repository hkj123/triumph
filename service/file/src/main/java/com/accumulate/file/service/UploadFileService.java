//package com.accumulate.file.service;
//
//import com.accumulate.core.service.MongoGenericManager;
//import com.accumulate.entity.file.UploadFile;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//
///**
// * @System: 车贷金融
// * @Auther: hukaijia
// * @Description:
// * @Modified By:
// */
//public interface UploadFileService extends MongoGenericManager<UploadFile, String> {
//
//    UploadFile uploadFile(MultipartFile file, String creator) throws IOException;
//
//    UploadFile uploadFile(InputStream inputStream, long fileSize, String fileName, String fileExtName, String creator);
//
//    void uploadCaseFileReduce(InputStream inputStream, String userId, String userName, String batchNum);
//
//    String uploadFileUrl(MultipartFile file) throws IOException;
//}
