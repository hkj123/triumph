package com.accumulate.report.service.impl;

import com.accumulate.report.service.ReportService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *@System: 车贷金融
 *@Auther: hukaijia
 *@Description:
 *@Modified By:
*/

//@Transactional(rollbackFor = Exception.class)
@Service
public class ReportServiceImpl implements ReportService {
    private final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);


    @Autowired
    private RestTemplate restTemplate;
}
