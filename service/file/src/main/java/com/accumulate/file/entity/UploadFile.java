package com.accumulate.file.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class UploadFile {
    @Id  // 主键
    private String id;
    private String fileName;
    private String fileExtName;
    private long size;
    private String localUrl;
    private String url;
    private String type;
}
