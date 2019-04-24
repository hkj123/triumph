package com.accumulate.file.model;

import com.accumulate.entity.file.UploadFile;
import lombok.Data;

import java.io.Serializable;

/**
 *@System: 车贷金融
 *@Auther: hukaijia
 *@Description: 文件上传完成消息
 *@Modified By:
*/
@Data
public class ImportFileUploadSuccessMessage implements Serializable {
    private UploadFile uploadFile;
    private String batchNum;
    private String userId;
    private String userName;
    private String companyCode;
    private String caseNumber;

}
