package com.accumulate.file.repository;

        import com.accumulate.entity.file.UploadFile;
        import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ChenChang on 2017/3/31.
 */
public interface UploadFileRepository extends MongoRepository<UploadFile, String> {
}
