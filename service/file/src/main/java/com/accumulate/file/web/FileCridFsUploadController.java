package com.accumulate.file.web;

import com.accumulate.file.entity.UploadFile;
import com.accumulate.file.model.ImportFileUploadSuccessMessage;
import com.accumulate.file.util.Result;
import com.accumulate.file.model.UnZipCaseFileRequest;
import com.accumulate.file.repository.UploadFileRepository;
import com.accumulate.file.service.UploadFileCridFsService;
import com.google.common.collect.Lists;
import com.mongodb.gridfs.GridFSDBFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description:
 * @Modified By:
 */
@RestController
@RequestMapping("/FileCridFsUploadController")
@Api(value = "", description = "文件上传")
public class FileCridFsUploadController {
    @Autowired
    private RestTemplate restTemplate;
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UploadFileRepository uploadFileRepository;
    @Autowired
    UploadFileCridFsService uploadFileCridFsService;

    private org.slf4j.Logger logger = LoggerFactory.getLogger(FileCridFsUploadController.class);


    @RequestMapping(value = "/uploadFileGrid", method = RequestMethod.POST, headers = {"content-type=multipart/mixed", "content-type=multipart/form-data"}, consumes = {"multipart/form-data"})
    @ResponseBody
    @ApiOperation(value = "Grid方式上传文件", notes = "返回JSON data 为UploadFile 对象")
    public Result uploadFileGrid(@RequestParam("file") MultipartFile file) throws Exception {
        try {
            if (Objects.isNull(file)) {
                throw new RuntimeException("MultipartFile是空的");
            }
            UploadFile uploadFile = uploadFileCridFsService.uploadFile(file);
            return new Result(Result.ReturnValue.SUCCESS, "", uploadFile);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new Result(Result.ReturnValue.FAILURE,"");
        }
    }


//    @PostMapping("/unZipCaseFile")
//    @ResponseBody
//    @ApiOperation(value = "上传压缩文件，后台进行解压缩", notes = "返回的为文件记录对象")
//    public Result unZipCaseFile(@RequestBody UnZipCaseFileRequest request,
//                                                    @RequestHeader(value = "authorization") String authorization) throws Exception {
//        if (StringUtils.isBlank(request.getUploadFile())) {
//            return new Result(Result.ReturnValue.FAILURE, Constants.ERROR_MESSAGE);
//        }
//        if (StringUtils.isBlank(request.getBatchNum())) {
//            return new Result(Result.ReturnValue.FAILURE, Constants.ERROR_MESSAGE);
//        }
//        UploadFile uploadFile = uploadFileRepository.findOne(request.getUploadFile());
//        ImportFileUploadSuccessMessage message = new ImportFileUploadSuccessMessage();
//        message.setBatchNum(request.getBatchNum());
//        message.setUploadFile(uploadFile);
//        rabbitTemplate.convertAndSend("mr.cui.file.import.upload.success", message);
//        return new Result(Result.ReturnValue.SUCCESS, "", uploadFile);
//    }
//
//    @GetMapping("/getAllUploadFileByIdList")
//    @ResponseBody
//    @ApiOperation(value = "查询文件信息", notes = "查询文件信息")
//    public Result getAllUploadFileByIds(@RequestParam(required = false) @ApiParam(value = "文件id集合") List<String> fileIds)
//            throws Exception {
//        List<UploadFile> uploadFiles = Lists.newArrayList(uploadFileRepository.findAll(fileIds));
//        return new Result(Result.ReturnValue.SUCCESS, "", uploadFiles);
//    }
//
//    /**
//     * 在线显示文件
//     *
//     * @param id
//     * @return
//     */
//    @GetMapping("/view/{id}")
//    @ResponseBody
//    @ApiOperation(value = "在线显示文件", notes = "在线显示文件")
//    public ResponseEntity<Object> serveFileOnline(@PathVariable String id) throws IOException {
//        UploadFile file = uploadFileCridFsService.getFileById(id);
//        if (file != null) {
//            GridFSDBFile gridFSDBFile = uploadFileCridFsService.getFileContent(id);
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            gridFSDBFile.writeTo(os);
//            return ResponseEntity
//                    .ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "fileName=\"" + new String(file.getRealName().getBytes("UTF-8"), "ISO-8859-1") + "\"")
//                    .header(HttpHeaders.CONTENT_TYPE, "application/".concat(file.getType()))
//                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
//                    .header("Connection", "close")
//                    .body(os.toByteArray());
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not fount");
//        }
//    }
//
//    /**
//     * 下载文件
//     *
//     * @param id
//     * @return
//     */
//    @GetMapping("/file/{id}")
//    @ResponseBody
//    @ApiOperation(value = "下载文件", notes = "下载文件")
//    public ResponseEntity<Object> downFile(@PathVariable String id) throws IOException {
//        UploadFile file = uploadFileCridFsService.getFileById(id);
//        if (file != null) {
//            GridFSDBFile gridFSDBFile = uploadFileCridFsService.getFileContent(id);
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            gridFSDBFile.writeTo(os);
//            return ResponseEntity
//                    .ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + file.getRealName() + "\"")
//                    .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
//                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
//                    .header("Connection", "close")
//                    .body(os.toByteArray());
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not found");
//        }
//    }
//
//    /**
//     * 删除文件
//     *
//     * @param id
//     * @return
//     */
//    @DeleteMapping("/{id}")
//    @ResponseBody
//    @ApiOperation(value = "删除文件", notes = "删除文件")
//    public ResponseEntity<String> deleteFile(@PathVariable String id) {
//        try {
//            uploadFileCridFsService.removeFile(id);
//            return ResponseEntity.status(HttpStatus.OK).body("DELETE Success!");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }

}
