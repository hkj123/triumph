package com.accumulate.test.scheduled;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by  hukaijia.
 * Description:
 * Date: 2017-07-05-11:36
 */
@Component
@EnableScheduling
@Lazy(value = false)
public class SystemBackupScheduled {
    private final Logger log = LoggerFactory.getLogger(SystemBackupScheduled.class);


//    @Scheduled(cron = "*/4 * * * * ?")
    void systemBackup() throws IOException {
        log.info("开始mysql数据库备份" + new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        try {
            System.out.print("测试spring 跑批");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
