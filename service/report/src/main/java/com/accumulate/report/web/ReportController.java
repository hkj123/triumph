package com.accumulate.report.web;

import com.accumulate.core.util.Constants;
import com.accumulate.core.web.util.Result;
import com.accumulate.report.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description:
 * @Modified By:
 */

@RestController
@RequestMapping("/report")
@Api(description = "报表管理")
public class ReportController {
    private final Logger log = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private ReportService reportService;

    /**
     * @System: 车贷金融
     * @Auther: xiaqun
     * @Description: 查询清结算日报表
     * @Modified By:
     */
    @GetMapping("/getSettlementReport")
    @ApiOperation(value = "查询清结算日报表", notes = "查询清结算日报表")
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "Correct response", response = SettlementReportModel.class)})
    public Result getSettlementReport() {
        log.debug("request to get settlement report");
        try {
            return new Result(Result.ReturnValue.SUCCESS, "");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new Result(Result.ReturnValue.FAILURE, Constants.ERROR_MESSAGE, e.getMessage());
        }
    }
}
